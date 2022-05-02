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

    LazyRequest POST(String path, HttpRequest.BodyPublisher bodyPublisher);

    LazyRequest POST(String path, Supplier<? extends InputStream> inputStreamSupplier);

    LazyRequest POST(String path, String body);

    LazyRequest GET(String path);

    LazyRequest PUT(String path, HttpRequest.BodyPublisher bodyPublisher);

    LazyRequest PUT(String path, Supplier<? extends InputStream> inputStreamSupplier);

    LazyRequest PUT(String path, String body);

    LazyRequest DELETE(String path);

    <T> HttpResponse<T> doRequest(HttpResponse.BodyHandler<T> bodyHandler) throws IOException, InterruptedException;

    HttpResponse<InputStream> doRequestAndGetInputStream() throws IOException, InterruptedException;

    HttpResponse<String> doRequestAndGetString() throws IOException, InterruptedException;

    LazyRequest clone();

}
