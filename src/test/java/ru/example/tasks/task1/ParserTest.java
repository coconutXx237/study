package ru.example.tasks.task1;


import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParserTest {

    Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void testGetCSVAsList() {
        List<Film> expectedResult = List.of(
                new Film("Test test", "Testing", LocalDate.of(2019, 8, 5), 58, 2.5, "English"),
                new Film("Test2 test2", "Testing2", LocalDate.of(2020, 8, 21), 81, 2.6, "Spanish")
        );
        List<Film> actualResult = Parser.getCSVAsList("test.csv");
        List<Film> actualResult2 = Parser.getCSVAsList("empty.csv");

        assertEquals(actualResult, expectedResult);
        assertTrue(actualResult2.isEmpty());
    }

    @Test
    void testCsvRecordToFilmWithReflection() {
        CSVRecord mockRecord = mock(CSVRecord.class);

        when(mockRecord.get("Title")).thenReturn("Test Movie");
        when(mockRecord.get("Genre")).thenReturn("Testing");
        when(mockRecord.get("Premiere")).thenReturn("August 5, 2019");
        when(mockRecord.get("Runtime")).thenReturn("58");
        when(mockRecord.get("IMDB Score")).thenReturn("2.5");
        when(mockRecord.get("Language")).thenReturn("English");

        try {
            Method method = Parser.class.getDeclaredMethod("csvRecordToFilm", CSVRecord.class);
            method.setAccessible(true);

            Film expectedResult = new Film("Test Movie", "Testing", LocalDate.of(2019, 8, 5), 58, 2.5, "English");
            Film actualResult = (Film) method.invoke(parser, mockRecord);

            assertEquals(actualResult, expectedResult);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseDateWithReflection() {
        try {
            Method method = Parser.class.getDeclaredMethod("parseDate", String.class);
            method.setAccessible(true);

            LocalDate expectedResult = LocalDate.of(2019, 8, 5);
            LocalDate actualResult1 = (LocalDate) method.invoke(parser, "August 5, 2019");
            LocalDate actualResult2 = (LocalDate) method.invoke(parser, "August 5. 2019");

            assertEquals(actualResult1, expectedResult);
            assertEquals(actualResult2, expectedResult);

            assertThrows(InvocationTargetException.class, () -> method.invoke(parser, "Aug 5. 2019"));
            assertThrows(InvocationTargetException.class, () -> method.invoke(parser, "August 5. 20190"));
            assertThrows(InvocationTargetException.class, () -> method.invoke(parser, "32 December, 2020"));
            assertThrows(InvocationTargetException.class, () -> method.invoke(parser, "some invalid date"));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
