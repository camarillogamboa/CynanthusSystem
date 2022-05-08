package edu.cynanthus.domain;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * El tipo Sensed environment.
 */
public class SensedEnvironment implements Bean {

    /**
     * El Temp.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.temp}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.temp}")
    @JProperty(alias = "temp")
    private Float temp;

    /**
     * El North laps.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.northLaps}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.northLaps}")
    @JProperty(alias = "vueltas_norte")
    private Integer northLaps;

    /**
     * El East laps.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.eastLaps}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.eastLaps}")
    @JProperty(alias = "vueltas_este")
    private Integer eastLaps;

    /**
     * El South laps.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.southLaps}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.southLaps}")
    @JProperty(alias = "vueltas_sur")
    private Integer southLaps;

    /**
     * El West laps.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.westLaps}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.westLaps}")
    @JProperty(alias = "vueltas_oeste")
    private Integer westLaps;

    /**
     * El Sampling time.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.samplingTime}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.samplingTime}")
    @JProperty(alias = "tiempo_muestreo")
    private Long samplingTime;

    /**
     * El Co 2.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.co2}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.co2}")
    @JProperty(alias = "co2")
    private Float co2;

    /**
     * El Hum.
     */
    @NotNull(groups = Required.class, message = "{NotNull.sensedEnvironment.hum}")
    @Positive(groups = {Required.class, ValidInfo.class}, message = "{Positive.sensedEnvironment.hum}")
    @JProperty(alias = "hum")
    private Float hum;

    /**
     * Instancia un nuevo Sensed environment.
     *
     * @param temp         el temp
     * @param northLaps    el north laps
     * @param eastLaps     el east laps
     * @param southLaps    el south laps
     * @param westLaps     el west laps
     * @param samplingTime el sampling time
     * @param co2          el co 2
     * @param hum          el hum
     */
    public SensedEnvironment(
        Float temp,
        Integer northLaps,
        Integer eastLaps,
        Integer southLaps,
        Integer westLaps,
        Long samplingTime,
        Float co2,
        Float hum
    ) {
        this.temp = temp;
        this.northLaps = northLaps;
        this.eastLaps = eastLaps;
        this.southLaps = southLaps;
        this.westLaps = westLaps;
        this.samplingTime = samplingTime;
        this.co2 = co2;
        this.hum = hum;
    }

    /**
     * Instancia un nuevo Sensed environment.
     */
    public SensedEnvironment() {
    }

    /**
     * Permite obtener temp.
     *
     * @return el temp
     */
    public final Float getTemp() {
        return temp;
    }

    /**
     * Permite establecer temp.
     *
     * @param temp el temp
     */
    public void setTemp(Float temp) {
        this.temp = temp;
    }

    /**
     * Permite obtener north laps.
     *
     * @return el north laps
     */
    public Integer getNorthLaps() {
        return northLaps;
    }

    /**
     * Permite establecer north laps.
     *
     * @param northLaps el north laps
     */
    public void setNorthLaps(Integer northLaps) {
        this.northLaps = northLaps;
    }

    /**
     * Permite obtener east laps.
     *
     * @return el east laps
     */
    public Integer getEastLaps() {
        return eastLaps;
    }

    /**
     * Permite establecer east laps.
     *
     * @param eastLaps el east laps
     */
    public void setEastLaps(Integer eastLaps) {
        this.eastLaps = eastLaps;
    }

    /**
     * Permite obtener south laps.
     *
     * @return el south laps
     */
    public Integer getSouthLaps() {
        return southLaps;
    }

    /**
     * Permite establecer south laps.
     *
     * @param southLaps el south laps
     */
    public void setSouthLaps(Integer southLaps) {
        this.southLaps = southLaps;
    }

    /**
     * Permite obtener west laps.
     *
     * @return el west laps
     */
    public Integer getWestLaps() {
        return westLaps;
    }

    /**
     * Permite establecer west laps.
     *
     * @param westLaps el west laps
     */
    public void setWestLaps(Integer westLaps) {
        this.westLaps = westLaps;
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
     * Permite obtener co 2.
     *
     * @return el co 2
     */
    public Float getCo2() {
        return co2;
    }

    /**
     * Permite establecer co 2.
     *
     * @param co2 el co 2
     */
    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    /**
     * Permite obtener hum.
     *
     * @return el hum
     */
    public Float getHum() {
        return hum;
    }

    /**
     * Permite establecer hum.
     *
     * @param hum el hum
     */
    public void setHum(Float hum) {
        this.hum = hum;
    }

    /**
     * Clone sensed environment.
     *
     * @return el sensed environment
     */
    @Override
    public SensedEnvironment clone() {
        return new SensedEnvironment(
            temp,
            northLaps,
            eastLaps,
            southLaps,
            westLaps,
            samplingTime,
            co2,
            hum
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
        SensedEnvironment that = (SensedEnvironment) o;
        return Objects.equals(temp, that.temp) &&
            Objects.equals(northLaps, that.northLaps) &&
            Objects.equals(eastLaps, that.eastLaps) &&
            Objects.equals(southLaps, that.southLaps) &&
            Objects.equals(westLaps, that.westLaps) &&
            Objects.equals(samplingTime, that.samplingTime) &&
            Objects.equals(co2, that.co2) &&
            Objects.equals(hum, that.hum);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(temp, northLaps, eastLaps, southLaps, westLaps, samplingTime, co2, hum);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "temp:" + temp +
            ",northLaps:" + northLaps +
            ",eastLaps:" + eastLaps +
            ",southLaps:" + southLaps +
            ",westLaps:" + westLaps +
            ",samplingTime:" + samplingTime +
            ",co2:" + co2 +
            ",hum:" + hum +
            '}';
    }

}
