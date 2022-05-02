package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Bean;

import java.util.List;

/**
 * El tipo Wrapped bean service.
 *
 * @param <T> el parámetro de tipo
 */
public class WrappedBeanService<T extends Bean> extends WrappedCrudService<T> implements BeanService<T> {

    /**
     * El Bean service.
     */
    private final BeanService<T> beanService;

    /**
     * Instancia un nuevo Wrapped bean service.
     *
     * @param beanService el bean service
     */
    public WrappedBeanService(BeanService<T> beanService) {
        super(beanService);
        this.beanService = beanService;
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> read() {
        return beanService.read();
    }


    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    public List<? extends T> delete() {
        return beanService.delete();
    }

}
