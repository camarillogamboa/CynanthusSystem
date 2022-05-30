package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CrudService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.lang.reflect.Type;

/**
 * El tipo Crud service consumer.
 *
 * @param <T> el parámetro de tipo
 */
abstract class CrudServiceConsumer<T> extends ServiceConsumer implements CrudService<T> {

    /**
     * El Resource path.
     */
    protected final String resourcePath;
    /**
     * El Data type.
     */
    private final Type dataType;

    /**
     * Instancia un nuevo Crud service consumer.
     *
     * @param lazyRequest  el lazy request
     * @param resourcePath el resource path
     * @param dataType     el data type
     */
    CrudServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type dataType) {
        super(lazyRequest);
        this.resourcePath = resourcePath;
        this.dataType = dataType;
    }

    /**
     * Create t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T create(T data) {
        checkNotNull(data);
        return sendAndConsume(
            lazyRequest -> lazyRequest.POST(
                resourcePath,
                () -> JsonProvider.toJsonInputStream(data)
            ),
            dataType
        );
    }

    /**
     * Read t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T read(T data) {
        checkNotNull(data);
        return consume(lazyRequest -> lazyRequest.GET(resourcePath + "/" + getId(data)), dataType);
    }

    /**
     * Update t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T update(T data) {
        checkNotNull(data);
        return sendAndConsume(
            lazyRequest -> lazyRequest.PUT(
                resourcePath,
                () -> JsonProvider.toJsonInputStream(data)
            ),
            dataType
        );
    }

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T delete(T data) {
        checkNotNull(data);
        return consume(lazyRequest -> lazyRequest.DELETE(resourcePath + "/" + getId(data)), dataType);
    }

    /**
     * Check not null.
     *
     * @param bean el bean
     */
    void checkNotNull(T bean) {
        if (bean == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de " + dataType + " para realizar esta acción"
            );
    }

    /**
     * Permite obtener id.
     *
     * @param data el data
     * @return el id
     */
    abstract Object getId(T data);

}
