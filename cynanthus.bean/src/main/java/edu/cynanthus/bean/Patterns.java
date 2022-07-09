package edu.cynanthus.bean;

/**
 * Define algunos patrones ReGex de uso común
 *
 * @author L.G. Camarillo
 */
public interface Patterns {

    /**
     * Patrón utilizado para analizar la sintaxis de una dirección física (mac).
     * EL patrón solo evalúa como válidas las direcciones mac que se encuentran en la conocida notación de
     * 6 pares separados por 2 puntos, admite mayúsculas y minúsculas.
     */
    String MAC = "[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}";

    /**
     * Patrón utilizado para analizar una cadena candidata a identificador aplicando las reglas de un
     * identificador java.
     */
    String IDENTIFIER = "[a-zA-Z_$][a-zA-Z\\d_$]*";

    /**
     * Patrón utilizado en cadenas que representen algún nombre propio, admite solo caracteres alfabéticos
     * y espacios.
     */
    String NAME = "[a-zA-Z_$][a-zA-Z\\s\\d_$]*";

    /**
     * Patrón utilizado para reconocer secuencias de números de cualquier longitud pero
     * que sus valores se encuentren entre el 0 y el 3.
     */
    String CODE_VECTOR = "[0-3]*";

}
