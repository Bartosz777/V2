package com.crud.restapp.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private String toCc;

    public Mail(final String mailTo, final String subject, final String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }
}