package edu.cynanthus.stris;

import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.domain.config.StrisConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoService;
import edu.cynanthus.microservice.nanoservice.NanoServices;
import edu.cynanthus.microservice.nanoservice.TcpServer;

import java.util.Map;
import java.util.TreeMap;

/**
 * El tipo Stris micro service.
 */
public class StrisMicroService extends MicroService {

    /**
     * Instancia un nuevo Stris micro service.
     *
     * @param args el args
     */
    public StrisMicroService(String... args) {
        super("Cynanthus Stris MicroService", StrisConfig.class, AgentUser.DEFAUL_AGENT_USER, args);
    }

    /**
     * Load nano service nano service.
     *
     * @param context el context
     * @return el nano service
     */
    @Override
    public NanoService loadNanoService(Context context) {
        Map<String, ConnectableControlNode> controlNodes = new TreeMap<>(String::compareTo);
        return new NanoServices(
            "strisNanoservices",
            new TcpServer("tcpServer", context, new StrisTcpHandler(controlNodes)),
            new StrisServer("webServer", context, controlNodes)
        );
    }

}
