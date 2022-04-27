package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.json.JsonProvider;

import java.io.*;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * El tipo Json body handler.
 *
 * @param <T> el parámetro de tipo
 */
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {

    /**
     * El Type.
     */
    private final Type type;

    /**
     * Instancia un nuevo Json body handler.
     *
     * @param type el type
     */
    public JsonBodyHandler(Type type) {
        this.type = Objects.requireNonNull(type);
    }

    /**
     * Apply http response . body subscriber.
     *
     * @param responseInfo el response info
     * @return el http response . body subscriber
     */
    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {

        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
            upstream,
            stream -> {
                try (Reader reader = new InputStreamReader(stream)) {
                    return JsonProvider.fromJson(reader, type);
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            }
        );
    }

}
