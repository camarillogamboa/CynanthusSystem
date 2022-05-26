package edu.cynanthus.auri.api;

import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.util.List;

/**
 * La interface Instruction set service.
 */
public interface InstructionSetService extends BeanService<InstructionSet> {

    /**
     * La constante INSTRUCTION_SET_SERVICE_PATH.
     */
    String INSTRUCTION_SET_SERVICE_PATH = AURI_PATH + "/set";

    /**
     * Read only set instruction set.
     *
     * @param instructionSet el instruction set
     * @return el instruction set
     */
    InstructionSet readOnlySet(InstructionSet instructionSet);

    /**
     * Read only sets instruction set.
     *
     * @return el instruction set
     */
    List<? extends InstructionSet> readOnlySets();


    /**
     * Read instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    Instruction readInstruction(Integer id);

    /**
     * Read instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    Instruction readInstruction(Integer idSet, String name);

    /**
     * Read instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    Instruction readInstruction(String setName, String name);

    /**
     * Delete instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    Instruction deleteInstruction(Integer id);

    /**
     * Delete instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    Instruction deleteInstruction(Integer idSet, String name);

    /**
     * Delete instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    Instruction deleteInstruction(String setName, String name);

}
