package jdbc;

import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "qx357Git");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. adim: Prepared statement query sini olustur  // elimle girdiklerim hardcoding

        //String sql1 = "UPDATE companies SET number_of_employees = 9999 where company = 'IBM'";  bu dinamik degilll
        // ? koyularak yazilir

        String sql1 = "UPDATE companies SET number_of_employees = ? where company = ?";

        //2. adim: Prepared statement objesini olustur. prepared Statement hazirliyorum
        con.prepareStatement(sql1); //objemizi olusturduk
        PreparedStatement pst1 = con.prepareStatement(sql1); //interface e atadik bu kural gibi soru isareti ile calisacak o yuzden interface???

        //3. adim: set....() methodlari ile ? icin deger gir
        pst1.setInt(1, 9999);// 1. SORU İSARETİ 9999 YAP.........
        pst1.setString(2, "IBM");  //2. SORU İSARETI IBM YAP

        //4. Adim : Query calistir/ Execute Update
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " satir guncellendi.");

        String sql2 = "select * from companies";
        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()) {
            System.out.println(result1.getInt(1) + "--" + result1.getString(2) + "--" + result1.getInt(3));

        }


        //Google icin degisiklik
        pst1.setInt(1, 9999);// 1. SORU İSARETİ 9999 YAP.........
        pst1.setString(2, "IBM");  //2. SORU İSARETI IBM YAP

        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi2 + " satır güncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result3 = st.executeQuery(sql3);

        while (result3.next()) {

            System.out.println(result3.getInt(1) + "--" + result3.getString(2) + "--" + result3.getInt(3));
        }

        //Google için değişiklik
       /* pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");

        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi2+" satır güncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result3 =  st.executeQuery(sql3);

        while (result3.next()){

            System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+result3.getInt(3));
        }
        */


        //kebap case aa-bb-cc
        //snake case read_data
        //pascal case tamamen buyuk harf

        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.

        System.out.println("============");
        read_data(con,"companies");  //countries olur





    }
        //Bir tablonun istenilen datasını prepared statement ile çağırmak için kullanılan method.
        public static void read_data(Connection con, String tableName ){


            try {

                String query = String.format("SELECT * FROM %s",tableName);//Format() methodu dinamik String oluşturmak için kullanılır.
                //SQL query'yi çalıştır.
                Statement statement = con.createStatement();

                ResultSet rs = statement.executeQuery(query);//Datayı çağırıp ResultSet konteynırına koyuyoruz.

                while (rs.next()){//Tüm datayı çağıralım.

                    System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getInt(3));

                }


            }catch (Exception e){
                System.out.println(e);
            }

        }
}
