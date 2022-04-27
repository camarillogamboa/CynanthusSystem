package edu.cynanthus.bean;

/**
 * El tipo Value validation.
 */
public final class ValueValidation {

    /**
     * Instancia un nuevo Value validation.
     */
    private ValueValidation() {}

    /**
     * Is valid boolean.
     *
     * @param string el string
     * @return el boolean
     */
    public static boolean isValid(String string) {
        return string == null || !string.isBlank() && !string.isEmpty();
    }

}
