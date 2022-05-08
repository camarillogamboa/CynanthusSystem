package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * El tipo Host.
 */
public class Host implements Bean {

    /**
     * El Mac.
     */
    @NotEmpty(groups = {Required.class, NaturalIdCandidate.class}, message = "{NotEmpty.host.mac}")
    @Pattern(
        regexp = Patterns.MAC,
        groups = {Required.class, NaturalIdCandidate.class, ValidInfo.class},
        message = "{Pattern.host.mac}"
    )
    @JProperty
    private String mac;

    /**
     * Instancia un nuevo Host.
     *
     * @param mac el mac
     */
    public Host(String mac) {
        this.mac = mac;
    }

    /**
     * Instancia un nuevo Host.
     */
    public Host() {
    }

    /**
     * Permite obtener mac.
     *
     * @return el mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * Permite establecer mac.
     *
     * @param mac el mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * Clone host.
     *
     * @return el host
     */
    @Override
    public Host clone() {
        return new Host(mac);
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
        Host host = (Host) o;
        return Objects.equals(mac, host.mac);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(mac);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "mac:'" + mac + '\'' +
            '}';
    }

}
