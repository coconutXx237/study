package ru.example.tasks.task2;

import java.util.List;

public class Task2Main {
    public static void main(String[] args) {
        List<Long> longList= List.of(1L, 2L, 3L);
        List<Integer> integerList= List.of(10, 20, 30);
        List<Double> doubleList = List.of(11.1, 22.2, 33.3);

        summ(longList, integerList, doubleList);
    }

    public static void summ(List<? extends Number>... lists) {
        double summ = 0.0;
        for(List<? extends Number> list : lists){
            for(Number number : list){
                summ += number.doubleValue();
            }
        }
        System.out.println("sum: " + summ);
    }
}
