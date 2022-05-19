package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CrudService;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.function.Consumer;

class SessionBasedCrudService<T, S extends CrudService<T>> extends SessionBasedService<S> implements CrudService<T> {

    SessionBasedCrudService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public T create(T data) {
        return consume(service -> service.create(data));
    }

    @Override
    public T read(T data) {
        return consume(service -> service.read(data));
    }

    @Override
    public T update(T data) {
        return consume(service -> service.update(data));
    }

    @Override
    public T delete(T data) {
        return consume(service -> service.delete(data));
    }

}
