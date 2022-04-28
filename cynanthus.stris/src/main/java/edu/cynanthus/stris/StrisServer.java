package edu.cynanthus.stris;

import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.common.net.http.HttpException;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.net.tcp.TcpExchange;
import edu.cynanthus.common.security.SystemRole;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.ControlOption;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.config.StrisConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.CynanthusServer;
import edu.cynanthus.microservice.net.http.server.engine.RequestHandler;
import edu.cynanthus.microservice.net.http.server.engine.ServerPath;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * El tipo Stris server.
 */
@ServerPath(path = "/cynanthus/stris")
public class StrisServer extends CynanthusServer<StrisConfig> {

    /**
     * El Control nodes.
     */
    private final Map<String, ConnectableControlNode> controlNodes;

    /**
     * Instancia un nuevo Stris server.
     *
     * @param id           el id
     * @param context      el context
     * @param controlNodes el control nodes
     */
    public StrisServer(String id, Context context, Map<String, ConnectableControlNode> controlNodes) {
        super(id, context);
        this.controlNodes = Objects.requireNonNull(controlNodes);
    }

    /**
     * Perform indication string.
     *
     * @param indication el indication
     * @return el string
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/indication", method = RequestMethod.POST, roles = SystemRole.ROLE_AGENT)
    public String performIndication(Indication indication) throws HttpException {
        if (indication != null && BeanValidation.validateRequired(indication)) {
            ConnectableControlNode controlNode = controlNodes.get(indication.getMac());
            if (controlNode != null) {

                TcpExchange tcpExchange = controlNode.getTcpExchange();

                try {
                    tcpExchange.writeByte(ControlOption.EXEC_INSTRUCTION_OPTION.ordinal());

                    int[] instruction = indication.getInstruction();
                    tcpExchange.writeByte(instruction.length);

                    for (int code : instruction) tcpExchange.writeByte(code);
                } catch (IOException ex) {
                    throw new HttpException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Comunicación con control \"" + controlNode.getMac() + "\" interrumpida",
                        ex
                    );
                }
            } else throw new HttpException(HttpStatus.NOT_FOUND);
        } else throw new HttpException(HttpStatus.BAD_REQUEST);
        return "ok";
    }

    /**
     * Get control nodes control node [ ].
     *
     * @param selector el selector
     * @return el control node [ ]
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/node", method = RequestMethod.GET, roles = SystemRole.ROLE_AGENT)
    public ControlNode[] getControlNodes(String selector) throws HttpException {
        if (selector.isEmpty() || selector.isBlank() || selector.equals("*"))
            return controlNodes.values().stream().map(ControlNode::clone).toArray(ControlNode[]::new);
        else {
            ControlNode controlNode = controlNodes.get(selector);
            if (controlNode != null) return new ControlNode[]{controlNode.clone()};
            else throw new HttpException(HttpStatus.NOT_FOUND);
        }
    }

}
