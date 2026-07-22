package com.jpstechno.stock_back.modeles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.stock_back.enumerations.Unites;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_upc", columnList = "upc"),
        @Index(name = "idx_designation", columnList = "designation")
})
public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NaturalId(mutable = true)
    private String reference; // reference interne (surtout si pas de upc pour le produit)

    @Column(unique = true, length = 14)
    @NaturalId(mutable = true)
    @Pattern(regexp = "\\d{8}|\\d{12}|\\d{13}|\\d{14}", message = "Le code UPC doit contenir 8, 12, 13 ou 14 chiffres")
    private String upc; // code universel du produit (issue du code barre) ou GTIN

    @NotBlank
    private String designation;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categories categorie;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vous devez indiquer l'unite de stockage")
    private Unites unite;

    @Positive(message = "Le prix unitaire doit etre superieur a 0")
    @NotNull(message = "Le prix unitaire est obligatoire")
    private BigDecimal prixUnitaire;

    @Builder.Default
    private boolean actif = true;

    @Builder.Default
    private boolean taxable = true;

    @ManyToMany
    @Builder.Default
    private List<Produits> produitsEquivalent = new ArrayList<Produits>();

    @ElementCollection
    @Builder.Default
    @Size(max = 8, message = "nombre maximal de photo est 8")
    private List<String> photoUrls = new ArrayList<String>(); // lien vers les photos
}
