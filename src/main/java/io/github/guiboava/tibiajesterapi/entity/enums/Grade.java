package io.github.guiboava.tibiajesterapi.entity.enums;

public enum Grade {

    A("1"),
    B("2"),
    C("3"),
    D("4");

    private final String fullGenre;

    Grade(String fullGenre) {
        this.fullGenre = fullGenre;
    }

    public String getFullGenre() {
        return fullGenre;
    }
}