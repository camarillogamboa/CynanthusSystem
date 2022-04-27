package edu.cynanthus.control;

/**
 * La interface Transmitter.
 */
public interface Transmitter {

    /**
     * Emit.
     *
     * @param instruction el instruction
     * @throws Exception el exception
     */
    void emit(int[] instruction) throws Exception;

}
