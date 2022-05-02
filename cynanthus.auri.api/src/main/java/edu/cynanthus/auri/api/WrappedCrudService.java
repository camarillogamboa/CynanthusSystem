package edu.cynanthus.auri.api;

import java.util.Objects;

/**
 * El tipo Wrapped data service.
 *
 * @param <T> el parámetro de tipo
 */
public class WrappedCrudService<T> implements CrudService<T> {

    /**
     * El Data service.
     */
    private final CrudService<T> crudService;

    /**
     * Instancia un nuevo Wrapped data service.
     *
     * @param crudService el data service
     */
    public WrappedCrudService(CrudService<T> crudService) {
        this.crudService = Objects.requireNonNull(crudService);
    }

    /**
     * Create t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T create(T data) {
        return crudService.create(data);
    }

    /**
     * Read t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T read(T data) {
        return crudService.read(data);
    }

    /**
     * Update t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T update(T data) {
        return crudService.update(data);
    }

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T delete(T data) {
        return crudService.delete(data);
    }

}
