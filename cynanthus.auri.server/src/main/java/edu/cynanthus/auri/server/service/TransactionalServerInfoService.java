package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.server.repository.ServerInfoRepository;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionalServerInfoService extends TransactionalBeanService<ServerInfo> implements ServerInfoService {

    @Autowired
    public TransactionalServerInfoService(ServerInfoRepository serverInfoRepository) {
        super(new BasicServerInfoService(serverInfoRepository));
    }

}
