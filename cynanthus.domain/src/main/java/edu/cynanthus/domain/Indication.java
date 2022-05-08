package edu.cynanthus.domain;

import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * El tipo Indication.
 */
public class Indication extends Host {

    /**
     * El Instruction.
     */
    @NotNull(groups = Required.class, message = "{NotNull.indication.instruction}")
    @Size(max = 255, groups = {Required.class, ValidInfo.class}, message = "{Size.indication.instruction}")
    private int[] instruction;

    /**
     * Instancia un nuevo Indication.
     *
     * @param mac         el mac
     * @param instruction el instruction
     */
    public Indication(String mac, int[] instruction) {
        super(mac);
        this.instruction = instruction;
    }

    /**
     * Instancia un nuevo Indication.
     */
    public Indication() {
    }

    /**
     * Get instruction int [ ].
     *
     * @return el int [ ]
     */
    public int[] getInstruction() {
        return instruction;
    }

    /**
     * Permite establecer instruction.
     *
     * @param instruction el instruction
     */
    public void setInstruction(int[] instruction) {
        this.instruction = instruction;
    }

    /**
     * Clone indication.
     *
     * @return el indication
     */
    @Override
    public Indication clone() {
        return new Indication(getMac(), instruction.clone());
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
        return Arrays.equals(instruction, that.instruction);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(instruction);
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
            super.toString() + "," +
            "intruction:" + Arrays.toString(instruction) +
            '}';
    }

}
