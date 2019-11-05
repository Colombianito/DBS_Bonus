import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieFactory {
    
	private PreparedStatement stmt;
	
    public MovieFactory() throws SQLException
    {
    	
    }
    
    public Movie findByID(int movieID) throws SQLException
    {
        //SQL-Statement
        String sql_Movie_ID = "SELECT * FROM " + Movie.table + " WHERE " + Movie.col_Movie_ID + " = ?";
        System.out.println(sql_Movie_ID + "\n");
        stmt = Select_2.conn.prepareStatement(sql_Movie_ID);
        
        stmt.setInt(1, movieID);
        
        //SELECT:
        ResultSet rs = stmt.executeQuery();
        
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
        stmt = Select_2.conn.prepareStatement(sql_ID + "\n");
        
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
    

    public String printMovie(List<Movie> movies)
    {
    	String str_Movie = "";
    	
    	for (Movie m : movies)
    	{
    		str_Movie += m.getMovieID() + " " + m.getTitle() + " " + m.getYear() + " " + m.getType() + "\n";
    	}
    	
		return str_Movie;
    }
}
