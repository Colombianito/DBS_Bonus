package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hsh.dbs2.imdb.entities.GenreFactory;
import de.hsh.dbs2.imdb.entities.MovieCharacterFactory;
import de.hsh.dbs2.imdb.entities.MovieFactory;
import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;

public class MovieManager {

	List<MovieDTO> movie_DTO = new ArrayList<MovieDTO>();
	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception
	{
		MovieFactory mf 			= new MovieFactory();
		GenreFactory gf 			= new GenreFactory();
		MovieCharacterFactory mcf 	= new MovieCharacterFactory();
		
		movie_DTO = new ArrayList<MovieDTO>(mf.Select_All_MoviesByTitel(search));
		
		System.out.println("Checkpoint: 1");
		
		for(int i = 0; i < movie_DTO.size(); i++)
		{
			MovieDTO movie = movie_DTO.get(i); //Ein einzelnes Movie
			
			//Holt alle Genres pro Movie_ID mithilfe der getGenresByID-Methode und fügt sie der String-Liste genres hinzu
			Set<String> genres = new HashSet<String>(gf.getGenresByID(movie.getId())); 
			
			movie.setGenres(genres); //Fügt das Set von Genres dem einzelnen MovieDTO hinzu
			
			List<CharacterDTO> character_DTO = new ArrayList<CharacterDTO>(mcf.Select_MovieCharacterByMovieID(movie.getId()));
			movie.setCharacters(character_DTO);
		}
		
		System.out.println("Checkpoint: 2");

		return movie_DTO;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception
	{
		
	}

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movie
	 * @throws Exception
	 */
	public void deleteMovie(long movieId) throws Exception {
		// TODO Auto-generated method stub
	}

	public MovieDTO getMovie(long movieId) throws Exception {
		
		//MovieDTO Iteration über movieID
		//Gebe Movie aus
		
		return null;
	}

}
