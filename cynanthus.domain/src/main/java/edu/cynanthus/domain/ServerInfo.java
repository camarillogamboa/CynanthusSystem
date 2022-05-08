package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Server info.
 */
public class ServerInfo implements Bean {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class, message = "{NotNull.serverInfo.id}")
    @Positive(groups = {IdCandidate.class, ValidInfo.class}, message = "{Positive.serverInfo.id}")
    @JProperty
    private Integer id;

    /**
     * El Name.
     */
    @NotEmpty(groups = {Required.class, NaturalIdCandidate.class}, message = "{NotEmpty.serverInfo.name}")
    @Size(
        max = 45,
        groups = {Required.class, ValidInfo.class, NaturalIdCandidate.class},
        message = "{Size.serverInfo.name}"
    )
    @Pattern(
        regexp = Patterns.NAME,
        groups = {Required.class, ValidInfo.class, NaturalIdCandidate.class},
        message = "{Pattern.serverInfo.name}"
    )
    @JProperty
    private String name;

    /**
     * El Address.
     */
    @NotEmpty(groups = Required.class, message = "{NotEmpty.serverInfo.address}")
    @Size(max = 60, groups = {Required.class, ValidInfo.class}, message = "{Size.serverInfo.address}")
    @JProperty
    private String address;

    /**
     * El Port.
     */
    @NotNull(groups = Required.class, message = "{NotNull.serverInfo.port}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.serverInfo.port}")
    @Max(value = 65536, groups = {Required.class, ValidInfo.class}, message = "{Max.serverInfo.port}")
    @JProperty
    private Integer port;

    /**
     * El Server type.
     */
    @NotNull(groups = Required.class, message = "{NotNull.serverInfo.serverType}")
    @JProperty
    private ServerType serverType;

    /**
     * El Info.
     */
    @NotEmpty(message = "{NotEmpty.serverInfo.info}")
    @Size(max = 300, groups = ValidInfo.class, message = "{Size.serverInfo.info}")
    @JProperty
    private String info;

    /**
     * Instancia un nuevo Server info.
     *
     * @param id         el id
     * @param name       el name
     * @param address    el address
     * @param port       el port
     * @param serverType el server type
     * @param info       el info
     */
    public ServerInfo(
        Integer id,
        String name,
        String address,
        Integer port,
        ServerType serverType,
        String info
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.port = port;
        this.serverType = serverType;
        this.info = info;
    }

    /**
     * Instancia un nuevo Server info.
     *
     * @param name       el name
     * @param address    el address
     * @param port       el port
     * @param serverType el server type
     * @param info       el info
     */
    public ServerInfo(String name, String address, Integer port, ServerType serverType, String info) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.serverType = serverType;
        this.info = info;
    }

    /**
     * Instancia un nuevo Server info.
     *
     * @param id el id
     */
    public ServerInfo(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo Server info.
     *
     * @param name el name
     */
    public ServerInfo(String name) {
        this.name = name;
    }

    /**
     * Instancia un nuevo Server info.
     */
    public ServerInfo() {
    }

    /**
     * Permite obtener id.
     *
     * @return el id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Permite establecer id.
     *
     * @param id el id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Permite obtener name.
     *
     * @return el name
     */
    public String getName() {
        return name;
    }

    /**
     * Permite establecer name.
     *
     * @param name el name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permite obtener address.
     *
     * @return el address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Permite establecer address.
     *
     * @param address el address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Permite obtener port.
     *
     * @return el port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Permite establecer port.
     *
     * @param port el port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Permite obtener server type.
     *
     * @return el server type
     */
    public ServerType getServerType() {
        return serverType;
    }

    /**
     * Permite establecer server type.
     *
     * @param serverType el server type
     */
    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    /**
     * Permite obtener info.
     *
     * @return el info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Permite establecer info.
     *
     * @param info el info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Clone server info.
     *
     * @return el server info
     */
    @Override
    public ServerInfo clone() {
        return new ServerInfo(
            id,
            name,
            address,
            port,
            serverType,
            info
        );
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerInfo that = (ServerInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(address, that.address) &&
            Objects.equals(port, that.port) && serverType == that.serverType &&
            Objects.equals(info, that.info);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, port, serverType, info);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "id:" + id +
            ",name:'" + name + '\'' +
            ",address:'" + address + '\'' +
            ",port:" + port +
            ",serverType:" + serverType +
            ",info:'" + info + '\'' +
            '}';
    }

}
