package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.InstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructionRepository extends JpaRepository<InstructionEntity, Integer> {

    List<InstructionEntity> findAllByIdSet(Integer idSet);

    Optional<InstructionEntity> findByIdSetAndName(Integer idSet, String name);

    boolean existsByIdSetAndName(Integer idSet, String name);

}