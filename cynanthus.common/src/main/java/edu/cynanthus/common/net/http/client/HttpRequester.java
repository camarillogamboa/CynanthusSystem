package edu.cynanthus.common.net.http.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.function.Consumer;

public class HttpRequester {

    final HttpClient httpClient;

    public HttpRequester(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient);
    }

    public HttpRequester() {
        this(HttpClient.newHttpClient());
    }

    public LazyRequest lazyRequest() {
        return new BasicLazyRequest(httpClient);
    }

    public <T> HttpResponse<T> doRequest(
        Consumer<HttpRequest.Builder> builderConsumer,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return lazyRequest().building(builderConsumer).doRequest(bodyHandler);
    }

}
