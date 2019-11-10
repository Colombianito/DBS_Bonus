package de.hsh.dbs2.imdb.entities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.dto.MovieDTO;

public class MovieFactory {
    
	private Statement stmt;
	
    public MovieFactory() throws SQLException
    {
    	
    }
    
    public List<MovieDTO> Select_All_MoviesByTitel(String search) throws SQLException
    {
    	List<MovieDTO> arrL_Movies = new ArrayList<MovieDTO>(); //Träger für die MovieDTO-Objekte

		//SQL-Statement:
        String sql_Select_Titel = "SELECT * FROM Movie WHERE UPPER(Title) LIKE UPPER('%" + search + "%')";
        stmt = ConnectionManager.getConnection().createStatement();
        System.out.println(sql_Select_Titel);
        //stmt = Select.conn.createStatement();
        //stmt.setString(1, search);
        
        ResultSet rs = stmt.executeQuery(sql_Select_Titel); //EXEC SELECT:
        
        /*Hier werden alle Movie-Klassenattribute außer Genre und Cahracter gefüllt.
        Genre und Character werden später gefüllt / ergänzt durch die dazu vorgesehene Factory MovieCharacterFactory*/
        while(rs.next())
        {
        	MovieDTO movie_DTO = new MovieDTO(); //Wird hier Instanziiert um das Objekt mit den Daten aus der MovieDTO-ArrayList zu füllen
        	
        	movie_DTO.setId(rs.getLong(1)); //getInt oder getLong?
        	movie_DTO.setTitle(rs.getString(2));
        	movie_DTO.setYear(rs.getInt(3));
        	movie_DTO.setType(rs.getString(4)); //Achtung, convert nötig??
        	
        	arrL_Movies.add(movie_DTO);
        }
        rs.close();
        stmt.close();
        
        return arrL_Movies;
    }
    
    public Movie findByID(int movieID) throws SQLException
    {
        //SQL-Statement
        String sql_Movie_ID = "SELECT * FROM " + Movie.table + " WHERE " + Movie.col_Movie_ID + " = ?";
        System.out.println(sql_Movie_ID + "\n");
        stmt = Select.conn.prepareStatement(sql_Movie_ID);
        
        //stmt.set(1, movieID);
        
        //SELECT:
        ResultSet rs = stmt.executeQuery(sql_Movie_ID);
        
        Movie movie = null;
        
        if(!rs.next())
        {
            System.out.println("Der Film mit der ID " + movieID + " existiert nicht!");
        }
        else
        {
            movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4).charAt(0));
        }
        rs.close();
        stmt.close();
        
        return movie;
    }
    
    public List<Movie> findByTitle(String title) throws SQLException
    {
        //SQL-Statement:
        String sql_ID = "SELECT * FROM " + Movie.table + " WHERE UPPER(" + Movie.col_Title + ") LIKE UPPER('%" + title + "%')\n";
        System.out.println(sql_ID);
        stmt = Select.conn.prepareStatement(sql_ID + "\n");
        
        //SELECT:
        ResultSet rs = stmt.executeQuery(sql_ID);
        List<Movie> movies = new ArrayList<Movie>();
        
        while(rs.next())
        {
        	movies.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4).charAt(0)));
        }
        rs.close();
        stmt.close();
        
        return movies;
    }
    
    public String printMovie(Movie movie)
    {
    	String str_Movie = movie.getMovieID() + " " + movie.getTitle() + " " + movie.getYear() + " " + movie.getType();
    	
		return str_Movie;
    }
    

    public String printMovieList(List<Movie> movies)
    {
    	String str_Movie = "";
    	
    	for (Movie m : movies)
    	{
    		str_Movie += m.getMovieID() + " " + m.getTitle() + " " + m.getYear() + " " + m.getType() + "\n";
    	}
    	
		return str_Movie;
    }
}
