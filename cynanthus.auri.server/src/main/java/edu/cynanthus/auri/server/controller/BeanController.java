package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BeanController<T extends Bean> extends WrappedBeanService<T> {

    BeanController(BeanService<T> beanService) {
        super(beanService);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public T create(@RequestBody T bean) {
        return super.create(bean);
    }

    @Override
    @GetMapping
    @ResponseBody
    public List<? extends T> read() {
        return super.read();
    }

    @Override
    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public T read(T bean) {
        return super.read(bean);
    }

    @Override
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public T update(@RequestBody T bean) {
        return super.update(bean);
    }

    @Override
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public List<? extends T> delete() {
        return super.delete();
    }

    @Override
    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public T delete(T data) {
        return super.delete(data);
    }

}
