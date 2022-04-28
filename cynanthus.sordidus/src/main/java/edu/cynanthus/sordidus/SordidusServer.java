package edu.cynanthus.sordidus;

import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.common.net.http.HttpException;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.security.SystemRole;
import edu.cynanthus.domain.SampleSet;
import edu.cynanthus.domain.config.SordidusConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.CynanthusServer;
import edu.cynanthus.microservice.net.http.server.engine.RequestHandler;
import edu.cynanthus.microservice.net.http.server.engine.ServerPath;

import java.util.List;
import java.util.Objects;

/**
 * El tipo Sordidus server.
 */
@ServerPath(path = "/cynanthus/sordidus")
public final class SordidusServer extends CynanthusServer<SordidusConfig> {

    /**
     * El Sample set buffer.
     */
    private final List<SampleSet> sampleSetBuffer;

    /**
     * Instancia un nuevo Sordidus server.
     *
     * @param id              el id
     * @param context         el context
     * @param sampleSetBuffer el sample set buffer
     */
    public SordidusServer(String id, Context context, List<SampleSet> sampleSetBuffer) {
        super(id, context);
        this.sampleSetBuffer = Objects.requireNonNull(sampleSetBuffer);
    }

    /**
     * Put sample set string.
     *
     * @param sampleSet el sample set
     * @return el string
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/sampleset", method = RequestMethod.POST, roles = SystemRole.ROLE_AGENT)
    public String putSampleSet(SampleSet sampleSet) throws HttpException {
        if (sampleSet != null && BeanValidation.fullyValidate(sampleSet)) {
            System.out.println(sampleSet);
            synchronized (sampleSetBuffer) {
                sampleSetBuffer.add(sampleSet);
            }
        } else throw new HttpException(HttpStatus.BAD_REQUEST);
        return "ok";
    }

}
