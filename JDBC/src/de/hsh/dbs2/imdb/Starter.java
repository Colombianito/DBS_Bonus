package de.hsh.dbs2.imdb;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import de.hsh.dbs2.imdb.entities.ConnectionManager;
import de.hsh.dbs2.imdb.gui.SearchMovieDialog;
import de.hsh.dbs2.imdb.gui.SearchMovieDialogCallback;

public class Starter {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		
		Connection conn = ConnectionManager.getConnection();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Starter().run();
			}
		});
	
	}
	
	public void run() {
		SearchMovieDialogCallback callback = new SearchMovieDialogCallback();
		SearchMovieDialog sd = new SearchMovieDialog(callback);
		sd.setVisible(true);
	}

}
