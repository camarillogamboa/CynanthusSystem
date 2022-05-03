package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CrudService;
import edu.cynanthus.auri.api.error.NullPointerServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;

import java.lang.reflect.Type;

abstract class CrudServiceConsumer<T> extends GeneralConsumer implements CrudService<T> {

    protected final String resourcePath;
    private final Type dataType;

    CrudServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type dataType) {
        super(lazyRequest);
        this.resourcePath = resourcePath;
        this.dataType = dataType;
    }

    @Override
    public T create(T data) {
        checkNotNull(data);
        return consume(
            lazyRequest -> lazyRequest.POST(
                resourcePath,
                () -> StreamUtil.asInputStream(JsonProvider.toJson(data))
            ),
            dataType
        );
    }

    @Override
    public T read(T data) {
        checkNotNull(data);
        return consume(lazyRequest -> lazyRequest.GET(resourcePath + "/" + getId(data)), dataType);
    }

    @Override
    public T update(T data) {
        checkNotNull(data);
        return consume(
            lazyRequest -> lazyRequest.PUT(
                resourcePath,
                () -> StreamUtil.asInputStream(JsonProvider.toJson(data))
            ),
            dataType
        );
    }

    @Override
    public T delete(T data) {
        checkNotNull(data);
        return consume(lazyRequest -> lazyRequest.DELETE(resourcePath + "/" + getId(data)), dataType);
    }

    void checkNotNull(T bean) {
        if (bean == null)
            throw new NullPointerServiceException(
                "Se requiere una instancia de " + dataType + " para realizar esta acción"
            );
    }

    abstract Object getId(T data);

}
