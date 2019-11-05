import java.sql.*;

public class Select {
  public static void main(String[] args) {

      Connection con = null;
      Statement stmt = null;
      try {

    	con = ConnectionManager.getConnection();
        stmt = con.createStatement();

	      // Select durchfuehren
        String anfang = "";
	    String query1 = "select cof_name, price from coffees where cof_name like '"
              + anfang + "%'" ;
	      System.out.println(query1);
        ResultSet rs = stmt.executeQuery(query1);
        // ResultSet lesen und ausgeben
        String cof_name=""; double price=0.0;
        while (rs.next()){
           cof_name = rs.getString("cof_name");
           price = rs.getDouble(2);
           System.out.println(cof_name + "\t\t\t" + price);
        }
      }
      catch (SQLException e){
        System.out.println(e.getMessage());
        System.out.println(e.getSQLState());
        System.out.println(e.getErrorCode());
        e.printStackTrace();
      }
      finally{
          try {
            stmt.close();   con.close();
            System.out.println("aufgeraeumt");
          }
          catch (Exception e) {e.printStackTrace();}
    }
  }
}