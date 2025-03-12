package ru.example.tasks.task3;

public class StringBox extends Box<String> {
    @Override
    public void put(String s) {
        setValue(s);
        System.out.println("strVal was set to " + get());
    }

    @Override
    public String get() {
        return getValue();
    }
}
