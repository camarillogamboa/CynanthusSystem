package edu.cynanthus.auri.server.entity;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * El tipo Server info entity.
 */
@Entity(name = "ServerInfo")
@Table(schema = "cynanthus", name = "server_info")
public class ServerInfoEntity extends ServerInfo implements Bean {

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
    @Column(name = "name", nullable = false, unique = true, length = 45)
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Permite obtener address.
     *
     * @return el address
     */
    @Column(name = "address", nullable = false, length = 60)
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    /**
     * Permite obtener port.
     *
     * @return el port
     */
    @Column(name = "port", nullable = false)
    @Override
    public Integer getPort() {
        return super.getPort();
    }

    /**
     * Permite obtener server type.
     *
     * @return el server type
     */
    @Column(name = "server_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public ServerType getServerType() {
        return super.getServerType();
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

}
