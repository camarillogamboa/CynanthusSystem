package edu.cynanthus.microservice.nanoservice;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.net.http.server.engine.RequestHandler;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * El tipo Cynanthus server.
 *
 * @param <T> el parámetro de tipo
 */
public class CynanthusServer<T extends Config> extends ConfigurationServer<T> {

    /**
     * Instancia un nuevo Cynanthus server.
     *
     * @param id                   el id
     * @param context              el context
     * @param configObjectSupplier el config object supplier
     * @param fieldAliasFinder     el field alias finder
     */
    public CynanthusServer(
        String id,
        Context context,
        Supplier<T> configObjectSupplier,
        Function<Field, String> fieldAliasFinder
    ) {
        super(id, context, configObjectSupplier, fieldAliasFinder);

        Map<Integer, Supplier<InputStream>> errorMessageProvider = httpErrorManager.getErrorMessageProvider();

        errorMessageProvider.put(HttpStatus.BAD_REQUEST, StreamUtil.toImputStreamSupplier(
            "Petición no válida. Datos erróneos o incompletos."
        ));
        errorMessageProvider.put(HttpStatus.UNAUTHORIZED, StreamUtil.toImputStreamSupplier(
            "Credenciales de usuario no válidas o ausentes."
        ));
        errorMessageProvider.put(HttpStatus.FORBIDDEN, StreamUtil.toImputStreamSupplier(
            "Usuario no autorizado para acceder al recurso."
        ));
        errorMessageProvider.put(HttpStatus.METHOD_NOT_ALLOWED, StreamUtil.toImputStreamSupplier(
            "Método de petición no disponible para el contexto actual."
        ));
        errorMessageProvider.put(HttpStatus.INTERNAL_SERVER_ERROR, StreamUtil.toImputStreamSupplier(
            "Error interno del servidor."
        ));
        errorMessageProvider.put(HttpStatus.NOT_IMPLEMENTED, StreamUtil.toImputStreamSupplier(
            "Método de petición no soportado por el servidor."
        ));
        errorMessageProvider.put(HttpStatus.NOT_FOUND, StreamUtil.toImputStreamSupplier(
            "Recurso no encontrado."
        ));
    }

    /**
     * Instancia un nuevo Cynanthus server.
     *
     * @param id                   el id
     * @param context              el context
     * @param configObjectSupplier el config object supplier
     */
    public CynanthusServer(String id, Context context, Supplier<T> configObjectSupplier) {
        this(id, context, configObjectSupplier, FieldAliasFinder.INSTANCE);
    }

    /**
     * Is available string.
     *
     * @param string el string
     * @return el string
     */
    @RequestHandler(context = "/", method = RequestMethod.GET)
    public String isAvailable(String string) {
        return getClass().getSimpleName() + " disponible.";
    }

}
