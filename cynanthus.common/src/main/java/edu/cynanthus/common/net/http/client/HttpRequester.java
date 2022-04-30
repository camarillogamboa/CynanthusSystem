package edu.cynanthus.common.net.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class HttpRequester {

    private final HttpClient httpClient;

    public HttpRequester(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient);
    }

    public HttpRequester() {
        this(HttpClient.newHttpClient());
    }

    public <T> HttpResponse<T> doRequest(
        String path,
        UnaryOperator<HttpRequest.Builder> operator,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(path));
        builder = operator.apply(builder);
        return httpClient.send(builder.build(), bodyHandler);
    }

    public HttpResponse<InputStream> doRequest(
        String path,
        UnaryOperator<HttpRequest.Builder> operator
    ) throws IOException, InterruptedException {
        return doRequest(path, operator, HttpResponse.BodyHandlers.ofInputStream());
    }

    public <T> HttpResponse<T> GET(
        String path,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return doRequest(path, HttpRequest.Builder::GET, bodyHandler);
    }

    public HttpResponse<InputStream> GET(String path) throws IOException, InterruptedException {
        return GET(path, HttpResponse.BodyHandlers.ofInputStream());
    }

    public <T> HttpResponse<T> POST(
        String path,
        HttpRequest.BodyPublisher bodyPublisher,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return doRequest(path, builder -> builder.POST(bodyPublisher), bodyHandler);
    }

    public HttpResponse<InputStream> POST(
        String path,
        Supplier<? extends InputStream> supplier
    ) throws IOException, InterruptedException {
        return POST(
            path,
            HttpRequest.BodyPublishers.ofInputStream(supplier),
            HttpResponse.BodyHandlers.ofInputStream()
        );
    }

    public <T> HttpResponse<T> PUT(
        String path,
        HttpRequest.BodyPublisher bodyPublisher,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return doRequest(path, builder -> builder.PUT(bodyPublisher), bodyHandler);
    }

    public HttpResponse<InputStream> PUT(
        String path,
        Supplier<? extends InputStream> supplier
    ) throws IOException, InterruptedException {
        return PUT(
            path,
            HttpRequest.BodyPublishers.ofInputStream(supplier),
            HttpResponse.BodyHandlers.ofInputStream()
        );
    }

    public <T> HttpResponse<T> DELETE(
        String path,
        HttpResponse.BodyHandler<T> bodyHandler
    ) throws IOException, InterruptedException {
        return doRequest(path, HttpRequest.Builder::DELETE, bodyHandler);
    }

    public HttpResponse<InputStream> DELETE(String subPath) throws IOException, InterruptedException {
        return DELETE(subPath, HttpResponse.BodyHandlers.ofInputStream());
    }

}
