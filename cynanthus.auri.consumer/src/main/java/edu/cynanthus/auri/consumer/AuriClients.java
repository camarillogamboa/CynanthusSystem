package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.ClientInfo;

public final class AuriClients {

    private AuriClients() {}

    public static ServerInfoService createServerInfoServiceClient(ClientInfo clientInfo) {
        return new ServerInfoServiceClient(clientInfo);
    }

    public static NodeInfoService createNodeInfoServiceClient(ClientInfo clientInfo) {
        return new NodeInfoServiceClient(clientInfo);
    }

    public static InstructionSetService createInstructionSetServiceClient(ClientInfo clientInfo) {
        return new InstructionSetServiceClient(clientInfo);
    }

    public static UserService createUserServiceClient(ClientInfo clientInfo) {
        return new UserServiceClient(clientInfo);
    }

    public static SordidusServerService createSordidusServerServiceClient(ClientInfo clientInfo) {
        return new SordidusServerServiceClient(clientInfo);
    }

    public static LatiroServerService createLatiroServerServiceClient(ClientInfo clientInfo) {
        return new LatiroServerServiceClient(clientInfo);
    }

    public static StrisServerService createStrisServerServiceClient(ClientInfo clientInfo) {
        return new StrisServerServiceClient(clientInfo);
    }

}
