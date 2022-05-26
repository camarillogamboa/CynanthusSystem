package edu.cynanthus.domain;

/**
 * La enumeración Role type.
 */
public enum RoleType {

    /**
     * Role agent role type.
     */
    ROLE_AGENT(RoleLevel.TOP_LEVEL),
    /**
     * Role admin role type.
     */
    ROLE_ADMIN(RoleLevel.TOP_LEVEL),
    /**
     * Role user role type.
     */
    ROLE_USER(RoleLevel.LOW_LEVEL);

    private final RoleLevel roleLevel;

    RoleType(RoleLevel roleLevel) {
        this.roleLevel = roleLevel;
    }

    public RoleLevel getRoleLevel() {
        return roleLevel;
    }

    public enum RoleLevel {
        LOW_LEVEL, MID_LEVEL, TOP_LEVEL
    }

}
