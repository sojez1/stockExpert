package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TranslatorServices {

    private final MessageSource messageSource;

    public TranslatorServices(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String messageKey) {
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

}
