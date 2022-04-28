package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.ServerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * La interface Server info repository.
 */
@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Integer> {

    /**
     * Find by name optional.
     *
     * @param name el name
     * @return el optional
     */
    Optional<ServerInfoEntity> findByName(String name);

}