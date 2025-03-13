package ru.example.tasks.task1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PrinterUtil {
    public static void printMaxMinRatingsByYear(Map<Integer, List<Film>> filmsByYear, List<Film> filmsList) {
        filmsByYear.forEach((year, filmList) -> {
            Film bestFilm = filmList.stream()
                    .max(Comparator.comparingDouble(Film::getImdbScore))
                    .orElse(null);
            Film worstFilm = filmsList.stream()
                    .min(Comparator.comparingDouble(Film::getImdbScore))
                    .orElse(null);

            System.out.printf("\n    Год: %s", year);
            System.out.printf("\n        Лучший фильм: %s", bestFilm);
            System.out.printf("\n        Худший фильм: %s", worstFilm);
        });
    }
}
