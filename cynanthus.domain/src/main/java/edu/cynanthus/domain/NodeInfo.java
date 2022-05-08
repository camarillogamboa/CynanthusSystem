package edu.cynanthus.domain;

import edu.cynanthus.bean.*;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Node info.
 */
public class NodeInfo extends Host {

    /**
     * El Id.
     */
    @NotNull(groups = IdCandidate.class, message = "{NotNull.nodeInfo.id}")
    @Positive(groups = {IdCandidate.class, ValidInfo.class}, message = "{Positive.nodeinfo.id}")
    @JProperty
    private Integer id;

    /**
     * El Name.
     */
    @NotEmpty(groups = Required.class, message = "{NotEmpty.nodeInfo.name}")
    @Size(max = 45, groups = {Required.class, ValidInfo.class}, message = "{Size.nodeInfo.name}")
    @Pattern(regexp = Patterns.NAME, groups = {Required.class, ValidInfo.class}, message = "{Pattern.nodeInfo.name}")
    @JProperty
    private String name;

    /**
     * El Id server info.
     */
    @NotNull(groups = Required.class, message = "{NotNull.nodeInfo.idServerInfo}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.nodeInfo.idServerInfo}")
    @JProperty
    private Integer idServerInfo;

    /**
     * El Id set.
     */
    @NotEmpty(message = "{NotEmpty.nodeInfo.idset}")
    @Positive(groups = ValidInfo.class, message = "{Positive.nodeInfo.idSet}")
    @JProperty
    private Integer idSet;

    /**
     * Instancia un nuevo Node info.
     *
     * @param id           el id
     * @param mac          el mac
     * @param name         el name
     * @param idServerInfo el id server info
     * @param idSet        el id set
     */
    public NodeInfo(Integer id, String mac, String name, Integer idServerInfo, Integer idSet) {
        super(mac);
        this.id = id;
        this.name = name;
        this.idServerInfo = idServerInfo;
        this.idSet = idSet;
    }

    /**
     * Instancia un nuevo Node info.
     *
     * @param mac          el mac
     * @param name         el name
     * @param idServerInfo el id server info
     * @param idSet        el id set
     */
    public NodeInfo(String mac, String name, Integer idServerInfo, Integer idSet) {
        super(mac);
        this.name = name;
        this.idServerInfo = idServerInfo;
        this.idSet = idSet;
    }

    /**
     * Instancia un nuevo Node info.
     *
     * @param id el id
     */
    public NodeInfo(Integer id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo Node info.
     *
     * @param mac el mac
     */
    public NodeInfo(String mac) {
        super(mac);
    }

    /**
     * Instancia un nuevo Node info.
     */
    public NodeInfo() {
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
     * Permite obtener id server info.
     *
     * @return el id server info
     */
    public Integer getIdServerInfo() {
        return idServerInfo;
    }

    /**
     * Permite establecer id server info.
     *
     * @param idServerInfo el id server info
     */
    public void setIdServerInfo(Integer idServerInfo) {
        this.idServerInfo = idServerInfo;
    }

    /**
     * Permite obtener id set.
     *
     * @return el id set
     */
    public Integer getIdSet() {
        return idSet;
    }

    /**
     * Permite establecer id set.
     *
     * @param idSet el id set
     */
    public void setIdSet(Integer idSet) {
        this.idSet = idSet;
    }

    /**
     * Clone node info.
     *
     * @return el node info
     */
    @Override
    public NodeInfo clone() {
        return new NodeInfo(id, getMac(), name, idServerInfo, idSet);
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
        if (!super.equals(o)) return false;
        NodeInfo nodeInfo = (NodeInfo) o;
        return Objects.equals(id, nodeInfo.id) &&
            Objects.equals(name, nodeInfo.name) &&
            Objects.equals(idServerInfo, nodeInfo.idServerInfo) &&
            Objects.equals(idSet, nodeInfo.idSet);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, idServerInfo, idSet);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "id:" + id +
            ",name:'" + name + '\'' +
            ",idServerInfo:" + idServerInfo +
            ",idSet:" + idSet +
            '}';
    }

}
