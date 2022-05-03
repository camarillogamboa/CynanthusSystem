package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cynanthus/auri/server")
public class ServerInfoController extends BeanController<ServerInfo> implements ServerInfoService {

    @Autowired
    public ServerInfoController(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public ServerInfo readByName(ServerInfo bean) {
        return read(bean);
    }

    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public ServerInfo deleteByName(ServerInfo bean) {
        return delete(bean);
    }

}
