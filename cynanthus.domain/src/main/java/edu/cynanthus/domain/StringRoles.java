package edu.cynanthus.domain;

import java.util.LinkedList;
import java.util.List;

public interface StringRoles {

    String[] ALL_ROLES = loadAllRoles();
    String[] LOW_LEVEL_ROLES = loadRolesByLevel(RoleType.RoleLevel.LOW_LEVEL);
    String[] MID_LEVEL_ROLES = loadRolesByLevel(RoleType.RoleLevel.MID_LEVEL);
    String[] TOP_LEVEL_ROLES = loadRolesByLevel(RoleType.RoleLevel.TOP_LEVEL);

    private static String[] loadAllRoles(){
        RoleType[] roleTypes = RoleType.values();
        String[] roles = new String[roleTypes.length];

        for (int i = 0; i < roles.length; i++) roles[i] = roleTypes[i].name();
        return roles;
    }

    private static String[] loadRolesByLevel(RoleType.RoleLevel roleLevel){
        List<String> roles = new LinkedList<>();

        for(RoleType roleType: RoleType.values())
            if(roleType.getRoleLevel().equals(roleLevel))
                roles.add(roleType.name());

        return roles.toArray(String[]::new);
    }

}
