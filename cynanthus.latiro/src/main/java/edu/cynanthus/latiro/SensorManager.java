package edu.cynanthus.latiro;

import edu.cynanthus.microservice.Context;
import edu.cynanthus.microservice.nanoservice.ProcessNanoService;
import edu.cynanthus.microservice.property.ObservableProperty;
import edu.cynanthus.microservice.property.ReadOnlyProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * El tipo Sensor manager.
 */
public class SensorManager extends ProcessNanoService {

    /**
     * El Storable nodes.
     */
    private final Map<String, StorableSensingNode> storableNodes;

    /**
     * El Sensor time out.
     */
    private final ReadOnlyProperty<Long> sensorTimeOut;
    /**
     * El Sensor sampling time.
     */
    private final ReadOnlyProperty<Long> sensorSamplingTime;

    /**
     * Instancia un nuevo Sensor manager.
     *
     * @param id            el id
     * @param context       el context
     * @param storableNodes el storable nodes
     */
    public SensorManager(
        String id,
        Context context,
        Map<String, StorableSensingNode> storableNodes
    ) {
        super(id, context);
        this.storableNodes = Objects.requireNonNull(storableNodes);

        ObservableProperty<Long> observableSensorTimeOut = getProperty("timeOut").asObservableLongProperty();
        ObservableProperty<Long> observableSensorSamplingTime =
            getProperty("samplingTime").asObservableLongProperty();

        Function<String, Consumer<Long>> observerFactory = name -> value -> storableNodes.forEach(
            (mac, upgradeableNode) -> {
                List<String> updates = upgradeableNode.getUpdates();
                synchronized (updates) {
                    updates.add(name + "=" + value);
                }
            }
        );

        observableSensorTimeOut.addAsObserver(observerFactory.apply("timeOut"));
        observableSensorSamplingTime.addAsObserver(observerFactory.apply("samplingTime"));

        this.sensorTimeOut = observableSensorTimeOut;
        this.sensorSamplingTime = observableSensorSamplingTime;
    }

    /**
     * Process loop.
     *
     * @throws InterruptedException el interrupted exception
     */
    @Override
    public void processLoop() throws InterruptedException {
        long sensorLyfeCycle = sensorTimeOut.getValue() + sensorSamplingTime.getValue();
        Thread.sleep(sensorLyfeCycle);

        long currentTime = System.currentTimeMillis();

        storableNodes.forEach((mac, upgradeableNode) -> {
            if (currentTime - upgradeableNode.getLastConnection() >= sensorLyfeCycle * 1.5)
                upgradeableNode.setAvailable(false);
        });
    }

}
