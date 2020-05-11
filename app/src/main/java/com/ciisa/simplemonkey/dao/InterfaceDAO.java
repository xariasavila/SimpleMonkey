package com.ciisa.simplemonkey.dao;

import java.util.ArrayList;

interface InterfaceDAO<T,G> {
    boolean insert(T t);

    ArrayList<T> findAll(int limit);

    T findById(G id);

    boolean update(T t);

    boolean deleteById(G id);
}