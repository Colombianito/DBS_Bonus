import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Select_2
{
	public static Connection conn;
    
	public static void main(String[] args)
	{
		try
		{
			testInsert();
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage() + ex.getStackTrace() + ex.getCause());
			System.out.println("LOL");
		}
	}

	public static void testInsert() throws SQLException
	{
		boolean ok = false;

		try
		{
			conn = ConnectionManager.getConnection();
			
			Movie movie = new Movie();
			movie.setTitle("Die tolle Komoedie");
			movie.setYear(2012);
			movie.setType('C');
			movie.insert();
			
			MovieFactory mf = new MovieFactory();
			movie = mf.findByID(2);
			System.out.println(mf.printMovie(movie)+ "\n");
			
			List<Movie> list_Movie = mf.findByTitle("Green Mile");
			System.out.println(mf.printMovie(list_Movie) + "\n");
			
			 Person person = new Person();
			 person.setName("Karl Tester");
			 person.setSex('M');
			 person.insert();
			 
			 MovieCharacter chr = new MovieCharacter();
			 chr.setMovChar_ID(movie.getMovieID());
			 chr.setPlayerId(person.getId()); //achtung, setPlayerID
			 chr.setCharacter("Hauptrolle");
			 chr.setAlias(null);
			 chr.setPos(1);
			 chr.insert();
			 
			 Genre genre = new Genre();
			 genre.setGenre("Unklar");
			 genre.insert();
			 
			 MovieGenre movieGenre = new MovieGenre();
			 movieGenre.setGenreId(genre.getId());
			 movieGenre.setMovieId(movie.getId());
			 movieGenre.insert();
			 conn.commit();
			
			conn.close();
			ok = true;
		}
		finally
		{
			if (!ok) conn.rollback();
		}
	}
}