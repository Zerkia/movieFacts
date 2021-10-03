package com.example.moviefacts.controllers;
import com.example.moviefacts.model.Movie;
import com.example.moviefacts.sqlConnection.DBManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.sql.*;
import java.util.ArrayList;

@RestController
public class MovieController {
    Connection connection = DBManager.getConnection();

    @GetMapping("/")
    public String homepage(){
        return "Welcome to Movie facts, on this web application, you will be shown a few different things regarding movies.";
    }

    @GetMapping("/getFirst")
    //Called getMovies because it fetches all movies, but only displays the first result
    //due to a lack of a while loop around "resultSet.next();"
    public String getMovies(){
        String sqlStr = "SELECT * FROM movies";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            //gets a specific table column, in this case, the title
            String result = resultSet.getString(4);

            return "The first movie in the database is: " + result;
        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
        return "Error fetching desired database query.";
    }

    @GetMapping("/getRandom")
    public String getRandom(){
        String sqlStr = "SELECT * FROM movies ORDER BY RAND()";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            //gets a specific table column, in this case, the title
            String result = resultSet.getString(4);

            return "The first movie in the database is: " + result;
        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
        return "Error fetching desired database query.";
    }

    @GetMapping("/getTenSortByPopularity")
    public ArrayList<Movie> getTenSortByPopularity(){
        ArrayList<Movie> result = new ArrayList<>();
        String sqlStr = "SELECT * FROM (SELECT * FROM movies ORDER BY RAND() LIMIT 10) as random ORDER BY popularity ASC;";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieID(resultSet.getInt(1));
                movie.setYear(resultSet.getInt(2));
                movie.setLength(resultSet.getInt(3));
                movie.setTitle(resultSet.getString(4));
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

    @GetMapping("/howManyWonAnAward")
    public ArrayList<Movie> howManyWonAnAward(){
        ArrayList<Movie> result = new ArrayList<>();
        String sqlStr = "SELECT * FROM movies WHERE Awards = \"Yes\";";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieID(resultSet.getInt(1));
                movie.setYear(resultSet.getInt(2));
                movie.setLength(resultSet.getInt(3));
                movie.setTitle(resultSet.getString(4));
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

    @GetMapping("/comedyWonAnAward")
    public ArrayList<Movie> comedyWonAnAward(){
        ArrayList<Movie> result = new ArrayList<>();
        String sqlStr = "SELECT * FROM movies WHERE Subject = \"Comedy\" AND Awards = \"Yes\";";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieID(resultSet.getInt(1));
                movie.setYear(resultSet.getInt(2));
                movie.setLength(resultSet.getInt(3));
                movie.setTitle(resultSet.getString(4));
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
