import java.sql.*;
// erzeugt Tabelle Coffee

public class CreateCoffees {

  public static void main(String args[]) {
	Connection con =  null;
	Statement stmt = null;
    String createString = "create table COFFEES " +
				"(COF_NAME VARCHAR(32), " +
				"SUP_ID INTEGER, " +
				"PRICE FLOAT, " +
				"SALES INTEGER, " +
				"TOTAL INTEGER," +
        		"constraint PK_COFFEE primary key (COF_NAME))";
    try {
      con =  ConnectionManager.getConnection();
      stmt = con.createStatement();
      stmt.executeUpdate(createString);
      System.out.println("Tabelle Coffees angelegt");
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
    finally{
          try {
            stmt.close();
            con.close();
            System.out.println("aufgeraeumt");
          }
          catch (Exception e) {e.printStackTrace();}
    }
  }
}


