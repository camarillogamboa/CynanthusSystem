package edu.cynanthus.control;

import edu.cynanthus.common.net.Net;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.domain.Node;
import edu.cynanthus.domain.config.ControlConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoService;
import edu.cynanthus.microservice.nanoservice.NanoServices;

import java.util.function.Supplier;

/**
 * El tipo Control micro service.
 */
public abstract class ControlMicroService extends MicroService {

    /**
     * Instancia un nuevo Control micro service.
     *
     * @param args el args
     */
    public ControlMicroService(String... args) {
        super("Cynanthus Control MicroService", ControlConfig.class, AgentUser.DEFAUL_AGENT_USER, args);
    }

    /**
     * Load nano service nano service.
     *
     * @param context             el context
     * @param transmitterSupplier el transmitter supplier
     * @return el nano service
     */
    public NanoService loadNanoService(Context context, Supplier<Transmitter> transmitterSupplier) {
        return loadNanoService(context, transmitterSupplier, 1);
    }

    /**
     * Load nano service nano service.
     *
     * @param context             el context
     * @param transmitterSupplier el transmitter supplier
     * @param instances           el instances
     * @return el nano service
     */
    NanoService loadNanoService(Context context, Supplier<Transmitter> transmitterSupplier, int instances) {

        NanoServices nanoServices = new NanoServices("controlNanoServices");

        for (int i = 0; i < instances; i++)
            nanoServices.addNanoService(
                new Connector("connector", context, transmitterSupplier.get(), new Node(Net.getRamdonMac()))
            );

        return nanoServices;
    }

}
