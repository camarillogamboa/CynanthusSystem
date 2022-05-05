package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import org.springframework.http.HttpStatus;
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
    public T read(@PathVariable("id") T bean) {
        return super.read(bean);
    }

    @Override
    @PutMapping
    @ResponseBody
    public T update(@RequestBody T bean) {
        return super.update(bean);
    }

    @Override
    @ResponseBody
    public List<? extends T> delete() {
        return super.delete();
    }

    @Override
    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    public T delete(@PathVariable("id") T data) {
        return super.delete(data);
    }

}
