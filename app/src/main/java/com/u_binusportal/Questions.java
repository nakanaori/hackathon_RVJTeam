package com.u_binusportal;

import java.util.UUID;

public class Questions {
    private String id;
    private String questions;
    private String askedId;
    private String UmkmId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAskedId() {
        return askedId;
    }

    public void setAskedId(String askedId) {
        this.askedId = askedId;
    }

    public String getUmkmId() {
        return UmkmId;
    }

    public void setUmkmId(String umkmId) {
        this.UmkmId = umkmId;
    }

    public Questions(String questions, String askedId, String UmkmId) {
        this.id = UUID.randomUUID().toString();
        this.questions = questions;
        this.askedId = askedId;
        this.UmkmId = UmkmId;
    }
}
