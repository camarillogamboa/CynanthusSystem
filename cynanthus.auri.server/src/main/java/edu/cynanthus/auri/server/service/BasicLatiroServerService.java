package edu.cynanthus.auri.server.service;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerType;
import edu.cynanthus.domain.config.LatiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class BasicLatiroServerService
    extends BasicTreeServerService<LatiroConfig, SensingNode> implements LatiroServerService {

    private static final Type SENSING_NODE_LIST_TYPE = new TypeToken<List<SensingNode>>() {}.getType();

    @Autowired
    public BasicLatiroServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService,
        @Qualifier("transactionalNodeInfoService") NodeInfoService nodeInfoService
    ) {
        super(
            serverInfoService,
            nodeInfoService,
            "/cynanthus/latiro",
            LatiroConfig.class,
            ServerType.STREAM_DATA,
            SENSING_NODE_LIST_TYPE
        );
    }

}
