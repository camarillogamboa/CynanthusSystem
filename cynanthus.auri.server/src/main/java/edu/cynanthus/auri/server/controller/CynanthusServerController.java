package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.WrappedCynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CynanthusServerController<T extends Config> extends WrappedCynanthusServerService<T> {

    CynanthusServerController(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
    }

    @Override
    @GetMapping("/config/{id:\\d+}")
    @ResponseBody
    public T getConfigOf(ServerInfo serverInfo) {
        return super.getConfigOf(serverInfo);
    }

    @GetMapping("/config/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public T getConfigOfByName(ServerInfo serverInfo) {
        return getConfigOf(serverInfo);
    }

    @Override
    @ResponseBody
    public String updateConfigOf(ServerInfo serverInfo, Config config) {
        return super.updateConfigOf(serverInfo, config);
    }

    @Override
    @ResponseBody
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    @Override
    @ResponseBody
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    @Override
    @ResponseBody
    public Boolean isAvailable(ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

}
