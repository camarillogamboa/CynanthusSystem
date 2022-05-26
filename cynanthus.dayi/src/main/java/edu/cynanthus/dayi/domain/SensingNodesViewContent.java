package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;

public class SensingNodesViewContent extends TreeServerViewContent<SensingNode> {

    public SensingNodesViewContent(
        ServerInfo serverInfo,
        Boolean available,
        List<GeneralNode<SensingNode>> sensingNodes
    ) {
        super(serverInfo, available, sensingNodes);
    }

}
