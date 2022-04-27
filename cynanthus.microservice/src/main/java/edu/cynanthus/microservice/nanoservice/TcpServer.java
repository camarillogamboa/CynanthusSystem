package edu.cynanthus.microservice.nanoservice;

import edu.cynanthus.common.net.tcp.SocketHandler;
import edu.cynanthus.common.net.tcp.TcpExchange;
import edu.cynanthus.common.net.tcp.TcpHandler;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;

/**
 * El tipo Tcp server.
 */
public final class TcpServer extends ContextNanoService {

    /**
     * El Port.
     */
    private final ReadOnlyProperty<Integer> port;
    /**
     * El Server task.
     */
    private final Runnable serverTask;

    /**
     * El Server socket.
     */
    private ServerSocket serverSocket;

    /**
     * Instancia un nuevo Tcp server.
     *
     * @param id            el id
     * @param context       el context
     * @param socketHandler el socket handler
     */
    public TcpServer(String id, Context context, SocketHandler socketHandler) {
        super(id, context);
        Objects.requireNonNull(socketHandler);
        this.port = getProperty("port").asReadOnlyIntegerProperty();
        this.serverTask = () -> {
            while (isWorking()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            socketHandler.handle(clientSocket);
                        } catch (IOException ex) {
                            logger.log(Level.SEVERE, "Error al controlar conexión con el cliente", ex);
                        }
                    }).start();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, "Error en el servidor TCP.", ex);
                }
            }
        };
    }

    /**
     * Instancia un nuevo Tcp server.
     *
     * @param id         el id
     * @param context    el context
     * @param tcpHandler el tcp handler
     */
    public TcpServer(String id, Context context, TcpHandler tcpHandler) {
        this(id, context, (SocketHandler) socket -> tcpHandler.handle(new TcpExchange(socket)));
    }

    /**
     * Start.
     */
    @Override
    public void start() {
        if (!working) {
            try {
                logger.info("Inciando servidor tcp...");

                serverSocket = new ServerSocket(port.getValue());
                working = true;
                new Thread(serverTask).start();
                logger.info("Servidor TCP iniciado en el puerto " + port);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Falló la inicialización del servidor TCP.", ex);
            }
        }
    }

    /**
     * Stop.
     */
    @Override
    public void stop() {
        if (working) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Falló el cierre del servidor TCP.", ex);
            }
            working = false;
        }
    }

}
