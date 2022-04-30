package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.ClientInfo;

public final class AuriClients {

    private AuriClients() {}

    public static ServerInfoService createServerInfoServiceClient(ClientInfo clientInfo) {
        return new ServerInfoServiceConsumer(clientInfo);
    }

    public static NodeInfoService createNodeInfoServiceClient(ClientInfo clientInfo) {
        return new NodeInfoServiceConsumer(clientInfo);
    }

    public static InstructionSetService createInstructionSetServiceClient(ClientInfo clientInfo) {
        return new InstructionSetServiceConsumer(clientInfo);
    }

    public static UserService createUserServiceClient(ClientInfo clientInfo) {
        return new UserServiceConsumer(clientInfo);
    }

    public static SordidusServerService createSordidusServerServiceClient(ClientInfo clientInfo) {
        return new SordidusServerServiceConsumer(clientInfo);
    }

    public static LatiroServerService createLatiroServerServiceClient(ClientInfo clientInfo) {
        return new LatiroServerServiceConsumer(clientInfo);
    }

    public static StrisServerService createStrisServerServiceClient(ClientInfo clientInfo) {
        return new StrisServerServiceConsumer(clientInfo);
    }

}
