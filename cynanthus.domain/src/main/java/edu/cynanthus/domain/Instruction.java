package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Instruction.
 */
public class Instruction implements Bean {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class, message = "#{NotNull.instrucction.id}")
    @Min(value = 0, groups = {IdCandidate.class, ValidInfo.class}, message = "#{Min.instruction.id}")
    @JProperty
    private Integer id;

    /**
     * El Name.
     */
    @NotEmpty(groups = {Required.class, NaturalIdCandidate.class}, message = "#{NotEmpty.instruction.name}")
    @Size(
        max = 45,
        groups = {Required.class, NaturalIdCandidate.class, ValidInfo.class},
        message = "#{Size.instruction.name}"
    )
    @Pattern(
        regexp = Patterns.NAME,
        groups = {Required.class, NaturalIdCandidate.class, ValidInfo.class},
        message = "#{Pattern.instruction.name}"
    )
    @JProperty
    private String name;

    /**
     * El Vector.
     */
    @NotEmpty(groups = Required.class, message = "#{NotEmpty.instruction.vector}")
    @Size(max = 255, groups = {Required.class, ValidInfo.class}, message = "#{Size.instruction.vector}")
    @Pattern(
        regexp = Patterns.CODE_VECTOR,
        groups = {Required.class, ValidInfo.class},
        message = "#{Pattern.instruction.vector}"
    )
    @JProperty
    private String vector;

    /**
     * Instancia un nuevo Instruction.
     *
     * @param id     el id
     * @param name   el name
     * @param vector el vector
     */
    public Instruction(Integer id, String name, String vector) {
        this.id = id;
        this.name = name;
        this.vector = vector;
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
     * @param name   el name
     * @param vector el vector
     */
    public Instruction(String name, String vector) {
        this.name = name;
        this.vector = vector;
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
     * Clone instruction.
     *
     * @return el instruction
     */
    @Override
    public Instruction clone() {
        return new Instruction(id, name, vector);
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(vector, that.vector);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, vector);
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
            ",vector:'" + vector + '\'' +
            '}';
    }

}
