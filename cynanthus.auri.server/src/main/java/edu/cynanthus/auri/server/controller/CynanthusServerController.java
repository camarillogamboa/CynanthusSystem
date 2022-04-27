package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.WrappedCynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/config/{id:\\d+}")
    @ResponseBody
    public String updateConfigOf(ServerInfo serverInfo, @RequestBody Config config) {
        return super.updateConfigOf(serverInfo, config);
    }

    @PutMapping("/config/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public String updateConfigOfByName(ServerInfo serverInfo, @RequestBody Config config) {
        return updateConfigOf(serverInfo, config);
    }

    @Override
    @GetMapping("/log/{id:\\d+}")
    @ResponseBody
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    @GetMapping("/log/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public String[] getLogFilesOfByName(ServerInfo serverInfo) {
        return getLogFilesOf(serverInfo);
    }

    @Override
    @GetMapping("/log/{id:\\d+}/{logFileName}")
    @ResponseBody
    public String getLogContentOf(ServerInfo serverInfo, @PathVariable String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    @GetMapping("/log/{id:" + Patterns.NAME + "}/{logFileName}")
    @ResponseBody
    public String getLogContentOfByName(ServerInfo serverInfo, @PathVariable String logFileName) {
        return getLogContentOf(serverInfo, logFileName);
    }

    @Override
    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Boolean isAvailable(ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}")
    @ResponseBody
    public Boolean isAvailableByName(ServerInfo serverInfo) {
        return isAvailable(serverInfo);
    }

}
