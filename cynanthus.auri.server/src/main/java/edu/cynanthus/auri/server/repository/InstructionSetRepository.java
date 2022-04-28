package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.InstructionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * La interface Instruction set repository.
 */
@Repository
public interface InstructionSetRepository extends JpaRepository<InstructionSetEntity, Integer> {

    /**
     * Find by name optional.
     *
     * @param name el name
     * @return el optional
     */
    Optional<InstructionSetEntity> findByName(String name);

    /**
     * Exists by name boolean.
     *
     * @param name el name
     * @return el boolean
     */
    boolean existsByName(String name);

}