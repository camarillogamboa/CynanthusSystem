package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ConnectionPoint;

import java.net.http.HttpClient;
import java.util.Objects;

public class RequestingClient extends HttpRequester {

    private final ConnectionPoint connectionPoint;

    public RequestingClient(HttpClient httpClient, ConnectionPoint connectionPoint) {
        super(httpClient);
        this.connectionPoint = Objects.requireNonNull(connectionPoint);
    }

    public RequestingClient(ConnectionPoint connectionPoint) {
        this(HttpClient.newHttpClient(), connectionPoint);
    }

    @Override
    public LazyRequest lazyRequest() {
        return new LazyClientRequest(httpClient, connectionPoint);
    }

}
