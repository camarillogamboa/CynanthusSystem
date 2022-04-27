package edu.cynanthus.stris;

import edu.cynanthus.common.net.tcp.TcpExchange;
import edu.cynanthus.common.net.tcp.TcpHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * El tipo Stris tcp handler.
 */
public class StrisTcpHandler implements TcpHandler {

    /**
     * El Control nodes.
     */
    private final Map<String, ConnectableControlNode> controlNodes;

    /**
     * Instancia un nuevo Stris tcp handler.
     *
     * @param controlNodes el control nodes
     */
    public StrisTcpHandler(Map<String, ConnectableControlNode> controlNodes) {
        this.controlNodes = Objects.requireNonNull(controlNodes);
    }

    /**
     * Handle.
     *
     * @param tcpExchange el tcp exchange
     * @throws IOException el io exception
     */
    @Override
    public void handle(TcpExchange tcpExchange) throws IOException {
        String mac = tcpExchange.readUTF();
        synchronized (controlNodes) {
            ConnectableControlNode connectableControlNode = controlNodes.get(mac);
            if (connectableControlNode != null) connectableControlNode.setTcpExchange(tcpExchange);
            else {
                connectableControlNode = new ConnectableControlNode(mac, tcpExchange);
                controlNodes.put(mac, connectableControlNode);
            }
            connectableControlNode.updateLastConnection();
        }
    }

}
