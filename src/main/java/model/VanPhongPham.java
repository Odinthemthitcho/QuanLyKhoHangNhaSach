package model;

public class VanPhongPham extends SanPham {
    private String thuonghieu;
    private String xuatxu;
    private String chatluong;

    public VanPhongPham() {
    }

    public VanPhongPham(String masp, String tensp, int soluong, double dongia,
                        String thuonghieu, String xuatxu, String chatluong) {
        super(masp, tensp, soluong, dongia);
        this.thuonghieu = thuonghieu;
        this.xuatxu = xuatxu;
        this.chatluong = chatluong;
    }

    public String getThuongHieu() {
        return thuonghieu;
    }

    public void setThuongHieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getXuatXu() {
        return xuatxu;
    }

    public void setXuatXu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getChatLuong() {
        return chatluong;
    }

    public void setChatLuong(String chatluong) {
        this.chatluong = chatluong;
    }
    

    @Override
    public String toString() {
        return "VanPhongPham{" +
                "masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", thuonghieu='" + thuonghieu + '\'' +
                ", xuatxu='" + xuatxu + '\'' +
                ", chatluong='" + chatluong + '\'' +
                '}';
    }
}