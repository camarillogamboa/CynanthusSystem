package edu.cynanthus.auri.server.entity;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.domain.Instruction;

import javax.persistence.*;
import java.util.Objects;

/**
 * El tipo Instruction entity.
 */
@Entity(name = "Instruction")
@Table(schema = "cynanthus", name = "instruction")
public class InstructionEntity extends Instruction {

    /**
     * El Id set.
     */
    @JProperty
    private Integer idSet;

    /**
     * Instancia un nuevo Instruction entity.
     *
     * @param idInstruction el id instruction
     * @param idSet         el id set
     * @param name          el name
     * @param value         el value
     */
    public InstructionEntity(Integer idInstruction, Integer idSet, String name, int[] value) {
        super(idInstruction, name, value);
        this.idSet = idSet;
    }

    /**
     * Instancia un nuevo Instruction entity.
     */
    public InstructionEntity() {
    }

    /**
     * Permite obtener id.
     *
     * @return el id
     */
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    /**
     * Permite obtener id set.
     *
     * @return el id set
     */
    @Column(name = "id_set", nullable = false)
    public Integer getIdSet() {
        return idSet;
    }

    /**
     * Permite establecer id set.
     *
     * @param idSet el id set
     */
    public void setIdSet(Integer idSet) {
        this.idSet = idSet;
    }

    /**
     * Permite obtener name.
     *
     * @return el name
     */
    @Column(name = "name", nullable = false, length = 45)
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Get value int [ ].
     *
     * @return el int [ ]
     */
    @Column(name = "value", nullable = false)
    @Convert(converter = InstructionValueConverter.class)
    @Override
    public int[] getValue() {
        return super.getValue();
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InstructionEntity entity = (InstructionEntity) o;
        return Objects.equals(idSet, entity.idSet);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSet);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "idSet:" + idSet +
            '}';
    }

}
