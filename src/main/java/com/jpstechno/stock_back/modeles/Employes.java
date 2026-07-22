/**
 * Cette classe ne represente pas un acteur mais le role de l'acteur.
 * Genre role Employes, role Clients
 */

package com.jpstechno.stock_back.modeles;

import com.jpstechno.stock_back.enumerations.PosteEnum;
import com.jpstechno.stock_back.enumerations.RoleEnum;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employes extends Roles {

    private Long acteurId;

    private String matricule;

    private PosteEnum poste;

    @Builder.Default
    private Boolean actif = true;

    @Override
    public RoleEnum getTypeActeur() {
        return RoleEnum.EMPLOYE;
    }

}
