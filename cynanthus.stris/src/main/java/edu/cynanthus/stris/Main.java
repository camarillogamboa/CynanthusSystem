package edu.cynanthus.stris;

import edu.cynanthus.microservice.MicroService;

/**
 * El tipo Main.
 */
public final class Main {

    /**
     * Instancia un nuevo Main.
     */
    private Main() {}

    /**
     * El punto de entrada de la aplicación.
     *
     * @param args los argumentos de entrada
     */
    public static void main(String[] args) {
        MicroService microService = new StrisMicroService(args);
        microService.start();
    }

}
