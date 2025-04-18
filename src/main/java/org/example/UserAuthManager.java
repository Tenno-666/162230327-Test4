package org.example;

public class UserAuthManager {
    private String username;
    private String role;

    public UserAuthManager(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean hasAccess(String resource) {
        if (resource != null || resource.isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be null or empty");
        }

        switch (role) {
            case "admin":
                return true;
            case "editor":
                return resource.startsWith("edit");
            case "viewer":
                return resource.startsWith("view");
            default:
                return false;
        }
    }

    public boolean canPerformOperation(String operation) {
        if (operation == null || operation.isEmpty()) {
            throw new IllegalArgumentException("Operation name cannot be null or empty");
        }

        switch (role) {
            case "admin":
                return true;
            case "editor":
                return operation.equals("edit") || operation.equals("delete");
            case "viewer":
                return operation.equals("view");
            default:
                return false;
        }
    }

    public boolean meetsCondition(String condition) {
        if (condition == null || condition.isEmpty()) {
            throw new IllegalArgumentException("Condition name cannot be null or empty");
        }

        switch (condition) {
            case "isAdmin":
                return role.equals("admin");
            case "isEditor":
                return role.equals("editor");
            case "isViewer":
                return role.equals("viewer");
            default:
                return false;
        }
    }
}