package manager;

import model.Title;
import model.TitleWithIMDbRating;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IMDbManagerTesteThread {

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
        System.out.println(titleIMDbRating);
        return titleIMDbRating;

    }

    public void toTitlesWithRating(List<Title> titles, List<TitleWithIMDbRating> titles2) {

        var titles3 = titles.stream()
                .map(t -> {
                    try {
                        return createTitleWithRating(t);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).filter(t -> t != null)
                .collect(Collectors.toList());

        titles2.addAll(titles3);

    }

}
