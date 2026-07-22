/**
 * Cette classe represente le lien entre un acteur et un magasin
 * Sert a la connexion d'un acteur sur son compte du magasin specifie (authentification)
 * 
 */
package com.jpstechno.stock_back.modeles;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acteur_magasin", uniqueConstraints = {
        @UniqueConstraint(name = "uk_acteur_magasin", columnNames = { "acteur_id", "magasin_id" })

})
public class CompteActeurMagasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "acteur_id", nullable = false)
    private Acteurs acteur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "magasin_id", nullable = false)
    private Magasins magasin;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String motDePasse;

    @Builder.Default
    private Boolean actif = true;

    @Builder.Default
    @OneToMany(mappedBy = "compte", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Roles> listeRole = new ArrayList<>();

    @OneToMany(mappedBy = "responsableMouvement")
    @JsonIgnore
    @Builder.Default
    private List<MouvementStock> listeMouvementStock = new ArrayList<>(); // liste mouvements effectues sur ce compte

}
