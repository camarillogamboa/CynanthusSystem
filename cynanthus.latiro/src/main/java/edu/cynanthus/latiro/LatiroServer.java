package edu.cynanthus.latiro;

import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.FullyValidate;
import edu.cynanthus.common.SSV;
import edu.cynanthus.common.net.http.HttpException;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.security.SystemRole;
import edu.cynanthus.domain.Node;
import edu.cynanthus.domain.Sample;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.CynanthusServer;
import edu.cynanthus.microservice.net.http.server.engine.RequestHandler;
import edu.cynanthus.microservice.net.http.server.engine.ServerPath;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * El tipo Latiro server.
 */
@ServerPath(path = "/cynanthus/latiro")
public final class LatiroServer extends CynanthusServer<LatiroConfig> {

    /**
     * El Sample buffer.
     */
    private final List<Sample> sampleBuffer;
    /**
     * El Storable nodes.
     */
    private final Map<String, StorableSensingNode> storableNodes;

    /**
     * Instancia un nuevo Latiro server.
     *
     * @param id            el id
     * @param context       el context
     * @param sampleBuffer  el sample buffer
     * @param storableNodes el storable nodes
     */
    public LatiroServer(
        String id,
        Context context,
        List<Sample> sampleBuffer,
        Map<String, StorableSensingNode> storableNodes
    ) {
        super(id, context);
        this.sampleBuffer = Objects.requireNonNull(sampleBuffer);
        this.storableNodes = Objects.requireNonNull(storableNodes);
    }

    /**
     * Put sample string.
     *
     * @param sample el sample
     * @return el string
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/sample", method = RequestMethod.POST, roles = SystemRole.ROLE_AGENT)
    public String putSample(Sample sample) throws HttpException {
        if (sample != null) {
            System.out.println(sample);
            BeanValidation.validateAndThrow(sample, FullyValidate.class);
            synchronized (sampleBuffer) {
                sampleBuffer.add(sample);
            }

            Node sampleNode = sample.getNode();
            StorableSensingNode storableSensingNode = storableNodes.get(sampleNode.getMac());

            if (storableSensingNode != null) storableSensingNode.getNode().setRssi(sampleNode.getRssi());
            else {
                storableSensingNode = new StorableSensingNode(sampleNode);
                storableNodes.put(sampleNode.getMac(), storableSensingNode);
            }

            storableSensingNode.updateLastConnection();
            storableSensingNode.setEnvironment(sample.getEnvironment());

            if (storableSensingNode.contains()) {
                SSV.Builder ssvBuilder = new SSV.Builder(",");
                storableSensingNode.consume(ssvBuilder::append);
                return ssvBuilder.toString();
            }

        } else throw new HttpException(HttpStatusCode.BAD_REQUEST);
        return "ok";
    }

    /**
     * Get sensing nodes sensing node [ ].
     *
     * @param selector el selector
     * @return el sensing node [ ]
     * @throws HttpException el http exception
     */
    @RequestHandler(context = "/node", method = RequestMethod.GET, roles = SystemRole.ROLE_AGENT)
    public SensingNode[] getSensingNodes(String selector) throws HttpException {
        if (selector.isEmpty() || selector.isBlank() || selector.equals("*"))
            return storableNodes.values().stream().map(SensingNode::clone).toArray(SensingNode[]::new);
        else {
            SensingNode sensingNode = storableNodes.get(selector);
            if (sensingNode != null) return new SensingNode[]{sensingNode.clone()};
            else throw new HttpException(HttpStatusCode.NOT_FOUND);
        }
    }

}
