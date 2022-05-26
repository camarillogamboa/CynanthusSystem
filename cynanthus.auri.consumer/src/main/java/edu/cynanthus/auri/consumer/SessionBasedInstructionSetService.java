package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.util.List;
import java.util.function.Consumer;

public class SessionBasedInstructionSetService
    extends SessionBasedBeanService<InstructionSet, InstructionSetService> implements InstructionSetService {

    SessionBasedInstructionSetService(
        AuriServiceConsumer<InstructionSetService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public InstructionSet readOnlySet(InstructionSet instructionSet) {
        return consume(service -> service.readOnlySet(instructionSet));
    }

    @Override
    public List<? extends InstructionSet> readOnlySets() {
        return consume(InstructionSetService::readOnlySets);
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
