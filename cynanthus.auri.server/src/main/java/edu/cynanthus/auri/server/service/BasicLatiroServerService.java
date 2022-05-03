package edu.cynanthus.auri.server.service;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.domain.*;
import edu.cynanthus.domain.config.LatiroConfig;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Service
public class BasicLatiroServerService extends BasicCynanthusServerService<LatiroConfig> implements LatiroServerService {

    private final NodeInfoService nodeInfoService;

    @Autowired
    public BasicLatiroServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService,
        @Qualifier("transactionalNodeInfoService") NodeInfoService nodeInfoService
    ) {
        super(serverInfoService, "/cynanthus/latiro", LatiroConfig.class, ServerType.STREAM_DATA);
        this.nodeInfoService = Objects.requireNonNull(nodeInfoService);
    }

    @Override
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkType(fullServerInfo);

        List<? extends NodeInfo> nodeInfos = nodeInfoService.readAllByIdServerInfo(fullServerInfo.getId());

        List<SensingNode> sensingNodes = consume(lazyRequest ->
            lazyRequest.GET(buildUri(fullServerInfo,"/node")),
            new TypeToken<List<SensingNode>>(){}.getType()
        );

        Map<String,NodeInfo> nodeInfoMap = new TreeMap<>(String::compareTo);
        Map<String,SensingNode> sensingNodeMap = new TreeMap<>(String::compareTo);

        return null;
    }

}
