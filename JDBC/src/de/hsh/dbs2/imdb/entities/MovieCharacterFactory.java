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
        		"SELECT " + MovieCharacter.col_Person_ID + "," +MovieCharacter.col_Character + "," + MovieCharacter.col_alias +
        		" FROM " + MovieCharacter.table +
        		" WHERE " + Movie.col_Movie_ID + " = " + movie_ID;
        
        System.out.println(sql_Select_Character);
        stmt = Select.conn.prepareStatement(sql_Select_Character);
        
        //EXEC SELECT:
        ResultSet rs = stmt.executeQuery();
    	List<CharacterDTO> arrL_charDTO = new ArrayList<CharacterDTO>();
    	
    	while(rs.next()) //Fülle jedes neue charDTO mit den ausgelesenen Werten
    	{
    		CharacterDTO charDTO = new CharacterDTO();
    		
    		charDTO.setPerson_ID(rs.getInt(1));
    		charDTO.setCharacter(rs.getString(2));
    		charDTO.setAlias(rs.getString(3));
    		
    		arrL_charDTO.add(charDTO);
    	}
		
		return arrL_charDTO;
	}
}
