package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.server.repository.NodeInfoRepository;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
    @Transactional(readOnly = true)
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return nodeInfoService.readAllByIdServerInfo(idServerInfo);
    }

}
