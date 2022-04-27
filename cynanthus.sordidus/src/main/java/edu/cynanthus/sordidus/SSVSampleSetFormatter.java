package edu.cynanthus.sordidus;

import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.Formatter;
import edu.cynanthus.common.PairKeyValueFormatter;
import edu.cynanthus.common.SSV;
import edu.cynanthus.domain.Sample;
import edu.cynanthus.domain.SampleSet;

import java.util.Objects;

/**
 * El tipo Ssv sample set formatter.
 */
final class SSVSampleSetFormatter implements Formatter<SampleSet> {

    /**
     * El Separator.
     */
    private String separator;

    /**
     * Instancia un nuevo Ssv sample set formatter.
     *
     * @param separator el separator
     */
    public SSVSampleSetFormatter(String separator) {
        setSeparator(separator);
    }

    /**
     * Permite obtener separator.
     *
     * @return el separator
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * Permite establecer separator.
     *
     * @param separator el separator
     */
    public void setSeparator(String separator) {
        this.separator = Objects.requireNonNull(separator);
    }

    /**
     * Format string.
     *
     * @param sampleSet el sample set
     * @return el string
     */
    @Override
    public String format(SampleSet sampleSet) {
        SSV.Builder ssvBuilder = new SSV.Builder(System.lineSeparator());
        Formatter<Sample> sampleFormatter = new PairKeyValueFormatter<>(
            FieldAliasFinder.INSTANCE,
            k -> k,
            v -> v instanceof Float ? String.format("%.2f", v) : v.toString(),
            "=",
            separator
        );
        for (Sample sample : sampleSet.getSamples()) ssvBuilder.append(sampleFormatter.format(sample));
        return ssvBuilder.toString();
    }

}
