package service;
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
    public void
// Nhập hàng (+ tìm id để sộng số lượng nếu chưa có thì thêm sản phẩm)
// Xuất hàng (hiển thị toàn bộ sản phẩm)
}
