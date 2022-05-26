package edu.cynanthus.domain;

/**
 * La enumeración Server type.
 */
public enum ServerType {
    /**
     * Storage server type.
     */
    STORAGE("Servidor de almacenamiento"),

    /**
     * Stream data server type.
     */
    STREAM_DATA("Servidor de flujo de datos"),
    /**
     * Control server type.
     */
    CONTROL("Servidor de control");

    private final String alias;

    ServerType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

}
