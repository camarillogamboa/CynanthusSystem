package edu.cynanthus.domain;

import edu.cynanthus.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * El tipo General node.
 *
 * @param <T> el parámetro de tipo
 */
public class GeneralNode<T extends RuntimeNode> implements Bean {

    /**
     * El Node info.
     */
    @NotNull(message = "#{NotNull.generalNode.nodeInfo}")
    @Valid
    private NodeInfo nodeInfo;

    /**
     * El Runtime node.
     */
    @NotNull(message = "#{NotNull.generalNode.runtimeNode}")
    @Valid
    private T runtimeNode;

    /**
     * Instancia un nuevo General node.
     *
     * @param nodeInfo    el node info
     * @param runtimeNode el runtime node
     */
    public GeneralNode(NodeInfo nodeInfo, T runtimeNode) {
        this.nodeInfo = nodeInfo;
        this.runtimeNode = runtimeNode;
    }

    /**
     * Instancia un nuevo General node.
     */
    public GeneralNode() {
    }

    /**
     * Permite obtener node info.
     *
     * @return el node info
     */
    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    /**
     * Permite establecer node info.
     *
     * @param nodeInfo el node info
     */
    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    /**
     * Permite obtener runtime node.
     *
     * @return el runtime node
     */
    public T getRuntimeNode() {
        return runtimeNode;
    }

    /**
     * Permite establecer runtime node.
     *
     * @param runtimeNode el runtime node
     */
    public void setRuntimeNode(T runtimeNode) {
        this.runtimeNode = runtimeNode;
    }

    /**
     * Clone general node.
     *
     * @return el general node
     */
    @Override
    public GeneralNode<T> clone() {
        return new GeneralNode<>(nodeInfo, runtimeNode);
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
        GeneralNode<?> that = (GeneralNode<?>) o;
        return Objects.equals(nodeInfo, that.nodeInfo) && Objects.equals(runtimeNode, that.runtimeNode);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(nodeInfo, runtimeNode);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "nodeInfo:" + nodeInfo +
            ",runtimeNode:" + runtimeNode +
            '}';
    }

}
