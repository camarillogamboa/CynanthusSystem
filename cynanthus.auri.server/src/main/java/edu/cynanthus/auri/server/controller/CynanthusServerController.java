package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.api.WrappedCynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.Patterns;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.web.bind.annotation.*;

/**
 * El tipo Cynanthus server controller.
 *
 * @param <T> el parámetro de tipo
 */
public class CynanthusServerController<T extends Config> extends WrappedCynanthusServerService<T> {

    /**
     * Instancia un nuevo Cynanthus server controller.
     *
     * @param cynanthusServerService el cynanthus server service
     */
    CynanthusServerController(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
    }

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    @Override
    @GetMapping("/{id:\\d+}/config")
    @ResponseBody
    public T getConfigOf(ServerInfo serverInfo) {
        return super.getConfigOf(serverInfo);
    }

    /**
     * Permite obtener config of by name.
     *
     * @param serverInfo el server info
     * @return el config of by name
     */
    @GetMapping("{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    public T getConfigOfByName(ServerInfo serverInfo) {
        return getConfigOf(serverInfo);
    }

    /**
     * Update config of string.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el string
     */
    @Override
    @PutMapping("/{id:\\d+}/config")
    @ResponseBody
    public String updateConfigOf(ServerInfo serverInfo, @RequestBody Config config) {
        return super.updateConfigOf(serverInfo, config);
    }

    /**
     * Update config of by name string.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el string
     */
    @PutMapping("/{name:" + Patterns.NAME + "}/config")
    @ResponseBody
    public String updateConfigOfByName(ServerInfo serverInfo, @RequestBody Config config) {
        return updateConfigOf(serverInfo, config);
    }

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @Override
    @GetMapping("/{id:\\d+}/log")
    @ResponseBody
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return super.getLogFilesOf(serverInfo);
    }

    /**
     * Get log files of by name string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @GetMapping("{name:" + Patterns.NAME + "}/log")
    @ResponseBody
    public String[] getLogFilesOfByName(ServerInfo serverInfo) {
        return getLogFilesOf(serverInfo);
    }

    /**
     * Permite obtener log content of.
     *
     * @param serverInfo  el server info
     * @param logFileName el log file name
     * @return el log content of
     */
    @Override
    @GetMapping("/{id:\\d+}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOf(ServerInfo serverInfo, @PathVariable String logFileName) {
        return super.getLogContentOf(serverInfo, logFileName);
    }

    /**
     * Permite obtener log content of by name.
     *
     * @param serverInfo  el server info
     * @param logFileName el log file name
     * @return el log content of by name
     */
    @GetMapping("{id:" + Patterns.NAME + "}/log/{logFileName}")
    @ResponseBody
    public String getLogContentOfByName(ServerInfo serverInfo, @PathVariable String logFileName) {
        return getLogContentOf(serverInfo, logFileName);
    }

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @Override
    @GetMapping("{id:\\d+}/available")
    @ResponseBody
    public Boolean isAvailable(ServerInfo serverInfo) {
        return super.isAvailable(serverInfo);
    }

    /**
     * Is available by name boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @GetMapping("/{name:" + Patterns.NAME + "}/available")
    @ResponseBody
    public Boolean isAvailableByName(ServerInfo serverInfo) {
        return isAvailable(serverInfo);
    }

}
