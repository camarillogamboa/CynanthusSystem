package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.net.http.client.RequestingClient;

import java.net.http.HttpClient;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * El tipo Auri service consumer.
 *
 * @param <T> el parámetro de tipo
 */
class AuriServiceConsumerImpl<T extends AuriService> implements AuriServiceConsumer<T> {

    /**
     * El Requesting client.
     */
    private final RequestingClient requestingClient;
    /**
     * El Factory.
     */
    private final Function<LazyRequest, T> factory;

    /**
     * Instancia un nuevo Auri service consumer.
     *
     * @param httpClient  el http client
     * @param hostAddress el host address
     * @param factory     el factory
     */
    AuriServiceConsumerImpl(HttpClient httpClient, HostAddress hostAddress, Function<LazyRequest, T> factory) {
        this.requestingClient = new RequestingClient(httpClient, hostAddress);
        this.factory = factory;
    }

    /**
     * Prepare t.
     *
     * @return el t
     */
    @Override
    public T prepare() {
        return factory.apply(requestingClient.lazyRequest());
    }

    /**
     * Prepare t.
     *
     * @param lazyRequestConsumer el lazy request consumer
     * @return el t
     */
    public T prepare(Consumer<LazyRequest> lazyRequestConsumer) {
        LazyRequest lazyRequest = requestingClient.lazyRequest();
        lazyRequestConsumer.accept(lazyRequest);
        return factory.apply(lazyRequest);
    }

}
