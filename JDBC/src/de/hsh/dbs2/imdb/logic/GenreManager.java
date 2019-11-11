package de.hsh.dbs2.imdb.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.entities.GenreFactory;

public class GenreManager
{

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * 
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres()
	{	
		GenreFactory gf = new GenreFactory();
		List<String> genres = null;
		
		try
		{
			genres = new ArrayList<String>(gf.Select_All_Genres()); //Hier moviedto longid
			
			for(String genre : genres)
				System.out.println(genre);
		}
		catch(SQLException ex_SQL)
		{
			ex_SQL.printStackTrace();
		}
		return genres;
	}
}
