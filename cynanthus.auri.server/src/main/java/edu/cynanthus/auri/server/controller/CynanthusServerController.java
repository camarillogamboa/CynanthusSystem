package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.WrappedCynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public class CynanthusServerController<T extends Config> extends WrappedCynanthusServerService<T> {

    CynanthusServerController(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
    }

    @Override
    @GetMapping("/{id:\\d+}/config")
    @ResponseBody
    public T getConfigOf(ServerInfo serverInfo) {
        return super.getConfigOf(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    public T getConfigOfByName(ServerInfo serverInfo) {
        return getConfigOf(serverInfo);
    }

    @Override
    @PutMapping("/{id:\\d+}/config")
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public Boolean updateConfigOf(ServerInfo serverInfo, @RequestBody T config) {
        return super.updateConfigOf(serverInfo, config);
    }

    @PutMapping("/{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public Boolean updateConfigOfByName(ServerInfo serverInfo, @RequestBody T config) {
        return updateConfigOf(serverInfo, config);
    }

    @Override
    @GetMapping("/{id:\\d+}/log")
    @ResponseBody
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/log")
    @ResponseBody
    public String[] getLogFilesOfByName(ServerInfo serverInfo) {
        return getLogFilesOf(serverInfo);
    }

    @Override
    @GetMapping("/{id:\\d+}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOf(ServerInfo serverInfo, @PathVariable String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOfByName(ServerInfo serverInfo, @PathVariable String logFileName) {
        return getLogContentOf(serverInfo, logFileName);
    }

    @Override
    @GetMapping("/{id:\\d+}/available")
    @ResponseBody
    public Boolean isAvailable(ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/available")
    @ResponseBody
    public Boolean isAvailableByName(ServerInfo serverInfo) {
        return isAvailable(serverInfo);
    }

}
