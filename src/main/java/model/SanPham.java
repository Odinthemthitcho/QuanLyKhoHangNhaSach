package model;

public abstract class SanPham {
    protected String masp;
    protected String tensp;
    protected double dongia;
    protected int soluong;

    public SanPham(){};

    public SanPham(String masp, String tensp, int soluong, double dongia) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoai() {
        return "SanPham";
    }

}


