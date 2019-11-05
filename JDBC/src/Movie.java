import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie {
    
	public static final String seq_Movie_ID 	= "movie_ID";
	public static final String table			= "Movie";
	public static final String col_Movie_ID		= "MovieID";
	public static final String col_Type			= "Type";
	public static final String col_Title		= "Title";
	public static final String col_Year			= "Year";
	
    private int movie_ID;
    private String title;
    private int year;
    private char type;
    private PreparedStatement stmt;
    
    public Movie() {}
    
    public Movie(int movie_ID, String title, int year, char type) throws SQLException
    {
    	setMovieID(movie_ID);
    	setTitle(title);
    	setYear(year);
    	setType(type);
    }
    
	public void insert() throws SQLException
	{
		String sql_Insert =
				"INSERT INTO " + table +
				" VALUES (" + seq_Movie_ID + ".nextval, ?, ?, ?)";
		
		System.out.println(sql_Insert);

		stmt = Select_2.conn.prepareStatement(sql_Insert);

		stmt.setString(1, title);
		stmt.setInt(2, year);
		stmt.setString(3, String.valueOf(type));

		int row = stmt.executeUpdate();

		// rows affected
		System.out.println("rows affected: " + row); // 1
		System.out.println("Es wurde(n) " + row + " Datensaetze in Tabelle Movie eingefuegt");

		// Select ID FROM Movie
		String sql_Select = "SELECT " + seq_Movie_ID + ".currval FROM DUAL";
		stmt = Select_2.conn.prepareStatement(sql_Select);
		ResultSet rs = stmt.executeQuery();

		if (rs.next())
			System.out.println("\nCurrval: " + rs.getInt(1) + "\n");

		rs.close();
		stmt.close();
}
	
	public void update() throws SQLException
	{
		//SQL-Statement:
		String sql_Update =
				"UPDATE " + table +
				" SET " + col_Type + " = ?, " + col_Year + " = ?, " + col_Title + " = ?" +
				"WHERE " + col_Movie_ID + " = ?";
		
		stmt = Select_2.conn.prepareStatement(sql_Update);

		stmt.setString(1, String.valueOf(type));
		stmt.setInt(2, year);
		stmt.setString(3, title);
		stmt.setInt(4, movie_ID);

		// Update now:
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsUpdated + " Zeilen in Tabelle Movie hinzugefügt");

		stmt.close();
	}
	
	public void delete() throws SQLException
	{
		String sql_Delete =
				"DELETE FROM " + table +
				"WHERE " + col_Movie_ID + " = ?";
		
		stmt = Select_2.conn.prepareStatement(sql_Delete);

		stmt.setInt(1, movie_ID);

		// delete it
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsDeleted + " Datensaetze in Tabelle Movie gelï¿½scht");

		stmt.close();
	}

    public int getMovieID() {
        return movie_ID;
    }

    public void setMovieID(int movieID) {
        movie_ID = movieID;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}
        
//	public void createMovie()
//  {
//      Connection con =  null;
//      Statement stmt = null;
//      
//      String createString =
//              
//              "create Table Movie" + 
//              "(" + 
//              "    MovieID int," + 
//              "    Title varchar(100)," + 
//              "    Year int," + 
//              "    Type char(1)," + 
//              "    constraint pk_Movie primary key (MovieID)" + 
//              ")";
//      try {
//        con =  ConnectionManager.getConnection();
//        stmt = con.createStatement();
//        stmt.executeUpdate(createString);
//        System.out.println("Tabelle Movie angelegt");
//      }
//      catch(SQLException e) {
//        e.printStackTrace();
//      }
//      finally{
//            try {
//              stmt.close();
//              con.close();
//            }
//            catch (Exception e) {e.printStackTrace();}
//      }
//  }
