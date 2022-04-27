package edu.cynanthus.latiro;

import edu.cynanthus.bean.FieldAliasFinder;
import edu.cynanthus.common.net.http.RequestMethod;
import edu.cynanthus.common.net.http.client.ContextSuscriber;
import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.domain.Sample;
import edu.cynanthus.domain.SampleSet;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.HttpSuscriber;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Sample set sender.
 */
@ContextSuscriber(context = "/cynanthus/sordidus/sampleset", method = RequestMethod.POST)
final class SampleSetSender extends HttpSuscriber<String> {

    /**
     * El Sample buffer.
     */
    private final List<Sample> sampleBuffer;

    /**
     * El Buffering time.
     */
    private final ReadOnlyProperty<Long> bufferingTime;
    /**
     * El Time out.
     */
    private final ReadOnlyProperty<Long> timeOut;
    /**
     * El Send empty sample set.
     */
    private final ReadOnlyProperty<Boolean> sendEmptySampleSet;

    /**
     * Instancia un nuevo Sample set sender.
     *
     * @param id           el id
     * @param context      el context
     * @param sampleBuffer el sample buffer
     * @param requestQeue  el request qeue
     */
    public SampleSetSender(String id, Context context, List<Sample> sampleBuffer, RequestQeue<String> requestQeue) {
        super(id, context, requestQeue, HttpResponse.BodyHandlers.ofString(), FieldAliasFinder.INSTANCE);
        this.sampleBuffer = Objects.requireNonNull(sampleBuffer);

        this.bufferingTime = getProperty("bufferingTime").asReadOnlyLongProperty();
        this.timeOut = getProperty("timeOut").asReadOnlyLongProperty();
        this.sendEmptySampleSet = getProperty("emptySampleSetShipping").asReadOnlyBooleanProperty();
    }

    /**
     * Process loop.
     *
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void processLoop() throws InterruptedException {
        long initialMark = System.currentTimeMillis();
        Thread.sleep(bufferingTime.getValue());

        synchronized (sampleBuffer) {
            if (!sampleBuffer.isEmpty() || sendEmptySampleSet.getValue()) {
                doRequest(
                    new SampleSet(
                        initialMark,
                        System.currentTimeMillis(),
                        sampleBuffer.toArray(Sample[]::new)
                    ),
                    System.out::println
                );
            }

            sampleBuffer.clear();
        }
        Thread.sleep(timeOut.getValue());
    }

}
