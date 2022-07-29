package edu.cynanthus.domain.config;

import edu.cynanthus.bean.Config;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * El tipo Sordidus config.
 */
public class SordidusConfig implements Config {

    /**
     * El Port.
     */
    @NotNull(message = "#{NotNull.sordidusConfig.port}")
    @Min(value = 0, groups = ValidInfo.class, message = "#{Min.sordidusConfig.port}")
    @Max(value = 65536, groups = ValidInfo.class, message = "#{Max.sordidusConfig.port}")
    @JProperty(
        alias = "cynanthus.sordidus.webServer.port",
        defaultValue = "8000",
        info = "Establece el puerto por el que se escucharán las peticiones http enviadas por los clientes."
    )
    private Integer port;

    /**
     * El Data directory.
     */
    @NotEmpty(message = "#{NotEmpty.sordidusConfig.dataDirectory}")
    @JProperty(
        alias = "cynanthus.sordidus.dataWriter.dataDirectory",
        defaultValue = "data",
        info = "Indica la ruta al directorio raíz donde se guardarán los datos de muestras."
    )
    private String dataDirectory;

    /**
     * El Data format.
     */
    @NotEmpty(message = "#{NotEmpty.sordidusConfig.dataFormat}")
    @Pattern(
        regexp = "(ssv\\((.*)\\))|json",
        groups = ValidInfo.class,
        message = "#{Pattern.sordidusConfig.dataFormat}"
    )
    @JProperty(
        alias = "cynanthus.sordidus.dataWriter.dataFormat",
        defaultValue = "ssv( )",
        info = "Formato de texto en el que se escribirán las muestras en los archivos.\n" +
            "Los posibles formatos son:\n" +
            "   \"json\": Escribe los datos con el concocido formato JavaScript object notation.\n" +
            "   \"ssv(\" string \")\" : (String Separated Values) Escribe los datos en " +
            "pares claves-valor separados\n" +
            "   por alguna cadena y colocando una muestra por línea.\n" +
            "   La sintaxis para especificar la cadena de separación debe ir " +
            "entre paréntesis después de la palabra\n" +
            "   clave \"ssv\". Si se quiere separar los datos por espacios entonces " +
            "el valor será: \"ssv( )\" dejando el espacio\n" +
            "   o espacios dentro del paréntesis."
    )
    private String dataFormat;

    /**
     * Instancia un nuevo Sordidus config.
     *
     * @param port          el port
     * @param dataDirectory el data directory
     * @param dataFormat    el data format
     */
    public SordidusConfig(Integer port, String dataDirectory, String dataFormat) {
        this.port = port;
        this.dataDirectory = dataDirectory;
        this.dataFormat = dataFormat;
    }

    /**
     * Instancia un nuevo Sordidus config.
     */
    public SordidusConfig() {
        this.port = null;
        this.dataDirectory = null;
        this.dataFormat = null;
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
     * Permite establecer port.
     *
     * @param port el port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Permite obtener data directory.
     *
     * @return el data directory
     */
    public String getDataDirectory() {
        return dataDirectory;
    }

    /**
     * Permite establecer data directory.
     *
     * @param dataDirectory el data directory
     */
    public void setDataDirectory(String dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    /**
     * Permite obtener data format.
     *
     * @return el data format
     */
    public String getDataFormat() {
        return dataFormat;
    }

    /**
     * Permite establecer data format.
     *
     * @param dataFormat el data format
     */
    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    /**
     * Clone sordidus config.
     *
     * @return el sordidus config
     */
    @Override
    public SordidusConfig clone() {
        return new SordidusConfig(port, dataDirectory, dataFormat);
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
        SordidusConfig that = (SordidusConfig) o;
        return Objects.equals(port, that.port) &&
            Objects.equals(dataDirectory, that.dataDirectory) &&
            Objects.equals(dataFormat, that.dataFormat);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(port, dataDirectory, dataFormat);
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
            ",dataDirectory:'" + dataDirectory + '\'' +
            ",dataFormat:'" + dataFormat + '\'' +
            '}';
    }

}
