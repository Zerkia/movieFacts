package com.example.moviefacts.repository;

import com.example.moviefacts.model.Movie;
import java.sql.*;
import java.util.*;

public class MovieRepository {

    public ArrayList<Movie> getMovies() {

        ArrayList<Movie> result = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String sqlStr = "SELECT * FROM movies";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieID(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setYear(resultSet.getInt(3));
                movie.setLength(resultSet.getInt(4));
                movie.setSubject(resultSet.getString(5));
                movie.setPopularity(resultSet.getInt(6));
                movie.setAwards(resultSet.getString(7));
                result.add(movie);
            }

        } catch (SQLException err) {
            System.out.println(err);
        }
        return result;
    }
}
