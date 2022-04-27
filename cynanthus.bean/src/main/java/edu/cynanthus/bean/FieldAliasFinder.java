package edu.cynanthus.bean;


import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * El tipo Field alias finder.
 */
public final class FieldAliasFinder implements Function<Field, String> {

    /**
     * La constante INSTANCE.
     */
    public static final FieldAliasFinder INSTANCE = new FieldAliasFinder();

    /**
     * Instancia un nuevo Field alias finder.
     */
    private FieldAliasFinder() {}

    /**
     * Apply string.
     *
     * @param field el field
     * @return el string
     */
    @Override
    public String apply(Field field) {
        JProperty jProperty = field.getAnnotation(JProperty.class);
        if (jProperty != null) {
            String alias = jProperty.alias();
            return alias.isEmpty() || alias.isBlank() ? field.getName() : alias;
        }
        return null;
    }

}
