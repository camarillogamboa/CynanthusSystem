package edu.cynanthus.common;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * El tipo Function iterator.
 *
 * @param <T> el parámetro de tipo
 * @param <R> el parámetro de tipo
 */
public final class FunctionIterator<T, R> implements Iterator<R> {

    /**
     * El Iterator.
     */
    private final Iterator<? extends T> iterator;
    /**
     * El Function.
     */
    private final Function<? super T, ? extends R> function;

    /**
     * Instancia un nuevo Function iterator.
     *
     * @param iterator el iterator
     * @param function el function
     */
    public FunctionIterator(Iterator<? extends T> iterator, Function<? super T, ? extends R> function) {
        this.iterator = Objects.requireNonNull(iterator);
        this.function = Objects.requireNonNull(function);
    }

    /**
     * Instancia un nuevo Function iterator.
     *
     * @param iterable el iterable
     * @param function el function
     */
    public FunctionIterator(Iterable<? extends T> iterable, Function<? super T, ? extends R> function) {
        this(iterable.iterator(), function);
    }

    /**
     * Instancia un nuevo Function iterator.
     *
     * @param array    el array
     * @param function el function
     */
    public FunctionIterator(T[] array, Function<? super T, ? extends R> function) {
        this(new ArrayIterator<>(array), function);
    }

    /**
     * Has next boolean.
     *
     * @return el boolean
     */
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * Next r.
     *
     * @return el r
     */
    @Override
    public R next() {
        return function.apply(iterator.next());
    }

}
