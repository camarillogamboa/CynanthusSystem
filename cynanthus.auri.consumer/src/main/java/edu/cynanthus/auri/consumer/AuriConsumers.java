package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.*;
import edu.cynanthus.common.net.ClientInfo;

public final class AuriConsumers {

    private AuriConsumers() {}

    public static ServerInfoService createServerInfoServiceConsumer(ClientInfo clientInfo) {
        return new ServerInfoServiceConsumer(clientInfo);
    }

    public static NodeInfoService createNodeInfoServiceConsumer(ClientInfo clientInfo) {
        return new NodeInfoServiceConsumer(clientInfo);
    }

    public static InstructionSetService createInstructionSetServiceConsumer(ClientInfo clientInfo) {
        return new InstructionSetServiceConsumer(clientInfo);
    }

    public static UserService createUserServiceConsumer(ClientInfo clientInfo) {
        return new UserServiceConsumer(clientInfo);
    }

    public static SordidusServerService createSordidusServerServiceConsumer(ClientInfo clientInfo) {
        return new SordidusServerServiceConsumer(clientInfo);
    }

    public static LatiroServerService createLatiroServerServiceConsumer(ClientInfo clientInfo) {
        return new LatiroServerServiceConsumer(clientInfo);
    }

    public static StrisServerService createStrisServerServiceConsumer(ClientInfo clientInfo) {
        return new StrisServerServiceConsumer(clientInfo);
    }

}
