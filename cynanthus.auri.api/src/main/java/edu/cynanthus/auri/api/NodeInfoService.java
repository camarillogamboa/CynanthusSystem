package edu.cynanthus.auri.api;

import edu.cynanthus.domain.NodeInfo;

import java.util.List;

public interface NodeInfoService extends BeanService<NodeInfo> {

    List<? extends NodeInfo> readByIdServerInfo(Integer idServerInfo);

}
