package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.server.repository.NodeInfoRepository;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("transactionalNodeInfoService")
public class TransactionalNodeInfoService extends TransactionalBeanService<NodeInfo> implements NodeInfoService {

    private final NodeInfoService nodeInfoService;

    private TransactionalNodeInfoService(NodeInfoService nodeInfoService) {
        super(nodeInfoService);
        this.nodeInfoService = nodeInfoService;
    }

    @Autowired
    public TransactionalNodeInfoService(NodeInfoRepository nodeInfoRepository) {
        this(new BasicNodeInfoService(nodeInfoRepository));
    }


    @Override
    public List<? extends NodeInfo> readByIdServerInfo(Integer idServerInfo) {
        return nodeInfoService.readByIdServerInfo(idServerInfo);
    }

}
