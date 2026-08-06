package io.github.guiboava.tibiajesterapi.entity.enums;

public enum Party {

    SOLO("Solo"),
    Duo("Duo"),
    PARTY("Party");

    private final String fullParty;

    Party(String fullParty) {
        this.fullParty = fullParty;
    }

    public String getFullParty() {
        return fullParty;
    }
}