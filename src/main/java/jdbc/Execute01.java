package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {   //NOT DATABASE TESTİ YAPTIRACAKSA FİRMA BUNU KULLANACAGIZ-1 KERE YAPILAN BİRSEY YAPIP CEKECEGIZ
// BURDA SQL KODU YAZİP GONDERECEGİZ JAVA YA
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adim: Driver'a kaydol
        Class.forName("org.postgresql.Driver");  // parantez ici adres

        //2. adim: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","qx357Git"); // method overloading her parametre ekledigimde
        // bu connection datatype getConnection methodu connetion data turu dındurur

        //3. Adim: Statement olustur  -- 2. dimdaki connection ı kullan
        Statement st = con.createStatement();// createStatement methodu Statement data type donduruyor o yuzden esitledi assign

        //ARTIK QUERY LERI CAGIRABILIRIM SQL DE YAPTIGIM ISLEMLERI BURADA YAPACAGIM
        // DATA CAGIR

        //4. Adim: Query calistir:

        //1.Ornek: "workers" adinda bir table olusturup "worker_id, worker_name, worker_salary" sutunlarini ekleyin
        //st.execute("CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT)");
        //Parantez ici java kodu degil sql kodu string olarak geciyor o yuzden
        // Boyle uzun yazilmasi pek istenmez String bir value olusturup ona yazariz daha mantikli

        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT)";
        boolean result = st.execute(sql1); //execute methodu boolean dondurur atadik
        System.out.println(result);  // false return yapar cunku data cagrilmadi sadece islem yapti tablo olusturdu
        // true vermez tablo olusturunca boyle

        //postgreye git table i bul sag tik wiev/edit data--> allrows altta cikar


        //2. Example: Table'a worker_address sütunu ekleyerel alter yapın. table degistir
        //bastan run edince tekrar tablo olusturmaya calisip sorun olmasin diye sql de workers i sag tik delete/ drop yaptim
        String sql2 =  "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql2);

        //3.Ornek :Drop workers table
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);
        // drop yapacaksam once olusturup sonra silmem lazim

        //5. Adim : Baglanti connection-ve Statement' i kapat;  Bu guvenlik amacli baglantiyi kopaririz
        con.close();  //GUVENLI CIKIS
        st.close();  //veriler degisikliklerle kalmasin ve databasedeki veri korunsun


    }
}
