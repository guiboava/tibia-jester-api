package io.github.guiboava.tibiajesterapi.entity.enums;

public enum UserType {

    A("Administrador", "ADMIN"),
    U("Usuário", "USER"),
    V("Vip", "VIP");


    private final String fullUserType;
    private final String fullRoleUserType;

    UserType(String fullUserType, String fullRoleUserType) {
        this.fullUserType = fullUserType;
        this.fullRoleUserType = fullRoleUserType;
    }


    public boolean isAdmin() {
        return this.getRoleName().equals("ADMIN");
    }

    public String getFullUserType() {
        return fullUserType;
    }

    public String getRoleName() {
        return fullRoleUserType;
    }

}
