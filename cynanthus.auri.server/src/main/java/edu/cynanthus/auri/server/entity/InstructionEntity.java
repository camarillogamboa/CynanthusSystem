package edu.cynanthus.auri.server.entity;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.domain.Instruction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Instruction")
@Table(schema = "cynanthus", name = "instruction")
public class InstructionEntity extends Instruction {

    @JProperty
    private Integer idSet;

    public InstructionEntity(Integer idInstruction, Integer idSet, String name, int[] value) {
        super(idInstruction, name, value);
        this.idSet = idSet;
    }

    public InstructionEntity() {
    }

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Column(name = "id_set", nullable = false)
    public Integer getIdSet() {
        return idSet;
    }

    public void setIdSet(Integer idSet) {
        this.idSet = idSet;
    }

    @Column(name = "name", nullable = false, length = 45)
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "value", nullable = false)
    @Convert(converter = InstructionValueConverter.class)
    @Override
    public int[] getValue() {
        return super.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InstructionEntity entity = (InstructionEntity) o;
        return Objects.equals(idSet, entity.idSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSet);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "idSet:" + idSet +
            '}';
    }

}
