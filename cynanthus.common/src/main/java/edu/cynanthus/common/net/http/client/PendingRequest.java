package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.http.packet.Request;
import edu.cynanthus.common.net.http.packet.Response;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * El tipo Pending request.
 *
 * @param <T> el parámetro de tipo
 */
public final class PendingRequest<T> {

    /**
     * El Http client context.
     */
    private final HttpClientContext httpClientContext;
    /**
     * El Request.
     */
    private final Request<?> request;

    /**
     * El Body handler.
     */
    private final HttpResponse.BodyHandler<T> bodyHandler;
    /**
     * El Response consumer.
     */
    private final Consumer<Response<T>> responseConsumer;

    /**
     * El Executions.
     */
    private int executions;

    /**
     * Instancia un nuevo Pending request.
     *
     * @param httpClientContext el http client context
     * @param request           el request
     * @param bodyHandler       el body handler
     * @param responseConsumer  el response consumer
     */
    public PendingRequest(
        HttpClientContext httpClientContext,
        Request<?> request,
        HttpResponse.BodyHandler<T> bodyHandler,
        Consumer<Response<T>> responseConsumer
    ) {
        this.httpClientContext = Objects.requireNonNull(httpClientContext);
        this.request = Objects.requireNonNull(request);
        this.bodyHandler = Objects.requireNonNull(bodyHandler);
        this.responseConsumer = Objects.requireNonNull(responseConsumer);
        this.executions = 0;
    }

    /**
     * Permite obtener http client context.
     *
     * @return el http client context
     */
    public HttpClientContext getHttpClientContext() {
        return httpClientContext;
    }

    /**
     * Permite obtener request.
     *
     * @return el request
     */
    public Request<?> getRequest() {
        return request;
    }

    /**
     * Permite obtener response consumer.
     *
     * @return el response consumer
     */
    public Consumer<Response<T>> getResponseConsumer() {
        return responseConsumer;
    }

    /**
     * Permite obtener executions.
     *
     * @return el executions
     */
    public int getExecutions() {
        return executions;
    }

    /**
     * Run request.
     *
     * @throws IOException          el io exception
     * @throws InterruptedException el interrupted exception
     */
    public void runRequest() throws IOException, InterruptedException {
        executions++;
        responseConsumer.accept(httpClientContext.doRequest(request, bodyHandler));
    }

}
