package ru.example.tasks.task3;

public class Task3Main {
    public static void main(String[] args) {
        var strBox = new StringBox();
        strBox.put("Hello World");
        strBox.get();

        var intBox = new IntegerBox();
        intBox.put(123);
        
    }
}
