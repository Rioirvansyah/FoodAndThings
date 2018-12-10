package com.example.rioir.fat;

/**
 * Created by rioir on 10/20/2018.
 */

public class Home  {
    private String Menu;
    private String Daerah;
    private String Harga;
//    private int Gambar;

    public Home(){

    }

    public Home(String menu, String daerah, String harga) {
        Menu = menu;
        Daerah = daerah;
        Harga = harga;
//        Gambar = gambar;
    }

    public String getMenu() {
        return Menu;
    }

    public String getDaerah() {
        return Daerah;
    }

    public String getHarga() {
        return Harga;
    }

//    public int getGambar() {
//        return Gambar;
//    }
}
