package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ClientInfo;

import java.net.http.HttpClient;
import java.util.Objects;

public class RequestingClient extends HttpRequester {

    private final ClientInfo clientInfo;

    public RequestingClient(HttpClient httpClient, ClientInfo clientInfo) {
        super(httpClient);
        this.clientInfo = Objects.requireNonNull(clientInfo);
    }

    public RequestingClient(ClientInfo clientInfo) {
        this(HttpClient.newHttpClient(), clientInfo);
    }

    @Override
    public LazyRequest lazyRequest() {
        return new LazyClientRequest(httpClient, clientInfo);
    }

}
