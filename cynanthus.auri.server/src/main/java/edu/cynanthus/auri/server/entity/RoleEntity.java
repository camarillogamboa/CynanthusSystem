package edu.cynanthus.auri.server.entity;

import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.RoleType;

import javax.persistence.*;
import java.util.Objects;

/**
 * El tipo Role entity.
 */
@Entity(name = "Role")
@Table(schema = "cynanthus", name = "role")
public class RoleEntity extends Role {

    /**
     * El Id user.
     */
    private Integer idUser;

    /**
     * Instancia un nuevo Role entity.
     *
     * @param idRol    el id rol
     * @param idUser   el id user
     * @param roleType el role type
     */
    public RoleEntity(Integer idRol, Integer idUser, RoleType roleType) {
        super(idRol, roleType);
        this.idUser = idUser;
    }

    /**
     * Instancia un nuevo Role entity.
     */
    public RoleEntity() {
    }

    /**
     * Permite obtener id.
     *
     * @return el id
     */
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    /**
     * Permite obtener id user.
     *
     * @return el id user
     */
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Permite establecer id user.
     *
     * @param idUser el id user
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * Permite obtener role type.
     *
     * @return el role type
     */
    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public RoleType getRoleType() {
        return super.getRoleType();
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
        if (!super.equals(o)) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(idUser, that.idUser);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idUser);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "idUser:" + idUser +
            '}';
    }

}
