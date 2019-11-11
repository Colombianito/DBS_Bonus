package de.hsh.dbs2.imdb.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.util.DBConnection;

public class PersonFactory
{
	public List<String> Select_Persons_By_Name(String name) throws SQLException
	{
		List<String> persons = new ArrayList<String>();
		
		//SQL-Statement:
        String sql_Select_Persons = "SELECT name FROM Person WHERE UPPER(name) LIKE UPPER('%" + name + "%')";
        System.out.println(sql_Select_Persons);

        Statement stmt = DBConnection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql_Select_Persons);
        
        while(rs.next())
        {
        	persons.add(rs.getString(1));
        }
        
        return persons;
	}
}
