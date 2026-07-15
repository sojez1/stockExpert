package com.jpstechno.stock_back.serviceImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jpstechno.stock_back.config.ProduitPhotoStorage;
import com.jpstechno.stock_back.dto.ProduitRequestDto;
import com.jpstechno.stock_back.modeles.Categories;
import com.jpstechno.stock_back.modeles.Produits;
import com.jpstechno.stock_back.repositories.CategorieRepositories;
import com.jpstechno.stock_back.repositories.ProduitRepositories;
import com.jpstechno.stock_back.services.ProduitServices;

@Service
public class ProduitImplementation implements ProduitServices {

    private final ProduitRepositories prodRepo;
    private final CategorieRepositories caterepo;
    private final ProduitPhotoStorage produitPhotoStorage;

    public ProduitImplementation(ProduitRepositories prodRepo, CategorieRepositories caterepo,
            ProduitPhotoStorage produitPhotoStorage) {
        this.prodRepo = prodRepo;
        this.caterepo = caterepo;
        this.produitPhotoStorage = produitPhotoStorage;
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

        Produits produit = prodRepo.findById(productId).orElseThrow(() -> new RuntimeException("produit introuvable"));

        // Dossier definit dans le fichier application.yml
        Path dossier = Paths.get(produitPhotoStorage.getProduitPhotoStorage());

        if (!Files.exists(dossier)) {// creer le dossier s'il n'existe pas
            try {
                Files.createDirectories(dossier);
            } catch (IOException e) {
                throw new RuntimeException(
                        "Impossible de creer le dossier de sauvagarde du fichier: " + e.getMessage());
            }
        }

        try {
            String nomPhoto = productId + "_" + getNextPhotoNameSuffixe(productId, produit.getPhotoUrls());
            Path destination = dossier.resolve(nomPhoto);
            Files.copy(photo.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            produit.getPhotoUrls().add(nomPhoto);
            prodRepo.save(produit);
        } catch (IOException ioExc) {
            throw new RuntimeException("Echec lors de la sauvegarde de la photo");

        }

        return "image successfully saved";

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
        int nextSuffixe = 1;

        for (String photo : photoUrls) { // rechercher le suffixe
            String prefix = productId + "_";
            if (photo.startsWith(prefix)) {
                int numero = Integer.parseInt(photo.substring(prefix.length()));
                nextSuffixe = Math.max(nextSuffixe, numero);
            }
        }
        return nextSuffixe;
    }

}
