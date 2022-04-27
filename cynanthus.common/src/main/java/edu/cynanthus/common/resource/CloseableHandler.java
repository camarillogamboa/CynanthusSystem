package edu.cynanthus.common.resource;

import java.io.Closeable;
import java.io.IOException;

/**
 * La interface Closeable handler.
 *
 * @param <T> el parámetro de tipo
 */
@FunctionalInterface
public interface CloseableHandler<T extends Closeable> {

    /**
     * Handle.
     *
     * @param closeable el closeable
     * @throws IOException el io exception
     */
    void handle(T closeable) throws IOException;

}
