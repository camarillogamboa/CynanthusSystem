package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.WrappedCynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CynanthusServerController<T extends Config> extends WrappedCynanthusServerService<T> {

    CynanthusServerController(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
    }

    @Override
    @GetMapping("/{id:\\d+}/config")
    public T getConfigOf(ServerInfo serverInfo) {
        return super.getConfigOf(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/config")
    public T getConfigOfByName(ServerInfo serverInfo) {
        return getConfigOf(serverInfo);
    }

    @Override
    @PutMapping("/{id:\\d+}/config")
    public Boolean updateConfigOf(ServerInfo serverInfo, @RequestBody T config) {
        return super.updateConfigOf(serverInfo, config);
    }

    @PutMapping("/{name:" + Patterns.NAME + "}/config")
    public Boolean updateConfigOfByName(ServerInfo serverInfo, @RequestBody T config) {
        return updateConfigOf(serverInfo, config);
    }

    @Override
    @GetMapping("/{id:\\d+}/log")
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/log")
    public String[] getLogFilesOfByName(ServerInfo serverInfo) {
        return getLogFilesOf(serverInfo);
    }

    @Override
    @GetMapping("/{id:\\d+}/log/{logFileName}")
    public String getLogContentOf(ServerInfo serverInfo, @PathVariable String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/log/{logFileName}")
    public String getLogContentOfByName(ServerInfo serverInfo, @PathVariable String logFileName) {
        return getLogContentOf(serverInfo, logFileName);
    }

    @Override
    @GetMapping("/{id:\\d+}/available")
    public Boolean isAvailable(ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/available")
    public Boolean isAvailableByName(ServerInfo serverInfo) {
        return isAvailable(serverInfo);
    }

}
