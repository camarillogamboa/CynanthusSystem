package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.error.InvalidDataException;
import edu.cynanthus.auri.api.error.NoExistingElementException;
import edu.cynanthus.auri.api.error.ServiceException;
import edu.cynanthus.auri.server.entity.NodeInfoEntity;
import edu.cynanthus.auri.server.repository.NodeInfoRepository;
import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.NaturalIdCandidate;
import edu.cynanthus.bean.ValueUpdater;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;
import java.util.Optional;

class BasicNodeInfoService
    extends BasicBeanService<Integer, NodeInfo, NodeInfoEntity> implements NodeInfoService {

    private final NodeInfoRepository jpa;

    BasicNodeInfoService(NodeInfoRepository jpa) {
        super(jpa);
        this.jpa = jpa;
    }

    @Override
    public NodeInfo create(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        nodeInfo.setId(null);
        return jpa.save((NodeInfoEntity) nodeInfo);
    }

    @Override
    public NodeInfo read(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        return safeFind(nodeInfo);
    }

    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return jpa.findAllByIdServerInfo(idServerInfo);
    }

    @Override
    public NodeInfo update(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);

        NodeInfoEntity entity = safeFind(nodeInfo);

        ValueUpdater.updateIfNotNull(nodeInfo.getMac(), entity::setMac);
        ValueUpdater.updateIfNotNull(nodeInfo.getName(), entity::setName);
        ValueUpdater.updateIfNotNull(nodeInfo.getIdServerInfo(), entity::setIdServerInfo);
        entity.setIdSet(nodeInfo.getIdSet());

        return jpa.save(entity);
    }

    @Override
    public NodeInfo delete(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        NodeInfo result = safeFind(nodeInfo);
        jpa.deleteById(result.getId());
        return result;
    }

    @Override
    void checkNotNull(NodeInfo bean) {
        if (bean == null)
            throw new ServiceException("El elemento NodeInfo no debe ser nulo");
    }

    @Override
    Optional<NodeInfoEntity> find(NodeInfo nodeInfo) {
        if (BeanValidation.validate(nodeInfo, IdCandidate.class).isEmpty())
            return jpa.findById(nodeInfo.getId());
        else if (BeanValidation.validate(nodeInfo, NaturalIdCandidate.class).isEmpty())
            return jpa.findByMac(nodeInfo.getMac());

        throw new InvalidDataException("Se requiere un identificador válido del NodeInfo");
    }

    @Override
    NodeInfoEntity safeFind(NodeInfo bean) {
        return find(bean).orElseThrow(() -> new NoExistingElementException(
            "Registro NodeInfo{" +
                (bean.getId() != null ? bean.getId() : bean.getMac()) +
                "} no existe"
        ));
    }

}
