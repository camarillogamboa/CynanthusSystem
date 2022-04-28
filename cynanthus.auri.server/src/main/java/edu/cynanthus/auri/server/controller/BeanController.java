package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * El tipo Bean controller.
 *
 * @param <T> el parámetro de tipo
 */
public class BeanController<T extends Bean> extends WrappedBeanService<T> {

    /**
     * Instancia un nuevo Bean controller.
     *
     * @param beanService el bean service
     */
    BeanController(BeanService<T> beanService) {
        super(beanService);
    }

    /**
     * Create t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public T create(@RequestBody T bean) {
        return super.create(bean);
    }

    /**
     * Read list.
     *
     * @return el list
     */
    @Override
    @GetMapping
    @ResponseBody
    public List<? extends T> read() {
        return super.read();
    }

    /**
     * Read t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public T read(T bean) {
        return super.read(bean);
    }

    /**
     * Update t.
     *
     * @param bean el bean
     * @return el t
     */
    @Override
    @PutMapping
    @ResponseBody
    public T update(@RequestBody T bean) {
        return super.update(bean);
    }

    /**
     * Delete list.
     *
     * @return el list
     */
    @Override
    @ResponseBody
    public List<? extends T> delete() {
        return super.delete();
    }

    /**
     * Delete t.
     *
     * @param data el data
     * @return el t
     */
    @Override
    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    public T delete(T data) {
        return super.delete(data);
    }

}
