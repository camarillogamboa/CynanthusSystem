package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.ServerInfo;

import java.util.List;

public class ControlNodesViewContent extends TreeServerViewContent<ControlNode> {

    public ControlNodesViewContent(
        ServerInfo serverInfo,
        Boolean available,
        List<GeneralNode<ControlNode>> generalNodes
    ) {
        super(serverInfo, available, generalNodes);
    }

}
