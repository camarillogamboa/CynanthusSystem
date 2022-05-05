package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;

/**
 * La interface Tree server service.
 *
 * @param <T> el parámetro de tipo
 * @param <N> el parámetro de tipo
 */
public interface TreeServerService<T extends Config, N extends RuntimeNode> extends CynanthusServerService<T> {

    /**
     * Permite obtener general nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el general nodes of
     */
    List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector);

}
