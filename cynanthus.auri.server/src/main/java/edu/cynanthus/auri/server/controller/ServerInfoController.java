package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * El tipo Server info controller.
 */
@RestController
@RequestMapping("/cynanthus/auri/server")
public class ServerInfoController extends BeanController<ServerInfo> implements ServerInfoService {

    /**
     * Instancia un nuevo Server info controller.
     *
     * @param serverInfoService el server info service
     */
    @Autowired
    public ServerInfoController(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService);
    }

    /**
     * Read by name server info.
     *
     * @param bean el bean
     * @return el server info
     */
    @GetMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public ServerInfo readByName(ServerInfo bean) {
        return read(bean);
    }

    /**
     * Delete by name server info.
     *
     * @param bean el bean
     * @return el server info
     */
    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public ServerInfo deleteByName(ServerInfo bean) {
        return delete(bean);
    }

}
