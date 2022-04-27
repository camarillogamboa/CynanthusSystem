package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.InstructionSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructionSetRepository extends JpaRepository<InstructionSetEntity, Integer> {

    Optional<InstructionSetEntity> findByName(String name);

    boolean existsByName(String name);

}