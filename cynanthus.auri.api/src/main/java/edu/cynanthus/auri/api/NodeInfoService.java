package edu.cynanthus.auri.api;

import edu.cynanthus.domain.NodeInfo;

import java.util.List;

/**
 * La interface Node info service.
 */
public interface NodeInfoService extends BeanService<NodeInfo> {

    /**
     * La constante NODE_INFO_SERVICE_PATH.
     */
    String NODE_INFO_SERVICE_PATH = AURI_PATH + "/node";


    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo);

}
