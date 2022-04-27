package edu.cynanthus.domain;

import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.JProperty;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.Objects;

/**
 * El tipo Sample set.
 */
public class SampleSet implements Bean {

    /**
     * El Initial mark.
     */
    @NotNull(groups = Required.class)
    @Positive(groups = {
        Required.class,
        ValidInfo.class
    })
    @JProperty
    private Long initialMark;

    /**
     * El End mark.
     */
    @NotNull(groups = Required.class)
    @Positive(groups = {
        Required.class,
        ValidInfo.class
    })
    @JProperty
    private Long endMark;

    /**
     * El Samples.
     */
    @NotNull(groups = Required.class)
    @Valid
    private Sample[] samples;

    /**
     * Instancia un nuevo Sample set.
     *
     * @param initialMark el initial mark
     * @param endMark     el end mark
     * @param samples     el samples
     */
    public SampleSet(Long initialMark, Long endMark, Sample[] samples) {
        this.initialMark = initialMark;
        this.endMark = endMark;
        this.samples = samples;
    }

    /**
     * Instancia un nuevo Sample set.
     */
    public SampleSet() {
    }

    /**
     * Permite obtener initial mark.
     *
     * @return el initial mark
     */
    public Long getInitialMark() {
        return initialMark;
    }

    /**
     * Permite obtener end mark.
     *
     * @return el end mark
     */
    public Long getEndMark() {
        return endMark;
    }

    /**
     * Get samples sample [ ].
     *
     * @return el sample [ ]
     */
    public Sample[] getSamples() {
        return samples;
    }

    /**
     * Permite establecer initial mark.
     *
     * @param initialMark el initial mark
     */
    public void setInitialMark(Long initialMark) {
        this.initialMark = initialMark;
    }

    /**
     * Permite establecer end mark.
     *
     * @param endMark el end mark
     */
    public void setEndMark(Long endMark) {
        this.endMark = endMark;
    }

    /**
     * Permite establecer samples.
     *
     * @param samples el samples
     */
    public void setSamples(Sample[] samples) {
        this.samples = samples;
    }

    /**
     * Clone sample set.
     *
     * @return el sample set
     */
    @Override
    public SampleSet clone() {
        return new SampleSet(initialMark, endMark, samples != null ? samples.clone() : null);
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
        SampleSet sampleSet = (SampleSet) o;
        return Objects.equals(initialMark, sampleSet.initialMark) &&
            Objects.equals(endMark, sampleSet.endMark) &&
            Arrays.equals(samples, sampleSet.samples);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(initialMark, endMark);
        result = 31 * result + Arrays.hashCode(samples);
        return result;
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "initialMark:" + initialMark +
            ",endMark:" + endMark +
            ",samples:" + Arrays.toString(samples) + '}';
    }

}
