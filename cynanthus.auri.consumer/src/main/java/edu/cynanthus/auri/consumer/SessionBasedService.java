package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.util.function.Consumer;
import java.util.function.Function;

class SessionBasedService<S extends AuriService> implements AuriService {

    private final AuriServiceConsumer<S> auriServiceConsumer;
    private final SessionStarter sessionStarter;
    private final Consumer<LazyRequest> lazyRequestConsumer;

    SessionBasedService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        this.auriServiceConsumer = auriServiceConsumer;
        this.sessionStarter = sessionStarter;
        this.lazyRequestConsumer = lazyRequestConsumer;
    }

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
