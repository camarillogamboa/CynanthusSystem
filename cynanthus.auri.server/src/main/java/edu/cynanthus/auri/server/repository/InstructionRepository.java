package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.InstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * La interface Instruction repository.
 */
@Repository
public interface InstructionRepository extends JpaRepository<InstructionEntity, Integer> {

    /**
     * Find all by id set list.
     *
     * @param idSet el id set
     * @return el list
     */
    List<InstructionEntity> findAllByIdSet(Integer idSet);

    /**
     * Find by id set and name optional.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el optional
     */
    Optional<InstructionEntity> findByIdSetAndName(Integer idSet, String name);

    /**
     * Exists by id set and name boolean.
     *
     * @param idSet el id set
     * @param name  el name
     * @return el boolean
     */
    boolean existsByIdSetAndName(Integer idSet, String name);

}