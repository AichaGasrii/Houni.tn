package com.esprit.achat.services.Interface;

import java.util.List;

public interface CrudService<T,ID>{
    List<T> retrieveAll();
    void add(T t);
    void update(T t);
    void remove(ID id);
    T retrieve(ID id);
}

