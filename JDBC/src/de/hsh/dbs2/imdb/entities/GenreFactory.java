package de.hsh.dbs2.imdb.entities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreFactory
{
	Statement stmt;
	
	public List<String>Select_All_Genres() throws SQLException
	{
		 //SQL-Statement:
        String sql_Select_Genres =
        		"SELECT " + Genre.col_genre +
        		" FROM " + Genre.table +
        		" ORDER BY " + Genre.col_genre + " asc"; //Genres alphabetic sortet
        
        stmt = ConnectionManager.getConnection().createStatement();
        System.out.println(sql_Select_Genres);
        
        //EXEC SELECT:
        ResultSet rs = stmt.executeQuery(sql_Select_Genres);
        List<String> arrL_Genres = new ArrayList<String>();
        
        while(rs.next())
        {
        	arrL_Genres.add(rs.getString(1)); //only one column
        }
        rs.close();
        stmt.close();
        
        return arrL_Genres;
	}
	
	public Set<String> getGenresByID(long movie_ID) throws SQLException
	{
		//SQL-Statement
        String sql_Select_Genres =
        		"SELECT " + "g." + Genre.col_genre +
        		" FROM " + Genre.table + " g" + 
        		" INNER JOIN " + GenreMovie.table + " gm " + " ON g." + Genre.col_genre_ID + " = gm." + GenreMovie.col_Genre_ID +
        		" WHERE " + Movie.col_Movie_ID + " = " + movie_ID;
        
        System.out.println(sql_Select_Genres);
        stmt = ConnectionManager.getConnection().createStatement();
        
        //EXEC SELECT:
        ResultSet rs = stmt.executeQuery(sql_Select_Genres);
        
        Set<String> set_Genres = new HashSet<String>();
        
        while(rs.next())
        {
        	set_Genres.add(rs.getString(1));
        }
        rs.close();
        stmt.close();
        
        return set_Genres;
	}
}
