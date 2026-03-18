package model;

public class VanPhongPham extends SanPham {
    private String thuongHieu;
    private String xuatXu;
    private String chatLuong;

    public VanPhongPham() {
    }

    public VanPhongPham(String masp, String tensp, int soluong, double dongia,
                        String thuongHieu, String xuatXu, String chatLuong) {
        super(masp, tensp, soluong, dongia);
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.chatLuong = chatLuong;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getChatLuong() {
        return chatLuong;
    }

    public void setChatLuong(String chatLuong) {
        this.chatLuong = chatLuong;
    }

    @Override
    public String toString() {
        return "VanPhongPham{" +
                "masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                ", thuongHieu='" + thuongHieu + '\'' +
                ", xuatXu='" + xuatXu + '\'' +
                ", chatLuong='" + chatLuong + '\'' +
                '}';
    }
}