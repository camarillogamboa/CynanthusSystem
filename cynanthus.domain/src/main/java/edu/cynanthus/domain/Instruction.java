package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * El tipo Instruction.
 */
public class Instruction implements Bean {

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
     * El Name.
     */
    @NotEmpty(groups = {
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
        regexp = Patterns.NAME,
        groups = {
            Required.class,
            NaturalIdCandidate.class,
            ValidInfo.class
        }
    )
    @JProperty
    private String name;

    /**
     * El Value.
     */
    @NotNull(groups = Required.class)
    @Size(
        max = 255,
        groups = {
            Required.class,
            ValidInfo.class
        }
    )
    @JProperty
    private int[] value;

    /**
     * Instancia un nuevo Instruction.
     *
     * @param id    el id
     * @param name  el name
     * @param value el value
     */
    public Instruction(Integer id, String name, int[] value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    /**
     * Instancia un nuevo Instruction.
     *
     * @param id el id
     */
    public Instruction(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo Instruction.
     *
     * @param name  el name
     * @param value el value
     */
    public Instruction(String name, int[] value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Instancia un nuevo Instruction.
     *
     * @param name el name
     */
    public Instruction(String name) {
        this.name = name;
    }

    /**
     * Instancia un nuevo Instruction.
     */
    public Instruction() {
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
     * Permite obtener name.
     *
     * @return el name
     */
    public String getName() {
        return name;
    }

    /**
     * Permite establecer name.
     *
     * @param name el name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get value int [ ].
     *
     * @return el int [ ]
     */
    public int[] getValue() {
        return value;
    }

    /**
     * Permite establecer value.
     *
     * @param value el value
     */
    public void setValue(int[] value) {
        this.value = value;
    }

    /**
     * Clone instruction.
     *
     * @return el instruction
     */
    @Override
    public Instruction clone() {
        return new Instruction(id, name, value);
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
        Instruction that = (Instruction) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Arrays.equals(value, that.value);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(id, name);
        result = 31 * result + Arrays.hashCode(value);
        return result;
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
            ",name:'" + name + '\'' +
            ",value:" + Arrays.toString(value) + '}';
    }

}
