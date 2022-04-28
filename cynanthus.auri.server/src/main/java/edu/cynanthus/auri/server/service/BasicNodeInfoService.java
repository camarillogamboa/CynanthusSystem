package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.auri.server.entity.NodeInfoEntity;
import edu.cynanthus.auri.server.repository.NodeInfoRepository;
import edu.cynanthus.bean.BeanValidation;
import edu.cynanthus.bean.IdCandidate;
import edu.cynanthus.bean.NaturalIdCandidate;
import edu.cynanthus.bean.ValueUpdater;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;
import java.util.Optional;

/**
 * El tipo Basic node info service.
 */
class BasicNodeInfoService
    extends BasicBeanService<Integer, NodeInfo, NodeInfoEntity> implements NodeInfoService {

    /**
     * El Jpa.
     */
    private final NodeInfoRepository jpa;

    /**
     * Instancia un nuevo Basic node info service.
     *
     * @param jpa el jpa
     */
    BasicNodeInfoService(NodeInfoRepository jpa) {
        super(jpa);
        this.jpa = jpa;
    }

    /**
     * Create node info.
     *
     * @param nodeInfo el node info
     * @return el node info
     */
    @Override
    public NodeInfo create(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        nodeInfo.setId(null);
        return jpa.save((NodeInfoEntity) nodeInfo);
    }

    /**
     * Read node info.
     *
     * @param nodeInfo el node info
     * @return el node info
     */
    @Override
    public NodeInfo read(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        return safeFind(nodeInfo);
    }

    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        return jpa.findAllByIdServerInfo(idServerInfo);
    }

    /**
     * Update node info.
     *
     * @param nodeInfo el node info
     * @return el node info
     */
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

    /**
     * Delete node info.
     *
     * @param nodeInfo el node info
     * @return el node info
     */
    @Override
    public NodeInfo delete(NodeInfo nodeInfo) {
        checkNotNull(nodeInfo);
        NodeInfo result = safeFind(nodeInfo);
        jpa.deleteById(result.getId());
        return result;
    }

    /**
     * Check not null.
     *
     * @param bean el bean
     */
    @Override
    void checkNotNull(NodeInfo bean) {
        if (bean == null)
            throw new ServiceException("El elemento NodeInfo no debe ser nulo", ExceptionType.NULL_POINTER);
    }

    /**
     * Find optional.
     *
     * @param nodeInfo el node info
     * @return el optional
     */
    @Override
    Optional<NodeInfoEntity> find(NodeInfo nodeInfo) {
        if (BeanValidation.validate(nodeInfo, IdCandidate.class).isEmpty())
            return jpa.findById(nodeInfo.getId());
        else if (BeanValidation.validate(nodeInfo, NaturalIdCandidate.class).isEmpty())
            return jpa.findByMac(nodeInfo.getMac());

        throw new ServiceException(
            "Se requiere un identificador válido del NodeInfo",
            ExceptionType.INVALID_ID
        );
    }

    /**
     * Safe find node info entity.
     *
     * @param bean el bean
     * @return el node info entity
     */
    @Override
    NodeInfoEntity safeFind(NodeInfo bean) {
        return find(bean).orElseThrow(() -> new ServiceException(
            "Registro NodeInfo{" +
                (bean.getId() != null ? bean.getId() : bean.getMac()) +
                "} no existe", ExceptionType.NO_EXISTING_ELEMENT
        ));
    }

}
