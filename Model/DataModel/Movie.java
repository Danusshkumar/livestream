package Model.DataModel;

public class Movie {

    public int movieId;
    public String name;

    public boolean isStreamable;
    public String path;

    @Override
    public String toString() {
        return movieId + " - " + name;
    }

    public Movie(int movieId, String name, boolean isStreamable, String path) {
        this.movieId = movieId;
        this.name = name;
        this.isStreamable = isStreamable;
        this.path = path;
    }
}
