package app;

import manager.HttpRequestManager;
import model.Title;
import reader.LeitorCSV;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Title> movies = LeitorCSV.csvToList("netflix-movies.csv");
        List<Title> tvShows = LeitorCSV.csvToList("netflix-tvShows.csv");

        //movies.forEach(System.out::println);
        //tvShows.forEach(System.out::println);

        String filme = "Ganglands";
        String response = HttpRequestManager.requestPorTitulo(filme);
        System.out.println(response);

        Pattern pattern = Pattern.compile("imdbRating\":\"[\\d*].[\\d]\"");
        Matcher matcher = pattern.matcher(response);
        matcher.find();
        String imdbRating = matcher.group(0);

        System.out.println(imdbRating);

        Double imdbRatingDouble = Double.valueOf(
                imdbRating.substring(imdbRating.indexOf(":"), imdbRating.length() - 1).substring(2));
        System.out.println(imdbRatingDouble);

    }
}
