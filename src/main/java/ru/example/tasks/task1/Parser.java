package ru.example.tasks.task1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Parser {

    public static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMMM d. yyyy", Locale.ENGLISH)
    );

    public static List<Film> getCSVAsList(String filename) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)
        ) {
            return parser.getRecords().stream()
                    .map(Parser::csvRecordToFilm)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Film csvRecordToFilm(CSVRecord record) {
        return new Film(
                record.get("Title"),
                record.get("Genre"),
                parseDate(record.get("Premiere")),
                Integer.parseInt(record.get("Runtime")),
                Double.parseDouble(record.get("IMDB Score")),
                record.get("Language")
        );
    }

    private static LocalDate parseDate(String dateString) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Невозможно распарсить дату: " + dateString);
    }
}
