package model;
import java.util.Scanner;

public class Sach extends SanPham {

   private String theloai;
   private int namXuatban;

   public Sach(String masp, String tensp, int soluong, double dongia,
               String theloai, int namXuatban) {
      super(masp, tensp, soluong, dongia);
      this.theloai = theloai;
      this.namXuatban = namXuatban;
   }

   public String getTheloai() {
      return theloai;
   }

   public int getNamXuatban() {
      return namXuatban;
   }

   public void setNamXuatban(int namXuatban) {
      this.namXuatban = namXuatban;
   }

   public void setTheloai(String theloai) {
      this.theloai = theloai;
   }
}



