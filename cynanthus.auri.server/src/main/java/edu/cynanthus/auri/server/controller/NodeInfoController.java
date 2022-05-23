package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(NodeInfoService.NODE_INFO_SERVICE_PATH)
public class NodeInfoController extends BeanController<NodeInfo> implements NodeInfoService {

    private final NodeInfoService nodeInfoService;

    @Autowired
    public NodeInfoController(
        @Qualifier(value = "transactionalNodeInfoService") NodeInfoService nodeInfoService
    ) {
        super(nodeInfoService);
        this.nodeInfoService = nodeInfoService;
    }

    @GetMapping("/{mac:" + Patterns.MAC + "}")
    public NodeInfo readByMac(NodeInfo bean) {
        return super.read(bean);
    }

    @Override
    @GetMapping("/of/{idServerInfo:\\d+}")
    public List<? extends NodeInfo> readAllByIdServerInfo(@PathVariable Integer idServerInfo) {
        return nodeInfoService.readAllByIdServerInfo(idServerInfo);
    }

}
