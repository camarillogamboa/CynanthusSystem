package edu.cynanthus.domain;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * El tipo Indication.
 */
public class Indication extends Host {

    /**
     * El Vector.
     */
    @NotNull(groups = Required.class, message = "#{NotEmpty.indication.vector}")
    @Size(max = 255, groups = {Required.class, ValidInfo.class}, message = "#{Size.indication.vector}")
    @Pattern(
        regexp = Patterns.CODE_VECTOR,
        groups = {Required.class, ValidInfo.class},
        message = "#{Pattern.indication.vector}"
    )
    @JProperty
    private String vector;

    /**
     * Instancia un nuevo Indication.
     *
     * @param mac    el mac
     * @param vector el vector
     */
    public Indication(String mac, String vector) {
        super(mac);
        this.vector = vector;
    }

    /**
     * Instancia un nuevo Indication.
     */
    public Indication() {
    }

    /**
     * Permite obtener vector.
     *
     * @return el vector
     */
    public String getVector() {
        return vector;
    }

    /**
     * Permite establecer vector.
     *
     * @param vector el vector
     */
    public void setVector(String vector) {
        this.vector = vector;
    }

    /**
     * Clone indication.
     *
     * @return el indication
     */
    @Override
    public Indication clone() {
        return new Indication(getMac(), vector);
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
        Indication that = (Indication) o;
        return Objects.equals(vector, that.vector);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vector);
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
            "vector:'" + vector + '\'' +
            '}';
    }

}
