package io.github.guiboava.tibiajesterapi.entity.enums;

public enum AccountStatus {

    PA("Premium Account"),
    FA("Free Account");

    private final String fullAccountStatus;

    AccountStatus(String fullAccountStatus) {
        this.fullAccountStatus = fullAccountStatus;
    }

    public String getFullAccountStatus() {
        return fullAccountStatus;
    }
}