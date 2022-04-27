package edu.cynanthus.microservice.nanoservice;

import edu.cynanthus.common.net.http.client.RequestQeue;
import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.property.ObservableProperty;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.io.IOException;
import java.util.Objects;

/**
 * El tipo Requester.
 */
public final class Requester extends ProcessNanoService {

    /**
     * El Request qeue.
     */
    private final RequestQeue<?> requestQeue;
    /**
     * El Wait time between attempts.
     */
    private final ReadOnlyProperty<Long> waitTimeBetweenAttempts;

    /**
     * Instancia un nuevo Requester.
     *
     * @param id          el id
     * @param context     el context
     * @param requestQeue el request qeue
     */
    public Requester(String id, Context context, RequestQeue<?> requestQeue) {
        super(id, context);
        this.requestQeue = Objects.requireNonNull(requestQeue);
        this.waitTimeBetweenAttempts = getProperty("waitTimeBetweenAttempts").asReadOnlyLongProperty();
        ObservableProperty<Integer> sendAttemps = getProperty("sendAttemps").asObservableIntegerProperty();
        sendAttemps.addAsObserver(requestQeue::setAttemps);
        sendAttemps.triggerNow();
    }

    /**
     * Process loop.
     *
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void processLoop() throws InterruptedException {
        try {
            requestQeue.dispatchQueuedRequest();
            Thread.sleep(waitTimeBetweenAttempts.getValue());
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        }
    }

}
