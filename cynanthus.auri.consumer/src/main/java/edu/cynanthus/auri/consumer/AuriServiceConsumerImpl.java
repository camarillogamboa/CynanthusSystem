package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.net.http.client.RequestingClient;

import java.net.http.HttpClient;
import java.util.function.Consumer;
import java.util.function.Function;

class AuriServiceConsumerImpl<T extends AuriService> implements AuriServiceConsumer<T> {

    private final RequestingClient requestingClient;
    private final Function<LazyRequest, T> factory;

    AuriServiceConsumerImpl(HttpClient httpClient, HostAddress hostAddress, Function<LazyRequest, T> factory) {
        this.requestingClient = new RequestingClient(httpClient, hostAddress);
        this.factory = factory;
    }

    @Override
    public T prepare() {
        return factory.apply(requestingClient.lazyRequest());
    }

    public T prepare(Consumer<LazyRequest> lazyRequestConsumer) {
        LazyRequest lazyRequest = requestingClient.lazyRequest();
        lazyRequestConsumer.accept(lazyRequest);
        return factory.apply(lazyRequest);
    }

}
