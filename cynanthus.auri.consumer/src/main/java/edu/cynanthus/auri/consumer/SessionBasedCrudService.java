package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CrudService;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.function.Consumer;

/**
 * El tipo Session based crud service.
 *
 * @param <T> el parámetro de tipo
 * @param <S> el parámetro de tipo
 */
class SessionBasedCrudService<T, S extends CrudService<T>> extends SessionBasedService<S> implements CrudService<T> {

    /**
     * Instancia un nuevo Session based crud service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedCrudService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Create t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T create(T data) {
        return consume(service -> service.create(data));
    }

    /**
     * Read t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T read(T data) {
        return consume(service -> service.read(data));
    }

    /**
     * Update t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T update(T data) {
        return consume(service -> service.update(data));
    }

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T delete(T data) {
        return consume(service -> service.delete(data));
    }

}
