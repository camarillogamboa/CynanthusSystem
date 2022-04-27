package edu.cynanthus.sensor;

import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.net.http.client.ContextSuscriber;
import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.common.net.http.packet.Response;
import edu.cynanthus.domain.Node;
import edu.cynanthus.domain.Sample;
import edu.cynanthus.domain.SensedEnvironment;
import edu.cynanthus.domain.config.SensorConfig;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.HttpSuscriber;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * El tipo Sample sender.
 */
@ContextSuscriber(context = "/cynanthus/latiro/sample", method = RequestMethod.POST)
final class SampleSender extends HttpSuscriber<String> {

    /**
     * El Parameter source.
     */
    private final ParameterSource parameterSource;
    /**
     * El Node.
     */
    private final Node node;

    /**
     * El Time out.
     */
    private final ReadOnlyProperty<Long> timeOut;
    /**
     * El Sampling time.
     */
    private final ReadOnlyProperty<Long> samplingTime;

    /**
     * El Response consumer.
     */
    private final Consumer<Response<String>> responseConsumer;

    /**
     * Instancia un nuevo Sample sender.
     *
     * @param id              el id
     * @param context         el context
     * @param parameterSource el parameter source
     * @param requestQeue     el request qeue
     * @param node            el node
     */
    public SampleSender(
        String id,
        Context context,
        ParameterSource parameterSource,
        RequestQeue<String> requestQeue,
        Node node
    ) {
        super(id, context, requestQeue, HttpResponse.BodyHandlers.ofString(), FieldAliasFinder.INSTANCE);
        this.parameterSource = Objects.requireNonNull(parameterSource);
        this.node = Objects.requireNonNull(node);

        this.timeOut = getProperty("timeOut").asReadOnlyLongProperty();
        this.samplingTime = getProperty("samplingTime").asReadOnlyLongProperty();

        this.responseConsumer = response -> {
            System.out.println(response);
            if (response.getResponseCode() == HttpStatus.OK && !response.getData().equals("ok")) {

                String[] updates = response.getData().split(",");
                SensorConfig sensorConfig = new SensorConfig();
                for (String update : updates) {
                    String[] keyValue = update.split("=");
                    try {
                        Long value = Long.parseLong(keyValue[1]);
                        switch (keyValue[0]) {
                            case "timeOut":
                                sensorConfig.setTimeOut(value);
                                break;
                            case "samplingTime":
                                sensorConfig.setSamplingTime(value);
                        }
                    } catch (NumberFormatException ex) {
                        logger.log(Level.WARNING, "Valores para propiedades inválidos", ex);
                    }
                }
                context.updatePropertiesFrom(sensorConfig);
            }
        };
    }

    /**
     * Process loop.
     *
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void processLoop() throws InterruptedException {
        Thread.sleep(timeOut.getValue());

        node.setRssi(parameterSource.loadRssi());
        int[] countTurns = parameterSource.countTurns(samplingTime.getValue());

        doRequest(
            new Sample(
                node,
                new SensedEnvironment(
                    parameterSource.loadTemp(),
                    countTurns[0],
                    countTurns[1],
                    countTurns[2],
                    countTurns[3],
                    samplingTime.getValue(),
                    parameterSource.loadCo2(),
                    parameterSource.loadHum()
                )
            ),
            responseConsumer
        );
    }

}
