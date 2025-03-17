package ru.example.tasks.task4;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task4Main {
    public static void main(String[] args) {
        var intList = List.of(100, 200, 300, 400, 500);
        var convertedList = convert(intList, i -> i.toString());
        System.out.println(convertedList);
    }

    public static<T, R> List<R> convert(List<T> list, Function<T, R> func) {
        return list.stream().
                map(func)
                .collect(Collectors.toList());
    }
}
