package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * El tipo User.
 */
public class User implements Bean {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class)
    @Positive(groups = {
        IdCandidate.class,
        ValidInfo.class
    })
    @JProperty
    private Integer id;

    /**
     * El Username.
     */
    @NotNull(groups = {
        Required.class,
        NaturalIdCandidate.class
    })
    @Size(
        max = 45,
        groups = {
            Required.class,
            NaturalIdCandidate.class,
            ValidInfo.class
        }
    )
    @Pattern(
        regexp = Patterns.IDENTIFIER,
        groups = {
            Required.class,
            NaturalIdCandidate.class,
            ValidInfo.class
        }
    )
    @JProperty
    private String username;

    /**
     * El Password.
     */
    @NotNull(groups = Required.class)
    @Size(
        max = 128,
        groups = {
            Required.class,
            ValidInfo.class
        }
    )
    @JProperty
    private String password;

    /**
     * El Roles.
     */
    @NotNull
    @Size(max = 3, groups = ValidInfo.class)
    @JProperty
    private List<@NotNull Role> roles;

    /**
     * Instancia un nuevo User.
     *
     * @param id       el id
     * @param username el username
     * @param password el password
     * @param roles    el roles
     */
    public User(Integer id, String username, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Instancia un nuevo User.
     *
     * @param id       el id
     * @param username el username
     * @param password el password
     */
    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Instancia un nuevo User.
     *
     * @param username el username
     * @param password el password
     * @param roles    el roles
     */
    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Instancia un nuevo User.
     *
     * @param username el username
     * @param password el password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Instancia un nuevo User.
     *
     * @param id el id
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo User.
     *
     * @param username el username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Instancia un nuevo User.
     */
    public User() {
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
     * Permite obtener username.
     *
     * @return el username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Permite establecer username.
     *
     * @param username el username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Permite obtener password.
     *
     * @return el password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Permite establecer password.
     *
     * @param password el password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Permite obtener roles.
     *
     * @return el roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Permite establecer roles.
     *
     * @param roles el roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Clone user.
     *
     * @return el user
     */
    @Override
    public User clone() {
        return new User(
            id,
            username,
            password,
            roles
        );
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
            Objects.equals(username, user.username) &&
            Objects.equals(password, user.password) &&
            Objects.equals(roles, user.roles);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles);
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
            ",username:'" + username + '\'' +
            ",password:'" + password + '\'' +
            ",roles:" + roles +
            '}';
    }

}
