package service;

import dao.SachDAO;
import model.Sach;

// import java.util.ArrayList;
// import java.util.HashMap;
import java.util.List;
// import java.util.Map;

public class ThongKeKho {
    private SachDAO sachDAO = new SachDAO();

//    Tổng số lượng tồn kho
    public int tongsoluongtonkho() {
        List<Sach> ds = sachDAO.selectAll();
        int tong = 0;

        for (Sach s : ds){
            tong += s.getSoluong();
        }
        return tong;
    }
//    Tổng giá trị kho
// public double tongGiaTriKho() {
//         List<Sach> ds = sachDAO.selectAll();
//         double tong = 0;

//         for (Sach s : ds) {
//             tong += s.getSoLuong() * s.getGia();
//         }

//         return tong;
//     }
//    Sản phẩm sắp hết hàng sp < 5 Z

}
