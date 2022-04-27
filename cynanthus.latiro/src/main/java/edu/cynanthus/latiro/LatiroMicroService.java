package edu.cynanthus.latiro;

import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.domain.Sample;
import edu.cynanthus.domain.config.LatiroConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoServices;
import edu.cynanthus.microservice.nanoservice.Requester;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * El tipo Latiro micro service.
 */
public class LatiroMicroService extends MicroService {

    /**
     * Instancia un nuevo Latiro micro service.
     *
     * @param args el args
     */
    public LatiroMicroService(String... args) {
        super("Cynanthus Latiro MicroService", LatiroConfig.class, AgentUser.DEFAUL_AGENT_USER, args);
    }

    /**
     * Load nano service nano services.
     *
     * @param context el context
     * @return el nano services
     */
    @Override
    public NanoServices loadNanoService(Context context) {
        List<Sample> sampleBuffer = new LinkedList<>();
        RequestQeue<String> requestQeue = new RequestQeue<>();
        Map<String, StorableSensingNode> storableNodes = new TreeMap<>(String::compareTo);

        return new NanoServices(
            "latiroNanoServices",
            new SensorManager("sensorManager", context, storableNodes),
            new LatiroServer("webServer", context, sampleBuffer, storableNodes),
            new Requester("requester", context, requestQeue),
            new SampleSetSender("sender", context, sampleBuffer, requestQeue)
        );
    }

}
