package com.jpstechno.stock_back.utilitaires;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translators {

    private static MessageSource messageSource;

    public Translators(MessageSource messageSource) {
        Translators.messageSource = messageSource;
    }

    public static String toLocale(String messageKey) {
        if (messageSource == null)
            throw new IllegalArgumentException("messageSource non initialise");
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }
}
