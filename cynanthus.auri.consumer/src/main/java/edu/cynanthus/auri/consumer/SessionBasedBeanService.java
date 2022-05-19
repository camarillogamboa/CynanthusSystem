package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.List;
import java.util.function.Consumer;

class SessionBasedBeanService<T extends Bean, S extends BeanService<T>>
    extends SessionBasedCrudService<T, S> implements BeanService<T> {

    SessionBasedBeanService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public List<? extends T> read() {
        return consume(BeanService::read);
    }

    @Override
    public List<? extends T> delete() {
        return consume(BeanService::delete);
    }

}
