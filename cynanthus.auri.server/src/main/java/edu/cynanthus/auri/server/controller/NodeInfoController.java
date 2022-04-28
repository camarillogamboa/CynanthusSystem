package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * El tipo Node info controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/node/info")
public class NodeInfoController extends BeanController<NodeInfo> implements NodeInfoService {

    /**
     * El Node info service.
     */
    private final NodeInfoService nodeInfoService;

    /**
     * Instancia un nuevo Node info controller.
     *
     * @param nodeInfoService el node info service
     */
    @Autowired
    public NodeInfoController(
        @Qualifier(value = "transactionalNodeInfoService") NodeInfoService nodeInfoService
    ) {
        super(nodeInfoService);
        this.nodeInfoService = nodeInfoService;
    }

    /**
     * Read by mac node info.
     *
     * @param bean el bean
     * @return el node info
     */
    @GetMapping("/{mac:" + Patterns.MAC + "}")
    @ResponseBody
    public NodeInfo readByMac(NodeInfo bean) {
        return super.read(bean);
    }

    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    @Override
    @GetMapping("/of/{idServerInfo:\\d+}")
    @ResponseBody
    public List<? extends NodeInfo> readAllByIdServerInfo(@PathVariable Integer idServerInfo) {
        return nodeInfoService.readAllByIdServerInfo(idServerInfo);
    }

}
