package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.server.repository.ServerInfoRepository;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * El tipo Transactional server info service.
 */
@Service
@Qualifier("transactionalServerInfoService")
public class TransactionalServerInfoService extends TransactionalBeanService<ServerInfo> implements ServerInfoService {

    /**
     * Instancia un nuevo Transactional server info service.
     *
     * @param serverInfoRepository el server info repository
     */
    @Autowired
    public TransactionalServerInfoService(ServerInfoRepository serverInfoRepository) {
        super(new BasicServerInfoService(serverInfoRepository));
    }

}
