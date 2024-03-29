package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.bean.ValidInfo;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(InstructionSetService.INSTRUCTION_SET_SERVICE_PATH)
public class InstructionSetController extends BeanController<InstructionSet> implements InstructionSetService {

    private final InstructionSetService instructionSetService;

    @Autowired
    public InstructionSetController(
        @Qualifier("transactionalInstructionSetService") InstructionSetService instructionSetService
    ) {
        super(instructionSetService);
        this.instructionSetService = instructionSetService;
    }


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructionSet create(@Validated({ValidInfo.class}) @RequestBody InstructionSet bean) {
        return super.create(bean);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}")
    public InstructionSet readByName(InstructionSet bean) {
        return read(bean);
    }

    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    public InstructionSet deleteByName(InstructionSet bean) {
        return delete(bean);
    }

    @Override
    @GetMapping("/only/{id:\\d+}")
    public InstructionSet readOnlySet(InstructionSet bean) {
        return instructionSetService.readOnlySet(bean);
    }

    @GetMapping("/only/{name:" + Patterns.NAME + "}")
    public InstructionSet readOnlySetByName(InstructionSet bean) {
        return instructionSetService.readOnlySet(bean);
    }

    @Override
    @GetMapping("/only")
    public List<? extends InstructionSet> readOnlySets() {
        return instructionSetService.readOnlySets();
    }

    @Override
    @GetMapping("/instruction/{id:\\d+}")
    public Instruction readInstruction(@PathVariable Integer id) {
        return instructionSetService.readInstruction(id);
    }

    @Override
    @GetMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    public Instruction readInstruction(@PathVariable Integer idSet, @PathVariable String name) {
        return instructionSetService.readInstruction(idSet, name);
    }

    @Override
    @GetMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    public Instruction readInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.readInstruction(setName, name);
    }

    @Override
    @DeleteMapping("/instruction/{id:\\d+}")
    public Instruction deleteInstruction(@PathVariable Integer id) {
        return instructionSetService.deleteInstruction(id);
    }

    @Override
    @DeleteMapping("/instruction/{idSet:\\d+}/{name:" + Patterns.NAME + "}")
    public Instruction deleteInstruction(@PathVariable Integer idSet, @PathVariable String name) {
        return instructionSetService.deleteInstruction(idSet, name);
    }

    @Override
    @DeleteMapping("/instruction/{setName:" + Patterns.NAME + "}/{name:" + Patterns.NAME + "}")
    public Instruction deleteInstruction(@PathVariable String setName, @PathVariable String name) {
        return instructionSetService.deleteInstruction(setName, name);
    }

}
