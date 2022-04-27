package edu.cynanthus.auri.api;

import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

public interface InstructionSetService extends BeanService<InstructionSet> {

    Instruction readInstruction(Integer id);

    Instruction readInstruction(Integer idSet, String name);

    Instruction readInstruction(String setName, String name);

    Instruction deleteInstruction(Integer id);

    Instruction deleteInstruction(Integer idSet, String name);

    Instruction deleteInstruction(String setName, String name);

}
