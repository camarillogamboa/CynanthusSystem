package edu.cynanthus.common.net;

/**
 * La interface Client info.
 */
public interface HostAddress {

    /**
     * Permite obtener server name.
     *
     * @return el server name
     */
    String getHostName();

    /**
     * Permite obtener server port.
     *
     * @return el server port
     */
    int getHostPort();

    /**
     * Permite establecer server name.
     *
     * @param serverName el server name
     */
    void setHostName(String serverName);

    /**
     * Permite establecer server port.
     *
     * @param serverPort el server port
     */
    void setHostPort(int serverPort);

}
