package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.bean.Config;
import edu.cynanthus.dayi.domain.PropertyInfo;
import edu.cynanthus.dayi.domain.ServerPropertiesViewContent;
import edu.cynanthus.dayi.util.PropertyInfoBuilder;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Controller
public class DayiDinamicController extends CommonController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public DayiDinamicController(AuriSession auriSession, SimpMessagingTemplate simpMessagingTemplate) {
        super(auriSession);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/server/{id:\\d+}/sensing")
    public void loadControlNodesViewContent(@DestinationVariable Integer id) {
        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));
    }

    @MessageMapping("/server/{id:\\d+}/control")
    public void loadSensingNodesViewContent(@DestinationVariable Integer id) {
        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));
        System.out.println("EJECUTANDO");
    }

    @MessageMapping("/server/{id:\\d+}/properties")
    public void loadServerPropertiesViewContent(@DestinationVariable Integer id) {
        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));

        CynanthusServerService<?> cynanthusServerService = auriSession.cynanthusServerService(
            serverInfo.getServerType()
        );

        Boolean available = cynanthusServerService.isAvailable(serverInfo);

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

    @MessageMapping("/server/{id:\\+}/log")
    public void loadServerLogViewContent(@DestinationVariable Integer id) {
        ServerInfo serverInfo = findServerInfo(new ServerInfo(id));

    }

}
