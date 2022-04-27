package edu.cynanthus.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * La interface J property.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JProperty {

    /**
     * Alias string.
     *
     * @return el string
     */
    String alias() default "";

    /**
     * Default value string.
     *
     * @return el string
     */
    String defaultValue() default "";

    /**
     * Info string.
     *
     * @return el string
     */
    String info() default "";

    /**
     * Permite obtener method.
     *
     * @return el method
     */
    String getMethod() default "";

    /**
     * Permite establecer method.
     *
     * @return el method
     */
    String setMethod() default "";

}
