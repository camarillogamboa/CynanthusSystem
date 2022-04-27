package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.auri.server.entity.ServerInfoEntity;
import edu.cynanthus.auri.server.repository.ServerInfoRepository;
import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.NaturalIdCandidate;
import edu.cynanthus.bean.ValueUpdater;
import edu.cynanthus.domain.ServerInfo;

import java.util.Optional;

class BasicServerInfoService
    extends BasicBeanService<Integer, ServerInfo, ServerInfoEntity> implements ServerInfoService {

    private final ServerInfoRepository jpa;

    BasicServerInfoService(ServerInfoRepository jpa) {
        super(jpa);
        this.jpa = jpa;
    }

    @Override
    public ServerInfo create(ServerInfo serverInfo) {
        checkNotNull(serverInfo);
        serverInfo.setId(null);
        return jpa.save((ServerInfoEntity) serverInfo);
    }

    @Override
    public ServerInfo read(ServerInfo serverInfo) {
        checkNotNull(serverInfo);
        return safeFind(serverInfo);
    }

    @Override
    public ServerInfo update(ServerInfo serverInfo) {
        checkNotNull(serverInfo);
        ServerInfoEntity entity = safeFind(serverInfo);

        ValueUpdater.updateIfNotNull(serverInfo.getName(), entity::setName);
        ValueUpdater.updateIfNotNull(serverInfo.getAddress(), entity::setAddress);
        ValueUpdater.updateIfNotNull(serverInfo.getPort(), entity::setPort);
        ValueUpdater.updateIfNotNull(serverInfo.getServerType(), entity::setServerType);
        ValueUpdater.updateIfNotNull(serverInfo.getInfo(), entity::setInfo);

        return jpa.save(entity);
    }

    @Override
    public ServerInfo delete(ServerInfo serverInfo) {
        checkNotNull(serverInfo);
        ServerInfo result = safeFind(serverInfo);
        jpa.deleteById(result.getId());
        return result;
    }

    @Override
    void checkNotNull(ServerInfo bean) {
        if (bean == null)
            throw new ServiceException("El elemento ServerInfo no debe ser nulo", ExceptionType.NULL_POINTER);
    }

    @Override
    Optional<ServerInfoEntity> find(ServerInfo serverInfo) {
        if (BeanValidation.validate(serverInfo, IdCandidate.class).isEmpty())
            return jpa.findById(serverInfo.getId());
        else if (BeanValidation.validate(serverInfo, NaturalIdCandidate.class).isEmpty())
            return jpa.findByName(serverInfo.getName());

        throw new ServiceException(
            "Se requiere un identificador válido del ServerInfo",
            ExceptionType.INVALID_ID
        );
    }

    @Override
    ServerInfoEntity safeFind(ServerInfo bean) {
        return find(bean).orElseThrow(() -> new ServiceException(
            "Registro ServerInfo{" +
                (bean.getId() != null ? bean.getId() : bean.getName()) +
                "} no existe", ExceptionType.NO_EXISTING_ELEMENT
        ));
    }

}
