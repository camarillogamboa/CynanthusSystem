package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

class SessionBasedBeanService<T extends Bean, S extends BeanService<T>>
    extends SessionBasedCrudService<T, S> implements BeanService<T> {

    SessionBasedBeanService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
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
