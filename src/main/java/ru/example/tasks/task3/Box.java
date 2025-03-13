package ru.example.tasks.task3;

public abstract class Box<T> {
    private T value;

    abstract T get();

    abstract void put(T t);

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
