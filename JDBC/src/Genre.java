import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Genre {
    
	public static final String seq_Genre_ID 	= "genre_ID";
	public static final String table			= "Genre";
	public static final String col_genre_ID		= "GenreID";
	public static final String col_genre		= "Genre";
	
	private int genre_ID;
    private String genre;
    private PreparedStatement stmt;
    
    public Genre() {}
    
    public Genre(int genre_ID, String genre)
    {
    	setGenre_ID(genre_ID);
    	setGenre(genre);
    }
    
    public void insert() throws SQLException {
    	String sql =
    			"INSERT INTO " + table + 
    			" VALUES(" + seq_Genre_ID + ".nextval, ?)";
    	
		stmt = Select_2.conn.prepareStatement(sql);
		
		stmt.setString(1, genre);
		int rowsInserted = stmt.executeUpdate();
		
		System.out.println("Es wurden " + rowsInserted + " Zeilen hinzugefügt");
		stmt.close();
	}
    
    public void update() throws SQLException {
		String sql =
				"UPDATE " + table +
				" SET " + col_genre + " = ? " +
				"WHERE " + col_genre_ID + " = ?";
		
		stmt = Select_2.conn.prepareStatement(sql);
		stmt.setString(1, genre);
		stmt.setInt(2, genre_ID);
		
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsUpdated + " Zeilen verändert.");
		
		stmt.close();
	}
    
    public void delete() throws SQLException
	{
		String sql_Delete =
				"DELETE FROM " + table +
				" WHERE " + col_genre_ID + " = ?";
		
		stmt = Select_2.conn.prepareStatement(sql_Delete);
		
		stmt.setInt(1, genre_ID);
		
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

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}
}
