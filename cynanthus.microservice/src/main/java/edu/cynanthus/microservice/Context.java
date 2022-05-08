package edu.cynanthus.microservice;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.resource.ResourceException;
import edu.cynanthus.microservice.property.MetaProperty;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;
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
    boolean loadMetaProperties();

    /**
     * Save properties boolean.
     *
     * @return el boolean
     */
    boolean saveMetaProperties();

    /**
     * Load properties properties.
     *
     * @param name el name
     * @return el properties
     */
    Properties loadProperties(String name);

    /**
     * Save properties boolean.
     *
     * @param properties el properties
     * @param name       el name
     * @return el boolean
     */
    boolean saveProperties(Properties properties, String name);

    /**
     * Permite obtener property.
     *
     * @param key el key
     * @return el property
     */
    MetaProperty getMetaProperty(String key);

    /**
     * Put property.
     *
     * @param key          el key
     * @param metaProperty el meta property
     */
    void putMetaProperty(String key, MetaProperty metaProperty);

    /**
     * Update properties from.
     *
     * @param configObject el config object
     * @param aliasFinder  el alias finder
     * @return el boolean
     */
    boolean updateMetaPropertiesFrom(Config configObject, Function<Field, String> aliasFinder);

    /**
     * Update properties from.
     *
     * @param configObject el config object
     */
    default void updateMetaPropertiesFrom(Config configObject) {
        updateMetaPropertiesFrom(configObject, FieldAliasFinder.INSTANCE);
    }

    /**
     * Copy properties to.
     *
     * @param configObject el config object
     * @param aliasFinder  el alias finder
     */
    void copyMetaPropertiesTo(Config configObject, Function<Field, String> aliasFinder);

    /**
     * Copy properties to.
     *
     * @param configObject el config object
     */
    default void copyMetaPropertiesTo(Config configObject) {
        copyMetaPropertiesTo(configObject, FieldAliasFinder.INSTANCE);
    }

    /**
     * Permite obtener properties as config object.
     *
     * @param <T> el parámetro de tipo
     * @return el properties as config object
     * @throws Exception el exception
     */
    <T extends Config> T getMetaPropertiesAsConfigObject() throws Exception;

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
     * Permite obtener messages.
     *
     * @return el messages
     */
    Map<String, ? extends Map<?, ?>> getMessages();

    /**
     * Permite obtener logger.
     *
     * @param name el name
     * @return el logger
     */
    Logger getLogger(String name);

}
