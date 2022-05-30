package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.List;
import java.util.function.Consumer;

/**
 * El tipo Session based bean service.
 *
 * @param <T> el parámetro de tipo
 * @param <S> el parámetro de tipo
 */
class SessionBasedBeanService<T extends Bean, S extends BeanService<T>>
    extends SessionBasedCrudService<T, S> implements BeanService<T> {

    /**
     * Instancia un nuevo Session based bean service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedBeanService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> read() {
        return consume(BeanService::read);
    }

    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> delete() {
        return consume(BeanService::delete);
    }

}
