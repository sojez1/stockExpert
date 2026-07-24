package com.jpstechno.stock_back.evenements.appEvents;

import org.springframework.context.ApplicationEvent;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;

public class CompteActeurMagasinCreationEvent extends ApplicationEvent {

    public CompteActeurMagasinCreationEvent(CompteActeurMagasin source) {
        super(source);
    }

}
