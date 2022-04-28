package edu.cynanthus.auri.server.entity;

import edu.cynanthus.domain.NodeInfo;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * El tipo Node info entity.
 */
@Entity(name = "NodeInfo")
@Table(schema = "cynanthus", name = "node_info")
public class NodeInfoEntity extends NodeInfo {

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
     * Permite obtener mac.
     *
     * @return el mac
     */
    @NaturalId(mutable = true)
    @Column(name = "mac", unique = true, nullable = false, length = 17)
    @Override
    public String getMac() {
        return super.getMac();
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
     * Permite obtener id server info.
     *
     * @return el id server info
     */
    @Column(name = "id_server_info", nullable = false)
    @Override
    public Integer getIdServerInfo() {
        return super.getIdServerInfo();
    }

    /**
     * Permite obtener id set.
     *
     * @return el id set
     */
    @Column(name = "id_set")
    @Override
    public Integer getIdSet() {
        return super.getIdSet();
    }

}
