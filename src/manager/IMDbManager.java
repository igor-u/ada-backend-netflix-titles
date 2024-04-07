package manager;

import model.Title;
import model.TitleWithIMDbRating;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMDbManager {

    private Pattern imdbRatingpattern = Pattern.compile("imdbRating\":\"[\\d*].[\\d]\"");

    public TitleWithIMDbRating createTitleWithRating(Title title) throws IOException, InterruptedException {

        TitleWithIMDbRating titleIMDbRating = null;

        String response = HttpRequestManager.requestPorTitulo(title.getTitle());
        Matcher matcher = imdbRatingpattern.matcher(response);
        if(matcher.find()) {
            String imdbRating = matcher.group(0);
            Double imdbRatingDouble = Double.valueOf(
                    imdbRating.substring(imdbRating.indexOf(":"), imdbRating.length() - 1).substring(2));

            titleIMDbRating = new TitleWithIMDbRating(title, imdbRatingDouble);
        }

        return titleIMDbRating;

    }

}
