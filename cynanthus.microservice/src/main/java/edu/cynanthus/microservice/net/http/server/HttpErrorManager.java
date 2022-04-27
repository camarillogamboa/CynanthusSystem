package edu.cynanthus.microservice.net.http.server;

import com.sun.net.httpserver.HttpHandler;
import edu.cynanthus.common.net.http.HttpException;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.resource.StreamUtil;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * El tipo Http error manager.
 */
public class HttpErrorManager {

    /**
     * El Context logger.
     */
    private final Logger contextLogger;
    /**
     * El Error message provider.
     */
    private final Map<Integer, Supplier<InputStream>> errorMessageProvider;

    /**
     * Instancia un nuevo Http error manager.
     *
     * @param contextLogger el context logger
     */
    public HttpErrorManager(Logger contextLogger) {
        this.contextLogger = contextLogger;
        this.errorMessageProvider = new TreeMap<>(Integer::compareTo);
    }

    /**
     * Permite obtener error message provider.
     *
     * @return el error message provider
     */
    public Map<Integer, Supplier<InputStream>> getErrorMessageProvider() {
        return errorMessageProvider;
    }

    /**
     * Wrap handler http handler.
     *
     * @param httpHandler el http handler
     * @return el http handler
     */
    public HttpHandler wrapHandler(HttpHandler httpHandler) {
        Objects.requireNonNull(httpHandler);
        return exchange -> {
            try {
                httpHandler.handle(exchange);
            } catch (Exception ex) {
                ex.printStackTrace();
                int code;
                if (ex instanceof HttpException) code = ((HttpException) ex).getCode();
                else {
                    code = HttpStatus.INTERNAL_SERVER_ERROR;
                    contextLogger.log(Level.SEVERE, "Error interno del servidor.", ex);
                }

                InputStream responseStream;

                Supplier<InputStream> messageSupplier = errorMessageProvider.get(code);
                if (messageSupplier != null)
                    responseStream = messageSupplier.get();
                else try (
                    Writer writer = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(writer)
                ) {
                    ex.printStackTrace(printWriter);
                    responseStream = StreamUtil.asInputStream(writer.toString());
                }

                exchange.sendResponseHeaders(code, 0);
                try (OutputStream out = exchange.getResponseBody()) {
                    responseStream.transferTo(out);
                }
            }
        };
    }

}
