package edu.cynanthus.auri.api;

/**
 * La interface CrudService expone las 4 operaciones CRUD que pueden ser
 * ejecutadas sobre un recurso en especifico.
 *
 * @param <T> el parámetro de tipo
 */
public interface CrudService<T> extends AuriService {

    /**
     * Create t.
     *
     * @param data el data
     * @return el t
     */
    T create(T data);

    /**
     * Read t.
     *
     * @param data el data
     * @return el t
     */
    T read(T data);

    /**
     * Update t.
     *
     * @param data el data
     * @return el t
     */
    T update(T data);

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    T delete(T data);

}
