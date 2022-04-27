package edu.cynanthus.microservice;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.resource.ResourceException;
import edu.cynanthus.microservice.property.MetaProperty;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * La interface Context.
 */
public interface Context {

    /**
     * Permite obtener name.
     *
     * @return el name
     */
    String getName();

    /**
     * Permite obtener config class.
     *
     * @return el config class
     */
    Class<? extends Config> getConfigClass();

    /**
     * Load properties boolean.
     *
     * @return el boolean
     */
    boolean loadProperties();

    /**
     * Save properties boolean.
     *
     * @return el boolean
     */
    boolean saveProperties();

    /**
     * Permite obtener property.
     *
     * @param key el key
     * @return el property
     */
    MetaProperty getProperty(String key);

    /**
     * Put property.
     *
     * @param key          el key
     * @param metaProperty el meta property
     */
    void putProperty(String key, MetaProperty metaProperty);

    /**
     * Update properties from.
     *
     * @param configObject el config object
     * @param aliasFinder  el alias finder
     */
    void updatePropertiesFrom(Config configObject, Function<Field, String> aliasFinder);

    /**
     * Update properties from.
     *
     * @param configObject el config object
     */
    default void updatePropertiesFrom(Config configObject) {
        updatePropertiesFrom(configObject, FieldAliasFinder.INSTANCE);
    }

    /**
     * Copy properties to.
     *
     * @param configObject el config object
     * @param aliasFinder  el alias finder
     */
    void copyPropertiesTo(Config configObject, Function<Field, String> aliasFinder);

    /**
     * Copy properties to.
     *
     * @param configObject el config object
     */
    default void copyPropertiesTo(Config configObject) {
        copyPropertiesTo(configObject, FieldAliasFinder.INSTANCE);
    }

    /**
     * Load resource input stream.
     *
     * @param name el name
     * @return el input stream
     * @throws ResourceException el resource exception
     */
    InputStream loadResource(String name) throws ResourceException;

    /**
     * Save resource.
     *
     * @param name        el name
     * @param inputStream el input stream
     * @throws ResourceException el resource exception
     */
    void saveResource(String name, InputStream inputStream) throws ResourceException;

    /**
     * Load object t.
     *
     * @param <T>  el parámetro de tipo
     * @param name el name
     * @param type el type
     * @return el t
     */
    <T> T loadObject(String name, Type type);

    /**
     * Save object boolean.
     *
     * @param name   el name
     * @param object el object
     * @return el boolean
     */
    boolean saveObject(String name, Object object);

    /**
     * Permite obtener user management.
     *
     * @return el user management
     */
    SystemUserManagement getUserManagement();

    /**
     * Permite obtener logger.
     *
     * @param name el name
     * @return el logger
     */
    Logger getLogger(String name);

}
