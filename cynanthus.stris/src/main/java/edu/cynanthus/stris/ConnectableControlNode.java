package edu.cynanthus.stris;

import edu.cynanthus.common.net.tcp.TcpExchange;
import edu.cynanthus.domain.ControlNode;

/**
 * El tipo Connectable control node.
 */
public class ConnectableControlNode extends ControlNode {

    /**
     * El Tcp exchange.
     */
    private TcpExchange tcpExchange;

    /**
     * Instancia un nuevo Connectable control node.
     *
     * @param mac            el mac
     * @param rssi           el rssi
     * @param available      el available
     * @param lastConnection el last connection
     * @param tcpExchange    el tcp exchange
     */
    public ConnectableControlNode(
        String mac,
        Float rssi,
        Boolean available,
        Long lastConnection,
        TcpExchange tcpExchange
    ) {
        super(mac, rssi, available, lastConnection);
        this.tcpExchange = tcpExchange;
    }

    /**
     * Instancia un nuevo Connectable control node.
     *
     * @param mac         el mac
     * @param tcpExchange el tcp exchange
     */
    public ConnectableControlNode(String mac, TcpExchange tcpExchange) {
        super(mac);
        this.tcpExchange = tcpExchange;
    }

    /**
     * Update last connection.
     */
    public void updateLastConnection() {
        setLastConnection(System.currentTimeMillis());
        setAvailable(true);
    }

    /**
     * Permite obtener tcp exchange.
     *
     * @return el tcp exchange
     */
    public TcpExchange getTcpExchange() {
        return tcpExchange;
    }

    /**
     * Permite establecer tcp exchange.
     *
     * @param tcpExchange el tcp exchange
     */
    public void setTcpExchange(TcpExchange tcpExchange) {
        this.tcpExchange = tcpExchange;
    }

}
