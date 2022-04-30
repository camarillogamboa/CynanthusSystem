package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.List;

public class LatiroServerServiceClient extends AuriApiClient implements LatiroServerService {

    LatiroServerServiceClient(ClientInfo clientInfo) {
        super(clientInfo);
    }

    @Override
    public LatiroConfig getConfigOf(ServerInfo serverInfo) {
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

    @Override
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
