package edu.cynanthus.sensor;

import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.MicroService;
import edu.cynanthus.microservice.nanoservice.NanoService;

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
        int virtualInstances = args.length >= 2 ? Integer.parseInt(args[1]) : 1;

        MicroService microService = new SensorMicroService(args) {

            @Override
            public NanoService loadNanoService(Context context) {
                return loadNanoService(context, VirtualParameterSource::new, virtualInstances);
            }

        };

        microService.start();
    }

}
