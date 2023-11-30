package org.example.lesson_4.s_4.task_1.v_2;

import java.util.ArrayList;
import java.util.List;

public class Result<T>{

    private final T value;

    private List<ValidationError> errors = new ArrayList<>();

    public T getValue() {
        return value;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public Result(T value, List<ValidationError> errors) {
        this.value = value;
        this.errors = errors;
    }
}
