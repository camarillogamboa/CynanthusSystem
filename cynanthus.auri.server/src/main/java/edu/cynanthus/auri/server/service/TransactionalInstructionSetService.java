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

@Service
public class TransactionalInstructionSetService
    extends TransactionalBeanService<InstructionSet> implements InstructionSetService {

    private final InstructionSetService instructionSetService;

    private TransactionalInstructionSetService(InstructionSetService instructionSetService) {
        super(instructionSetService);
        this.instructionSetService = instructionSetService;
    }

    @Autowired
    public TransactionalInstructionSetService(
        InstructionSetRepository instructionSetRepository,
        InstructionRepository instructionRepository
    ) {
        this(new BasicInstructionSetService(instructionSetRepository, instructionRepository));
    }

    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(Integer id) {
        return instructionSetService.readInstruction(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(Integer idSet, String name) {
        return instructionSetService.readInstruction(idSet, name);
    }

    @Override
    @Transactional(readOnly = true)
    public Instruction readInstruction(String setName, String name) {
        return instructionSetService.readInstruction(setName, name);
    }

    @Override
    @Transactional
    public Instruction deleteInstruction(Integer id) {
        return instructionSetService.deleteInstruction(id);
    }

    @Override
    @Transactional
    public Instruction deleteInstruction(Integer idSet, String name) {
        return instructionSetService.deleteInstruction(idSet, name);
    }

    @Override
    @Transactional
    public Instruction deleteInstruction(String setName, String name) {
        return instructionSetService.deleteInstruction(setName, name);
    }

}
