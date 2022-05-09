package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.HostAddress;
import edu.cynanthus.domain.User;

import java.net.http.HttpClient;
import java.util.Map;

public interface AuriSession {

    ServerInfoService serverInfoService(Map<String, String> headers);

    ServerInfoService serverInfoService();

    NodeInfoService nodeInfoService(Map<String, String> headers);

    NodeInfoService nodeInfoService();

    InstructionSetService instructionSetService(Map<String, String> headers);

    InstructionSetService instructionSetService();

    UserService userService(Map<String, String> headers);

    UserService userService();

    SordidusServerService sordidusServerService(Map<String, String> headers);

    SordidusServerService sordidusServerService();

    LatiroServerService latiroServerService(Map<String, String> headers);

    LatiroServerService latiroServerService();

    StrisServerService strisServerService(Map<String, String> headers);

    StrisServerService strisServerService();

    static AuriSession createRenewableSession(HttpClient httpClient, HostAddress hostAddress, User user) {
        return new RenewableAuriSession(httpClient, hostAddress, user);
    }

    static AuriSession createRenewableSession(HostAddress hostAddress, User user) {
        return createRenewableSession(HttpClient.newHttpClient(), hostAddress, user);
    }

}
