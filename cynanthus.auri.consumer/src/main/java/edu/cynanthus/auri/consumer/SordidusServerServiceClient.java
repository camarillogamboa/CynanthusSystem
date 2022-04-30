package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.SordidusConfig;

public class SordidusServerServiceClient extends AuriApiClient implements SordidusServerService {

    SordidusServerServiceClient(ClientInfo clientInfo) {
        super(clientInfo);
    }

    @Override
    public SordidusConfig getConfigOf(ServerInfo serverInfo) {
        return null;
    }

    @Override
    public String updateConfigOf(ServerInfo serverInfo, Config config) {
        return null;
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return new String[0];
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return null;
    }

    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        return null;
    }
}
