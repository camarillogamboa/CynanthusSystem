package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.NodeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * La interface Node info repository.
 */
@Repository
public interface NodeInfoRepository extends JpaRepository<NodeInfoEntity, Integer> {

    /**
     * Find by mac optional.
     *
     * @param mac el mac
     * @return el optional
     */
    Optional<NodeInfoEntity> findByMac(String mac);

    /**
     * Find all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    List<NodeInfoEntity> findAllByIdServerInfo(Integer idServerInfo);

}