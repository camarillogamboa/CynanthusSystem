package edu.cynanthus.microservice.property;

import java.util.Objects;

/**
 * El tipo Named property.
 *
 * @param <P> el parámetro de tipo
 */
public final class NamedProperty<P extends Property<?>> extends ReadOnlyProperty<String> {

    /**
     * El Property.
     */
    private final P property;

    /**
     * Instancia un nuevo Named property.
     *
     * @param name     el name
     * @param property el property
     */
    NamedProperty(String name, P property) {
        super(name);
        this.property = Objects.requireNonNull(property);
    }

    /**
     * Permite obtener property.
     *
     * @return el property
     */
    public P getProperty() {
        return property;
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
        NamedProperty<?> that = (NamedProperty<?>) o;
        return Objects.equals(property, that.property);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), property);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "[ " + super.toString() + " : " + property.toString() + " ]";
    }

    /**
     * As named property named property.
     *
     * @param <P>      el parámetro de tipo
     * @param name     el name
     * @param property el property
     * @return el named property
     */
    public static <P extends Property<?>> NamedProperty<P> asNamedProperty(String name, P property) {
        return new NamedProperty<>(name, property);
    }

}
