package model;

public class VanPhongPham extends SanPham {
    private String thuonghieu;
    private String xuatXu;
    private String chatluong;

    public VanPhongPham() {
    }

    public VanPhongPham(String masp, String tensp, int soluong, double dongia,
                        String thuonghieu, String xuatXu, String chatluong) {
        super(masp, tensp, soluong, dongia);
        this.thuonghieu = thuonghieu;
        this.xuatXu = xuatXu;
        this.chatluong = chatluong;
    }

    public String getThuongHieu() {
        return thuonghieu;
    }

    public void setThuongHieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getChatLuong() {
        return chatluong;
    }

    public void setChatLuong(String chatLuong) {
        this.chatluong = chatLuong;
    }

    @Override
    public String toString() {
        return "VanPhongPham{" +
                "masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", thuonghieu='" + thuonghieu + '\'' +
                ", xuatXu='" + xuatXu + '\'' +
                ", chatluong='" + chatluong + '\'' +
                '}';
    }
}