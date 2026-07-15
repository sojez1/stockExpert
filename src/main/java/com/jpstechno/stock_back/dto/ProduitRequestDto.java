package com.jpstechno.stock_back.dto;

import java.math.BigDecimal;

import com.jpstechno.stock_back.enumerations.Unites;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProduitRequestDto(

        @NotBlank(message = "La reference interne est obligatoire") String reference,

        @Pattern(regexp = "\\d{8}|\\d{12}|\\d{13}|\\d{14}", message = "Le code UPC doit contenir 8, 12, 13 ou 14 chiffres") String upc,

        @NotBlank(message = "Vous devez indiquer la designation du produit") String designation,

        @NotNull(message = "La categorie est obligatoire") Long categorieId,

        @NotNull(message = "vous devez indiquer l'unite de stockage") Unites unite,

        @NotNull(message = "Le prix est obligatoire") @Positive(message = "Le prix doit etre superieur a 0") BigDecimal prixUnitaire,

        Boolean taxable

) {

}
