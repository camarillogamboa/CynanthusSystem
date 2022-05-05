package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

class TreeServerController<T extends Config, N extends RuntimeNode>
    extends CynanthusServerController<T> implements TreeServerService<T, N> {

    private final TreeServerService<T, N> treeServerService;

    TreeServerController(TreeServerService<T, N> treeServerService) {
        super(treeServerService);
        this.treeServerService = treeServerService;
    }

    @Override
    @GetMapping("/{id:\\d+}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector) {
        return treeServerService.getGeneralNodesOf(serverInfo, selector);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/node/{selector}")
    @ResponseBody
    public List<GeneralNode<N>> getGeneralNodesOfByName(ServerInfo serverInfo, String selector) {
        return getGeneralNodesOf(serverInfo, selector);
    }

}
