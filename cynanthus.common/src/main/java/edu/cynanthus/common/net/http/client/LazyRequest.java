package edu.cynanthus.common.net.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface LazyRequest {

    LazyRequest building(Consumer<HttpRequest.Builder> builderConsumer);

    LazyRequest addHeaders(Map<String, String> headers);

    LazyRequest addHeader(String name, String value);

    LazyRequest POST(String uri, HttpRequest.BodyPublisher bodyPublisher);

    LazyRequest POST(String uri, Supplier<? extends InputStream> inputStreamSupplier);

    LazyRequest POST(String uri, String body);

    LazyRequest GET(String uri);

    LazyRequest PUT(String uri, HttpRequest.BodyPublisher bodyPublisher);

    LazyRequest PUT(String uri, Supplier<? extends InputStream> inputStreamSupplier);

    LazyRequest PUT(String uri, String body);

    LazyRequest DELETE(String uri);

    <T> HttpResponse<T> doRequest(HttpResponse.BodyHandler<T> bodyHandler) throws IOException, InterruptedException;

    HttpResponse<InputStream> doRequestAndGetInputStream() throws IOException, InterruptedException;

    HttpResponse<String> doRequestAndGetString() throws IOException, InterruptedException;

    LazyRequest clone();

}
