package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ServerInfoService.SERVER_INFO_SERVICE_PATH)
public class ServerInfoController extends BeanController<ServerInfo> implements ServerInfoService {

    @Autowired
    public ServerInfoController(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService
    ) {
        super(serverInfoService);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}")
    public ServerInfo readByName(ServerInfo bean) {
        return read(bean);
    }

    @DeleteMapping("/{name:" + Patterns.NAME + "}")
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public ServerInfo deleteByName(ServerInfo bean) {
        return delete(bean);
    }

}
