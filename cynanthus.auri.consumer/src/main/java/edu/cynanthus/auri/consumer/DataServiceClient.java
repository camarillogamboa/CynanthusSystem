package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.DataService;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.common.resource.StreamUtil;

import java.lang.reflect.Type;

abstract class DataServiceClient<T> extends AuriApiClient implements DataService<T> {

    protected final String resourcePath;
    private final Type dataType;

    DataServiceClient(ClientInfo clientInfo, String resourcePath, Type dataType) {
        super(clientInfo);
        this.resourcePath = resourcePath;
        this.dataType = dataType;
    }

    @Override
    public T create(T data) {
        checkNotNull(data);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.POST(
                resourcePath,
                () -> StreamUtil.asInputStream(JsonProvider.toJson(data))
            ),
            dataType
        );
    }

    @Override
    public T read(T data) {
        checkNotNull(data);
        String path = resourcePath + "/" + getId(data);
        return consumeApi(webServiceConsumer -> webServiceConsumer.GET(path), dataType);
    }

    @Override
    public T update(T data) {
        checkNotNull(data);
        return consumeApi(
            webServiceConsumer -> webServiceConsumer.PUT(
                resourcePath,
                () -> StreamUtil.asInputStream(JsonProvider.toJson(data))
            ),
            dataType
        );
    }

    @Override
    public T delete(T data) {
        checkNotNull(data);
        String path = resourcePath + "/" + getId(data);
        return consumeApi(webServiceConsumer -> webServiceConsumer.DELETE(path), dataType);
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
