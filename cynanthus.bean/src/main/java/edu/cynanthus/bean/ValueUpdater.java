package edu.cynanthus.bean;

import java.util.function.Consumer;

/**
 * El tipo Value updater.
 */
public class ValueUpdater {

    /**
     * Instancia un nuevo Value updater.
     */
    private ValueUpdater() {}

    /**
     * Update t.
     *
     * @param <T>             el parámetro de tipo
     * @param oldValue        el old value
     * @param newValue        el new value
     * @param effectiveUpdate el effective update
     * @return el t
     */
    public static <T> T update(T oldValue, T newValue, boolean effectiveUpdate) {
        return effectiveUpdate ? newValue : newValue != null ? newValue : oldValue;
    }

    /**
     * Update t.
     *
     * @param <T>      el parámetro de tipo
     * @param oldValue el old value
     * @param newValue el new value
     * @return el t
     */
    public static <T> T update(T oldValue, T newValue) {
        return update(oldValue, newValue, false);
    }

    /**
     * Update if not null.
     *
     * @param <T>    el parámetro de tipo
     * @param value  el value
     * @param setter el setter
     */
    public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null)
            setter.accept(value);
    }

}
