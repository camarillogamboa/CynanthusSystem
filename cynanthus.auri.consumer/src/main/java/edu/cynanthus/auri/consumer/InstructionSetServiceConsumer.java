package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;

import java.util.List;

class InstructionSetServiceConsumer extends BeanServiceConsumer<InstructionSet> implements InstructionSetService {

    InstructionSetServiceConsumer(ClientInfo clientInfo) {
        super(
            clientInfo,
            "/cynanthus/auri/set",
            InstructionSet.class,
            new TypeToken<List<InstructionSet>>() {}.getType()
        );
    }

    @Override
    public Instruction readInstruction(Integer id) {
        checkId(id);
        return consumeApi(webServiceConsumer -> webServiceConsumer.GET("/instruction/" + id), Instruction.class);
    }

    @Override
    public Instruction readInstruction(Integer idSet, String name) {
        checkIdSet(idSet);
        checkName(name);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.GET("/instruction/" + idSet + "/" + name), Instruction.class
        );
    }

    @Override
    public Instruction readInstruction(String setName, String name) {
        checkSetName(setName);
        checkName(name);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.GET("/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    @Override
    public Instruction deleteInstruction(Integer id) {
        checkId(id);
        return consumeApi(webServiceConsumer ->
                webServiceConsumer.DELETE("/instruction/" + id),
            Instruction.class
        );
    }

    @Override
    public Instruction deleteInstruction(Integer idSet, String name) {
        checkIdSet(idSet);
        checkName(name);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.DELETE("/instruction/" + idSet + "/" + name),
            Instruction.class
        );
    }

    @Override
    public Instruction deleteInstruction(String setName, String name) {
        checkSetName(setName);
        checkName(name);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.DELETE("/instruction/" + setName + "/" + name),
            Instruction.class
        );
    }

    private void checkId(Integer id) {
        if (id == null)
            throw new ServiceException("Se requiere un identificador de instrucción", ExceptionType.REQUIRED_DATA);
    }

    private void checkIdSet(Integer idSet) {
        if (idSet == null)
            throw new ServiceException(
                "Se requiere un identificador de conjunto de instrucciones",
                ExceptionType.REQUIRED_DATA
            );
    }

    private void checkSetName(String setName) {
        if (setName == null) throw new ServiceException(
            "Se requiere un nombre de conjunto de instrucciones",
            ExceptionType.REQUIRED_DATA
        );
    }

    private void checkName(String name) {
        if (name == null)
            throw new ServiceException("Se requiere un nombre de instruccón", ExceptionType.REQUIRED_DATA);
    }

    @Override
    Object getId(InstructionSet bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getName();
        else throw new ServiceException(
                "Se requiere un identificador válido para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );
    }

}
