package de.hsh.dbs2.imdb.entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieFactory {
    
	private Statement stmt;
    
    public List<MovieDTO> Select_All_MoviesByTitel(String search) throws SQLException
    {
    	List<MovieDTO> arrL_Movies = new ArrayList<MovieDTO>(); //Träger für die MovieDTO-Objekte

		//SQL-Statement:
        String sql_Select_Titel = "SELECT * FROM Movie WHERE UPPER(Title) LIKE UPPER('%" + search + "%')";
        System.out.println(sql_Select_Titel);
        
        stmt = DBConnection.getConnection().createStatement();
        
        
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
    
    public static MovieDTO getMovieByID(long movieID) throws SQLException
    {    
    	CharacterDTO movCharacter = new CharacterDTO(); //Für Später
        MovieDTO currentMovie = new MovieDTO();
        
        currentMovie.setId(movieID);
        currentMovie.setTitle(currentMovie.getTitle());
        currentMovie.setYear(currentMovie.getYear());
        currentMovie.setType(currentMovie.getType());
        
        return currentMovie;
    }
    
    public void insertMovie(MovieDTO movie) throws SQLException {
        String seq_movieID = "movie_ID";
        String sql = "INSERT INTO MOVIE VALUES (" + seq_movieID + ".nextVal, ?, ?, ?)";

        try {

            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getYear());
            stmt.setString(3, movie.getType());
            
            int rows = stmt.executeUpdate();
            System.out.println(rows + " Zeilen hinzugefügt");
            
            if (rows == 1) {
                System.out.println("DONE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void updateMovie(MovieDTO movie) throws SQLException {
        String sql = "UPDATE Movie SET TITLE = ?, Year = ?, TYPE = ? WHERE MOVIEID = ?";
        System.out.println(sql);
        
        try {

            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getYear());
            stmt.setString(3, movie.getType());
            stmt.setLong(4, movie.getId());
            stmt.executeUpdate();
            DBConnection.getConnection().commit();

            System.out.println("Movie updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMovieByID(long movieID) throws SQLException
    {
    	String sql_Delete = "DELETE FROM MOVIE WHERE movieID = ?";
    	
    	PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql_Delete);
		stmt.setLong(1, movieID);
		stmt.executeUpdate();	
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
