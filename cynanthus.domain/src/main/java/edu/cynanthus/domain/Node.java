package edu.cynanthus.domain;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * El tipo Node.
 */
public class Node extends Host {

    /**
     * El Rssi.
     */
    @NotNull(message = "#{NotNull.node.rssi}")
    @Negative(groups = ValidInfo.class, message = "#{Negative.node.rssi}")
    @JProperty
    private Float rssi;

    /**
     * Instancia un nuevo Node.
     *
     * @param mac  el mac
     * @param rssi el rssi
     */
    public Node(String mac, Float rssi) {
        super(mac);
        this.rssi = rssi;
    }

    /**
     * Instancia un nuevo Node.
     *
     * @param mac el mac
     */
    public Node(String mac) {
        super(mac);
    }

    /**
     * Instancia un nuevo Node.
     */
    public Node() {
    }

    /**
     * Permite obtener rssi.
     *
     * @return el rssi
     */
    public Float getRssi() {
        return rssi;
    }

    /**
     * Permite establecer rssi.
     *
     * @param rssi el rssi
     */
    public void setRssi(Float rssi) {
        this.rssi = rssi;
    }

    /**
     * Clone node.
     *
     * @return el node
     */
    @Override
    public Node clone() {
        return new Node(getMac(), rssi);
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
        Node node = (Node) o;
        return Objects.equals(rssi, node.rssi);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rssi);
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
            "rssi:" + rssi +
            '}';
    }

}
