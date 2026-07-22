/**
 * Regles metiers:
 *  1- Un acteur est soit employe soit client
 *  2- un acteur peut etre a la fois employe et client
 */

package com.jpstechno.stock_back.modeles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Acteurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String firstName;

    @NotBlank(message = "")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String telephone;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "acteur", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CompteActeurMagasin> comptes = new ArrayList<>();

}
