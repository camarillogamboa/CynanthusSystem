package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
public class DayiDinamicController extends CommonController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public DayiDinamicController(AuriSession auriSession, SimpMessagingTemplate simpMessagingTemplate) {
        super(auriSession);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/server/{id:\\d+}")
    public void loadServerState(@DestinationVariable Integer id){

    }

    @MessageMapping("/server/{id:\\d+}/sensing}")
    public void loadSensingData(@DestinationVariable Integer id){
        ServerInfo serverInfo = new ServerInfo(id, ServerType.STREAM_DATA);



    }

    @MessageMapping("/server/{id:\\d+}/control")
    public void loadControlData(@DestinationVariable Integer id){

    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event){

    }

}
