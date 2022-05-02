package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Bean;

import java.util.List;

/**
 * La interface Bean service.
 *
 * @param <T> el parámetro de tipo
 */
public interface BeanService<T extends Bean> extends CrudService<T> {

    /**
     * Read list.
     *
     * @return el list
     */
    List<? extends T> read();

    /**
     * Delete list.
     *
     * @return el list
     */
    List<? extends T> delete();

}
