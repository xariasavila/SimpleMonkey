package com.example.simplemonkey.dao;

import java.util.ArrayList;

interface InterfaceDAO<T> {
    boolean insert(T t);

    ArrayList<T> findAll();

    T findById(int id);

    boolean update(T t);

    boolean deleteById(int id);
}