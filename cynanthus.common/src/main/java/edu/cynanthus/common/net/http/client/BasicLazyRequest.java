package edu.cynanthus.common.net.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BasicLazyRequest implements LazyRequest {

    final HttpClient httpClient;

    Consumer<HttpRequest.Builder> builderConsumer;

    BasicLazyRequest(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.builderConsumer = builder -> {};
    }

    @Override
    public LazyRequest building(Consumer<HttpRequest.Builder> builderConsumer) {
        this.builderConsumer = this.builderConsumer.andThen(builderConsumer);
        return this;
    }

    @Override
    public <T> HttpResponse<T> doRequest(
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builderConsumer.accept(builder);
        return httpClient.send(builder.build(), bodyHandler);
    }

    @Override
    public LazyRequest addHeader(String name, String value) {
        return building(builder -> builder.header(name, value));
    }

    @Override
    public LazyRequest addHeaders(Map<String, String> headers) {
        return building(builder -> headers.forEach(builder::header));
    }

    @Override
    public LazyRequest POST(String path, HttpRequest.BodyPublisher bodyPublisher) {
        return building(builder -> builder.uri(URI.create(path)).POST(bodyPublisher));
    }

    @Override
    public LazyRequest POST(String path, Supplier<? extends InputStream> inputStreamSupplier) {
        return POST(path, HttpRequest.BodyPublishers.ofInputStream(inputStreamSupplier));
    }

    @Override
    public LazyRequest POST(String path, String body) {
        return POST(path, HttpRequest.BodyPublishers.ofString(body));
    }

    @Override
    public LazyRequest GET(String path) {
        return building(builder -> builder.uri(URI.create(path)).GET());
    }

    @Override
    public LazyRequest PUT(String path, HttpRequest.BodyPublisher bodyPublisher) {
        return building(builder -> builder.uri(URI.create(path)).PUT(bodyPublisher));
    }

    @Override
    public LazyRequest PUT(String path, Supplier<? extends InputStream> inputStreamSupplier) {
        return PUT(path, HttpRequest.BodyPublishers.ofInputStream(inputStreamSupplier));
    }

    @Override
    public LazyRequest PUT(String path, String body) {
        return PUT(path, HttpRequest.BodyPublishers.ofString(body));
    }

    @Override
    public LazyRequest DELETE(String path) {
        return building(builder -> builder.uri(URI.create(path)).DELETE());
    }

    @Override
    public HttpResponse<InputStream> doRequestAndGetInputStream() throws IOException, InterruptedException {
        return doRequest(HttpResponse.BodyHandlers.ofInputStream());
    }

    @Override
    public HttpResponse<String> doRequestAndGetString() throws IOException, InterruptedException {
        return doRequest(HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public LazyRequest clone() {
        BasicLazyRequest basicLazyRequest = new BasicLazyRequest(httpClient);
        basicLazyRequest.builderConsumer = builderConsumer;
        return basicLazyRequest;
    }

}
