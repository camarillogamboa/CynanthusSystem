package edu.cynanthus.domain;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Required;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * El tipo Sample.
 */
public class Sample implements Bean {

    /**
     * El Node.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sample.node}")
    @Valid
    @JProperty
    private Node node;

    /**
     * El Environment.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sample.environment}")
    @Valid
    @JProperty
    private SensedEnvironment environment;

    /**
     * Instancia un nuevo Sample.
     *
     * @param node        el node
     * @param environment el environment
     */
    public Sample(Node node, SensedEnvironment environment) {
        this.node = node;
        this.environment = environment;
    }

    /**
     * Instancia un nuevo Sample.
     */
    public Sample() {
    }

    /**
     * Permite obtener node.
     *
     * @return el node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Permite establecer node.
     *
     * @param node el node
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Permite obtener environment.
     *
     * @return el environment
     */
    public SensedEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Permite establecer environment.
     *
     * @param environment el environment
     */
    public void setEnvironment(SensedEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Clone sample.
     *
     * @return el sample
     */
    @Override
    public Sample clone() {
        return new Sample(
            node != null ? node.clone() : null,
            environment != null ? environment.clone() : null
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
        Sample sample = (Sample) o;
        return Objects.equals(node, sample.node) && Objects.equals(environment, sample.environment);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(node, environment);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "node:" + node +
            ",environment:" + environment +
            '}';
    }

}
