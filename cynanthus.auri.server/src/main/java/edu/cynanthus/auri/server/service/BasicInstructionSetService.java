package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.ServiceException;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
        this.instJpa = Objects.requireNonNull(instructionRepository);
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
                            null, entity.getId(), instruction.getName(), instruction.getValue()
                        )
                    ).clone()
                );

        } else entity = setJpa.save((InstructionSetEntity) bean);

        entity.setInstructions(instructions);
        return entity.clone();
    }

    @Override
    public InstructionSet read(InstructionSet bean) {
        checkNotNull(bean);
        InstructionSetEntity entity = safeFind(bean);
        findInstructions(entity);
        return entity;
    }

    @Override
    public List<? extends InstructionSet> read() {
        List<? extends InstructionSet> sets = super.read();
        for (InstructionSet set : sets) findInstructions(set);
        return sets;
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
            () -> new ServiceException(
                "Registro InstructionSet{" + setName + "} no existe",
                ExceptionType.NO_EXISTING_ELEMENT
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
                        ValueUpdater.updateIfNotNull(instruction.getValue(), v::setValue);
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
        return find(id).orElseThrow(() -> new ServiceException(
            "Registro Instruction{" + id + "} no existe",
            ExceptionType.NO_EXISTING_ELEMENT
        ));
    }

    private Optional<InstructionEntity> find(Integer idset, String name) {
        return instJpa.findByIdSetAndName(idset, name);
    }

    private InstructionEntity safeFind(Integer idSet, String name) {
        return find(idSet, name).orElseThrow(() -> new ServiceException(
            "Registro Instruction{" + idSet + ":" + name + "} no existe",
            ExceptionType.NO_EXISTING_ELEMENT
        ));
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
        System.out.println(bean);

        if (BeanValidation.validate(bean, IdCandidate.class).isEmpty()) {
            System.out.println("Buscando por id");
            return setJpa.findById(bean.getId());
        } else if (BeanValidation.validate(bean, NaturalIdCandidate.class).isEmpty()) {
            System.out.println("Buscando por nombre");
            return setJpa.findByName(bean.getName());
        }

        throw new ServiceException(
            "Se requiere un identificador válido del InstructionSet",
            ExceptionType.INVALID_ID
        );
    }

    @Override
    InstructionSetEntity safeFind(InstructionSet bean) {
        return find(bean).orElseThrow(() -> new ServiceException(
            "Registro InstructionSet{" +
                (bean.getId() != null ? bean.getId() : bean.getName()) +
                "} no existe", ExceptionType.NO_EXISTING_ELEMENT
        ));
    }

    private void checkId(Integer id) {
        if (id == null || id < 0)
            throw new ServiceException("Identificador \"" + id + "\" inválido", ExceptionType.INVALID_ID);
    }

    private void checkNaturalId(String naturalId) {
        if (naturalId == null || naturalId.isBlank() || naturalId.isEmpty())
            throw new ServiceException("Identificador \"" + naturalId + "\"", ExceptionType.INVALID_ID);
    }

    @Override
    void checkNotNull(InstructionSet bean) {
        if (bean == null)
            throw new ServiceException("El elemento InstructionSet no debe ser nulo", ExceptionType.NULL_POINTER);
    }

}
