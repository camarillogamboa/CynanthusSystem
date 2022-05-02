package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.DataService;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;

import java.lang.reflect.Type;

abstract class DataServiceConsumer<T> extends ServiceConsumer implements DataService<T> {

    protected final String resourcePath;
    private final Type dataType;

    DataServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type dataType) {
        super(lazyRequest);
        this.resourcePath = resourcePath;
        this.dataType = dataType;
    }

    @Override
    public T create(T data) {
        checkNotNull(data);
        return consumeService(
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
        return consumeService(lazyRequest -> lazyRequest.GET(resourcePath + "/" + getId(data)), dataType);
    }

    @Override
    public T update(T data) {
        checkNotNull(data);
        return consumeService(
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
        return consumeService(lazyRequest -> lazyRequest.DELETE(resourcePath + "/" + getId(data)), dataType);
    }

    void checkNotNull(T bean) {
        if (bean == null)
            throw new ServiceException(
                "Se requiere una instancia de " + dataType + " para realizar esta acción",
                ExceptionType.NULL_POINTER
            );
    }

    abstract Object getId(T data);

}
