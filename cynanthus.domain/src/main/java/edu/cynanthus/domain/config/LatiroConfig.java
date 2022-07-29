package edu.cynanthus.domain.config;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Latiro config.
 */
public class LatiroConfig implements Config {

    /**
     * El Port.
     */
    @NotNull(message = "#{NotNull.latiroConfig.port}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.latiroConfig.port}")
    @Max(value = 65536, groups = ValidInfo.class, message = "#{Max.latiroConfig.port}")
    @JProperty(
        alias = "cynanthus.latiro.webServer.port",
        defaultValue = "8001",
        info = "Establece el puerto por el que se escucharán las peticiones http enviadas por los clientes."
    )
    private Integer port;

    /**
     * El Server name.
     */
    @NotEmpty(message = "#{NotEmpty.latiroConfig.serverName}")
    @Size(min = 6, groups = ValidInfo.class, message = "#{Size.latiroConfig.serverName}")
    @JProperty(
        alias = "cynanthus.latiro.sender.serverName",
        defaultValue = "127.0.0.1",
        info = "La dirección IP o nombre del host donde se encuentra el servidor " +
            "Sordidus al que le serán enviadas\n" +
            "las muestras."
    )
    private String serverName;

    /**
     * El Server port.
     */
    @NotNull(message = "#{NotNull.latiroConfig.serverPort}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.latiroConfig.serverPort}")
    @Max(value = 65536, groups = ValidInfo.class, message = "#{Max.latiroConfig.serverPort}")
    @JProperty(
        alias = "cynanthus.latiro.sender.serverPort",
        defaultValue = "8000",
        info = "Establece el puerto del servidor Sordidus al que se le enviarán los datos."
    )
    private Integer serverPort;

    /**
     * El Buffering time.
     */
    @NotNull(message = "#{NotNull.latiroConfig.bufferingTime}")
    @Min(value = 500, groups = ValidInfo.class, message = "#{Min.latiroConfig.bufferingTime}")
    @JProperty(
        alias = "cynanthus.latiro.sender.bufferingTime",
        defaultValue = "15000",
        info = "Establece el tiempo de acumulación (en milisegundos) de datos llegados " +
            "al servidor desde los nodos,\n" +
            "al terminar este tiempo, los datos son enviados al servidor de proceso."
    )
    private Long bufferingTime;

    /**
     * El Time out.
     */
    @NotNull(message = "#{NotNull.latiroConfig.timeOut}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.latiroConfig.timeOut}")
    @JProperty(
        alias = "cynanthus.latiro.sender.timeOut",
        defaultValue = "1000",
        info = "Establece la cantidad de tiempo muerto (en milisegundos),\n" +
            "durante este momento se ignora cualquier dato proveniente de los nodos de sensado."
    )
    private Long timeOut;

    /**
     * El Send attemps.
     */
    @NotNull(message = "#{NotNull.latiroConfig.sendAttemps}")
    @Min(value = 1, groups = ValidInfo.class, message = "#{Min.latiroConfig.sendAttemps}")
    @Max(value = 20, groups = ValidInfo.class, message = "#{Max.latiroConfig.sendAttemps}")
    @JProperty(
        alias = "cynanthus.latiro.requester.sendAttemps",
        defaultValue = "5",
        info = "Número de veces en las que se intentará realizar" +
            " el envío del conjunto de muestras hacia el servidor\n" +
            "Sordidus"
    )
    private Integer sendAttemps;

    /**
     * El Wait time between attempts.
     */
    @NotNull(message = "#{NotNull.latiroConfig.waitTimeBetweenAttempts}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.latiroConfig.waitTimeBetweenAttempts}")
    @JProperty(
        alias = "cynanthus.latiro.requester.waitTimeBetweenAttempts",
        defaultValue = "500",
        info = "Tiempo de espera entre cada intento de envío de muestras hacia el servidor Sordidus"
    )
    private Long waitTimeBetweenAttempts;

    /**
     * El Empty sample set shipping.
     */
    @NotNull(message = "#{NotNull.latiroConfig.emptySampleSetShipping}")
    @JProperty(
        alias = "cynanthus.latiro.sender.emptySampleSetShipping",
        defaultValue = "true",
        info = "El programa realiza el envío de muestras periodicamente, cuando llegue " +
            "el momento, todas las muestras\n" +
            "se recopilarán para ser unidas en una cadena con formato JSON. Es posible " +
            "que el programa no haya recibido\n" +
            "ninguna muestra durante el último periodo de recepción, por lo que con el " +
            "parámetro \"emptySampleSetShipping\" en \"true\"\n" +
            "el programa hará el envío del conjunto de muestras aunque este sea vacío. " +
            "Establecer el valor en \"false\" hará que el\n" +
            "programa omita el envío de conjunto de muestras vacío."
    )
    private Boolean emptySampleSetShipping;

    /**
     * El Sensor time out.
     */
    @NotNull(message = "#{NotNull.latiroConfig.sensorTimeOut}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.latiroConfig.sensorTimeOut}")
    @JProperty(
        alias = "cynanthus.latiro.sensorManager.timeOut",
        defaultValue = "500",
        info = "Establece la cantidad de tiempo muerto (en milisegundos), previo a la recolección de muestras en el " +
            "no de sensado."
    )
    private Long sensorTimeOut;

    /**
     * El Sensor sampling time.
     */
    @NotNull(message = "#{NotNull.latiroConfig.sensorSamplingTime}")
    @Min(value = 500, groups = ValidInfo.class, message = "#{Min.latiroConfig.sensorSamplingTime}")
    @JProperty(
        alias = "cynanthus.latiro.sensorManager.samplingTime",
        defaultValue = "5000",
        info = "Establece la cantidad de tiempo de muestreo efectivo de los nodos de sensado."
    )
    private Long sensorSamplingTime;

    /**
     * Instancia un nuevo Latiro config.
     *
     * @param port                    el port
     * @param serverName              el server name
     * @param serverPort              el server port
     * @param bufferingTime           el buffering time
     * @param timeOut                 el time out
     * @param sendAttemps             el send attemps
     * @param waitTimeBetweenAttempts el wait time between attempts
     * @param emptySampleSetShipping  el empty sample set shipping
     * @param sensorTimeOut           el sensor time out
     * @param sensorSamplingTime      el sensor sampling time
     */
    public LatiroConfig(
        Integer port,
        String serverName,
        Integer serverPort,
        Long bufferingTime,
        Long timeOut,
        Integer sendAttemps,
        Long waitTimeBetweenAttempts,
        Boolean emptySampleSetShipping,
        Long sensorTimeOut,
        Long sensorSamplingTime
    ) {
        this.port = port;
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.bufferingTime = bufferingTime;
        this.timeOut = timeOut;
        this.sendAttemps = sendAttemps;
        this.waitTimeBetweenAttempts = waitTimeBetweenAttempts;
        this.emptySampleSetShipping = emptySampleSetShipping;
        this.sensorTimeOut = sensorTimeOut;
        this.sensorSamplingTime = sensorSamplingTime;
    }

    /**
     * Instancia un nuevo Latiro config.
     */
    public LatiroConfig() {
    }

    /**
     * Permite obtener port.
     *
     * @return el port
     */
    public Integer getPort() {
        return port;
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
     * Permite obtener server port.
     *
     * @return el server port
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * Permite obtener buffering time.
     *
     * @return el buffering time
     */
    public Long getBufferingTime() {
        return bufferingTime;
    }

    /**
     * Permite obtener time out.
     *
     * @return el time out
     */
    public Long getTimeOut() {
        return timeOut;
    }

    /**
     * Permite obtener send attemps.
     *
     * @return el send attemps
     */
    public Integer getSendAttemps() {
        return sendAttemps;
    }

    /**
     * Permite obtener wait time between attempts.
     *
     * @return el wait time between attempts
     */
    public Long getWaitTimeBetweenAttempts() {
        return waitTimeBetweenAttempts;
    }

    /**
     * Permite obtener empty sample set shipping.
     *
     * @return el empty sample set shipping
     */
    public Boolean getEmptySampleSetShipping() {
        return emptySampleSetShipping;
    }

    /**
     * Permite obtener sensor time out.
     *
     * @return el sensor time out
     */
    public Long getSensorTimeOut() {
        return sensorTimeOut;
    }

    /**
     * Permite obtener sensor sampling time.
     *
     * @return el sensor sampling time
     */
    public Long getSensorSamplingTime() {
        return sensorSamplingTime;
    }

    /**
     * Permite establecer port.
     *
     * @param port el port
     */
    public void setPort(Integer port) {
        this.port = port;
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
     * Permite establecer server port.
     *
     * @param serverPort el server port
     */
    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Permite establecer buffering time.
     *
     * @param bufferingTime el buffering time
     */
    public void setBufferingTime(Long bufferingTime) {
        this.bufferingTime = bufferingTime;
    }

    /**
     * Permite establecer time out.
     *
     * @param timeOut el time out
     */
    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Permite establecer send attemps.
     *
     * @param sendAttemps el send attemps
     */
    public void setSendAttemps(Integer sendAttemps) {
        this.sendAttemps = sendAttemps;
    }

    /**
     * Permite establecer wait time between attempts.
     *
     * @param waitTimeBetweenAttempts el wait time between attempts
     */
    public void setWaitTimeBetweenAttempts(Long waitTimeBetweenAttempts) {
        this.waitTimeBetweenAttempts = waitTimeBetweenAttempts;
    }

    /**
     * Permite establecer empty sample set shipping.
     *
     * @param emptySampleSetShipping el empty sample set shipping
     */
    public void setEmptySampleSetShipping(Boolean emptySampleSetShipping) {
        this.emptySampleSetShipping = emptySampleSetShipping;
    }

    /**
     * Permite establecer sensor time out.
     *
     * @param sensorTimeOut el sensor time out
     */
    public void setSensorTimeOut(Long sensorTimeOut) {
        this.sensorTimeOut = sensorTimeOut;
    }

    /**
     * Permite establecer sensor sampling time.
     *
     * @param sensorSamplingTime el sensor sampling time
     */
    public void setSensorSamplingTime(Long sensorSamplingTime) {
        this.sensorSamplingTime = sensorSamplingTime;
    }

    /**
     * Clone latiro config.
     *
     * @return el latiro config
     */
    @Override
    public LatiroConfig clone() {
        return new LatiroConfig(
            port,
            serverName,
            serverPort,
            bufferingTime,
            timeOut,
            sendAttemps,
            waitTimeBetweenAttempts,
            emptySampleSetShipping,
            sensorTimeOut,
            sensorSamplingTime
        );
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
        LatiroConfig that = (LatiroConfig) o;
        return Objects.equals(port, that.port) &&
            Objects.equals(serverName, that.serverName) &&
            Objects.equals(serverPort, that.serverPort) &&
            Objects.equals(bufferingTime, that.bufferingTime) &&
            Objects.equals(timeOut, that.timeOut) &&
            Objects.equals(sendAttemps, that.sendAttemps) &&
            Objects.equals(waitTimeBetweenAttempts, that.waitTimeBetweenAttempts) &&
            Objects.equals(emptySampleSetShipping, that.emptySampleSetShipping) &&
            Objects.equals(sensorTimeOut, that.sensorTimeOut) &&
            Objects.equals(sensorSamplingTime, that.sensorSamplingTime);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(
            port,
            serverName,
            serverPort,
            bufferingTime,
            timeOut,
            sendAttemps,
            waitTimeBetweenAttempts,
            emptySampleSetShipping,
            sensorTimeOut,
            sensorSamplingTime
        );
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "port:" + port +
            ",serverName:'" + serverName + '\'' +
            ",serverPort:" + serverPort +
            ",bufferingTime:" + bufferingTime +
            ",timeOut:" + timeOut +
            ",sendAttemps:" + sendAttemps +
            ",waitTimeBetweenAttempts:" + waitTimeBetweenAttempts +
            ",emptySampleSetShipping:" + emptySampleSetShipping +
            ",sensorTimeOut:" + sensorTimeOut +
            ",sensorSamplingTime:" + sensorSamplingTime +
            '}';
    }

}
