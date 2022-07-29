package edu.cynanthus.domain;

import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * El tipo Sensing node.
 */
public class SensingNode extends Sample implements RuntimeNode {

    /**
     * El Available.
     */
    @NotNull(groups = Required.class, message = "#{NotNull.sensingNode.available}")
    @JProperty
    private Boolean available;

    /**
     * El Last connection.
     */
    @NotNull(message = "#{NotNull.sensingNode.lastConnection}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sensingNode.lastConnection}")
    @JProperty
    private Long lastConnection;

    /**
     * Instancia un nuevo Sensing node.
     *
     * @param node           el node
     * @param environment    el environment
     * @param available      el available
     * @param lastConnection el last connection
     */
    public SensingNode(Node node, SensedEnvironment environment, Boolean available, Long lastConnection) {
        super(node, environment);
        this.available = available;
        this.lastConnection = lastConnection;
    }

    /**
     * Instancia un nuevo Sensing node.
     */
    public SensingNode() {
    }

    @Override
    public String getMac() {
        return getNode() != null ? getNode().getMac() : null;
    }

    /**
     * Is available boolean.
     *
     * @return el boolean
     */
    public Boolean isAvailable() {
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
     * Clone sensing node.
     *
     * @return el sensing node
     */
    @Override
    public SensingNode clone() {
        Node node = getNode();
        SensedEnvironment sensedEnvironment = getEnvironment();
        return new SensingNode(
            node != null ? node.clone() : null,
            sensedEnvironment != null ? sensedEnvironment.clone() : null,
            available,
            lastConnection
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
        if (!super.equals(o)) return false;
        SensingNode that = (SensingNode) o;
        return Objects.equals(available, that.available) &&
            Objects.equals(lastConnection, that.lastConnection);
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
