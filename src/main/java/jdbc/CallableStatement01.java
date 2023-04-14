package jdbc;

import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa da, void olsa da methods olarak adlandirilir.
    SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" diye adlandirilir.
     */



    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","qx357Git");
        Statement st = con.createStatement();

        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.

        //1. adim : Fonksiyon kodunu yaz

        String sql1 = "create or replace function addF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "as\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2. adim : fonksiyonu calistir

        st.execute(sql1);

        //3. adim : fonksiyonu cagir

        con.prepareCall("{? = call addF(?, ?)}");// bu prepareCall collablestatement donduruyor esitliyoruz
        CallableStatement cst1 = con.prepareCall("{? = call addF(?, ?)}");

        //4. adim : ilk soru isareti ve 2. grup  ? icin parametreler icin
        //Return icin resgisterOutParameter() methodunu, parametreler icin set() methodlarindan uygun olanlari kullan.

        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);


        //5. adim :  Calistirmak icin execute() methodunu kullan.
        cst1.execute();

        //6. adim : Sonucu cagirmak icin  return data tipine gore "get" methodlarindan uygun olani kullan
        System.out.println(cst1.getBigDecimal(1));

//                 ======================================================

        //2. Ornek : Koninin hacmini hesaplayan bir function yazin


        String sql2 = "create or replace function addF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "as\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        st.execute(sql2);

        con.prepareCall("{? = call addF(?, ?)}");// bu prepareCall collablestatement donduruyor esitliyoruz
        CallableStatement cst2 = con.prepareCall("{? = call addF(?, ?)}");

        cst1.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,15);
        cst2.setInt(3,25);

        cst2.execute();

        System.out.println(cst1.getBigDecimal(1));

    }
}
