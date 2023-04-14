package jdbcPractise;

import static jdbcPractise.DatabaseUtilty.*;

public class Query06 {

    public static void main(String[] args) {

        createConnection();

        String query = "select * from ogrenciler";

        System.out.println("Sutun isimleri: " + getColumnNames(query));

        System.out.println("Ogrenci Ismi : " + getColumnData(query, "okul_no"));  //database testi var mi kontrolu bu sekilde

        System.out.println("Sinif: " + getColumnData(query,"sinif"));

        System.out.println("Cinsiyet : " + getColumnData(query, "cinsiyet"));




    }
}
