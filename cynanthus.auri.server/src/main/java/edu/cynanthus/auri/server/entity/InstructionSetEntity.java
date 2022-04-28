package edu.cynanthus.auri.server.entity;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.domain.Instruction;
import edu.cynanthus.domain.InstructionSet;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

/**
 * El tipo Instruction set entity.
 */
@Entity(name = "InstructionSet")
@Table(schema = "cynanthus", name = "instruction_set")
public class InstructionSetEntity extends InstructionSet implements Bean {

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
     * Permite obtener name.
     *
     * @return el name
     */
    @NaturalId(mutable = true)
    @Column(name = "name", nullable = false, unique = true, length = 25)
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Permite obtener info.
     *
     * @return el info
     */
    @Column(name = "info", length = 300)
    @Override
    public String getInfo() {
        return super.getInfo();
    }

    /**
     * Permite obtener instructions.
     *
     * @return el instructions
     */
    @Transient
    @Override
    public List<Instruction> getInstructions() {
        return super.getInstructions();
    }

}
