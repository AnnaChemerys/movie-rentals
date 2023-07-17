package org.example.dao;

import org.example.model.Movie;
import org.example.model.MovieType;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDao extends AbstractDao<Movie> {
    @Override
    protected String getFileName() {
        return FileNames.MOVIES.getFileName();
    }

    @Override
    public void update(Movie movie) {
        List<Movie> tempList = items;
        for (Movie tempMovie : tempList) {
            if (tempMovie.getId().equals(movie.getId())) {
                tempMovie.setTitle(movie.getTitle());
                tempMovie.setDirector(movie.getDirector());
                tempMovie.setActors(movie.getActors());
                tempMovie.setShortDescription(movie.getShortDescription());
            }
        }
        fileOperation.writeIntoFile(tempList, filename);
        items = tempList;
    }


    public List<Movie> searchBy(String text) {
        return items.stream()
                .filter(item -> item.getTitle().contains(text))
                .collect(Collectors.toList());
    }


    public List<Movie> getByType(String type) {
        return items.stream()
                .filter(item -> item.getMovieType().equals(type))
                .collect(Collectors.toList());
    }
}
