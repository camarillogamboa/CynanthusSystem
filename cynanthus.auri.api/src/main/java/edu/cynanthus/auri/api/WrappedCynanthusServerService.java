package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

/**
 * El tipo Wrapped cynanthus server service.
 *
 * @param <T> el parámetro de tipo
 */
public class WrappedCynanthusServerService<T extends Config>
    extends WrappedConfigurationServerService<T> implements CynanthusServerService<T> {

    /**
     * El Cynanthus server service.
     */
    private final CynanthusServerService<T> cynanthusServerService;

    /**
     * Instancia un nuevo Wrapped cynanthus server service.
     *
     * @param cynanthusServerService el cynanthus server service
     */
    public WrappedCynanthusServerService(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
        this.cynanthusServerService = cynanthusServerService;
    }

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return cynanthusServerService.isAvailable(serverInfo);
    }

}
