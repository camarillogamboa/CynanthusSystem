package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.lang.reflect.Type;
import java.util.List;

/**
 * El tipo Instruction set service consumer.
 */
class InstructionSetServiceConsumer extends BeanServiceConsumer<InstructionSet> implements InstructionSetService {

    /**
     * La constante INSTRUCTION_SET_LIST_TYPE.
     */
    private static final Type INSTRUCTION_SET_LIST_TYPE = new TypeToken<List<InstructionSet>>() {}.getType();

    /**
     * Instancia un nuevo Instruction set service consumer.
     *
     * @param lazyRequest el lazy request
     */
    InstructionSetServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            INSTRUCTION_SET_SERVICE_PATH,
            InstructionSet.class,
            INSTRUCTION_SET_LIST_TYPE
        );
    }

    /**
     * Read only set instruction set.
     *
     * @param bean el bean
     * @return el instruction set
     */
    @Override
    public InstructionSet readOnlySet(InstructionSet bean) {
        checkNotNull(bean);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/only/" + getId(bean)), InstructionSet.class
        );
    }

    /**
     * Read only sets list.
     *
     * @return el list
     */
    @Override
    public List<? extends InstructionSet> readOnlySets() {
        return consume(lazyRequest -> lazyRequest.GET(resourcePath + "/only"), INSTRUCTION_SET_LIST_TYPE);
    }

    /**
     * Read instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    public Instruction readInstruction(Integer id) {
        checkId(id);
        return consume(lazyRequest -> lazyRequest.GET(resourcePath + "/instruction/" + id), Instruction.class);
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
        checkIdSet(idSet);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/instruction/" + idSet + "/" + name), Instruction.class
        );
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
        checkSetName(setName);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    /**
     * Delete instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    public Instruction deleteInstruction(Integer id) {
        checkId(id);
        return consume(lazyRequest -> lazyRequest.DELETE(resourcePath + "/instruction/" + id), Instruction.class);
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
        checkIdSet(idSet);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.DELETE(resourcePath + "/instruction/" + idSet + "/" + name),
            Instruction.class
        );
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
        checkSetName(setName);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.DELETE(resourcePath + "/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    /**
     * Check id.
     *
     * @param id el id
     */
    private void checkId(Integer id) {
        if (id == null)
            throw new InvalidArgumentException("Se requiere un identificador de instrucción");
    }

    /**
     * Check id set.
     *
     * @param idSet el id set
     */
    private void checkIdSet(Integer idSet) {
        if (idSet == null)
            throw new InvalidArgumentException(
                "Se requiere un identificador de conjunto de instrucciones"
            );
    }

    /**
     * Check set name.
     *
     * @param setName el set name
     */
    private void checkSetName(String setName) {
        if (setName == null) throw new InvalidArgumentException(
            "Se requiere un nombre de conjunto de instrucciones"
        );
    }

    /**
     * Check name.
     *
     * @param name el name
     */
    private void checkName(String name) {
        if (name == null)
            throw new InvalidArgumentException("Se requiere un nombre de instruccón");
    }

    /**
     * Permite obtener id.
     *
     * @param bean el bean
     * @return el id
     */
    @Override
    Object getId(InstructionSet bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
