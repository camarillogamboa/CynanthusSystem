package edu.cynanthus.auri.api;

public class WrappedDataService<T> implements DataService<T> {

    private final DataService<T> dataService;

    public WrappedDataService(DataService<T> dataService) {
        this.dataService = dataService;
    }

    @Override
    public T create(T data) {
        return dataService.create(data);
    }

    @Override
    public T read(T data) {
        return dataService.read(data);
    }

    @Override
    public T update(T data) {
        return dataService.update(data);
    }

    @Override
    public T delete(T data) {
        return dataService.delete(data);
    }

}
