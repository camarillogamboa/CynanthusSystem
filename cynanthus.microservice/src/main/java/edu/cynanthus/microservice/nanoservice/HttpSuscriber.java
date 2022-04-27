package edu.cynanthus.microservice.nanoservice;

import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.net.http.client.ContextSuscriber;
import edu.cynanthus.common.net.http.client.HttpClientContext;
import edu.cynanthus.common.net.http.client.PendingRequest;
import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.common.net.http.packet.Request;
import edu.cynanthus.common.net.http.packet.Response;
import edu.cynanthus.common.security.Encryption;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.property.ObservableProperty;

import java.lang.reflect.Field;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * El tipo Http suscriber.
 *
 * @param <T> el parámetro de tipo
 */
@ContextSuscriber(method = RequestMethod.GET)
public abstract class HttpSuscriber<T> extends ProcessNanoService {

    /**
     * El Http client context.
     */
    private final HttpClientContext httpClientContext;
    /**
     * El Request qeue.
     */
    private final RequestQeue<T> requestQeue;

    /**
     * El Body handler.
     */
    private final HttpResponse.BodyHandler<T> bodyHandler;
    /**
     * El Headers.
     */
    private final Map<String, String[]> headers;

    /**
     * El Request method.
     */
    private final RequestMethod requestMethod;

    /**
     * Instancia un nuevo Http suscriber.
     *
     * @param id               el id
     * @param context          el context
     * @param requestQeue      el request qeue
     * @param bodyHandler      el body handler
     * @param fieldAliasFinder el field alias finder
     */
    public HttpSuscriber(
        String id,
        Context context,
        RequestQeue<T> requestQeue,
        HttpResponse.BodyHandler<T> bodyHandler,
        Function<Field, String> fieldAliasFinder
    ) {
        super(id, context);
        this.requestQeue = Objects.requireNonNull(requestQeue);
        this.bodyHandler = Objects.requireNonNull(bodyHandler);

        Objects.requireNonNull(fieldAliasFinder);

        ObservableProperty<String> serverName = getProperty("serverName");
        ObservableProperty<Integer> serverPort = getProperty("serverPort").asObservableIntegerProperty();

        Class<?> clazz = getClass();

        ContextSuscriber note = clazz.getAnnotation(ContextSuscriber.class);

        String serverContext = note.context();
        requestMethod = note.method();

        this.httpClientContext = HttpClientContext.create(
            serverName.getValue(),
            serverPort.getValue(),
            serverContext,
            fieldAliasFinder
        );

        serverName.addAsObserver(httpClientContext::setServerName);
        serverPort.addAsObserver(httpClientContext::setServerPort);

        this.headers = new TreeMap<>(String::compareTo);
        String[] credentials = context.getUserManagement().getMainUser().getCredentials();

        try {
            credentials[1] = Encryption.decrypt(credentials[1]);
        } catch (GeneralSecurityException e) {
            logger.log(Level.SEVERE, "Error de seguridad.", e);
        }

        headers.put("Credentials", credentials);
    }

    /**
     * Do request.
     *
     * @param requestFactory   el request factory
     * @param responseConsumer el response consumer
     */
    protected final void doRequest(
        Function<Map<String, String[]>, Request<?>> requestFactory,
        Consumer<Response<T>> responseConsumer
    ) {
        Request<?> request = requestFactory.apply(headers);
        requestQeue.queueRequest(new PendingRequest<>(httpClientContext, request, bodyHandler, responseConsumer));
    }

    /**
     * Do request.
     *
     * @param data             el data
     * @param responseConsumer el response consumer
     */
    protected final void doRequest(Object data, Consumer<Response<T>> responseConsumer) {
        doRequest(headers -> Request.create(requestMethod, headers, data), responseConsumer);
    }

}
