package manager;

import model.Title;
import model.TitleWithIMDbRating;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IMDbManager {

    private Pattern imdbRatingpattern = Pattern.compile("imdbRating\":\"[\\d*].[\\d]\"");

    public TitleWithIMDbRating createTitleWithRating(Title title) throws IOException, InterruptedException {

        TitleWithIMDbRating titleIMDbRating = null;

        String response = HttpRequestManager.requestPorTitulo(title.getTitle());
        Matcher matcher = imdbRatingpattern.matcher(response);
        if (matcher.find()) {
            String imdbRating = matcher.group(0);
            Double imdbRatingDouble = Double.valueOf(
                    imdbRating.substring(imdbRating.indexOf(":"), imdbRating.length() - 1).substring(2));

            titleIMDbRating = new TitleWithIMDbRating(title, imdbRatingDouble);
        }

        return titleIMDbRating;

    }

    public List<TitleWithIMDbRating> toTitlesWithImdbRating(List<Title> titles) {
        List<TitleWithIMDbRating> titlesWithImdbRating = titles.parallelStream().map(t -> {
            try {
                System.out.println(createTitleWithRating(t));
                return createTitleWithRating(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        return titlesWithImdbRating;
    }

}
