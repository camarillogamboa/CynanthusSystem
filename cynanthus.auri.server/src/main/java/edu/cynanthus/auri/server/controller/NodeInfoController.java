package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cynanthus/auri/node/info")
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
    @ResponseBody
    public NodeInfo readByMac(NodeInfo bean) {
        return super.read(bean);
    }

    @Override
    @GetMapping("/of/{idServerInfo:\\d+}")
    @ResponseBody
    public List<? extends NodeInfo> readByIdServerInfo(@PathVariable Integer idServerInfo) {
        return nodeInfoService.readByIdServerInfo(idServerInfo);
    }

}
