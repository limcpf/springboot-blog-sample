package kr.limc.limcblog.Entity.Comm.Enum;

public enum UserRole {
    USER("USER"), ADMIN("ADMIN");

    UserRole(String role) { this.role = role; }
    private final String role;
    public String getRole() {
        return role;
    }
}
