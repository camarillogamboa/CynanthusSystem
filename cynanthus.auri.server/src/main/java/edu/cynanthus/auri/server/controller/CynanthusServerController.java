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
    @GetMapping("/{id:\\d+}/config")
    @ResponseBody
    public T getConfigOf(@PathVariable("id") ServerInfo serverInfo) {
        return super.getConfigOf(serverInfo);
    }

    @GetMapping("{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    public T getConfigOfByName(@PathVariable("name") ServerInfo serverInfo) {
        return getConfigOf(serverInfo);
    }

    @Override
    @PutMapping("/{id:\\d+}/config")
    @ResponseBody
    public Boolean updateConfigOf(@PathVariable("id") ServerInfo serverInfo, @RequestBody T config) {
        return super.updateConfigOf(serverInfo, config);
    }

    @PutMapping("/{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    public Boolean updateConfigOfByName(@PathVariable("name") ServerInfo serverInfo, @RequestBody T config) {
        return updateConfigOf(serverInfo, config);
    }

    @Override
    @GetMapping("/{id:\\d+}/log")
    @ResponseBody
    public String[] getLogFilesOf(@PathVariable("id") ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    @GetMapping("{name:" + Patterns.NAME + "}/log")
    @ResponseBody
    public String[] getLogFilesOfByName(@PathVariable("name") ServerInfo serverInfo) {
        return getLogFilesOf(serverInfo);
    }

    @Override
    @GetMapping("/{id:\\d+}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOf(@PathVariable("id") ServerInfo serverInfo, @PathVariable String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    @GetMapping("{name:" + Patterns.NAME + "}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOfByName(@PathVariable("name") ServerInfo serverInfo, @PathVariable String logFileName) {
        return getLogContentOf(serverInfo, logFileName);
    }

    @Override
    @GetMapping("{id:\\d+}/available")
    @ResponseBody
    public Boolean isAvailable(@PathVariable("id") ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

    @GetMapping("/{name:" + Patterns.NAME + "}/available")
    @ResponseBody
    public Boolean isAvailableByName(@PathVariable("name") ServerInfo serverInfo) {
        return isAvailable(serverInfo);
    }

}
