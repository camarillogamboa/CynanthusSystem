package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.HostAddress;

import java.net.http.HttpClient;
import java.util.Objects;

public class RequestingClient extends HttpRequester {

    private final HostAddress hostAddress;

    public RequestingClient(HttpClient httpClient, HostAddress hostAddress) {
        super(httpClient);
        this.hostAddress = Objects.requireNonNull(hostAddress);
    }

    public RequestingClient(HostAddress hostAddress) {
        this(HttpClient.newHttpClient(), hostAddress);
    }

    @Override
    public LazyRequest lazyRequest() {
        return new LazyClientRequest(httpClient, hostAddress);
    }

}
