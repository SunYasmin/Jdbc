package jdbc;

import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","qx357Git");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql1 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "ORDER BY number_of_employees DESC\n" +
                "OFFSET 1 ROW\n" +
                "FETCH NEXT 1 ROW ONLY";

        ResultSet result1 = st.executeQuery(sql1);
        while (result1.next()){
            System.out.println(result1.getString("company")+"---"+result1.getInt("number_of_employees"));
        }

        //st.execute(sql1);  tablo olusturursak bunu kullanacagiz yurutup

//        ResultSet result1 = st.executeQuery(sql1);
        //resultSet e atalim
        //System.out.println(result.toString());  boyle calismaz referansi getirir benim tek tek number_of_emploo  ve company adini almam lazim

       // while (result1.next()){  // loop yaptim next geldigi surece yap asagidakini yap
            //System.out.println(result1.getString("company" + " " + result1.getInt("number_of_employees")));
            // index 0 en bas siralama numarasinin oldugu yer   2   company


        //2. yol subquery kullanarak
            String sql2 = "SELECT company, number_of_employees\n" +
                    "FROM companies\n" +
                    "WHERE number_of_employees = (SELECT  MAX(number_of_employees)\n" +
                    "                             FROM companies\n" +
                    "                             WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                    "                             FROM companies))";

            ResultSet result2 = st.executeQuery(sql2);
            while (result2.next()){
                System.out.println(result2.getString("company")+"---"+result2.getInt("number_of_employees"));
            }
        }

}
