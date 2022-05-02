package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.common.net.ConnectionPoint;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.net.http.client.RequestingClient;

import java.util.Map;
import java.util.function.Function;

class AuriServiceConsumerImpl<T extends AuriService> implements AuriServiceConsumer<T> {

    private final RequestingClient requestingClient;
    private final Function<LazyRequest, T> factory;

    AuriServiceConsumerImpl(ConnectionPoint connectionPoint, Function<LazyRequest, T> factory) {
        requestingClient = new RequestingClient(connectionPoint);
        this.factory = factory;
    }

    @Override
    public T prepare(Map<String, String> headers) {
        return factory.apply(requestingClient.lazyRequest().addHeaders(headers));
    }

    @Override
    public T prepare(String headerName, String headerValue) {
        return factory.apply(requestingClient.lazyRequest().addHeader(headerName, headerValue));
    }

}
