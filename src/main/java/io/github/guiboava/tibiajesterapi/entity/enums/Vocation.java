package io.github.guiboava.tibiajesterapi.entity.enums;

public enum Vocation {

    EM("Exalted Monk"),
    EK("Elite Knight"),
    ED("Elder Druid"),
    MS("Master Sorcerer"),
    RP("Royal Paladin");

    private final String fullVocation;

    Vocation(String fullVocation) {
        this.fullVocation = fullVocation;
    }

    public String getFullVocation() {
        return fullVocation;
    }
}