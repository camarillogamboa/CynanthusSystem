package edu.cynanthus.domain.config;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * Contiene las propiedades de funcionamiento del sistema para una
 * aplicación que funge como nodo de control.
 * El nodo de control puede ser simulado o implementado en una aplicación Java,
 * cuando ese es el caso, las propiedades definidas como campos de esta clase son utiles
 * para modelar y transportar los datos de la aplicación.
 * <p>
 * Un nodo de control pone en ejecución un cliente tcp que se comunica con un servidor tcp
 *
 * @author L.G. Camarillo
 */
public class ControlConfig implements Config {

    /**
     * Contiene el nombre del servidor a
     */
    @NotEmpty(message = "#{NotEmpty.controlConfig.serverName}")
    @Size(min = 6, groups = ValidInfo.class, message = "#{Size.controlConfig.serverName}")
    @JProperty(
        alias = "cynanthus.control.connector.serverName",
        defaultValue = "127.0.0.1",
        info = "Establece el nombre del servidor a donde se conetará el cliente tcp"
    )
    private String serverName;

    /**
     * El Server port.
     */
    @NotNull(message = "#{NotNull.controlConfig.serverPort}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.controlConfig.serverPort}")
    @Max(value = 65536, groups = ValidInfo.class, message = "#{Max.controlConfig.serverPort}")
    @JProperty(
        alias = "cynanthus.control.connector.serverPort",
        defaultValue = "8003",
        info = "Establece el puerto del servidor a donde se conectará el cliente tcp"
    )
    private Integer serverPort;

    /**
     * Instancia un nuevo Control config.
     *
     * @param serverName el server name
     * @param serverPort el server port
     */
    public ControlConfig(String serverName, Integer serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    /**
     * Instancia un nuevo Control config.
     */
    public ControlConfig() {
    }

    /**
     * Permite obtener server name.
     *
     * @return el server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Permite establecer server name.
     *
     * @param serverName el server name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Permite obtener server port.
     *
     * @return el server port
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * Permite establecer server port.
     *
     * @param serverPort el server port
     */
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Clone control config.
     *
     * @return el control config
     */
    @Override
    public ControlConfig clone() {
        return new ControlConfig(serverName, serverPort);
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlConfig that = (ControlConfig) o;
        return Objects.equals(serverName, that.serverName) && Objects.equals(serverPort, that.serverPort);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(serverName, serverPort);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "serverName:'" + serverName + '\'' +
            ",severPort:" + serverPort +
            '}';
    }

}
