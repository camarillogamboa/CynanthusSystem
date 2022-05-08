package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.lang.reflect.Type;
import java.util.List;

class InstructionSetServiceConsumer extends BeanServiceConsumer<InstructionSet> implements InstructionSetService {

    private static final Type INSTRUCTION_SET_LIST_TYPE = new TypeToken<List<InstructionSet>>() {}.getType();

    InstructionSetServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            "/cynanthus/auri/set",
            InstructionSet.class,
            INSTRUCTION_SET_LIST_TYPE
        );
    }

    @Override
    public Instruction readInstruction(Integer id) {
        checkId(id);
        return consume(lazyRequest -> lazyRequest.GET("/instruction/" + id), Instruction.class);
    }

    @Override
    public Instruction readInstruction(Integer idSet, String name) {
        checkIdSet(idSet);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.GET("/instruction/" + idSet + "/" + name), Instruction.class
        );
    }

    @Override
    public Instruction readInstruction(String setName, String name) {
        checkSetName(setName);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.GET("/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    @Override
    public Instruction deleteInstruction(Integer id) {
        checkId(id);
        return consume(lazyRequest -> lazyRequest.DELETE("/instruction/" + id), Instruction.class);
    }

    @Override
    public Instruction deleteInstruction(Integer idSet, String name) {
        checkIdSet(idSet);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.DELETE("/instruction/" + idSet + "/" + name),
            Instruction.class
        );
    }

    @Override
    public Instruction deleteInstruction(String setName, String name) {
        checkSetName(setName);
        checkName(name);
        return consume(
            lazyRequest -> lazyRequest.DELETE("/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    private void checkId(Integer id) {
        if (id == null)
            throw new InvalidArgumentException("Se requiere un identificador de instrucción");
    }

    private void checkIdSet(Integer idSet) {
        if (idSet == null)
            throw new InvalidArgumentException(
                "Se requiere un identificador de conjunto de instrucciones"
            );
    }

    private void checkSetName(String setName) {
        if (setName == null) throw new InvalidArgumentException(
            "Se requiere un nombre de conjunto de instrucciones"
        );
    }

    private void checkName(String name) {
        if (name == null)
            throw new InvalidArgumentException("Se requiere un nombre de instruccón");
    }

    @Override
    Object getId(InstructionSet bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
