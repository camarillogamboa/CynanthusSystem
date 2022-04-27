package edu.cynanthus.common.net.tcp;

/**
 * La interface Tcp status.
 */
public interface TcpStatus {

    /**
     * La constante UNEXPECTED_DATA.
     */
    int UNEXPECTED_DATA = -2;
    /**
     * La constante COMMUNICATION_ERROR.
     */
    int COMMUNICATION_ERROR = -1;
    /**
     * La constante CONTINUE.
     */
    int CONTINUE = 0;
    /**
     * La constante OK.
     */
    int OK = 1;

}
