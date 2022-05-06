package edu.cynanthus.domain;

import edu.cynanthus.bean.JProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AuthenticatedUser extends User{

    @NotNull
    @NotEmpty
    @JProperty
    private String token;

    public AuthenticatedUser(String username, String password) {
        super(username, password);
    }

    public AuthenticatedUser(String username, String password, String token) {
        super(username, password);
        this.token = token;
    }

    public AuthenticatedUser(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthenticatedUser that = (AuthenticatedUser) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), token);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "token:'" + token + '\'' +
            '}';
    }

}
