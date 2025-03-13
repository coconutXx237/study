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

    public static List<String> convert(List<Integer> list, Function<Integer, String> func) {
        return list.stream().
                map(el -> func.apply(el))
                .collect(Collectors.toList());
    }
}
