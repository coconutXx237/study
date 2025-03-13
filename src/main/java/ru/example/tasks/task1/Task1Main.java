package ru.example.tasks.task1;

import java.util.List;

public class Task1Main {
    public static void main(String[] args) {
        List<Film> films = Parser.getCSVAsList("NetflixOriginals.csv");
        Stats stats = new Stats(films);
        System.out.println("Количество фильмов по жанрам: " + stats.countFilmsByGenre());
        System.out.println("Количество фильмов по языкам: " + stats.countFilmsByLanguage());
        System.out.println("Топ 5 самых популярных жанров: " + stats.getTop5ByGenre());
        System.out.println("Топ 5 самых популярных языков: " + stats.getTop5ByLanguage());
        System.out.println("Самый новый фильм: " + stats.getLatestFilm());
        System.out.println("Самый старый фильм: " + stats.getOldestFilm());
        System.out.printf("Общая длительность всех фильмов: %s дней, %s часов",
                stats.getTotalRuntime().get("days"), stats.getTotalRuntime().get("hours"));
        System.out.println();
        System.out.println("Лучший/худший фильмы по годам:");
        PrinterUtil.printMaxMinRatingsByYear(stats.getYearlyBestWorstRatings(), films);
    }
}