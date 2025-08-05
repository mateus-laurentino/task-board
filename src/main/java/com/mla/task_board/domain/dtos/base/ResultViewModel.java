package com.mla.task_board.domain.dtos.base;

import java.util.ArrayList;
import java.util.List;

public class ResultViewModel<T> {
    public T result;
    public List<String> errors = new ArrayList<>();
    public boolean success = true;

    public ResultViewModel() { }

    public ResultViewModel(T result) {
        this.result = result;
    }

    public ResultViewModel<T> addResult(T result){
        this.result = result;
        return this;
    }

    public ResultViewModel<T> addErrors(String error){
        this.errors.add(error);
        success = false;

        return this;
    }
}
