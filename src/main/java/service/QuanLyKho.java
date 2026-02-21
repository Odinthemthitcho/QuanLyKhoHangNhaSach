package service;
import java.util.ArrayList;

import dao.SachDAO;
import model.Sach;

public class QuanLyKho {
    //Them sach
    private SachDAO sachDAO = new SachDAO();
    public void themSach(Sach sach){
        sachDAO.insert(sach);
    }
// Sửa sách
    public void suaSach(Sach sach){
        sachDAO.update(sach);
    }
// Xóa sách
    public void xoaSach(Sach sach){
        sachDAO.delete(sach);
    }
//Hiển thị danh sách kho
    public void hienThiKho() {
        ArrayList<Sach> danhSach = sachDAO.selectAll();
        if (danhSach == null) {
            System.out.println("Kho hien dang trong");
        } else {
            System.out.println("--- Danh sach san pham trong kho ---");
            for (Sach s : danhSach) {
                System.out.println(s.toString()); 
            }
        }
    }
// Nhập hàng (+ tìm id để sộng số lượng nếu chưa có thì thêm sản phẩm)
    public void nhapHang(Sach sachNhap) {
        int maInt = Integer.parseInt(sachNhap.getMasp()); 
        Sach sachHienTai = sachDAO.selectById(maInt);
        if (sachHienTai != null) {
            int soLuongMoi = sachHienTai.getSoluong() + sachNhap.getSoluong();
            sachHienTai.setSoluong(soLuongMoi);
            sachDAO.update(sachHienTai);
        } else {
            sachDAO.insert(sachNhap);
        }
    }
// Tìm hàng theo điều kiện truy vấn
    public void timHang(String dieuKien) {
        ArrayList<Sach> ketQua = sachDAO.selectByCondition(dieuKien);
        if (ketQua != null) {
            for (Sach s : ketQua) {
                System.out.println(s.toString());
            }
        }
    }
// Xuất hàng (hiển thị toàn bộ sản phẩm)
    public void xuatHang() {
        ArrayList<Sach> danhSachXuat = sachDAO.selectAll();
        System.out.println("---------- DANH SACH SAN PHAM ----------");
        if (danhSachXuat == null ) {
            System.out.println("Khong co san pham de xuat");
        } else {
            for (Sach s : danhSachXuat) {
                System.out.println(s.toString());
            }
        }
        System.out.println("-----------------------------------------");
    }
}


    

