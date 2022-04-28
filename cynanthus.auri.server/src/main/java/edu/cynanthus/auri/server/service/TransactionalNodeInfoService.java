package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.server.repository.NodeInfoRepository;
import edu.cynanthus.domain.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * El tipo Transactional node info service.
 */
@Service
@Qualifier("transactionalNodeInfoService")
public class TransactionalNodeInfoService extends TransactionalBeanService<NodeInfo> implements NodeInfoService {

    /**
     * El Node info service.
     */
    private final NodeInfoService nodeInfoService;

    /**
     * Instancia un nuevo Transactional node info service.
     *
     * @param nodeInfoService el node info service
     */
    private TransactionalNodeInfoService(NodeInfoService nodeInfoService) {
        super(nodeInfoService);
        this.nodeInfoService = nodeInfoService;
    }

    /**
     * Instancia un nuevo Transactional node info service.
     *
     * @param nodeInfoRepository el node info repository
     */
    @Autowired
    public TransactionalNodeInfoService(NodeInfoRepository nodeInfoRepository) {
        this(new BasicNodeInfoService(nodeInfoRepository));
    }


    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return nodeInfoService.readAllByIdServerInfo(idServerInfo);
    }

}
