package edu.cynanthus.domain;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * El tipo Control node.
 */
public class ControlNode extends Node implements RuntimeNode {

    /**
     * El Available.
     */
    @NotNull(groups = Required.class, message = "#{NotNull.controlNode.available}")
    @JProperty
    private Boolean available;

    /**
     * El Last connection.
     */
    @NotNull(message = "#{NotNull.controlNode.lastConnection}")
    @Positive(groups = ValidInfo.class, message = "#{Positive.controlNode.lastConnection}")
    @JProperty
    private Long lastConnection;

    /**
     * Instancia un nuevo Control node.
     *
     * @param mac            el mac
     * @param rssi           el rssi
     * @param available      el available
     * @param lastConnection el last connection
     */
    public ControlNode(String mac, Float rssi, Boolean available, Long lastConnection) {
        super(mac, rssi);
        this.available = available;
        this.lastConnection = lastConnection;
    }

    /**
     * Instancia un nuevo Control node.
     *
     * @param mac el mac
     */
    public ControlNode(String mac) {
        super(mac);
    }

    /**
     * Instancia un nuevo Control node.
     */
    public ControlNode() {
    }

    /**
     * Permite obtener available.
     *
     * @return el available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Permite establecer available.
     *
     * @param available el available
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Permite obtener last connection.
     *
     * @return el last connection
     */
    public Long getLastConnection() {
        return lastConnection;
    }

    /**
     * Permite establecer last connection.
     *
     * @param lastConnection el last connection
     */
    public void setLastConnection(Long lastConnection) {
        this.lastConnection = lastConnection;
    }

    /**
     * Clone control node.
     *
     * @return el control node
     */
    @Override
    public ControlNode clone() {
        return new ControlNode(getMac(), getRssi(), available, lastConnection);
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
        ControlNode that = (ControlNode) o;
        return Objects.equals(available, that.available) && Objects.equals(lastConnection, that.lastConnection);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), available, lastConnection);
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
            "available:" + available +
            ",lastConnection:" + lastConnection +
            '}';
    }

}
