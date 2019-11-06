import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieCharacter {
    
    public static final String seq_MovChar_ID	= "movchar_ID";
	public static final String table			= "MovieCharacter";
	public static final String col_MovChar_ID	= "MovCharID";
	public static final String col_Movie_ID		= "MovieID";
	public static final String col_Person_ID	= "PersonID";
	public static final String col_Character	= "Character";
	public static final String col_alias		= "Alias";
	public static final String col_position		= "Position";
	
	private int movChar_ID;
	private int movie_ID;
	private int person_ID;
    private String character;
    private char alias;
    private int position;
    private PreparedStatement stmt;
    
    public MovieCharacter() {}
    
    public MovieCharacter(int movChar_ID, int movie_ID, int person_ID, String character, char alias, int position)
    {
    	setMovChar_ID(movChar_ID);
    	setMovie_ID(movie_ID);
    	setPerson_ID(person_ID);
    	setCharacter(character);
    	setAlias(alias);
    	setPosition(position);
    }
    
    public void insert() throws SQLException {
    	String sql =
    			"INSERT INTO " + table + 
    			" VALUES(" + seq_MovChar_ID + ".nextval, ?, ?, ?, ?, ?)";
    	
		stmt = Select.conn.prepareStatement(sql);
		stmt.setInt(1, movie_ID);
		stmt.setInt(2, person_ID);
		stmt.setString(3, getCharacter());
		stmt.setString(4, String.valueOf(alias));
		stmt.setInt(5, position);
		
		System.out.println("Movie_ID: " + movie_ID);
		System.out.println("Person_ID: " + person_ID);
		
		
		int rowsInserted = stmt.executeUpdate();
		
		System.out.println("Es wurden " + rowsInserted + " Zeilen hinzugefügt");
	}
    
    public void update() throws SQLException {
		String sql =
				"UPDATE " + table +
				" SET " + col_Character + " = ?, " + col_position + " = ?," +
						col_Movie_ID + " = ?," + col_Person_ID + " = ?, " +
						col_alias + " = ?" +
				"WHERE " + col_MovChar_ID;

		stmt = Select.conn.prepareStatement(sql);
		
		stmt.setString(1, character);
		stmt.setInt(2, position);
		stmt.setInt(3, movie_ID);
		stmt.setInt(4, person_ID);
		stmt.setString(5, String.valueOf(alias));
		stmt.setInt(6, movChar_ID);
		
		int rowsInserted = stmt.executeUpdate();
		
		System.out.println("Es wurden " + rowsInserted + " Zeilen verändert");
		stmt.close();
	}
    
    public void delete() throws SQLException //Hier weitermachen!
	{
		String sql_Delete =
				"DELETE FROM " + table +
				" WHERE " + col_MovChar_ID + " = ?";
		
		stmt = Select.conn.prepareStatement(sql_Delete);
		
		//delete it
		int rowsDeleted = stmt.executeUpdate();
		
		System.out.println("Es wurden " + rowsDeleted + " Zeilen gelöscht");
		stmt.close();
	}
    
    public int getMovChar_ID()
   	{
   		return movChar_ID;
   	}
   	public void setMovChar_ID(int movChar_ID)
   	{
   		this.movChar_ID = movChar_ID;
   	}
   	
	public int getMovie_ID()
	{
		return movie_ID;
	}

	public void setMovie_ID(int movie_ID)
	{
		this.movie_ID = movie_ID;
	}
	
   	public int getPerson_ID()
   	{
   		return person_ID;
   	}
   	public void setPerson_ID(int person_ID)
   	{
   		this.person_ID = person_ID;
   	}
   	
   	public String getCharacter()
   	{
   		return character;
   	}
   	public void setCharacter(String character)
   	{
   		this.character = character;
   	}
   	
   	public char getAlias()
   	{
   		return alias;
   	}
   	public void setAlias(char alias)
   	{
   		this.alias = alias;
   	}
   	
   	public int getPosition()
   	{
   		return position;
   	}
   	public void setPosition(int position)
   	{
   		this.position = position;
   	}
}