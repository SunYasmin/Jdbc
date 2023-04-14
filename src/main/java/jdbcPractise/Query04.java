package jdbcPractise;

import java.sql.*;

public class Query04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "qx357Git");

        //Statement st = con.createStatement(); Statement yavas ve dinamik degil
        // farklari prepared daha dinamik ve daha az yer kaplar prepared statement  prestatement daha guvenli bu ? belli ona gore yer hazir daha dinamik

        PreparedStatement ps = con.prepareStatement("insert into ogrenciler values(?, ?, ?, ?)");

        ps.setInt(1,200);
        ps.setString(2,"Veli Can");
        ps.setString(3,"12");
        ps.setString(4,"E");

        ps.executeUpdate();

        System.out.println("Veri girisi yapildi");

        con.close();
        ps.close();
    }
}
