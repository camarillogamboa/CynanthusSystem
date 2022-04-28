package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * El tipo Basic bean service.
 *
 * @param <ID> el parámetro de tipo
 * @param <B>  el parámetro de tipo
 * @param <E>  el parámetro de tipo
 */
abstract class BasicBeanService<ID, B extends Bean, E extends B> implements BeanService<B> {

    /**
     * El Jpa.
     */
    private final JpaRepository<E, ID> jpa;

    /**
     * Instancia un nuevo Basic bean service.
     *
     * @param jpa el jpa
     */
    BasicBeanService(JpaRepository<E, ID> jpa) {
        this.jpa = Objects.requireNonNull(jpa);
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    public List<? extends B> read() {
        return jpa.findAll();
    }

    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    public List<? extends B> delete() {
        List<? extends B> result = read();
        jpa.deleteAll();
        return result;
    }

    /**
     * Check not null.
     *
     * @param bean el bean
     */
    abstract void checkNotNull(B bean);

    /**
     * Find optional.
     *
     * @param bean el bean
     * @return el optional
     */
    abstract Optional<E> find(B bean);

    /**
     * Safe find e.
     *
     * @param bean el bean
     * @return el e
     */
    abstract E safeFind(B bean);

}
