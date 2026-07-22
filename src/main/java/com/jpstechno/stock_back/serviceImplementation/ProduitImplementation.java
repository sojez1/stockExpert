package com.jpstechno.stock_back.serviceImplementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jpstechno.stock_back.config.ProduitPhotoStorage;
import com.jpstechno.stock_back.dto.dtoRequests.ProduitRequestDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.modeles.Produits;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.repositories.ProduitRepositories;
import com.jpstechno.stock_back.services.ProduitServices;

@Service
public class ProduitImplementation implements ProduitServices {

    private final ProduitRepositories prodRepo;
    private final CategorieRepositories caterepo;

    final String photoStorage = "fichiers" + File.separator + "photos" + File.separator + "produits";

    public ProduitImplementation(ProduitRepositories prodRepo, CategorieRepositories caterepo,
            ProduitPhotoStorage produitPhotoStorage) {
        this.prodRepo = prodRepo;
        this.caterepo = caterepo;
    }

    @Override
    public Produits enregistrerUnProduit(ProduitRequestDto produitData) {
        // 1- rechercher la categorie du produit
        Categories categorie = caterepo.findById(produitData.categorieId())
                .orElseThrow(() -> new RuntimeException("Categorie not found"));

        Produits produit = Produits.builder()
                .reference(produitData.reference())
                .prixUnitaire(produitData.prixUnitaire())
                .upc(produitData.upc())
                .unite(produitData.unite())
                .taxable(produitData.taxable())
                .designation(produitData.designation())
                .categorie(categorie)
                .build();

        return prodRepo.save(produit);
    }

    @Override
    public String ajouterUnePhoto(long productId, MultipartFile photo) {

        if (photo == null || photo.isEmpty()) {
            throw new RuntimeException("le fichier est vide");
        }

        Produits produit = this.findProductById(productId);

        // Dossier definit dans le fichier application.yml
        Path dossier = Paths.get(photoStorage);

        try {
            Files.createDirectories(dossier); // creer le dossier s'il n'existe pas

            String extension = getFileExtension(photo.getOriginalFilename());

            // attribution du nom a donner au photo sur le serveur
            String nomPhoto = productId + "_" + getNextPhotoNameSuffixe(productId, produit.getPhotoUrls()) + extension;

            Path destination = dossier.resolve(nomPhoto);
            Files.copy(photo.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            produit.getPhotoUrls().add(nomPhoto);
            prodRepo.save(produit);
            return "image successfully saved";
        } catch (IOException exc) {
            throw new RuntimeException("Echec de la sauvegarde de la photo");
        }
    }

    @Override
    public List<Produits> listeDesproduits() {
        Sort trieParNom = Sort.by(Sort.Direction.ASC, "designation");
        return prodRepo.findAll(trieParNom);
    }

    /**
     * methode permettant de retourner le suffixe le plus grand associe au nom de
     * photos d'un produit
     * exemple: produit1_1
     * produit1_2
     * produit_1_3
     * va retourner 4 qui serait le suffixe du prochain nom de fichier pour la photo
     * a enregistrer
     * 
     * @param productId identifiant du produit pour lequel on aimerait ajouter une
     *                  photo
     * @param photoUrls liste des urls des photos deja enregistrer
     * @return suffixe a donner aau prochain nom de photo;
     */
    private int getNextPhotoNameSuffixe(long productId, List<String> photoUrls) {
        int nextSuffixe = 0;

        for (String photo : photoUrls) { // rechercher le suffixe
            String prefix = productId + "_";
            if (photo.startsWith(prefix)) {
                int indexDebut = photo.indexOf(prefix.length());
                int indexFin = photo.lastIndexOf(".");
                String suffixe = photo.substring(indexDebut, indexFin);
                int numero = Integer.parseInt(suffixe);
                nextSuffixe = Math.max(nextSuffixe, numero);
            }
        }
        return nextSuffixe + 1;
    }

    /**
     * Permet de rechercher un produit connaissant son identifiant
     * 
     * @param id identifiant du produit
     * @return produit dont identifiant passe en parametre
     * @exception RuntimeException si le produit n'existe pas
     */
    private Produits findProductById(long id) {
        return prodRepo.findById(id).orElseThrow(() -> new RuntimeException("produit non trouve"));
    }

    /**
     * Permet de recuperer l'extension du fichier
     * 
     * @param urlDuFichier url du fichier
     * @return extension du fichier
     */
    private String getFileExtension(String urlDuFichier) {
        String extension = "";
        if (urlDuFichier != null && urlDuFichier.contains(".")) {
            extension = urlDuFichier.substring(urlDuFichier.lastIndexOf("."));
        }
        return extension;

    }

}
