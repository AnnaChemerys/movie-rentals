package org.example.service;

import org.example.model.Movie;
import org.example.model.MovieType;

import java.util.List;

public interface MovieService {
    void saveMovie(Movie movie) ;

    void updateMovie(Movie movie);

    void removeMovie(Movie movie);

    List<Movie> getAllMovies();

    List<Movie> searchMovies(String searchText);

    List<Movie> searchMoviesByType(String type);

    Movie getMovieById(String Id);
}
