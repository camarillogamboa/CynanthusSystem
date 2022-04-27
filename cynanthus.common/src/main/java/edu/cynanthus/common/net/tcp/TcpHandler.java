package edu.cynanthus.common.net.tcp;

import edu.cynanthus.common.resource.CloseableHandler;

import java.io.IOException;

/**
 * La interface Tcp handler.
 */
@FunctionalInterface
public interface TcpHandler extends CloseableHandler<TcpExchange> {

    /**
     * Handle.
     *
     * @param tcpExchange el tcp exchange
     * @throws IOException el io exception
     */
    @Override
    void handle(TcpExchange tcpExchange) throws IOException;

}
