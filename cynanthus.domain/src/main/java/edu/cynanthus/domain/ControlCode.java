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

    public static ControlCode valueOf(int code) {
        for (ControlCode controlCode : values()) if (controlCode.getCode() == code) return controlCode;
        throw new IllegalArgumentException("Código \"" + code + "\" no existe entre los valores disponibles de ControlCode");
    }

    public static String toStringVector(int[] vector) {
        StringBuilder string = new StringBuilder();

        for (int code : vector) {
            if (code >= 5) {
                ControlCode controlCode = valueOf(code);
                code = controlCode.ordinal();
            }
            string.append(code);
        }
        return string.toString();
    }

    public static int[] toSimpleCodeArray(String vector) {
        int[] array = new int[vector.length()];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt("" + vector.charAt(i));
        return array;
    }

    public static int[] toRealCodeArray(String vector) {
        int[] array = new int[vector.length()];
        for (int i = 0; i < array.length; i++)
            array[i] = values()[Integer.parseInt("" + vector.charAt(i))].getCode();
        return array;
    }

    public static ControlCode[] toControlCodeArray(String vector) {
        ControlCode[] controlCodes = new ControlCode[vector.length()];
        for (int i = 0; i < vector.length(); i++)
            controlCodes[i] = values()[Integer.parseInt("" + vector.charAt(i))];

        return controlCodes;
    }

}
