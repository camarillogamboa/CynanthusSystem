package edu.cynanthus.common.json;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * El tipo Interface adapter.
 *
 * @param <T> el parámetro de tipo
 */
public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    /**
     * La constante CLASSNAME.
     */
    private static final String CLASSNAME = "CLASSNAME";
    /**
     * La constante DATA.
     */
    private static final String DATA = "DATA";

    /**
     * Deserialize t.
     *
     * @param jsonElement                el json element
     * @param type                       el type
     * @param jsonDeserializationContext el json deserialization context
     * @return el t
     * @throws JsonParseException el json parse exception
     */
    public T deserialize(
        JsonElement jsonElement,
        Type type,
        JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        Class<?> clazz = getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), clazz);
    }

    /**
     * Serialize json element.
     *
     * @param jsonElement              el json element
     * @param type                     el type
     * @param jsonSerializationContext el json serialization context
     * @return el json element
     */
    public JsonElement serialize(T jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }

    /**
     * Permite obtener object class.
     *
     * @param className el class name
     * @return el object class
     */
    public Class<?> getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

}
