package edu.cynanthus.common.net.tcp;

import edu.cynanthus.common.resource.DataIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * El tipo Tcp exchange.
 */
public class TcpExchange extends DataIO {

    /**
     * El Socket.
     */
    private final Socket socket;

    /**
     * Instancia un nuevo Tcp exchange.
     *
     * @param socket el socket
     * @throws IOException el io exception
     */
    public TcpExchange(Socket socket) throws IOException {
        super(socket.getInputStream(), socket.getOutputStream());
        this.socket = Objects.requireNonNull(socket);
    }

    /**
     * Permite obtener inet addres.
     *
     * @return el inet addres
     */
    public InetAddress getInetAddres() {
        return socket.getInetAddress();
    }

    /**
     * Close.
     *
     * @throws IOException el io exception
     */
    @Override
    public void close() throws IOException {
        super.close();
        socket.close();
    }

}
