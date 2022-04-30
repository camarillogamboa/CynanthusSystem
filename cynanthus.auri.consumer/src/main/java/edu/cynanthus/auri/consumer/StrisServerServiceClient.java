package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.List;

class StrisServerServiceClient extends AuriApiClient implements StrisServerService {

    StrisServerServiceClient(ClientInfo clientInfo) {
        super(clientInfo);
    }

    @Override
    public StrisConfig getConfigOf(ServerInfo serverInfo) {
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
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return null;
    }

    @Override
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
