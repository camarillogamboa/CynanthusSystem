package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.server.repository.InstructionRepository;
import edu.cynanthus.auri.server.repository.InstructionSetRepository;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * El tipo Transactional instruction set service.
 */
@Service
@Qualifier("transactionalInstructionSetService")
public class TransactionalInstructionSetService
    extends TransactionalBeanService<InstructionSet> implements InstructionSetService {

    /**
     * El Instruction set service.
     */
    private final InstructionSetService instructionSetService;

    /**
     * Instancia un nuevo Transactional instruction set service.
     *
     * @param instructionSetService el instruction set service
     */
    private TransactionalInstructionSetService(InstructionSetService instructionSetService) {
        super(instructionSetService);
        this.instructionSetService = instructionSetService;
    }

    /**
     * Instancia un nuevo Transactional instruction set service.
     *
     * @param instructionSetRepository el instruction set repository
     * @param instructionRepository    el instruction repository
     */
    @Autowired
    public TransactionalInstructionSetService(
        InstructionSetRepository instructionSetRepository,
        InstructionRepository instructionRepository
    ) {
        this(new BasicInstructionSetService(instructionSetRepository, instructionRepository));
    }

    /**
     * Read instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(Integer id) {
        return instructionSetService.readInstruction(id);
    }

    /**
     * Read instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(Integer idSet, String name) {
        return instructionSetService.readInstruction(idSet, name);
    }

    /**
     * Read instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(String setName, String name) {
        return instructionSetService.readInstruction(setName, name);
    }

    /**
     * Delete instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    @Transactional
    public Instruction deleteInstruction(Integer id) {
        return instructionSetService.deleteInstruction(id);
    }

    /**
     * Delete instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    @Override
    @Transactional
    public Instruction deleteInstruction(Integer idSet, String name) {
        return instructionSetService.deleteInstruction(idSet, name);
    }

    /**
     * Delete instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    @Override
    @Transactional
    public Instruction deleteInstruction(String setName, String name) {
        return instructionSetService.deleteInstruction(setName, name);
    }

}
