package ru.evgenii.SecurityWithJWT.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN(Arrays.asList(Permission.READ_ALL_ITEM, Permission.SAVE_ITEM)),
    USER(Arrays.asList(Permission.READ_ALL_ITEM));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
