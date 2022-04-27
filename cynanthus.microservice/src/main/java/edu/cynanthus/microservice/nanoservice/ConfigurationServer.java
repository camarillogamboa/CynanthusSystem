package edu.cynanthus.microservice.nanoservice;

import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.net.http.HttpException;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.resource.FileAccesObject;
import edu.cynanthus.common.resource.ResourceException;
import edu.cynanthus.common.security.SystemRole;
import edu.cynanthus.common.security.SystemUser;
import edu.cynanthus.microservice.ArchiveableContext;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.net.http.server.engine.RequestHandler;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;

/**
 * El tipo Configuration server.
 *
 * @param <T> el parámetro de tipo
 */
public class ConfigurationServer<T extends Config> extends WebServer {

    /**
     * El Config object supplier.
     */
    private final Supplier<T> configObjectSupplier;
    /**
     * El Identifier generator.
     */
    private final Function<Field, String> identifierGenerator;

    /**
     * Instancia un nuevo Configuration server.
     *
     * @param id                   el id
     * @param context              el context
     * @param configObjectSupplier el config object supplier
     * @param fieldAliasFinder     el field alias finder
     */
    public ConfigurationServer(
        String id,
        Context context,
        Supplier<T> configObjectSupplier,
        Function<Field, String> fieldAliasFinder
    ) {
        super(id, context);
        this.configObjectSupplier = Objects.requireNonNull(configObjectSupplier);
        this.identifierGenerator = Objects.requireNonNull(fieldAliasFinder);
    }

    /**
     * Instancia un nuevo Configuration server.
     *
     * @param id                   el id
     * @param context              el context
     * @param configObjectSupplier el config object supplier
     */
    public ConfigurationServer(
        String id,
        Context context,
        Supplier<T> configObjectSupplier
    ) {
        this(id, context, configObjectSupplier, FieldAliasFinder.INSTANCE);
    }

    /**
     * Permite obtener config.
     *
     * @param string el string
     * @return el config
     */
    @RequestHandler(context = "/config", method = RequestMethod.GET, roles = SystemRole.ROLE_AGENT)
    public final T getConfig(String string) {
        T configObject = configObjectSupplier.get();
        context.copyPropertiesTo(configObject, identifierGenerator);
        httpSecurityManager.logUserAction(Level.INFO, "Consultó la configuración del programa");
        return configObject;
    }

    /**
     * Update config string.
     *
     * @param configObject el config object
     * @return el string
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/config", method = RequestMethod.PUT, roles = SystemRole.ROLE_AGENT)
    public final String updateConfig(T configObject) throws HttpException {
        System.out.println(configObject);
        if (configObject != null && BeanValidation.isValid(configObject)) {
            context.updatePropertiesFrom(configObject, identifierGenerator);
            httpSecurityManager.logUserAction(Level.INFO, "Cambió la configuración del programa");
            return "ok";
        }
        throw new HttpException(HttpStatus.BAD_REQUEST);
    }

    /**
     * Get log files string [ ].
     *
     * @param string el string
     * @return el string [ ]
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/log/files", method = RequestMethod.GET, roles = SystemRole.ROLE_AGENT)
    public final String[] getLogFiles(String string) throws HttpException {
        ArchiveableContext archiveableContext = (ArchiveableContext) context;
        File logDirectory = archiveableContext.getLogDirectory();
        if (logDirectory.exists())
            return logDirectory.list((dir, name) -> name.endsWith(".log"));
        throw new HttpException(HttpStatus.NOT_FOUND);
    }

    /**
     * Permite obtener log content.
     *
     * @param logFileName el log file name
     * @return el log content
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/log", method = RequestMethod.GET, roles = SystemRole.ROLE_AGENT)
    public final InputStream getLogContent(String logFileName) throws HttpException {
        ArchiveableContext archiveableContext = (ArchiveableContext) context;
        File logDirectory = archiveableContext.getLogDirectory();
        if (logDirectory.exists()) {
            File logFile = new File(logDirectory + "/" + logFileName);
            if (logFile.exists())
                try {
                    return FileAccesObject.INPUT_STREAM_FAO.read(logFile);
                } catch (ResourceException e) {
                    throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e);
                }
        }
        throw new HttpException(HttpStatus.NOT_FOUND);
    }

    /**
     * Update user string.
     *
     * @param systemUser el system user
     * @return el string
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/user", method = RequestMethod.PUT, roles = SystemRole.ROLE_AGENT)
    public final String updateUser(SystemUser systemUser) throws HttpException {
        System.out.println();
        return "ok";
    }

}
