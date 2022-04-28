package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

/**
 * La interface Configuration server service.
 *
 * @param <T> el parámetro de tipo
 */
public interface ConfigurationServerService<T extends Config> {

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    T getConfigOf(ServerInfo serverInfo);

    /**
     * Update config of string.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el string
     */
    String updateConfigOf(ServerInfo serverInfo, Config config);

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    String[] getLogFilesOf(ServerInfo serverInfo);

    /**
     * Permite obtener log content of.
     *
     * @param serverInfo  el server info
     * @param logFileName el log file name
     * @return el log content of
     */
    String getLogContentOf(ServerInfo serverInfo, String logFileName);

}
