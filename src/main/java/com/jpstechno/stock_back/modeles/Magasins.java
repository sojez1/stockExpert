package com.jpstechno.stock_back.modeles;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Magasins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String denomination;

    @NotBlank(message = "")
    private String telephone;

    private String adresse;

    @Builder.Default
    @OneToMany(mappedBy = "magasin", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<CompteActeurMagasin> comptes = new ArrayList<>();

    @OneToMany(mappedBy = "magasin")
    @JsonIgnore
    private List<MouvementStock> listeMouvement;
}
