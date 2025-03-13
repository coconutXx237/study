package ru.example.tasks.task1;

import java.util.*;
import java.util.stream.Collectors;

public class Stats {

    private final List<Film> filmsList;

    public Stats(List<Film> filmsList) {
        this.filmsList = filmsList;
    }

    public Map<String, Long> countFilmsByGenre() {
        Map<String, Long> genresCount = filmsList.stream()
                .collect(Collectors.groupingBy(Film::getGenre, Collectors.counting()));
        return genresCount;
    }

    public Map<String, Long> countFilmsByLanguage() {
        Map<String, Long> languageCount = filmsList.stream()
                .collect(Collectors.groupingBy(Film::getLanguage, Collectors.counting()));
        return languageCount;
    }

    public Map<String, Long> getTop5ByGenre() {
        Map<String, Long> genresTop = filmsList.stream()
                .collect(Collectors.groupingBy(Film::getGenre, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        return genresTop;
    }

    public Map<String, Long> getTop5ByLanguage() {
        Map<String, Long> languagesTop = filmsList.stream()
                .collect(Collectors.groupingBy(Film::getLanguage, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        return languagesTop;
    }

    public Film getLatestFilm() {
        Film newestFilm = filmsList.stream()
                        .max(Comparator.comparing(Film::getPremiere))
                .orElse(null);
        return newestFilm;
    }

    public Film getOldestFilm() {
        Film oldestFilm = filmsList.stream()
                .min(Comparator.comparing(Film::getPremiere))
                .orElse(null);
        return oldestFilm;
    }

    public Map<String, Integer> getTotalRuntime() {
        var totalRuntimeMinutes = filmsList.stream()
                .mapToInt(Film::getRuntime)
                .sum();
        int totalRuntimeHours = totalRuntimeMinutes / 60;
        int totalRuntimeDays = totalRuntimeHours / 24;
        int leftRuntimeHours = totalRuntimeHours % 24;

        Map<String, Integer> result = new HashMap<>();
        result.put("days", totalRuntimeDays);
        result.put("hours", leftRuntimeHours);
        return result;
    }

    public Map<Integer, List<Film>> getYearlyBestWorstRatings() {
        Map<Integer, List<Film>> filmsByYear = filmsList.stream()
                .collect(Collectors.groupingBy(film -> film.getPremiere().getYear()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        return filmsByYear;
    }
}
