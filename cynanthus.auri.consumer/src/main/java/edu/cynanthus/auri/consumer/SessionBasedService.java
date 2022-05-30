package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * El tipo Session based service.
 *
 * @param <S> el parámetro de tipo
 */
class SessionBasedService<S extends AuriService> implements AuriService {

    /**
     * El Auri service consumer.
     */
    private final AuriServiceConsumer<S> auriServiceConsumer;
    /**
     * El Session starter.
     */
    private final SessionStarter sessionStarter;
    /**
     * El Lazy request consumer.
     */
    private final Consumer<LazyRequest> lazyRequestConsumer;

    /**
     * Instancia un nuevo Session based service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        this.auriServiceConsumer = auriServiceConsumer;
        this.sessionStarter = sessionStarter;
        this.lazyRequestConsumer = lazyRequestConsumer;
    }

    /**
     * Consume r.
     *
     * @param <R>           el parámetro de tipo
     * @param serviceAction el service action
     * @return el r
     */
    <R> R consume(Function<? super S, ? extends R> serviceAction) {
        try {
            return serviceAction.apply(auriServiceConsumer.prepare(lazyRequestConsumer));
        } catch (WebServiceException ex) {
            ErrorMessage<String> errorMessage = ex.getErrorMessage();
            if (errorMessage.getCode() == HttpStatusCode.UNAUTHORIZED) {
                sessionStarter.login();
                return consume(serviceAction);
            } else throw ex;
        }
    }

}
