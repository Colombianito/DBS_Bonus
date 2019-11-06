package de.hsh.dbs2.imdb.entities;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenreMovie {
    
	public static final String table			= "GenreMovie";
	public static final String col_Genre_ID		= "GenreID";
	public static final String col_Movie_ID		= "MovieID";
	
	private int genre_ID;
	private int movie_ID;
    private PreparedStatement stmt;
    
    public GenreMovie() {}
    public GenreMovie(int genre_ID, int movie_ID)
    {
    	setGenre_ID(genre_ID);
    	setMovie_ID(movie_ID);
    }
    
    public void insert() throws SQLException {
    	String sql =
    			"INSERT INTO " + table + 
    			" VALUES(?, ?)";
    	
		stmt = Select.conn.prepareStatement(sql);
		
		stmt.setInt(1, movie_ID);
		stmt.setInt(2, genre_ID);
		
		int rowsInserted = stmt.executeUpdate();
		
		System.out.println("Es wurden " + rowsInserted + " Zeilen hinzugefügt");
		stmt.close();
	}
    
    public void update() throws SQLException {
		String sql =
				"UPDATE " + table +
				" SET " + col_Movie_ID + " = ?, " + col_Genre_ID + " = ? " +
				"WHERE " + col_Movie_ID + " = ? AND " + col_Genre_ID + " = ?";

		stmt = Select.conn.prepareStatement(sql);
		
		stmt.setInt(1, movie_ID);
		stmt.setInt(2, genre_ID);
		stmt.setInt(3, movie_ID);
		stmt.setInt(4, genre_ID);
		
		int rowsInserted = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsInserted + " Zeilen verändert");
		stmt.close();
	}
    
    public void delete() throws SQLException
	{
		String sql_Delete =
				"DELETE FROM " + table +
				" WHERE " + col_Movie_ID + " = ? AND " + col_Genre_ID + " = ?";
		
		stmt = Select.conn.prepareStatement(sql_Delete);
		
		stmt.setInt(1, movie_ID);
		stmt.setInt(2, genre_ID);
		
		//delete it
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsDeleted + " Zeilen gelöscht");
		
		stmt.close();
	}
    
    public int getGenre_ID()
	{
		return genre_ID;
	}
	public void setGenre_ID(int genre_ID)
	{
		this.genre_ID = genre_ID;
	}
	public int getMovie_ID()
	{
		return movie_ID;
	}
	public void setMovie_ID(int movie_ID)
	{
		this.movie_ID = movie_ID;
	}
}
