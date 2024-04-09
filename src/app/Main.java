package app;

import manager.IMDbManager;
import model.Title;
import model.TitleWithIMDbRating;
import reader.LeitorCSV;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Title> movies = LeitorCSV.toList("netflix-movies.csv");
        //List<Title> tvShows = LeitorCSV.toList("netflix-tvShows.csv");

        //movies.forEach(System.out::println);
        //tvShows.forEach(System.out::println);

        IMDbManager imdbManager = new IMDbManager();

        List<TitleWithIMDbRating> moviesWithImdbRating = imdbManager
                .toTitlesWithRating(movies);

            long start = System.currentTimeMillis();
            imdbManager.toTitlesWithRating(movies);
            long finish = System.currentTimeMillis();
            System.out.println("Duração: " + (finish - start) + " milissegundos");

            Optional<TitleWithIMDbRating> filmeComNotaMaisAlta = moviesWithImdbRating
                    .stream()
                    .max(Comparator.comparing(t -> t.getImdbRating()));

            System.out.println("Filme com nota mais alta: " + filmeComNotaMaisAlta.get());

    }
}
