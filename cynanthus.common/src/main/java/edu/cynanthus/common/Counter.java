package edu.cynanthus.common;

import java.util.Iterator;

/**
 * El tipo Counter.
 */
public final class Counter implements Iterator<Integer> {

    /**
     * El Limit.
     */
    private final int limit;
    /**
     * El Index.
     */
    private int index;

    /**
     * Instancia un nuevo Counter.
     *
     * @param limit el limit
     */
    public Counter(int limit) {
        this.limit = limit;
        this.index = 0;
    }

    /**
     * Has next boolean.
     *
     * @return el boolean
     */
    @Override
    public boolean hasNext() {
        return index < limit;
    }

    /**
     * Next integer.
     *
     * @return el integer
     */
    @Override
    public Integer next() {
        return index++;
    }

}
