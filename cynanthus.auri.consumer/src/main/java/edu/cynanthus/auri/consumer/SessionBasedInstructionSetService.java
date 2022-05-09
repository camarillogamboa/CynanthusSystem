package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.util.Map;
import java.util.function.Supplier;

public class SessionBasedInstructionSetService
    extends SessionBasedBeanService<InstructionSet, InstructionSetService> implements InstructionSetService {

    SessionBasedInstructionSetService(
        AuriServiceConsumer<InstructionSetService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

    @Override
    public Instruction readInstruction(Integer id) {
        return consume(service -> service.readInstruction(id));
    }

    @Override
    public Instruction readInstruction(Integer idSet, String name) {
        return consume(service -> service.readInstruction(idSet, name));
    }

    @Override
    public Instruction readInstruction(String setName, String name) {
        return consume(service -> service.readInstruction(setName, name));
    }

    @Override
    public Instruction deleteInstruction(Integer id) {
        return consume(service -> service.deleteInstruction(id));
    }

    @Override
    public Instruction deleteInstruction(Integer idSet, String name) {
        return consume(service -> service.deleteInstruction(idSet, name));
    }

    @Override
    public Instruction deleteInstruction(String setName, String name) {
        return consume(service -> service.deleteInstruction(setName, name));
    }

}
