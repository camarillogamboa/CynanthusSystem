package edu.cynanthus.common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * El tipo Collection util.
 */
public class CollectionUtil {

    /**
     * Instancia un nuevo Collection util.
     */
    private CollectionUtil() {}

    /**
     * Transfer.
     *
     * @param <T>        el parámetro de tipo
     * @param <R>        el parámetro de tipo
     * @param iterable   el iterable
     * @param collection el collection
     * @param function   el function
     */
    public static <T, R> void transfer(Iterable<T> iterable, Collection<R> collection, Function<T, R> function) {
        iterable.forEach(e -> collection.add(function.apply(e)));
    }

    /**
     * Transfer.
     *
     * @param <T>        el parámetro de tipo
     * @param iterable   el iterable
     * @param collection el collection
     */
    public static <T> void transfer(Iterable<T> iterable, Collection<T> collection) {
        transfer(iterable, collection, Function.identity());
    }

    /**
     * As list list.
     *
     * @param <T>      el parámetro de tipo
     * @param <R>      el parámetro de tipo
     * @param iterable el iterable
     * @param function el function
     * @return el list
     */
    public static <T, R> List<R> asList(Iterable<T> iterable, Function<T, R> function) {
        List<R> list = new LinkedList<>();
        transfer(iterable, list, function);
        return list;
    }

    /**
     * As list list.
     *
     * @param <T>      el parámetro de tipo
     * @param iterable el iterable
     * @return el list
     */
    public static <T> List<T> asList(Iterable<T> iterable) {
        return asList(iterable, Function.identity());
    }

    /**
     * To string string.
     *
     * @param iterable el iterable
     * @return el string
     */
    public static String toString(Iterable<?> iterable) {
        return "[" + SSV.toSSVFormat(iterable, ",") + "]";
    }

    /**
     * Safe for each.
     *
     * @param <T>      el parámetro de tipo
     * @param iterable el iterable
     * @param action   el action
     */
    public static <T> void safeForEach(Iterable<T> iterable, Consumer<? super T> action) {
        if (iterable != null) iterable.forEach(action);
    }

}
