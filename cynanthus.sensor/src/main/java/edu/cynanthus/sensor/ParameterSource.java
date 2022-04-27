package edu.cynanthus.sensor;

/**
 * La interface Parameter source.
 */
public interface ParameterSource {

    /**
     * Load rssi float.
     *
     * @return el float
     */
    float loadRssi();

    /**
     * Load temp float.
     *
     * @return el float
     */
    float loadTemp();

    /**
     * Count turns int [ ].
     *
     * @param readingTime el reading time
     * @return el int [ ]
     * @throws InterruptedException el interrupted exception
     */
    int[] countTurns(long readingTime) throws InterruptedException;

    /**
     * Load co 2 float.
     *
     * @return el float
     */
    float loadCo2();

    /**
     * Load hum float.
     *
     * @return el float
     */
    float loadHum();

}
