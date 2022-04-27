package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ClientInfo;

/**
 * La interface Http client info.
 */
public interface HttpClientInfo extends ClientInfo {

    /**
     * Permite obtener server context.
     *
     * @return el server context
     */
    String getServerContext();

    /**
     * Permite establecer server context.
     *
     * @param context el context
     */
    void setServerContext(String context);

    /**
     * Create http client info.
     *
     * @param serverName    el server name
     * @param serverPort    el server port
     * @param serverContext el server context
     * @return el http client info
     */
    static HttpClientInfo create(String serverName, int serverPort, String serverContext) {
        return new HttpClientInfoImpl(serverName, serverPort, serverContext);
    }

}
