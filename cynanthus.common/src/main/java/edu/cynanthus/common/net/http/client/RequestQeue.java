package edu.cynanthus.common.net.http.client;

import java.io.IOException;
import java.util.LinkedList;

/**
 * El tipo Request qeue.
 *
 * @param <T> el parámetro de tipo
 */
public final class RequestQeue<T> {

    /**
     * El Pending requests.
     */
    private final LinkedList<PendingRequest<T>> pendingRequests;

    /**
     * El Attemps.
     */
    private volatile int attemps;

    /**
     * Instancia un nuevo Request qeue.
     *
     * @param attemps el attemps
     */
    public RequestQeue(int attemps) {
        this.pendingRequests = new LinkedList<>();
        this.attemps = attemps;
    }

    /**
     * Instancia un nuevo Request qeue.
     */
    public RequestQeue() {
        this(5);
    }

    /**
     * Permite obtener attemps.
     *
     * @return el attemps
     */
    public int getAttemps() {
        return attemps;
    }

    /**
     * Permite establecer attemps.
     *
     * @param attemps el attemps
     */
    public void setAttemps(int attemps) {
        this.attemps = attemps;
    }

    /**
     * Queue request.
     *
     * @param pendingRequest el pending request
     */
    public void queueRequest(PendingRequest<T> pendingRequest) {
        synchronized (pendingRequests) {
            pendingRequests.add(pendingRequest);
        }
    }

    /**
     * Dispatch queued request.
     *
     * @throws IOException          el io exception
     * @throws InterruptedException el interrupted exception
     */
    public void dispatchQueuedRequest() throws IOException, InterruptedException {
        PendingRequest<T> pendingRequest;
        synchronized (pendingRequests) {
            pendingRequest = pendingRequests.peek();
        }

        if (pendingRequest != null) try {
            pendingRequest.runRequest();
            synchronized (pendingRequests) {
                pendingRequests.pollFirst();
            }
        } catch (IOException | InterruptedException ex) {
            if (pendingRequest.getExecutions() >= attemps)
                synchronized (pendingRequests) {
                    pendingRequests.pollFirst();
                }
            throw ex;
        }
    }

}
