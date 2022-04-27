package edu.cynanthus.common;

import java.util.function.Consumer;

/**
 * La interface Consumable.
 *
 * @param <T> el parámetro de tipo
 */
public interface Consumable<T> {

    /**
     * Contains boolean.
     *
     * @return el boolean
     */
    boolean contains();

    /**
     * Consume.
     *
     * @param consumer el consumer
     */
    void consume(Consumer<? super T> consumer);

}
