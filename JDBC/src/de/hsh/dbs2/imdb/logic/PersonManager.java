package de.hsh.dbs2.imdb.logic;

import java.sql.SQLException;
import java.util.List;
import de.hsh.dbs2.imdb.entities.PersonFactory;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text)
	{
		PersonFactory pf = new PersonFactory();
		List<String> personList = null;
		
		try
		{
			personList = pf.Select_Persons_By_Name(text);
		}
		catch(SQLException ex_SQL)
		{
			ex_SQL.printStackTrace();
		}
        
		return personList;
	}

}
