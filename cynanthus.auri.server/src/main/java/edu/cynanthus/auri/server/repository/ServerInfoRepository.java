package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.ServerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Integer> {

    Optional<ServerInfoEntity> findByName(String name);

}