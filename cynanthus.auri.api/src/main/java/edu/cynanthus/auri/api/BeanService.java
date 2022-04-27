package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Bean;

import java.util.List;

public interface BeanService<T extends Bean> extends DataService<T> {

    List<? extends T> read();

    List<? extends T> delete();

}
