package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.net.http.HttpStatusCode;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

class SessionBasedService<S extends AuriService> implements AuriService {

    private final AuriServiceConsumer<S> auriServiceConsumer;
    private final SessionStarter sessionStarter;
    private final Supplier<Map<String, String>> headersSupplier;

    SessionBasedService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        this.auriServiceConsumer = auriServiceConsumer;
        this.sessionStarter = sessionStarter;
        this.headersSupplier = headersSupplier;
    }

    <R> R consume(Function<? super S, ? extends R> serviceAction) {
        try {
            return serviceAction.apply(auriServiceConsumer.prepare(headersSupplier.get()));
        } catch (WebServiceException ex) {
            ErrorMessage<String> errorMessage = ex.getErrorMessage();
            if (errorMessage.getCode() == HttpStatusCode.UNAUTHORIZED) {
                sessionStarter.login();
                return consume(serviceAction);
            } else throw ex;
        }
    }

}
