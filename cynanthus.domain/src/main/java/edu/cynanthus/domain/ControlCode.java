package edu.cynanthus.domain;

/**
 * La enumeración Control code.
 */
public enum ControlCode {

    /**
     * Code zero control code.
     */
    CODE_ZERO(9600),
    /**
     * Code one control code.
     */
    CODE_ONE(4600),
    /**
     * Code two control code.
     */
    CODE_TWO(4000),
    /**
     * Code three control code.
     */
    CODE_THREE(400),
    /**
     * Code four control code.
     */
    CODE_FOUR(10);
    /**
     * El Code.
     */
    private final int code;

    /**
     * Instancia un nuevo Control code.
     *
     * @param code el code
     */
    ControlCode(int code) {
        this.code = code;
    }

    /**
     * Permite obtener code.
     *
     * @return el code
     */
    public int getCode() {
        return code;
    }

}
