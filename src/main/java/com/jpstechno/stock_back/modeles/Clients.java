package com.jpstechno.stock_back.modeles;

import java.time.LocalDate;

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
public class Clients extends Roles {

    private Long acteurId;

    private String codeClient;

    private LocalDate dateInscription;

    @Builder.Default
    private Boolean actif = true;

    @Override
    public RoleEnum getTypeActeur() {
        return RoleEnum.CLIENT;
    }

}
