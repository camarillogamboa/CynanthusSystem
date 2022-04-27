package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Bean;

import java.util.List;

public class WrappedBeanService<T extends Bean> extends WrappedDataService<T> implements BeanService<T> {

    private final BeanService<T> beanService;

    public WrappedBeanService(BeanService<T> beanService) {
        super(beanService);
        this.beanService = beanService;
    }

    @Override
    public List<? extends T> read() {
        return beanService.read();
    }


    @Override
    public List<? extends T> delete() {
        return beanService.delete();
    }

}
