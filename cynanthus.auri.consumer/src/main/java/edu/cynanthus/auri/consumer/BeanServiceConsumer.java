package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.common.net.ClientInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

abstract class BeanServiceConsumer<T extends Bean> extends DataServiceConsumer<T> implements BeanService<T> {

    private final Type listType;

    BeanServiceConsumer(ClientInfo clientInfo, String resourcePath, Type dataType, Type listType) {
        super(clientInfo, resourcePath, dataType);
        this.listType = Objects.requireNonNull(listType);
    }

    @Override
    public List<? extends T> read() {
        return consumeApi(webConsumer -> webConsumer.GET(resourcePath), listType);
    }

    @Override
    public List<? extends T> delete() {
        return consumeApi(webConsumer -> webConsumer.GET(resourcePath), listType);
    }

}
