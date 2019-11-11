package de.hsh.dbs2.imdb.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieCharacterFactory
{
	public List<CharacterDTO> Select_MovieCharacterByMovieID(long movie_ID)
	{
		List<CharacterDTO> arrL_charDTO = new ArrayList<CharacterDTO>();
		
		//SQL-Statement:
        String sql_Select_Character =
        		"SELECT " + "mc." + MovieCharacter.col_Character + "," + "mc." + MovieCharacter.col_alias + "," + "p." + Person.col_Name +
        		" FROM " + MovieCharacter.table + " mc" +
        		" INNER JOIN " + Person.table + " p" + " ON mc." + MovieCharacter.col_Person_ID + " = p." + Person.col_Person_ID +
        		" WHERE " + MovieCharacter.col_Movie_ID + " = " + movie_ID;
        
        System.out.println(sql_Select_Character);
        
        try
        {
	        Statement stmt = DBConnection.getConnection().createStatement();
	        ResultSet rs = stmt.executeQuery(sql_Select_Character);
	    	
	    	while(rs.next()) //Fülle jedes neue charDTO mit den ausgelesenen Werten
	    	{
	    		CharacterDTO charDTO = new CharacterDTO();
	    		
	    		charDTO.setCharacter(rs.getString(1));
	    		charDTO.setAlias(rs.getString(2));
	    		charDTO.setPlayer(rs.getString(3)); //Player = Person.Name
	    		
	    		arrL_charDTO.add(charDTO);
	    	}
        }
        catch(SQLException ex_SQL)
        {
        	ex_SQL.printStackTrace();
        }
		
		return arrL_charDTO;
	}
}
