package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

class TransactionalBeanService<T extends Bean> extends WrappedBeanService<T> {

    TransactionalBeanService(BeanService<T> beanService) {
        super(beanService);
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends T> read() {
        return super.read();
    }

    @Override
    @Transactional
    public List<? extends T> delete() {
        return super.delete();
    }

    @Override
    @Transactional
    public T create(T bean) {
        return super.create(bean);
    }

    @Override
    @Transactional(readOnly = true)
    public T read(T bean) {
        return super.read(bean);
    }

    @Override
    @Transactional
    public T update(T bean) {
        return super.update(bean);
    }

    @Override
    @Transactional
    public T delete(T bean) {
        return super.delete(bean);
    }

}
