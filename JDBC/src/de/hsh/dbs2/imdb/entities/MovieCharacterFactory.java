package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;

public class MovieCharacterFactory
{
	PreparedStatement stmt;
	
	public List<CharacterDTO> Select_MovieCharacterByMovieID(long movie_ID) throws SQLException
	{
		//SQL-Statement:
        String sql_Select_Character =
        		"SELECT " + MovieCharacter.col_Character + "," + MovieCharacter.col_alias + "," + Person.col_Name +
        		" FROM " + MovieCharacter.table +
        		" INNER JOIN " + Person.table + " ON " + MovieCharacter.col_Person_ID + " = " + Person.col_Person_ID +
        		" WHERE " + MovieCharacter.col_Movie_ID + " = " + movie_ID;
        
        System.out.println(sql_Select_Character);
        stmt = Select.conn.prepareStatement(sql_Select_Character);
        
        //EXEC SELECT:
        ResultSet rs = stmt.executeQuery();
    	List<CharacterDTO> arrL_charDTO = new ArrayList<CharacterDTO>();
    	
    	while(rs.next()) //Fülle jedes neue charDTO mit den ausgelesenen Werten
    	{
    		CharacterDTO charDTO = new CharacterDTO();
    		
    		charDTO.setCharacter(rs.getString(1));
    		charDTO.setAlias(rs.getString(2));
    		charDTO.setPlayer(rs.getString(3)); //Player = Person.Name
    		
    		arrL_charDTO.add(charDTO);
    	}
		
		return arrL_charDTO;
	}
}
