package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Instruction set.
 */
public class InstructionSet implements Bean {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class, message = "{NotNull.instructionSet.id}")
    @Positive(groups = {IdCandidate.class, ValidInfo.class}, message = "{Positive.instructionSet.id}")
    @JProperty
    private Integer id;

    /**
     * El Name.
     */
    @NotEmpty(groups = {Required.class, NaturalIdCandidate.class}, message = "{NoEmpty.instructionSet.name}")
    @Size(
        max = 25,
        groups = {Required.class, ValidInfo.class, NaturalIdCandidate.class},
        message = "{Size.instructionSet.name}"
    )
    @Pattern(
        regexp = Patterns.NAME,
        groups = {Required.class, ValidInfo.class, NaturalIdCandidate.class},
        message = "{Pattern.instructionSet.name}"
    )
    @JProperty
    private String name;

    /**
     * El Info.
     */
    @NotEmpty(message = "{NotEmpty.instructionSet.info}")
    @Size(max = 300, groups = ValidInfo.class, message = "{Size.instructionSet.info}")
    @JProperty
    private String info;

    /**
     * El Instructions.
     */
    @JProperty
    private List<Instruction> instructions;

    /**
     * Instancia un nuevo Instruction set.
     *
     * @param id           el id
     * @param name         el name
     * @param info         el info
     * @param instructions el instructions
     */
    public InstructionSet(Integer id, String name, String info, List<Instruction> instructions) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.instructions = instructions;
    }

    /**
     * Instancia un nuevo Instruction set.
     *
     * @param name         el name
     * @param info         el info
     * @param instructions el instructions
     */
    public InstructionSet(String name, String info, List<Instruction> instructions) {
        this.name = name;
        this.info = info;
        this.instructions = instructions;
    }

    /**
     * Instancia un nuevo Instruction set.
     *
     * @param id el id
     */
    public InstructionSet(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo Instruction set.
     *
     * @param name el name
     */
    public InstructionSet(String name) {
        this.name = name;
    }

    /**
     * Instancia un nuevo Instruction set.
     */
    public InstructionSet() {
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
     * Permite obtener info.
     *
     * @return el info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Permite establecer info.
     *
     * @param info el info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Permite obtener instructions.
     *
     * @return el instructions
     */
    public List<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Permite establecer instructions.
     *
     * @param instructions el instructions
     */
    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    /**
     * Clone instruction set.
     *
     * @return el instruction set
     */
    @Override
    public InstructionSet clone() {
        return new InstructionSet(id, name, info, instructions);
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
        InstructionSet that = (InstructionSet) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(info, that.info) &&
            Objects.equals(instructions, that.instructions);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, instructions);
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
            ",info:'" + info + '\'' +
            ",instructions:" + instructions +
            '}';
    }

}
