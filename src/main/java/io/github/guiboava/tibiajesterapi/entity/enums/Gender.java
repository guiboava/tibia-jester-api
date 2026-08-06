package io.github.guiboava.tibiajesterapi.entity.enums;

public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String fullGender;

    Gender(String fullGender) {
        this.fullGender = fullGender;
    }

    public String getFullGender() {
        return fullGender;
    }
}