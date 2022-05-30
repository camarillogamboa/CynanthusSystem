package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.auri.api.exception.ResourceNotFoundException;
import edu.cynanthus.auri.server.entity.InstructionEntity;
import edu.cynanthus.auri.server.entity.InstructionSetEntity;
import edu.cynanthus.auri.server.repository.InstructionRepository;
import edu.cynanthus.auri.server.repository.InstructionSetRepository;
import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.NaturalIdCandidate;
import edu.cynanthus.bean.ValueUpdater;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class BasicInstructionSetService
    extends BasicBeanService<Integer, InstructionSet, InstructionSetEntity> implements InstructionSetService {

    private final InstructionSetRepository setJpa;
    private final InstructionRepository instJpa;

    BasicInstructionSetService(
        InstructionSetRepository instructionSetRepository,
        InstructionRepository instructionRepository
    ) {
        super(instructionSetRepository);
        this.setJpa = instructionSetRepository;
        this.instJpa = instructionRepository;
    }

    @Override
    public InstructionSet create(InstructionSet bean) {
        checkNotNull(bean);

        InstructionSetEntity entity;
        List<Instruction> instructions = new LinkedList<>();

        if (bean.getInstructions() != null && !bean.getInstructions().isEmpty()) {
            entity = find(bean).orElseGet(() -> setJpa.save((InstructionSetEntity) bean));

            for (Instruction instruction : bean.getInstructions())
                instructions.add(
                    instJpa.save(
                        new InstructionEntity(
                            null, entity.getId(), instruction.getName(), instruction.getVector()
                        )
                    ).clone()
                );

        } else entity = setJpa.save((InstructionSetEntity) bean);

        entity.setInstructions(instructions);
        return entity.clone();
    }

    @Override
    public InstructionSet read(InstructionSet bean) {
        bean = readOnlySet(bean);
        findInstructions(bean);
        return bean;
    }

    @Override
    public List<? extends InstructionSet> read() {
        List<? extends InstructionSet> sets = super.read();
        for (InstructionSet set : sets) findInstructions(set);
        return sets;
    }

    @Override
    public InstructionSet readOnlySet(InstructionSet bean) {
        checkNotNull(bean);
        return safeFind(bean);
    }

    @Override
    public List<? extends InstructionSet> readOnlySets() {
        return super.read();
    }

    @Override
    public Instruction readInstruction(Integer id) {
        checkId(id);
        return safeFind(id).clone();
    }

    @Override
    public Instruction readInstruction(Integer idSet, String name) {
        checkId(idSet);
        checkNaturalId(name);
        return safeFind(idSet, name).clone();
    }

    @Override
    public Instruction readInstruction(String setName, String name) {
        checkNaturalId(setName);
        checkNaturalId(name);

        InstructionSetEntity entity = setJpa.findByName(setName).orElseThrow(
            () -> new ResourceNotFoundException(
                "Conjunto de instrucciones \"" + setName + "\" no encontrado"
            )
        );

        return safeFind(entity.getId(), name).clone();
    }

    @Override
    public InstructionSet update(InstructionSet bean) {
        checkNotNull(bean);

        InstructionSetEntity entity = safeFind(bean);

        ValueUpdater.updateIfNotNull(bean.getName(), entity::setName);
        ValueUpdater.updateIfNotNull(bean.getInfo(), entity::setInfo);

        entity = setJpa.save(entity);

        List<Instruction> result = new LinkedList<>();

        if (bean.getInstructions() != null)
            for (Instruction instruction : bean.getInstructions()) {

                InstructionSetEntity finalEntity = entity;

                find(entity.getId(), instruction).ifPresent(v -> {

                    if (v.getIdSet().equals(finalEntity.getId())) {
                        ValueUpdater.updateIfNotNull(instruction.getName(), v::setName);
                        ValueUpdater.updateIfNotNull(instruction.getVector(), v::setVector);
                        result.add(instJpa.save(v).clone());
                    }

                });

            }

        entity.setInstructions(result);
        return entity.clone();
    }

    @Override
    public InstructionSet delete(InstructionSet bean) {
        checkNotNull(bean);
        InstructionSet result = read(bean);
        setJpa.deleteById(result.getId());
        return result;
    }

    @Override
    public Instruction deleteInstruction(Integer id) {
        checkId(id);
        Instruction result = readInstruction(id);
        instJpa.deleteById(result.getId());
        return result;
    }

    @Override
    public Instruction deleteInstruction(Integer idSet, String name) {
        checkId(idSet);
        Instruction result = readInstruction(idSet, name);
        instJpa.deleteById(result.getId());
        return result;
    }

    @Override
    public Instruction deleteInstruction(String setName, String name) {
        checkNaturalId(setName);
        checkNaturalId(name);
        Instruction result = readInstruction(setName, name);
        instJpa.deleteById(result.getId());
        return result;
    }


    private void findInstructions(InstructionSet instructionSet) {
        List<Instruction> result = instJpa.findAllByIdSet(instructionSet.getId()).
            stream().map(Instruction::clone).collect(Collectors.toList());

        instructionSet.setInstructions(result);
    }

    private Optional<InstructionEntity> find(Integer id) {
        return instJpa.findById(id);
    }

    private InstructionEntity safeFind(Integer id) {
        return find(id).orElseThrow(
            () -> new ResourceNotFoundException(
                "Intrucción \"" + id + "\" no encontrada"
            )
        );
    }

    private Optional<InstructionEntity> find(Integer idset, String name) {
        return instJpa.findByIdSetAndName(idset, name);
    }

    private InstructionEntity safeFind(Integer idSet, String name) {
        return find(idSet, name).orElseThrow(
            () -> new ResourceNotFoundException(
                "Intrucción \"" + idSet + ":" + name + "\" no encontrada"
            )
        );
    }

    private Optional<InstructionEntity> find(Integer idSet, Instruction bean) {
        if (bean == null) return Optional.empty();
        if (BeanValidation.validate(bean, IdCandidate.class).isEmpty())
            return find(bean.getId());
        else if (BeanValidation.validate(bean, NaturalIdCandidate.class).isEmpty())
            return find(idSet, bean.getName());
        else return Optional.empty();
    }

    @Override
    Optional<InstructionSetEntity> find(InstructionSet bean) {
        bean = new InstructionSet(bean.getId(), bean.getName(), null, null);

        if (BeanValidation.validate(bean, IdCandidate.class).isEmpty()) {
            return setJpa.findById(bean.getId());
        } else if (BeanValidation.validate(bean, NaturalIdCandidate.class).isEmpty()) {
            return setJpa.findByName(bean.getName());
        }

        throw new InvalidArgumentException(
            "Se requiere un identificador válido del conjunto de instrucción (id o setName)"
        );
    }

    @Override
    InstructionSetEntity safeFind(InstructionSet bean) {
        return find(bean).orElseThrow(() -> new ResourceNotFoundException(
            "Conjunto de instrucciones \"" +
                (bean.getId() != null ? bean.getId() : bean.getName()) +
                "\" no encontrado"
        ));
    }

    private void checkId(Integer id) {
        if (id == null || id < 0)
            throw new InvalidArgumentException("Identificador \"" + id + "\" inválido, no debe ser nulo o negativo");
    }

    private void checkNaturalId(String naturalId) {
        if (StringUtils.hasText(naturalId))
            throw new InvalidArgumentException("El identificador no puede ser nulo, estar vacío o lleno de espacios");
    }

    @Override
    void checkNotNull(InstructionSet bean) {
        if (bean == null)
            throw new InvalidArgumentException("El objeto InstructionSet no debe ser nulo");
    }

}
