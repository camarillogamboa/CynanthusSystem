package edu.cynanthus.common.net.tcp;

import edu.cynanthus.common.resource.CloseableHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * La interface Socket handler.
 */
@FunctionalInterface
public interface SocketHandler extends CloseableHandler<Socket> {

    /**
     * Handle.
     *
     * @param socket el socket
     * @throws IOException el io exception
     */
    @Override
    void handle(Socket socket) throws IOException;

}
