package edu.cynanthus.auri.server.controller;

import edu.cynanthus.auri.api.BeanService;
import edu.cynanthus.auri.api.WrappedBeanService;
import edu.cynanthus.bean.Bean;
import edu.cynanthus.bean.Required;
import edu.cynanthus.bean.ValidInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public T create(@RequestBody @Validated(Required.class) T bean) {
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
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public T update(@RequestBody @Validated(ValidInfo.class) T bean) {
        return super.update(bean);
    }

    @Override
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public List<? extends T> delete() {
        return super.delete();
    }

    @Override
    @DeleteMapping("/{id:\\d+}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGENT','ADMIN')")
    public T delete(T data) {
        return super.delete(data);
    }

}
