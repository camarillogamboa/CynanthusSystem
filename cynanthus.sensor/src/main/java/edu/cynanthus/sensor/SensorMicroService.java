package edu.cynanthus.sensor;

import edu.cynanthus.common.net.Net;
import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.domain.Node;
import edu.cynanthus.domain.config.SensorConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoService;
import edu.cynanthus.microservice.nanoservice.NanoServices;
import edu.cynanthus.microservice.nanoservice.Requester;

import java.util.function.Supplier;

/**
 * El tipo Sensor micro service.
 */
public abstract class SensorMicroService extends MicroService {

    /**
     * Instancia un nuevo Sensor micro service.
     *
     * @param args el args
     */
    public SensorMicroService(String... args) {
        super("Cynanthus Sensor MicroService", SensorConfig.class, AgentUser.DEFAUL_AGENT_USER, args);
    }

    /**
     * Load nano service nano service.
     *
     * @param context                 el context
     * @param parameterSourceSupplier el parameter source supplier
     * @return el nano service
     */
    public NanoService loadNanoService(
        Context context,
        Supplier<ParameterSource> parameterSourceSupplier
    ) {
        return loadNanoService(context, parameterSourceSupplier, 1);
    }

    /**
     * Load nano service nano service.
     *
     * @param context                 el context
     * @param parameterSourceSupplier el parameter source supplier
     * @param instances               el instances
     * @return el nano service
     */
    NanoService loadNanoService(
        Context context,
        Supplier<ParameterSource> parameterSourceSupplier,
        int instances
    ) {
        RequestQeue<String> requestQeue = new RequestQeue<>();

        NanoServices nanoServices = new NanoServices(
            "sensorNanoServices",
            new Requester("requester", context, requestQeue)
        );

        for (int i = 0; i < instances; i++) {
            nanoServices.addNanoService(
                new SampleSender(
                    "sender",
                    context,
                    parameterSourceSupplier.get(),
                    requestQeue,
                    new Node(Net.getRamdonMac())
                )
            );
        }

        return nanoServices;
    }

}
