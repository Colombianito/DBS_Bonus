package de.hsh.dbs2.imdb.entities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// liefert Connections zur Datenbank
// die Connection-Parameter mÃ¼ssen in der Property-Datei
// connect.properties festgelegt werden

public class ConnectionManager
{
	public static Connection conn;
	
	private static ResourceBundle b;
	private static String uid;
	private static String pwd;
	private static String driver;
	private static String dburl;

  static {
      // die Initialisierung von Treiber und Connect-Parameter findet
      // nur einmal statt
      // Laden der Connection-Parameter aus der Property-Datei
      b = ResourceBundle.getBundle("connect");
      uid    = b.getString("uid");
      pwd    = b.getString("pwd");

      driver = b.getString("driver");
      dburl  = b.getString("dburl");

      System.out.println("Account: " + uid +  "\nTreiber:  "  +  driver + "\ndburl:  " + dburl);

      try{
        // Initialisieren der Treiber-Klasse

		@SuppressWarnings("unused")
		Class<?> driverClass = Class.forName(driver);

      }catch (Exception e){
        System.out.println("Treiber konnte nicht initialisiert werden ....");
      }
  }

  // es kï¿½nnen mehrere Connections angefordert werden

  static public Connection getConnection() throws SQLException  {
    
    try
    {
        conn = DriverManager.getConnection(dburl, uid, pwd);
        System.out.println("Connect durchgeführt ....");
        conn.setAutoCommit(false);
    }
    catch (Exception e)
    {
        System.err.println("Could not establish the JDBC connection:");
        System.err.println(e);
        System.exit(1);
    }
    return conn;
  }

}
