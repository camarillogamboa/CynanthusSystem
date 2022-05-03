package edu.cynanthus.auri.server.entity;

import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity(name = "ServerInfo")
@Table(schema = "cynanthus", name = "server_info")
public class ServerInfoEntity extends ServerInfo {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @NaturalId(mutable = true)
    @Column(name = "name", nullable = false, unique = true, length = 45)
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "address", nullable = false, length = 60)
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Column(name = "port", nullable = false)
    @Override
    public Integer getPort() {
        return super.getPort();
    }

    @Column(name = "server_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public ServerType getServerType() {
        return super.getServerType();
    }

    @Column(name = "info", length = 300)
    @Override
    public String getInfo() {
        return super.getInfo();
    }

}
