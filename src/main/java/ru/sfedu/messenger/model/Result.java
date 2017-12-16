package ru.sfedu.messenger.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vovka
 */
public class Result<T> {
    ResultType resType;
    T resObject = null;
    List<T> list = new ArrayList(); 

    public Result() {
    }

    public Result(ResultType resType) {
        this.resType = resType;
    }

    public Result(ResultType resType, T resObject) {
        this.resType = resType;
        this.resObject = resObject;
    }
    
    public Result(ResultType resType, List<T> list) {
        this.resType = resType;
        this.list = list;
    }

    public ResultType getResType() {
        return resType;
    }

    public void setResType(ResultType resType) {
        this.resType = resType;
    }

    public T getResObject() {
        return resObject;
    }

    public void setResObject(T resObject) {
        this.resObject = resObject;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
