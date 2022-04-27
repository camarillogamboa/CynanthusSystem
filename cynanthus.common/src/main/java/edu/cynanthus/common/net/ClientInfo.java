package edu.cynanthus.common.net;

/**
 * La interface Client info.
 */
public interface ClientInfo {

    /**
     * Permite obtener server name.
     *
     * @return el server name
     */
    String getServerName();

    /**
     * Permite obtener server port.
     *
     * @return el server port
     */
    int getServerPort();

    /**
     * Permite establecer server name.
     *
     * @param serverName el server name
     */
    void setServerName(String serverName);

    /**
     * Permite establecer server port.
     *
     * @param serverPort el server port
     */
    void setServerPort(int serverPort);

}
