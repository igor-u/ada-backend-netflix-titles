package model;

public class TitleWithIMDbRating {

    private Title title;
    private Double imdbRating;

    public TitleWithIMDbRating(Title title, Double imdbRating) {
        this.title = title;
        this.imdbRating = imdbRating;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return this.title.toString() + " - IMDb Rating: " + imdbRating;
    }

}
