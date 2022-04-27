package edu.cynanthus.sordidus;

import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.domain.SampleSet;
import edu.cynanthus.domain.config.SordidusConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoService;
import edu.cynanthus.microservice.nanoservice.NanoServices;

import java.util.LinkedList;

/**
 * El tipo Sordidus micro service.
 */
public class SordidusMicroService extends MicroService {

    /**
     * Instancia un nuevo Sordidus micro service.
     *
     * @param args el args
     */
    public SordidusMicroService(String... args) {
        super("Cynanthus Sordidus MicroService", SordidusConfig.class, AgentUser.DEFAUL_AGENT_USER, args);
    }

    /**
     * Load nano service nano service.
     *
     * @param context el context
     * @return el nano service
     */
    @Override
    public NanoService loadNanoService(Context context) {
        LinkedList<SampleSet> sampleSetBuffer = new LinkedList<>();
        return new NanoServices(
            "sordidusNanoServices",
            new SordidusServer("webServer", context, sampleSetBuffer),
            new DataWriter("dataWriter", context, sampleSetBuffer)
        );
    }

}
