package edu.cynanthus.control;

import edu.cynanthus.common.net.tcp.TcpExchange;
import edu.cynanthus.domain.ControlCode;
import edu.cynanthus.domain.ControlOption;
import edu.cynanthus.domain.Node;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.ProcessNanoService;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.net.ConnectException;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;

/**
 * El tipo Connector.
 */
final class Connector extends ProcessNanoService {

    /**
     * El Transmitter.
     */
    private final Transmitter transmitter;
    /**
     * El Node.
     */
    private final Node node;
    /**
     * El Server name.
     */
    private final ReadOnlyProperty<String> serverName;
    /**
     * El Server port.
     */
    private final ReadOnlyProperty<Integer> serverPort;

    /**
     * Instancia un nuevo Connector.
     *
     * @param id          el id
     * @param context     el context
     * @param transmitter el transmitter
     * @param node        el node
     */
    public Connector(String id, Context context, Transmitter transmitter, Node node) {
        super(id, context);

        this.transmitter = Objects.requireNonNull(transmitter);
        this.node = Objects.requireNonNull(node);

        this.serverName = getProperty("serverName");
        this.serverPort = getProperty("serverPort").asReadOnlyIntegerProperty();
    }

    /**
     * Process loop.
     *
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void processLoop() throws InterruptedException {
        logger.info("Conectandose al servidor [" + serverName + ":" + serverPort + "]...");

        try (TcpExchange exchange = new TcpExchange(new Socket(serverName.getValue(), serverPort.getValue()))) {
            logger.info("Conexión exitosa!");
            exchange.writeUTF(node.getMac());

            while (true) {
                int option = exchange.readByte();
                switch (ControlOption.values()[option]) {
                    case EXEC_INSTRUCTION_OPTION:
                        int lenght = exchange.readByte();
                        int[] instruction = new int[lenght];

                        for (int i = 0; i < lenght; i++) {
                            int code = exchange.readByte();
                            instruction[i] = ControlCode.values()[code].getCode();
                        }
                        transmitter.emit(instruction);
                    default:

                }
            }
        } catch (ConnectException ex) {
            logger.info("Intento fallido de conexión, esperando para volver a intentarlo");
            Thread.sleep(2000);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error de entrada y salida de datos");
            ex.printStackTrace();
        }
    }

}
