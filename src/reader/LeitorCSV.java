package reader;

import model.Title;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class LeitorCSV {

    public static List<Title> toList(String path) {

        List<Title> lista = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.ISO_8859_1))) {

            lista = br.lines()
                    .skip(1).map(line -> {
                        String t[] = line.split(";");
                        return new Title.Builder()
                                .showId(t[0])
                                .type(t[1])
                                .title(t[2])
                                .director(t[3])
                                .cast(t[4])
                                .country(t[5])
                                .dateAdded(t[6])
                                .releaseYear(t[7])
                                .rating(t[8])
                                .duration(t[9])
                                .listedIn(t[10])
                                .description(t[11])
                                .build();
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
