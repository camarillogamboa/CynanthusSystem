package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.http.RequestMethod;

import java.lang.annotation.*;

/**
 * La interface Context suscriber.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ContextSuscriber {

    /**
     * Context string.
     *
     * @return el string
     */
    String context() default "/";

    /**
     * Method request method.
     *
     * @return el request method
     */
    RequestMethod method();

}
