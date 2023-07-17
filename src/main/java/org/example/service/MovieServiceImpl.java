package org.example.service;

import org.example.dao.MovieDao;
import org.example.model.Movie;
import org.example.model.MovieType;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao = new MovieDao();

    public void saveMovie(Movie movie) {
        if (isValidMovie(movie) && isExistMovie(movie)) {
            movieDao.save(movie);
        }
    }

    public void updateMovie(Movie movie) {
        if (isValidMovie(movie)) {
            movieDao.update(movie);
        }
    }

    public void removeMovie(Movie movie) {
        if (isValidMovie(movie)) {
            movieDao.delete(movie);
        }
    }

    private boolean isValidMovie(Movie movie) {
        boolean isValid = movie.getTitle() != null && movie.getMovieType() != null;
        if (!isValid) {
            System.out.println("Movie data is not valid.");
            return false;
        }
        System.out.println("Movie data is valid.");
        return true;
    }

    private boolean isExistMovie(Movie movie) {
        for (Movie tempMovie : movieDao.getAll()) {
            if (tempMovie.getTitle().equals(movie.getTitle()) && tempMovie.getMovieType().equals(movie.getMovieType())) {
                System.out.println("Movie data is exist");
                return false;
            }
        }
        return true;
    }

    public List<Movie> getAllMovies() {
        return movieDao.getAll();
    }

    public List<Movie> searchMovies(String searchText) {
        return movieDao.searchBy(searchText);
    }

    public List<Movie> searchMoviesByType(String type) {
        return movieDao.getByType(type);
    }

    public Movie getMovieById(String Id) {
        return movieDao.getById(Id);
    }
}
