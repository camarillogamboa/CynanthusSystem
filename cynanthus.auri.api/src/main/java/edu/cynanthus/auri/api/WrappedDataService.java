package edu.cynanthus.auri.api;

/**
 * El tipo Wrapped data service.
 *
 * @param <T> el parámetro de tipo
 */
public class WrappedDataService<T> implements DataService<T> {

    /**
     * El Data service.
     */
    private final DataService<T> dataService;

    /**
     * Instancia un nuevo Wrapped data service.
     *
     * @param dataService el data service
     */
    public WrappedDataService(DataService<T> dataService) {
        this.dataService = dataService;
    }

    /**
     * Create t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T create(T data) {
        return dataService.create(data);
    }

    /**
     * Read t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T read(T data) {
        return dataService.read(data);
    }

    /**
     * Update t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T update(T data) {
        return dataService.update(data);
    }

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    public T delete(T data) {
        return dataService.delete(data);
    }

}
