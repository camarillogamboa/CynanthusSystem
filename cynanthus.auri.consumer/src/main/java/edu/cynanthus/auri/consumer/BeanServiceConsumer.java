package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Bean service consumer.
 *
 * @param <T> el parámetro de tipo
 */
abstract class BeanServiceConsumer<T extends Bean> extends CrudServiceConsumer<T> implements BeanService<T> {

    /**
     * El List type.
     */
    private final Type listType;

    /**
     * Instancia un nuevo Bean service consumer.
     *
     * @param lazyRequest  el lazy request
     * @param resourcePath el resource path
     * @param dataType     el data type
     * @param listType     el list type
     */
    BeanServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type dataType, Type listType) {
        super(lazyRequest, resourcePath, dataType);
        this.listType = Objects.requireNonNull(listType);
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> read() {
        return consume(lazyRequest -> lazyRequest.GET(resourcePath), listType);
    }

    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> delete() {
        return consume(lazyRequest -> lazyRequest.GET(resourcePath), listType);
    }

}
