package com.beans;

public class Chinese implements Person {
    private Language language;

    public void speak() {
        System.out.println(language.kind());
    }

    public Chinese(Language language) {
        this.language = language;
    }
}
