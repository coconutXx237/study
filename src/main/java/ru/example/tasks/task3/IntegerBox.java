package ru.example.tasks.task3;

public class IntegerBox extends Box<Integer> {
    @Override
    public void put(Integer i) {
        setValue(i);
        System.out.println("intVal was set to " + get());
    }

    @Override
    public Integer get() {
        return getValue();
    }
}
