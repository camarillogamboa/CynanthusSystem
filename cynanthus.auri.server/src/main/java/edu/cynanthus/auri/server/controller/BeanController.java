package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BeanController<T extends Bean> extends WrappedBeanService<T> {

    BeanController(BeanService<T> beanService) {
        super(beanService);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody @Validated(Required.class) T bean) {
        return super.create(bean);
    }

    @Override
    @GetMapping
    public List<? extends T> read() {
        return super.read();
    }

    @Override
    @GetMapping("/{id:\\d+}")
    public T read(T bean) {
        return super.read(bean);
    }

    @Override
    @PutMapping
    public T update(@RequestBody @Validated(ValidInfo.class) T bean) {
        return super.update(bean);
    }

    @Override
    public List<? extends T> delete() {
        return super.delete();
    }

    @Override
    @DeleteMapping("/{id:\\d+}")
    public T delete(T data) {
        return super.delete(data);
    }

}
