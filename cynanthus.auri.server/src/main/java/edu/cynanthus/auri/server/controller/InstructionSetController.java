package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cynanthus/auri/set")
public class InstructionSetController extends BeanController<InstructionSet> implements InstructionSetService {

    private final InstructionSetService instructionSetService;

    @Autowired
    public InstructionSetController(
        @Qualifier("transactionalInstructionSetService") InstructionSetService beanService
    ) {
        super(beanService);
        this.instructionSetService = beanService;
    }

    @GetMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public InstructionSet readByName(InstructionSet bean) {
        return read(bean);
    }

    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public InstructionSet deleteByName(InstructionSet bean) {
        return delete(bean);
    }

    @Override
    @GetMapping("/instruction/{id:\\d+}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable Integer id) {
        return instructionSetService.readInstruction(id);
    }

    @Override
    @GetMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable Integer idSet, @PathVariable String name) {
        return instructionSetService.readInstruction(idSet, name);
    }

    @Override
    @GetMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction readInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.readInstruction(setName, name);
    }

    @Override
    @DeleteMapping("/instruction/{id:\\d+}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable Integer id) {
        return instructionSetService.deleteInstruction(id);
    }

    @Override
    @DeleteMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable Integer idSet, @PathVariable String name) {
        return instructionSetService.deleteInstruction(idSet, name);
    }

    @Override
    @DeleteMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Instruction deleteInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.deleteInstruction(setName, name);
    }

}
