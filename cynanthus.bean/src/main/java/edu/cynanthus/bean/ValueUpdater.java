package edu.cynanthus.bean;

import java.util.function.Consumer;

/**
 * El actualizador de valores es una clase de utilida que facilita la elección en
 * la comparación de valores que van a ser establecidos en un campo.
 *
 * @author L.G. Camarillo
 */
public final class ValueUpdater {

    /**
     * No está permitida la creación de instancias de esta clase
     */
    private ValueUpdater() {}

    /**
     * Toma la decisión al retornar un valor entre un valor antiguo y un valor nuevo tomando en cuenta
     * las siguientes condiciones:
     * <ul>
     *     <li>Si se establece la actualización efectiva en verdadero se retorna el nuevo valor
     *     sin importar si es null.</li>
     *     <li>Si se establece la actualización efectiva en falso se procede a evaluar:
     *     <ul>
     *         <li>Si el nuevo valor es distinto de null, se retorna el nuevo valor.</li>
     *         <li>Si el nuevo valor es null, se retorna el valor antiguo.</li>
     *     </ul>
     *     </li>
     * </ul>
     *
     * @param <T>             el tipo del valor con el que se aplicará la función
     * @param oldValue        el valor antiguo
     * @param newValue        el valor nuevo
     * @param effectiveUpdate la bandera booleana de actualización efectiva
     * @return el valor elegido
     */
    public static <T> T update(T oldValue, T newValue, boolean effectiveUpdate) {
        return effectiveUpdate ? newValue : newValue != null ? newValue : oldValue;
    }

    /**
     * Toma la decisión al retornar un valor entre un valor antiguo y un valor nuevo.
     *
     * @param <T>      el tipo del valor con el que se aplicará la función
     * @param oldValue el valor antiguo
     * @param newValue el valor nuevo
     * @return el valor elegido
     * @see edu.cynanthus.bean.ValueUpdater#update(Object, Object, boolean)
     */
    public static <T> T update(T oldValue, T newValue) {
        return update(oldValue, newValue, false);
    }

    /**
     * Actualiza un valor uitlizando un consumidor siempre y cuando el valor proporcionado
     * sea distinto de null.
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
