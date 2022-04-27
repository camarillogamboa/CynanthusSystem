package edu.cynanthus.domain.config;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * El tipo Stris config.
 */
public class StrisConfig implements Config {

    /**
     * El Web server port.
     */
    @NotNull
    @Positive(groups = ValidInfo.class)
    @Max(value = 65536, groups = ValidInfo.class)
    @JProperty(
        alias = "cynanthus.stris.webServer.port",
        defaultValue = "8002",
        info = "Establece el puerto por el que se escucharán las peticiones http enviadas por el frontEnd."
    )
    private Integer webServerPort;

    /**
     * El Tcp server port.
     */
    @NotNull
    @Positive(groups = ValidInfo.class)
    @Max(value = 65536, groups = ValidInfo.class)
    @JProperty(
        alias = "cynanthus.stris.tcpServer.port",
        defaultValue = "8003",
        info = "Establece el puerto por el que se recibirán las conexiones desde los nodos de control."
    )
    private Integer tcpServerPort;

    /**
     * Instancia un nuevo Stris config.
     *
     * @param webServerPort el web server port
     * @param tcpServerPort el tcp server port
     */
    public StrisConfig(Integer webServerPort, Integer tcpServerPort) {
        this.webServerPort = webServerPort;
        this.tcpServerPort = tcpServerPort;
    }

    /**
     * Instancia un nuevo Stris config.
     */
    public StrisConfig() {
    }

    /**
     * Permite obtener web server port.
     *
     * @return el web server port
     */
    public Integer getWebServerPort() {
        return webServerPort;
    }

    /**
     * Permite establecer web server port.
     *
     * @param webServerPort el web server port
     */
    public void setWebServerPort(Integer webServerPort) {
        this.webServerPort = webServerPort;
    }

    /**
     * Permite obtener tcp server port.
     *
     * @return el tcp server port
     */
    public Integer getTcpServerPort() {
        return tcpServerPort;
    }

    /**
     * Permite establecer tcp server port.
     *
     * @param tcpServerPort el tcp server port
     */
    public void setTcpServerPort(Integer tcpServerPort) {
        this.tcpServerPort = tcpServerPort;
    }

    /**
     * Clone stris config.
     *
     * @return el stris config
     */
    @Override
    public StrisConfig clone() {
        return new StrisConfig(webServerPort, tcpServerPort);
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
        StrisConfig that = (StrisConfig) o;
        return Objects.equals(webServerPort, that.webServerPort)
            && Objects.equals(tcpServerPort, that.tcpServerPort);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(webServerPort, tcpServerPort);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "webServerPort:" + webServerPort +
            ",tcpServerPort:" + tcpServerPort +
            '}';
    }

}
