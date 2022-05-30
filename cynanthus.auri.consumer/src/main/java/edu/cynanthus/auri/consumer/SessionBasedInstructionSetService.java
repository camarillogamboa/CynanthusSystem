package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.util.List;
import java.util.function.Consumer;

/**
 * El tipo Session based instruction set service.
 */
public class SessionBasedInstructionSetService
    extends SessionBasedBeanService<InstructionSet, InstructionSetService> implements InstructionSetService {

    /**
     * Instancia un nuevo Session based instruction set service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedInstructionSetService(
        AuriServiceConsumer<InstructionSetService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Read only set instruction set.
     *
     * @param instructionSet el instruction set
     * @return el instruction set
     */
    @Override
    public InstructionSet readOnlySet(InstructionSet instructionSet) {
        return consume(service -> service.readOnlySet(instructionSet));
    }

    /**
     * Read only sets list.
     *
     * @return el list
     */
    @Override
    public List<? extends InstructionSet> readOnlySets() {
        return consume(InstructionSetService::readOnlySets);
    }

    /**
     * Read instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    public Instruction readInstruction(Integer id) {
        return consume(service -> service.readInstruction(id));
    }

    /**
     * Read instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    @Override
    public Instruction readInstruction(Integer idSet, String name) {
        return consume(service -> service.readInstruction(idSet, name));
    }

    /**
     * Read instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    @Override
    public Instruction readInstruction(String setName, String name) {
        return consume(service -> service.readInstruction(setName, name));
    }

    /**
     * Delete instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    public Instruction deleteInstruction(Integer id) {
        return consume(service -> service.deleteInstruction(id));
    }

    /**
     * Delete instruction instruction.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el instruction
     */
    @Override
    public Instruction deleteInstruction(Integer idSet, String name) {
        return consume(service -> service.deleteInstruction(idSet, name));
    }

    /**
     * Delete instruction instruction.
     *
     * @param setName el set name
     * @param name    el name
     * @return el instruction
     */
    @Override
    public Instruction deleteInstruction(String setName, String name) {
        return consume(service -> service.deleteInstruction(setName, name));
    }

}
