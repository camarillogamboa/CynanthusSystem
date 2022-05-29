package edu.cynanthus.domain;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * El tipo Role.
 */
public class Role implements Bean {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class, message = "#{NotNull.role.id}")
    @Positive(groups = {IdCandidate.class, ValidInfo.class}, message = "#{Positive.role.id}")
    private Integer id;

    /**
     * El Role type.
     */
    @NotNull(groups = Required.class, message = "#{NotNull.role.roleType}")
    private RoleType roleType;

    /**
     * Instancia un nuevo Role.
     *
     * @param id       el id
     * @param roleType el role type
     */
    public Role(Integer id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    /**
     * Instancia un nuevo Role.
     *
     * @param id el id
     */
    public Role(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo Role.
     *
     * @param roleType el role type
     */
    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    /**
     * Instancia un nuevo Role.
     */
    public Role() {
    }

    /**
     * Permite obtener id.
     *
     * @return el id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permite establecer id.
     *
     * @param id el id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permite obtener role type.
     *
     * @return el role type
     */
    public RoleType getRoleType() {
        return roleType;
    }

    /**
     * Permite establecer role type.
     *
     * @param roleType el role type
     */
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    /**
     * Clone role.
     *
     * @return el role
     */
    @Override
    public Role clone() {
        return new Role(id, roleType);
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && roleType == role.roleType;
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, roleType);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "id:" + id +
            ",roleType:" + roleType +
            '}';
    }

}
