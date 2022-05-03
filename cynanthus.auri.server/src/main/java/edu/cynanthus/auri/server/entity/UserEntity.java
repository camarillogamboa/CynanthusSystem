package edu.cynanthus.auri.server.entity;

import edu.cynanthus.domain.Role;
import edu.cynanthus.domain.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(schema = "cynanthus", name = "user")
public class UserEntity extends User {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @NaturalId(mutable = true)
    @Column(name = "username", unique = true, nullable = false, length = 45)
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Column(name = "password", nullable = false, length = 128)
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Transient
    @Override
    public List<Role> getRoles() {
        return super.getRoles();
    }

}
