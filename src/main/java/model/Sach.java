package model;

public class Sach extends SanPham {
    private String tacgia;
    private String theloai;
    private int namxuatban;
    private String nhaxuatban;

    public Sach() {
    }

    public Sach(String masp, String tensp, int soluong, double dongia,String tacgia, String theloai, int namxuatban, String nhaxuatban) {
        super(masp, tensp, soluong, dongia);
        this.tacgia = tacgia;
        this.theloai = theloai;
        this.namxuatban = namxuatban;
        this.nhaxuatban = nhaxuatban;
    }

    public String getTacGia() {
        return tacgia;
    }

    public void setTacGia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getNamXuatban() {
        return namxuatban;
    }

    public void setNamXuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getNhaXuatBan() {
        return nhaxuatban;
    }

    public void setNhaXuatBan(String nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", tacgia='" + tacgia + '\'' +
                ", theloai='" + theloai + '\'' +
                ", namxuatban=" + namxuatban +
                ", nhaxuatban='" + nhaxuatban + '\'' +
                '}';
    }
}