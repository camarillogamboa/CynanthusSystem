package edu.cynanthus.bean;

/**
 * La interface Patterns.
 */
public interface Patterns {

    /**
     * La constante MAC.
     */
    String MAC = "[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}";

    /**
     * La constante IDENTIFIER.
     */
    String IDENTIFIER = "[a-zA-Z_$][a-zA-Z\\d_$]*";

    /**
     * La constante NAME.
     */
    String NAME = "[a-zA-Z_$][a-zA-Z\\s\\d_$]*";

    String CODE_VECTOR = "[0-5]*";

}
