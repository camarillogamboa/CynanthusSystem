package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

abstract class BeanServiceConsumer<T extends Bean> extends DataServiceConsumer<T> implements BeanService<T> {

    private final Type listType;

    BeanServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type dataType, Type listType) {
        super(lazyRequest, resourcePath, dataType);
        this.listType = Objects.requireNonNull(listType);
    }

    @Override
    public List<? extends T> read() {
        return consumeService(lazyRequest -> lazyRequest.GET(resourcePath), listType);
    }

    @Override
    public List<? extends T> delete() {
        return consumeService(lazyRequest -> lazyRequest.GET(resourcePath), listType);
    }

}
