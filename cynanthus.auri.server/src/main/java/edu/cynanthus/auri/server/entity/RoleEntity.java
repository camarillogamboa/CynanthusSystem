package edu.cynanthus.auri.server.entity;

import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.RoleType;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Role")
@Table(schema = "cynanthus", name = "role")
public class RoleEntity extends Role {

    private Integer idUser;

    public RoleEntity(Integer idRol, Integer idUser, RoleType roleType) {
        super(idRol, roleType);
        this.idUser = idUser;
    }

    public RoleEntity() {
    }

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public RoleType getRoleType() {
        return super.getRoleType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idUser);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "idUser:" + idUser +
            '}';
    }

}
