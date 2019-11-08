package de.hsh.dbs2.imdb.entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreFactory
{
	PreparedStatement stmt;
	
	public List<String>Select_All_Genres() throws SQLException
	{
		 //SQL-Statement:
        String sql_Select =
        		"SELECT " + Genre.col_genre + " FROM " + Genre.table + " " +
        		"ORDER BY " + Genre.col_genre + " asc"; //Genres alphabetic sortet
        
        stmt = Select.conn.prepareStatement(sql_Select);
        System.out.println(sql_Select);
        
        //SELECT:
        ResultSet rs = stmt.executeQuery(sql_Select);
        List<String> genres = new ArrayList<String>();
        
        while(rs.next())
        {
        	genres.add(rs.getString(1)); //only one column
        }
        rs.close();
        stmt.close();
        
        return genres;
	}
	
	public List<String> getGenresByID(long movie_ID)
	{
		/*Select genre FROM GENRE g
		inner join GENREMOVIE gm on g.GENREID = gm.GENREID
		where MOVIEID = movie_ID;*/
		
		return null;
		
	}
}
