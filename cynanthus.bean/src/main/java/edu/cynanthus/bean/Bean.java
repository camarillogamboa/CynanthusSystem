package edu.cynanthus.bean;

import java.io.Serializable;

/**
 * La interface de Bean.
 */
public interface Bean extends Serializable {

    /**
     * Clone bean.
     *
     * @return el bean
     */
    Bean clone();

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    boolean equals(Object o);

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    int hashCode();

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    String toString();

}
