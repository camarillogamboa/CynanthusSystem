package edu.cynanthus.sensor;

/**
 * El tipo Virtual parameter source.
 */
final class VirtualParameterSource implements ParameterSource {

    /**
     * Load rssi float.
     *
     * @return el float
     */
    @Override
    public float loadRssi() {
        return (float) -(Math.random() * 80 + 20);
    }

    /**
     * Load temp float.
     *
     * @return el float
     */
    @Override
    public float loadTemp() {
        return (float) (Math.random() * 60 + 10);
    }

    /**
     * Count turns int [ ].
     *
     * @param readingTime el reading time
     * @return el int [ ]
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public int[] countTurns(long readingTime) throws InterruptedException {
        Thread.sleep(readingTime);
        return new int[]{
            (int) (Math.random() * 40 + 5),
            (int) (Math.random() * 40 + 5),
            (int) (Math.random() * 40 + 5),
            (int) (Math.random() * 40 + 5)
        };
    }

    /**
     * Load co 2 float.
     *
     * @return el float
     */
    @Override
    public float loadCo2() {
        return (float) (Math.random() * 60 + 10);
    }

    /**
     * Load hum float.
     *
     * @return el float
     */
    @Override
    public float loadHum() {
        return (float) (Math.random() * 60 + 10);
    }

}
