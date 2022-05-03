package edu.cynanthus.auri.server.repository;

import edu.cynanthus.auri.server.entity.NodeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NodeInfoRepository extends JpaRepository<NodeInfoEntity, Integer> {

    Optional<NodeInfoEntity> findByMac(String mac);

    List<NodeInfoEntity> findAllByIdServerInfo(Integer idServerInfo);

}