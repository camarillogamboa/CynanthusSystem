package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

public class WrappedCynanthusServerService<T extends Config>
    extends WrappedConfigurationServerService<T> implements CynanthusServerService<T> {

    private final CynanthusServerService<T> cynanthusServerService;

    public WrappedCynanthusServerService(CynanthusServerService<T> cynanthusServerService) {
        super(cynanthusServerService);
        this.cynanthusServerService = cynanthusServerService;
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return cynanthusServerService.isAvailable(serverInfo);
    }

}
