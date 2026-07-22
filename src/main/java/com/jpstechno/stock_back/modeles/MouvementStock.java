package com.jpstechno.stock_back.modeles;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMouvement;

    private LocalDateTime dateMouvement;

    @ManyToOne
    private Magasins magasin;

    private String observations;

    @ManyToOne
    private CompteActeurMagasin responsableMouvement; // personne responsable de ce mouvement de stock ou
                                                      // compte a partir duquel le mouvement est effectue

}
