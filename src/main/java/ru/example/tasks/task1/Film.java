package ru.example.tasks.task1;

import java.time.LocalDate;
import java.util.Objects;

public class Film {
    private String title;
    private String genre    ;
    private LocalDate premiere;
    private int runtime;
    private double imdbScore;
    private String language;

    public Film(String title, String genre, LocalDate premiere, int runtime, double imdbScore, String language) {
        this.title = title;
        this.genre = genre;
        this.premiere = premiere;
        this.runtime = runtime;
        this.imdbScore = imdbScore;
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public int getRuntime() {
        return runtime;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public String getLanguage() {
        return language;
    }


    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", premiere=" + premiere +
                ", runtime=" + runtime +
                ", imdbScore=" + imdbScore +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return runtime == film.runtime && Double.compare(imdbScore, film.imdbScore) == 0 && Objects.equals(title, film.title) && Objects.equals(genre, film.genre) && Objects.equals(premiere, film.premiere) && Objects.equals(language, film.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, premiere, runtime, imdbScore, language);
    }
}
