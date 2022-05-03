package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

abstract class BasicBeanService<ID, B extends Bean, E extends B> implements BeanService<B> {

    private final JpaRepository<E, ID> jpa;

    BasicBeanService(JpaRepository<E, ID> jpa) {
        this.jpa = Objects.requireNonNull(jpa);
    }

    @Override
    public List<? extends B> read() {
        return jpa.findAll();
    }

    @Override
    public List<? extends B> delete() {
        List<? extends B> result = read();
        jpa.deleteAll();
        return result;
    }

    abstract void checkNotNull(B bean);

    abstract Optional<E> find(B bean);

    abstract E safeFind(B bean);

}
