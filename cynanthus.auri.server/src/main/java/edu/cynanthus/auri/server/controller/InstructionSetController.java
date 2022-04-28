package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * El tipo Instruction set controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/set")
public class InstructionSetController extends BeanController<InstructionSet> implements InstructionSetService {

    /**
     * El Instruction set service.
     */
    private final InstructionSetService instructionSetService;

    /**
     * Instancia un nuevo Instruction set controller.
     *
     * @param beanService el bean service
     */
    @Autowired
    public InstructionSetController(
        @Qualifier("transactionalInstructionSetService") InstructionSetService beanService
    ) {
        super(beanService);
        this.instructionSetService = beanService;
    }

    /**
     * Read by name instruction set.
     *
     * @param bean el bean
     * @return el instruction set
     */
    @GetMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public InstructionSet readByName(InstructionSet bean) {
        return read(bean);
    }

    /**
     * Delete by name instruction set.
     *
     * @param bean el bean
     * @return el instruction set
     */
    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public InstructionSet deleteByName(InstructionSet bean) {
        return delete(bean);
    }

    /**
     * Read instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    @GetMapping("/instruction/{id:\\d+}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable Integer id) {
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
    @GetMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable Integer idSet, @PathVariable String name) {
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
    @GetMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.readInstruction(setName, name);
    }

    /**
     * Delete instruction instruction.
     *
     * @param id el id
     * @return el instruction
     */
    @Override
    @DeleteMapping("/instruction/{id:\\d+}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable Integer id) {
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
    @DeleteMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable Integer idSet, @PathVariable String name) {
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
    @DeleteMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.deleteInstruction(setName, name);
    }

}
