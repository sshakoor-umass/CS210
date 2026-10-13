/*
 * Copyright 2026 Marc Liberatore.
 */

package comparables;

public class Movie implements Comparable<Movie> {
    int releaseYear;
    double rating;
    String title;

    public Movie(int releaseYear, double rating, String title) {
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + releaseYear;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (releaseYear != other.releaseYear)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [releaseYear=" + releaseYear + ", rating=" + rating + ", title=" + title + "]";
    }

    @Override
    public int compareTo(Movie otherMovie) {
        // TODO: Define a natural ordering on movies.
        // First, sort by release year (oldest to newest)
        // Break ties by rating (highest to lowest)
        // Break remaining ties by title, using the natural (lexicographic) ordering of
        // Strings
        if (this.releaseYear != otherMovie.releaseYear) {
            return this.releaseYear - otherMovie.releaseYear;
        } else if (this.rating != otherMovie.rating) {
            return Double.compare(otherMovie.rating, this.rating);
        } else {
            return this.title.compareTo(otherMovie.title);
        }
    }
}
