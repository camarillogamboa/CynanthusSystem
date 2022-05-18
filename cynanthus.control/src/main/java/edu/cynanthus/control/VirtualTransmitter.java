package edu.cynanthus.control;

/**
 * El tipo Virtual transmitter.
 */
class VirtualTransmitter implements Transmitter {

    /**
     * Emit.
     *
     * @param instruction el instruction
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void emit(int[] instruction) throws InterruptedException {
        System.out.println("Codigos:");
        for (int code : instruction) {
            System.out.println("Code: " + code);
            Thread.sleep(0, code);
        }
    }

}
