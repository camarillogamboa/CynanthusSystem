package edu.cynanthus.auri.api;

public interface DataService<T> {

    T create(T data);

    T read(T data);

    T update(T data);

    T delete(T data);

}
