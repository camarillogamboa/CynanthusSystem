package edu.cynanthus.domain.config;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Sensor config.
 */
public class SensorConfig implements Config {
    /**
     * El Server name.
     */
    @NotEmpty(message = "#{NotEmpty.sensorConfig.serverName}")
    @Size(max = 6, groups = ValidInfo.class, message = "#{Size.sensorConfig.serverName}")
    @JProperty(
        alias = "cynanthus.sensor.sender.serverName",
        defaultValue = "127.0.0.1",
        info = "La dirección IP o nombre del host donde se encuentra el " +
            "servidor Latiro al que le serán enviadas las muestras."
    )
    private String serverName;

    /**
     * El Server port.
     */
    @NotNull(message = "#{NotNull.sensorConfig.serverPort}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sensorConfig.serverPort}")
    @Max(value = 65536, groups = ValidInfo.class, message = "#{Max.sensorConfig.serverPort}")
    @JProperty(
        alias = "cynanthus.sensor.sender.serverPort",
        defaultValue = "8001",
        info = "Establece el puerto del servidor Latiro al que se le enviarán los datos."
    )
    private Integer serverPort;

    /**
     * El Time out.
     */
    @NotNull(message = "#{NotNull.sensorConfig.timeOut}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sensorConfig.timeOut}")
    @JProperty(
        alias = "cynanthus.sensor.sender.timeOut",
        defaultValue = "500",
        info = "Establece la cantidad de tiempo muerto (en milisegundos), previo a la recolección de muestras."
    )
    private Long timeOut;

    /**
     * El Sampling time.
     */
    @NotNull(message = "#{NotNull.sensorConfig.samplingTime}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sensorConfig.samplingTime}")
    @JProperty(
        alias = "cynanthus.sensor.sender.samplingTime",
        defaultValue = "2000",
        info = "Establece la cantidad de tiempo de muestreo efectivo."
    )
    private Long samplingTime;

    /**
     * El Wait time between attempts.
     */
    @NotNull(message = "#{NotNull.sensorConfig.waitTimeBetweenAttempts}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sensorConfig.waitTimeBetweenAttempts}")
    @JProperty(
        alias = "cynanthus.sensor.requester.waitTimeBetweenAttempts",
        defaultValue = "0",
        info = "Tiempo de espera entre cada intento de envío de muestras hacia el servidor Latiro"
    )
    private Long waitTimeBetweenAttempts;

    /**
     * El Send attemps.
     */
    @NotNull(message = "#{NotNull.sensorConfig.sendAttemps}")
    @Min(value = 1, groups = ValidInfo.class, message = "#{Min.sensorConfig.sendAttemps}")
    @Max(value = 20, groups = ValidInfo.class, message = "#{Max.sensorConfig.sendAttemps}")
    @JProperty(
        alias = "cynanthus.sensor.requester.sendAttemps",
        defaultValue = "5",
        info = "Número de veces en las que se intentará (en caso de error) realizar el envío de la " +
            "muestra hacia el servidor Latiro"
    )
    private Integer sendAttemps;

    /**
     * Instancia un nuevo Sensor config.
     *
     * @param serverName              el server name
     * @param serverPort              el server port
     * @param timeOut                 el time out
     * @param samplingTime            el sampling time
     * @param waitTimeBetweenAttempts el wait time between attempts
     * @param sendAttemps             el send attemps
     */
    public SensorConfig(
        String serverName,
        Integer serverPort,
        Long timeOut,
        Long samplingTime,
        Long waitTimeBetweenAttempts,
        Integer sendAttemps
    ) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.timeOut = timeOut;
        this.samplingTime = samplingTime;
        this.waitTimeBetweenAttempts = waitTimeBetweenAttempts;
        this.sendAttemps = sendAttemps;
    }

    /**
     * Instancia un nuevo Sensor config.
     */
    public SensorConfig() {
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
     * Permite obtener time out.
     *
     * @return el time out
     */
    public Long getTimeOut() {
        return timeOut;
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
     * Permite obtener sampling time.
     *
     * @return el sampling time
     */
    public Long getSamplingTime() {
        return samplingTime;
    }

    /**
     * Permite establecer sampling time.
     *
     * @param samplingTime el sampling time
     */
    public void setSamplingTime(Long samplingTime) {
        this.samplingTime = samplingTime;
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
     * Permite establecer wait time between attempts.
     *
     * @param waitTimeBetweenAttempts el wait time between attempts
     */
    public void setWaitTimeBetweenAttempts(Long waitTimeBetweenAttempts) {
        this.waitTimeBetweenAttempts = waitTimeBetweenAttempts;
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
     * Permite establecer send attemps.
     *
     * @param sendAttemps el send attemps
     */
    public void setSendAttemps(Integer sendAttemps) {
        this.sendAttemps = sendAttemps;
    }

    /**
     * Clone sensor config.
     *
     * @return el sensor config
     */
    @Override
    public SensorConfig clone() {
        return new SensorConfig(
            serverName,
            serverPort,
            timeOut,
            samplingTime,
            waitTimeBetweenAttempts,
            sendAttemps
        );
    }

    /**
     * Equals boolean.
     *
     * @param o él o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorConfig that = (SensorConfig) o;
        return Objects.equals(serverName, that.serverName) &&
            Objects.equals(serverPort, that.serverPort) &&
            Objects.equals(timeOut, that.timeOut) &&
            Objects.equals(samplingTime, that.samplingTime) &&
            Objects.equals(waitTimeBetweenAttempts, that.waitTimeBetweenAttempts) &&
            Objects.equals(sendAttemps, that.sendAttemps);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(
            serverName,
            serverPort,
            timeOut,
            samplingTime,
            waitTimeBetweenAttempts,
            sendAttemps
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
            "serverName:'" + serverName + '\'' +
            ",serverPort:" + serverPort +
            ",timeOut:" + timeOut +
            ",samplingTime:" + samplingTime +
            ",waitTimeBetweenAttempts:" + waitTimeBetweenAttempts +
            ",sendAttemps:" + sendAttemps +
            '}';
    }

}
