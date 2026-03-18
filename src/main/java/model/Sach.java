package model;

public class Sach extends SanPham {
    private String tacGia;
    private String theloai;
    private int namXuatban;
    private String nhaXuatBan;

    public Sach() {
    }

    public Sach(String masp, String tensp, int soluong, double dongia,
                String tacGia, String theloai, int namXuatban, String nhaXuatBan) {
        super(masp, tensp, soluong, dongia);
        this.tacGia = tacGia;
        this.theloai = theloai;
        this.namXuatban = namXuatban;
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getNamXuatban() {
        return namXuatban;
    }

    public void setNamXuatban(int namXuatban) {
        this.namXuatban = namXuatban;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", tacGia='" + tacGia + '\'' +
                ", theloai='" + theloai + '\'' +
                ", namXuatban=" + namXuatban +
                ", nhaXuatBan='" + nhaXuatBan + '\'' +
                '}';
    }
}