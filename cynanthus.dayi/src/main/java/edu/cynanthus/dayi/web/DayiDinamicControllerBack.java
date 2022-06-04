package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.Config;
import edu.cynanthus.dayi.domain.*;
import edu.cynanthus.dayi.util.PropertyInfoBuilder;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.LinkedList;
import java.util.List;

public class DayiDinamicControllerBack extends CommonController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public DayiDinamicControllerBack(AuriSession auriSession, SimpMessagingTemplate simpMessagingTemplate) {
        super(auriSession);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    //@MessageMapping("/server/{id:\\d+}/sensing")
    public void loadSensingNodesViewContent(@DestinationVariable Integer id) {
        System.out.println("EJECUTANDO loadSensingNodesViewContent");

        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));

        Boolean available = isAvailable(serverInfo);

        List<GeneralNode<SensingNode>> sensingNodes
            = auriSession.latiroServerService().getGeneralNodesOf(serverInfo, "*");

        SensingNodesViewContent sensingNodesViewContent = new SensingNodesViewContent(
            serverInfo,
            available,
            sensingNodes
        );

        simpMessagingTemplate.convertAndSend(
            "/topic/server/" + serverInfo.getId() + "/sensing",
            sensingNodesViewContent
        );
    }

    //@MessageMapping("/server/{id:\\d+}/control")
    public void loadControlNodesViewContent(@DestinationVariable Integer id) {
        System.out.println("EJECUNTANDO loadControlNodesViewContent");

        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));
        Boolean available = isAvailable(serverInfo);

        List<GeneralNode<ControlNode>> controlNodes
            = auriSession.strisServerService().getGeneralNodesOf(serverInfo, "*");

        ControlNodesViewContent controlNodesViewContent = new ControlNodesViewContent(
            serverInfo,
            available,
            controlNodes
        );

        simpMessagingTemplate.convertAndSend(
            "/topic/server/" + serverInfo.getId() + "/control",
            controlNodesViewContent
        );
    }

    //@MessageMapping("/server/{id:\\d+}/properties")
    public void loadServerPropertiesViewContent(@DestinationVariable Integer id) {
        System.out.println("EJECUTANDO loadServerPropertiesViewContent");
        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));

        CynanthusServerService<?> cynanthusServerService = auriSession.cynanthusServerService(
            serverInfo.getServerType()
        );

        Boolean available = isAvailable(cynanthusServerService, serverInfo);

        List<PropertyInfo> propertyInfos = new LinkedList<>();

        if (available) {
            Config config = cynanthusServerService.getConfigOf(serverInfo);
            PropertyInfoBuilder.toList(config, propertyInfos);
        }

        ServerPropertiesViewContent serverPropertiesViewContent = new ServerPropertiesViewContent(
            serverInfo,
            available,
            propertyInfos
        );

        simpMessagingTemplate.convertAndSend(
            "/topic/server/" + serverInfo.getId() + "/properties",
            serverPropertiesViewContent
        );
    }

    //@MessageMapping("/server/{id:\\d+}/log")
    public void loadServerLogViewContent(@DestinationVariable Integer id) {
        System.out.println("EJECUTANDO loadServerLogViewContent");

        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));
        CynanthusServerService<?> cynanthusServerService = auriSession.cynanthusServerService(serverInfo.getServerType());

        Boolean available = isAvailable(cynanthusServerService, serverInfo);

        String[] logFileNames = available ? cynanthusServerService.getLogFilesOf(serverInfo) : new String[]{};

        LogServerViewContent logServerViewContent = new LogServerViewContent(serverInfo, available, logFileNames);

        simpMessagingTemplate.convertAndSend(
            "/topic/server/" + serverInfo.getId() + "/log",
            logServerViewContent
        );
    }

}
