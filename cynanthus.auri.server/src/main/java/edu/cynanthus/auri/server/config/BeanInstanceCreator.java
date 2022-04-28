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

/**
 * La interface Bean instance creator.
 *
 * @param <B> el parámetro de tipo
 */
@FunctionalInterface
interface BeanInstanceCreator<B extends Bean> extends InstanceCreator<B> {

    /**
     * La constante SERVER_INFO_BEAN_INSTANCE_CREATOR.
     */
    BeanInstanceCreator<ServerInfo> SERVER_INFO_BEAN_INSTANCE_CREATOR = type -> new ServerInfoEntity();
    /**
     * La constante NODE_INFO_BEAN_INSTANCE_CREATOR.
     */
    BeanInstanceCreator<NodeInfo> NODE_INFO_BEAN_INSTANCE_CREATOR = type -> new NodeInfoEntity();
    /**
     * La constante USER_BEAN_INSTANCE_CREATOR.
     */
    BeanInstanceCreator<User> USER_BEAN_INSTANCE_CREATOR = type -> new UserEntity();
    /**
     * La constante INSTRUCTION_SET_BEAN_INSTANCE_CREATOR.
     */
    BeanInstanceCreator<InstructionSet> INSTRUCTION_SET_BEAN_INSTANCE_CREATOR = type -> new InstructionSetEntity();

}
