package edu.cynanthus.latiro;

import edu.cynanthus.common.Consumable;
import edu.cynanthus.domain.Node;
import edu.cynanthus.domain.SensedEnvironment;
import edu.cynanthus.domain.SensingNode;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * El tipo Storable sensing node.
 */
public class StorableSensingNode extends SensingNode implements Consumable<String> {

    /**
     * El Updates.
     */
    private final List<String> updates;

    /**
     * Instancia un nuevo Storable sensing node.
     *
     * @param node el node
     */
    public StorableSensingNode(Node node) {
        super(node, new SensedEnvironment(), false, null);
        this.updates = new LinkedList<>();
    }

    /**
     * Permite obtener updates.
     *
     * @return el updates
     */
    public List<String> getUpdates() {
        return updates;
    }

    /**
     * Contains boolean.
     *
     * @return el boolean
     */
    @Override
    public boolean contains() {
        return !updates.isEmpty();
    }

    /**
     * Consume.
     *
     * @param consumer el consumer
     */
    @Override
    public void consume(Consumer<? super String> consumer) {
        synchronized (updates) {
            updates.forEach(consumer);
            updates.clear();
        }
    }

    /**
     * Update last connection.
     */
    public final void updateLastConnection() {
        setLastConnection(System.currentTimeMillis());
        setAvailable(true);
    }

}
