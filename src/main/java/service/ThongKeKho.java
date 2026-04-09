package service;

import dao.SachDAO;
import model.Sach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongKeKho {
    private SachDAO sachDAO = new SachDAO();

//    Tổng số lượng tồn kho
    public int tongsoluongtonkho() {
        List<Sach> ds = sachDAO.getAll();
        int tong = 0;

        for (Sach s : ds){
            tong += s.getSoluong();
        }
        return tong;
    }
//    Tổng giá trị kho
public double tongGiaTriKho() {
        List<Sach> ds = sachDAO.getAll();
        double tong = 0;

        for (Sach s : ds) {
            tong += s.getSoluong() * s.getDongia();
        }

        return tong;
    }
//    Sản phẩm sắp hết hàng
//    Sách tồn kho nhiều nhất
//    Sách tồn kho ít nhất
//    Thống kê theo thể loại
//    Danh sách sách theo năm xuất bản
// vẽ biểu đồ hiển thị danh sách bán 
}
