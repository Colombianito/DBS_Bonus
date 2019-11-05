import java.sql.*;

public class InsertCoffees {

  public static void main(String args[]) {
    Connection con = null;
    Statement stmt = null;

    try {
    	con =  ConnectionManager.getConnection();
	    stmt = con.createStatement();
	    stmt.executeUpdate("insert into COFFEES " +
	            "values('Colombian', 00101, 7.99, 0, 0)");

	    stmt.executeUpdate("insert into COFFEES " +
	         "values('French_Roast', 00049, 8.99, 0, 0)");
	    stmt.executeUpdate("insert into COFFEES " +
	         "values('Espresso1', 00150, 9.99, 0, 0)");
	    stmt.executeUpdate("insert into COFFEES " +
	          "values('Colombian_Decaf', 00101, 8.99, 0, 0)");
	    stmt.executeUpdate("insert into COFFEES " +
	         "values('French_Roast_Decaf', 00049, 9.99, 0, 0)");
      System.out.println("Datensaetze in Tabelle COFFEES eingefuegt");
      
      stmt.close();   con.close();
      System.out.println("aufgeraeumt");

    } catch(SQLException e) {e.printStackTrace();   
    }
  }
}

