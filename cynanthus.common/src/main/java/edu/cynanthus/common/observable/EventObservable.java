package edu.cynanthus.common.observable;

import edu.cynanthus.common.observable.event.Event;

/**
 * El tipo Event observable.
 */
public class EventObservable extends BasicObservable<Event> {

    /**
     * Trigger now.
     */
    @Override
    public void triggerNow() {
        dispatchEvent(new Event(this));
    }

}
