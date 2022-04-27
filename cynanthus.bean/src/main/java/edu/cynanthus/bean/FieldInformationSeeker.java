package edu.cynanthus.bean;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * El tipo Field information seeker.
 */
public final class FieldInformationSeeker implements Function<Field, String[]> {

    /**
     * La constante ALIAS.
     */
    public static final int ALIAS = 0;
    /**
     * La constante DEFAULT_VALUE.
     */
    public static final int DEFAULT_VALUE = 1;
    /**
     * La constante INFO.
     */
    public static final int INFO = 2;
    /**
     * La constante GET_METHOD.
     */
    public static final int GET_METHOD = 3;
    /**
     * La constante SET_METHOD.
     */
    public static final int SET_METHOD = 4;

    /**
     * La constante INSTANCE.
     */
    public static final FieldInformationSeeker INSTANCE = new FieldInformationSeeker();

    /**
     * Instancia un nuevo Field information seeker.
     */
    private FieldInformationSeeker() {}

    /**
     * Apply string [ ].
     *
     * @param field el field
     * @return el string [ ]
     */
    @Override
    public String[] apply(Field field) {
        JProperty jProperty = field.getAnnotation(JProperty.class);
        if (jProperty != null) {
            return new String[]{
                jProperty.alias().isEmpty() || jProperty.alias().isBlank() ? field.getName() : jProperty.alias(),
                jProperty.defaultValue(),
                jProperty.info(),
                jProperty.getMethod(),
                jProperty.setMethod()
            };
        }
        return null;
    }

}
