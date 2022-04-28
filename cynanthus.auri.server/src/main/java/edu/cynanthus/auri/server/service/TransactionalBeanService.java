package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * El tipo Transactional bean service.
 *
 * @param <T> el parámetro de tipo
 */
class TransactionalBeanService<T extends Bean> extends WrappedBeanService<T> {

    /**
     * Instancia un nuevo Transactional bean service.
     *
     * @param beanService el bean service
     */
    TransactionalBeanService(BeanService<T> beanService) {
        super(beanService);
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    @Transactional(readOnly = true)
    public List<? extends T> read() {
        return super.read();
    }

    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    @Transactional
    public List<? extends T> delete() {
        return super.delete();
    }

    /**
     * Create t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @Transactional
    public T create(T bean) {
        return super.create(bean);
    }

    /**
     * Read t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @Transactional(readOnly = true)
    public T read(T bean) {
        return super.read(bean);
    }

    /**
     * Update t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @Transactional
    public T update(T bean) {
        return super.update(bean);
    }

    /**
     * Delete t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @Transactional
    public T delete(T bean) {
        return super.delete(bean);
    }

}
