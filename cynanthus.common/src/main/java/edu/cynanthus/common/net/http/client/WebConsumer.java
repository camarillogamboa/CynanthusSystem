package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ClientInfo;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class WebConsumer extends HttpRequester {

    protected final ClientInfo clientInfo;

    public WebConsumer(HttpClient httpClient, ClientInfo clientInfo) {
        super(httpClient);
        this.clientInfo = Objects.requireNonNull(clientInfo);
    }

    public WebConsumer(ClientInfo clientInfo) {
        this(HttpClient.newHttpClient(), clientInfo);
    }

    public String buildPath() {
        return "http://" + clientInfo.getServerName() + ":" + clientInfo.getServerPort();
    }

    @Override
    public <T> HttpResponse<T> doRequest(
        String path,
        UnaryOperator<HttpRequest.Builder> operator,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return super.doRequest(buildPath() + path, operator, bodyHandler);
    }

}
