package app;

import manager.IMDbManager;
import model.Title;
import model.TitleWithIMDbRating;
import reader.LeitorCSV;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Title> movies = LeitorCSV.csvToList("netflix-movies.csv");
        //List<Title> tvShows = LeitorCSV.csvToList("netflix-tvShows.csv");

        //movies.forEach(System.out::println);
        //tvShows.forEach(System.out::println);

        IMDbManager imdbManager= new IMDbManager();

        movies.forEach(m -> {
            try {
                System.out.println(imdbManager.createTitleWithRating(m));
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
