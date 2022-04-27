package edu.cynanthus.sordidus;

import edu.cynanthus.common.Formatter;
import edu.cynanthus.common.TimePatterns;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.resource.FileAccesObject;
import edu.cynanthus.common.resource.ResourceException;
import edu.cynanthus.domain.SampleSet;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.ProcessNanoService;
import edu.cynanthus.microservice.property.ObservableProperty;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * El tipo Data writer.
 */
final class DataWriter extends ProcessNanoService implements TimePatterns {

    /**
     * El Sample set buffer.
     */
    private final LinkedList<SampleSet> sampleSetBuffer;
    /**
     * El Data directory.
     */
    private final ReadOnlyProperty<File> dataDirectory;

    /**
     * El Sample set formatter.
     */
    private volatile Formatter<SampleSet> sampleSetFormatter;

    /**
     * Instancia un nuevo Data writer.
     *
     * @param id              el id
     * @param context         el context
     * @param sampleSetBuffer el sample set buffer
     */
    public DataWriter(String id, Context context, LinkedList<SampleSet> sampleSetBuffer) {
        super(id, context);
        this.sampleSetBuffer = Objects.requireNonNull(sampleSetBuffer);
        this.dataDirectory = getProperty("dataDirectory").asOtherReadOnlyProperty(File::new);

        ObservableProperty<String> dataFormat = getProperty("dataFormat");

        dataFormat.addAsObserver(dataFormatKey -> {
            if (dataFormatKey.equals("json"))
                sampleSetFormatter = JsonProvider::toJson;
            else if (dataFormatKey.startsWith("ssv(") && dataFormatKey.endsWith(")")) {
                String separator = dataFormatKey.substring(4, dataFormatKey.length() - 1);
                if (sampleSetFormatter instanceof SSVSampleSetFormatter) {
                    ((SSVSampleSetFormatter) sampleSetFormatter).setSeparator(separator);
                } else sampleSetFormatter = new SSVSampleSetFormatter(separator);
            } else
                logger.warning("El valor \"" + dataFormatKey + "\" no es una clave de formato válida");
        });

        dataFormat.triggerNow();
    }

    /**
     * Process loop.
     */
    @Override
    public void processLoop() {
        SampleSet sampleSet;
        synchronized (sampleSetBuffer) {
            try {
                sampleSet = sampleSetBuffer.pop();
            } catch (NoSuchElementException ex) {
                sampleSet = null;
            }
        }
        if (sampleSet != null) {
            FileAccesObject.createDirectoryIfNoExists(dataDirectory.getValue());

            File todayDirectory = new File(
                dataDirectory.getValue() + "/" + LocalDate.now().format(DIRECTORY_NAME_PATTERN)
            );

            FileAccesObject.createDirectoryIfNoExists(todayDirectory);

            LocalTime initialTime = LocalTime.ofInstant(
                Instant.ofEpochMilli(sampleSet.getInitialMark()),
                ZoneId.systemDefault()
            );

            LocalTime endTime = LocalTime.ofInstant(
                Instant.ofEpochMilli(sampleSet.getEndMark()),
                ZoneId.systemDefault()
            );

            File samplesFile = new File(
                todayDirectory + "/" +
                    initialTime.format(FILE_NAME_PATTERN) + "-" +
                    endTime.format(FILE_NAME_PATTERN) + ".samples"
            );

            try {
                FileAccesObject.STRING_FAO.create(samplesFile, sampleSetFormatter.format(sampleSet));
            } catch (ResourceException e) {
                logger.severe(e.getMessage());
            }
        }
    }

}
