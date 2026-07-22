
package com.jpstechno.stock_back.modeles;

import com.jpstechno.stock_back.enumerations.RoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
public abstract class Roles {

    @Id
    private Long getActeurId;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private CompteActeurMagasin compte;

    public abstract RoleEnum getTypeActeur();

}
