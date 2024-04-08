package app;

import manager.IMDbManagerTesteThread;
import model.Title;
import model.TitleWithIMDbRating;
import reader.LeitorCSV;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainComThread {

    public static List<TitleWithIMDbRating> listaTeste = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException, InterruptedException {

        List<Title> movies = LeitorCSV.toList("netflix-movies.csv");
        //List<Title> tvShows = LeitorCSV.toList("netflix-tvShows.csv");

        List<Title> movies1 = movies.subList(0, 500);
        List<Title> movies2 = movies.subList(501, 1000);
        List<Title> movies3 = movies.subList(1001, 1500);
        List<Title> movies4 = movies.subList(1501, 2000);

        //movies.forEach(System.out::println);
        //tvShows.forEach(System.out::println);

        IMDbManagerTesteThread imdbManager = new IMDbManagerTesteThread();

        ExecutorService service = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();

        service.execute(() -> imdbManager.toTitlesWithRating(movies1, listaTeste));
        service.execute(() -> imdbManager.toTitlesWithRating(movies2, listaTeste));
        service.execute(() -> imdbManager.toTitlesWithRating(movies3, listaTeste));
        service.execute(() -> imdbManager.toTitlesWithRating(movies4, listaTeste));

        service.shutdown();

        while(!service.isTerminated()) {
            Thread.sleep(5000);
            System.out.println("Thread sleeping, tamanho da lista: " + listaTeste.size());
        }
        long finish = System.currentTimeMillis();

        System.out.println("Duração :" + (finish - start));

        Optional<TitleWithIMDbRating> movieComNotaMaisAlta = listaTeste
                .stream()
                .max(Comparator.comparing(t -> t.getImdbRating()));

        System.out.println("Filme com nota mais alta: " + movieComNotaMaisAlta.get());

    }
}
