package edu.cynanthus.auri.server.config;

import com.google.gson.InstanceCreator;
import edu.cynanthus.auri.server.entity.InstructionSetEntity;
import edu.cynanthus.auri.server.entity.NodeInfoEntity;
import edu.cynanthus.auri.server.entity.ServerInfoEntity;
import edu.cynanthus.auri.server.entity.UserEntity;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.User;

@FunctionalInterface
interface BeanInstanceCreator<B extends Bean> extends InstanceCreator<B> {

    BeanInstanceCreator<ServerInfo> SERVER_INFO_BEAN_INSTANCE_CREATOR = type -> new ServerInfoEntity();
    BeanInstanceCreator<NodeInfo> NODE_INFO_BEAN_INSTANCE_CREATOR = type -> new NodeInfoEntity();
    BeanInstanceCreator<User> USER_BEAN_INSTANCE_CREATOR = type -> new UserEntity();
    BeanInstanceCreator<InstructionSet> INSTRUCTION_SET_BEAN_INSTANCE_CREATOR = type -> new InstructionSetEntity();

}
