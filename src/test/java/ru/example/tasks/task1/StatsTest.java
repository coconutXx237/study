package ru.example.tasks.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatsTest {

    Stats stats;
    List<Film> testFilms;

    @Mock
    private List<Film> mockFilmsList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stats = new Stats(mockFilmsList);
        testFilms = List.of(
                new Film("Movie1", "Action", LocalDate.of(2010, 1, 2), 120, 7.5, "English"),
                new Film("Movie2", "Action", LocalDate.of(2016, 2, 3), 110, 8.0, "English"),
                new Film("Movie3", "Comedy", LocalDate.of(2018, 3, 4), 90, 6.5, "French"),
                new Film("Movie4", "Drama", LocalDate.of(2018, 4, 5), 150, 9.0, "Spanish"),
                new Film("Movie5", "Comedy", LocalDate.of(2019, 5, 6), 100, 7.2, "Spanish"),
                new Film("Movie6", "Thriller", LocalDate.of(2020, 6, 7), 105, 6.8, "French"),
                new Film("Movie7", "Comedy", LocalDate.of(2021, 7, 8), 95, 5.9, "Spanish"),
                new Film("Movie8", "Thriller", LocalDate.of(2022, 8, 9), 130, 7.1, "English"),
                new Film("Movie9", "Sci-Fi", LocalDate.of(2023, 9, 10), 140, 8.3, "German"),
                new Film("Movie10", "Thriller", LocalDate.of(2024, 10, 11), 115, 7.6, "German"),
                new Film("Movie11", "Action", LocalDate.of(2024, 11, 12), 115, 7.6, "German"),
                new Film("Movie12", "Action", LocalDate.of(2024, 12, 13), 115, 7.6, "French"),
                new Film("Movie13", "Action", LocalDate.of(2024, 1, 14), 115, 7.6, "Spanish"),
                new Film("Movie14", "Comedy", LocalDate.of(2024, 2, 15), 115, 7.6, "English"),
                new Film("Movie15", "Drama", LocalDate.of(2024, 3, 16), 115, 7.6, "Japanese"),
                new Film("Movie16", "Action", LocalDate.of(2024, 3, 16), 115, 7.6, "Japanese"),
                new Film("Movie17", "Comedy", LocalDate.of(2024, 3, 16), 115, 7.6, "French"),
                new Film("Movie18", "Thriller", LocalDate.of(2024, 3, 16), 115, 7.6, "English"),
                new Film("Movie19", "Drama", LocalDate.of(2024, 3, 16), 115, 7.6, "English"),
                new Film("Movie20", "Sci-Fi", LocalDate.of(2024, 3, 16), 115, 7.6, "French"),
                new Film("Movie21", "Indie", LocalDate.of(2025, 03, 12), 115, 7.6, "Russian")
        );
        when(mockFilmsList.stream()).thenReturn(testFilms.stream());
    }

    @Test
    void testCountFilmsByGenre() {
        Map<String, Long> result = stats.countFilmsByGenre();

        assertEquals(6, result.get("Action"));
        assertEquals(5, result.get("Comedy"));
        assertEquals(4, result.get("Thriller"));
        assertEquals(3, result.get("Drama"));
        assertEquals(2, result.get("Sci-Fi"));
        assertEquals(1, result.get("Indie"));
        assertEquals(6, result.size());
    }

    @Test
    void testCountFilmsByLanguage() {
        Map<String, Long> result = stats.countFilmsByLanguage();

        assertEquals(6, result.get("English"));
        assertEquals(5, result.get("French"));
        assertEquals(4, result.get("Spanish"));
        assertEquals(3, result.get("German"));
        assertEquals(2, result.get("Japanese"));
        assertEquals(1, result.get("Russian"));
        assertEquals(6, result.size());
    }

    @Test
    void testGetTop5ByGenre() {
        Map<String, Long> result = stats.getTop5ByGenre();

        assertEquals(6, result.get("Action"));
        assertEquals(5, result.get("Comedy"));
        assertEquals(4, result.get("Thriller"));
        assertEquals(3, result.get("Drama"));
        assertEquals(2, result.get("Sci-Fi"));
        assertEquals(5, result.size());
    }

    @Test
    void testGetTop5ByLanguage() {
        Map<String, Long> result = stats.getTop5ByLanguage();

        assertEquals(6, result.get("English"));
        assertEquals(5, result.get("French"));
        assertEquals(4, result.get("Spanish"));
        assertEquals(3, result.get("German"));
        assertEquals(2, result.get("Japanese"));
        assertEquals(5, result.size());
    }

    @Test
    void testGetLatestFilm() {
        Film expectedResult = testFilms.get(testFilms.size() - 1);
        Film actualResult = stats.getLatestFilm();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetOldestFilm() {
        Film expectedResult = testFilms.get(0);
        Film actualResult = stats.getOldestFilm();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetTotalRuntime() {
        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("days", 1);
        expectedResult.put("hours", 16);

        var actualResult = stats.getTotalRuntime();
        assertEquals(expectedResult, actualResult);
    }
}
