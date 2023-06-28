package br.com.wallace.app.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String GetRole(){
        return role;
    }
}
