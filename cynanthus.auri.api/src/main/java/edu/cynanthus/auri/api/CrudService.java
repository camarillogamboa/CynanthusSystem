package edu.cynanthus.auri.api;

/**
 * La interface Data service.
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
