package de.hsh.dbs2.imdb.entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person { 
    
	public static final String seq_Person_ID	= "person_ID";
	public static final String table			= "Person";
	public static final String col_Person_ID	= "PersonID";
	public static final String col_Name			= "Name";
	public static final String col_Sex			= "Sex";
	
	private String name;
    private char sex;
	private int person_ID;
    private PreparedStatement stmt;
    
    public Person() {}
    
    public Person (int person_ID, String name, char sex)
    {
    	setPerson_ID(person_ID);
    	setName(name);
    	setSex(sex);
    }
    
	public void insert() throws SQLException
    {
    	String sql =
    			"INSERT INTO Person( " + 
    			 col_Person_ID + "," +
				 col_Name + "," +
    			 col_Sex +") " +
				"VALUES(" + seq_Person_ID + ".nextval,?,?)";
    	
    	stmt = Select.conn.prepareStatement(sql);
    	
    	stmt.setString(1, getName() + System.currentTimeMillis());
    	stmt.setString(2, String.valueOf(sex));
    	
    	int rowslnserted = stmt.executeUpdate();
    	stmt. close();
    	
    	if(rowslnserted < 1)
		{
    		System.out.println("Es wurde kein Datensatz eingefügt");
		}
    	else
    	{
    		System.out.println("Es wurde " + rowslnserted + " Zeilen hinzugefügt");
    	}
    	
    	//ID:
    	sql = "Select " + seq_Person_ID + ".currval FROM DUAL";
    	stmt = Select.conn.prepareStatement(sql);
    	ResultSet rs = stmt.executeQuery();
    	
    	if(rs.next())
    	{
    		setPerson_ID(rs.getInt(1));
    	}
    	rs.close();
    	stmt.close();
    }
	
	public void update() throws SQLException
	{
		//SQL-Statement:
		String sql_Update =
				"UPDATE " + table +
				" SET " + col_Name + " = ?, " + col_Sex + " = ? " +
				"WHERE " + col_Person_ID + " = ?";
		
		stmt = Select.conn.prepareStatement(sql_Update);
		stmt.setString(1, name);
		stmt.setString(2, String.valueOf(sex));
		stmt.setInt(3, person_ID);
		
		//Update now:
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsUpdated + " Zeilen verändert.");
		
		stmt.close();
	}
	
	public void delete() throws SQLException
	{
		String sql_Delete =
				"DELETE FROM " + table +
				" WHERE " + col_Person_ID + " = ?";
		
		stmt = Select.conn.prepareStatement(sql_Delete);
		
		stmt.setInt(1, getPerson_ID());
		
		//delete it
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden " + rowsDeleted + " Zeilen gelöscht");
		
		stmt.close();
	}
    
    public int getPerson_ID()
	{
		return person_ID;
	}
    
	public void setPerson_ID(int person_ID)
	{
		this.person_ID = person_ID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public char getSex()
	{
		return sex;
	}

	public void setSex(char sex)
	{
		this.sex = sex;
	}
}
