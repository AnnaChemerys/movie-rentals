package org.example.view.impl;

import org.example.model.Movie;
import org.example.model.MovieType;
import org.example.service.MovieService;
import org.example.service.MovieServiceImpl;
import org.example.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SearchMovieMenu implements Menu {
    private final MovieService movieService = new MovieServiceImpl();
    private final String[] items = {"1. Search by title", "2. Search by type", "0. Exit"};
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        showItems(items);

        //noinspection InfiniteLoopStatement
        while (true) {
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                System.out.println("Incorrect input");
                scanner.nextLine();
                show();
            }
            switch (choice) {
                case 1 -> searchByTitle();
                case 2 -> searchByType();
                case 0 -> exit();
            }
        }
    }

    private void searchByType() {
        System.out.println("Enter movie category:");
        String movieType = null;
        try {
            movieType = Movie.g(scanner.nextLine().toUpperCase());
        } catch (Exception ignored) {
        }
        if (movieService.searchMoviesByType(movieType).size() > 0) {
            movieService.searchMoviesByType(movieType).stream().forEach(System.out::println);
        } else {
            System.out.println("There is no movies with this type");
        }
        show();
    }

    private void searchByTitle() {
        System.out.println("Enter movie title:");
        String movieTitle = scanner.nextLine();
        List<Movie> movies = movieService.searchMovies(movieTitle);
        if (movies.size() > 0) {
            movies.stream().forEach(System.out::println);
        } else {
            System.out.println("There is no movie with this name");
        }
        show();
    }

    @Override
    public void exit() {
        new MovieMenu().show();
    }
}
