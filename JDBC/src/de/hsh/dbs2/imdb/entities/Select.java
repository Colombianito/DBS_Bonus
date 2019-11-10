package de.hsh.dbs2.imdb.entities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import de.hsh.dbs2.imdb.logic.GenreManager;
import de.hsh.dbs2.imdb.logic.MovieManager;

public class Select
{
	public static Connection conn;
	public static int debug = 0;

	public static void main(String[] args)
	{
		try
		{
			conn = ConnectionManager.getConnection();
			
			MovieManager manager = new MovieManager();
			manager.getMovieList("");
			//select();
			//testInsert();
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage() + ex.getStackTrace() + ex.getCause());
			System.out.println("Debug_NO: " + debug);
		}
	}
	
	public static void select() throws Exception
	{
		conn = ConnectionManager.getConnection();
		GenreManager g_Manager = new GenreManager();
		g_Manager.getGenres();
	}

//	public static void testInsert() throws SQLException
//	{
//		boolean ok = false;
//
//		try
//		{
//			conn = ConnectionManager.getConnection();
//
//			Movie movie = new Movie();
//			movie.setTitle("Die tolle Komoedie");
//			movie.setYear(2012);
//			movie.setType('C');
//			movie.insert();
//
//			MovieFactory mf = new MovieFactory();
//			movie = mf.findByID(2);
//			System.out.println(mf.printMovie(movie) + "\n");
//
//			List<Movie> list_Movie = mf.findByTitle("Green Mile");
//			System.out.println(mf.printMovieList(list_Movie) + "\n");
//			
//			debug = 1;
//
//			Person person = new Person();
//			person.setName("Karl Tester");
//			person.setSex('M');
//			person.insert();
//			Select.conn.commit();
//			
//			debug = 2;
//
//			MovieCharacter chr = new MovieCharacter();
//			chr.setMovChar_ID(movie.getMovieID());
//			chr.setPerson_ID(person.getPerson_ID()); // achtung, setPlayerID -- hasCharacter = movieID -- Plays = personID
//			debug = 3;
//			
//			chr.setMovie_ID(1);
//			chr.setPerson_ID(1);
//			chr.setCharacter("Hauptrolle");
//			chr.setAlias('s');
//			chr.setPosition(1);
//			chr.insert();
//			Select.conn.commit();
//			
//			debug = 4;
//
//			Genre genre = new Genre();
//			genre.setGenre("Unklar");
//			genre.insert();
//			Select.conn.commit();
//			
//			debug = 5;
//
//			GenreMovie genreMovie = new GenreMovie();
//			genreMovie.setGenre_ID(genre.getGenre_ID());
//			genreMovie.setMovie_ID(movie.getMovieID());
//			genreMovie.insert();
//			Select.conn.commit();
//			
//			debug = 6;
//
//			conn.commit();
//			conn.close();
//			ok = true;
//		}
//		finally
//		{
//			if (!ok)
//				conn.rollback();
//		}
//	}
}