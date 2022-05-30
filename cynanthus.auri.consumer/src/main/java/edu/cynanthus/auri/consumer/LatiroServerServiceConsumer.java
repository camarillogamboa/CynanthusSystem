package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;

import java.lang.reflect.Type;
import java.util.List;

/**
 * El tipo Latiro server service consumer.
 */
class LatiroServerServiceConsumer
    extends TreeServerServiceConsumer<LatiroConfig, SensingNode> implements LatiroServerService {

    /**
     * La constante GENARAL_NODE_LIST_TYPE.
     */
    private static final Type GENARAL_NODE_LIST_TYPE = new TypeToken<List<GeneralNode<SensingNode>>>() {}.getType();

    /**
     * Instancia un nuevo Latiro server service consumer.
     *
     * @param lazyRequest el lazy request
     */
    LatiroServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, LATIRO_SERVER_SERVICE_PATH, LatiroConfig.class, GENARAL_NODE_LIST_TYPE);
    }

}
