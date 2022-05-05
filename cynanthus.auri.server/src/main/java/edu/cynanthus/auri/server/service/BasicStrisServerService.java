package edu.cynanthus.auri.server.service;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.InstructionSetService;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.auri.api.error.InvalidOperationException;
import edu.cynanthus.auri.server.entity.ServerInfoEntity;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.domain.*;
import edu.cynanthus.domain.config.StrisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Service
public class BasicStrisServerService
    extends BasicTreeServerService<StrisConfig, ControlNode> implements StrisServerService {

    private static final Type CONTROL_NODE_LIST_TYPE = new TypeToken<List<ControlNode>>() {}.getType();

    private final NodeInfoService nodeInfoService;
    private final InstructionSetService instructionSetService;

    @Autowired
    public BasicStrisServerService(
        @Qualifier("transactionalServerInfoService") ServerInfoService serverInfoService,
        @Qualifier("transactionalNodeInfoService") NodeInfoService nodeInfoService,
        @Qualifier("transactionalInstructionSetService") InstructionSetService instructionSetService
    ) {
        super(
            serverInfoService,
            nodeInfoService,
            "/cynanthus/stris",
            StrisConfig.class,
            ServerType.CONTROL,
            CONTROL_NODE_LIST_TYPE
        );
        this.nodeInfoService = Objects.requireNonNull(nodeInfoService);
        this.instructionSetService = Objects.requireNonNull(instructionSetService);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);
        return sendIndication(serverInfo, indication);
    }

    @Override
    public Boolean performIndication(NodeInfo nodeInfo, String instructionName) {
        NodeInfo fullNodeInfo = nodeInfoService.read(nodeInfo);

        ServerInfo serverInfo = new ServerInfoEntity();
        serverInfo.setId(fullNodeInfo.getIdServerInfo());
        serverInfo = serverInfoService.read(serverInfo);

        checkServerType(serverInfo);

        Instruction instruction = instructionSetService.readInstruction(getIdSet(nodeInfo), instructionName);

        Indication indication = new Indication(nodeInfo.getMac(), instruction.getValue());

        return sendIndication(serverInfo, indication);
    }

    private Boolean sendIndication(ServerInfo serverInfo, Indication indication) {
        return consume(
            lazyRequest -> lazyRequest.POST(
                buildUri(serverInfo, "/indication"),
                () -> StreamUtil.asInputStream(JsonProvider.toJson(indication))
            )
            , Boolean.class
        );
    }

    private Integer getIdSet(NodeInfo nodeInfo) {
        if (nodeInfo.getIdSet() != null) return nodeInfo.getIdSet();
        throw new InvalidOperationException(
            "El NodeInfo especificado no tiene un conjunto de instrucciones asociado"
        );
    }

}
