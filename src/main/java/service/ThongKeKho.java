package service;

import dao.SachDAO;
import model.Sach;
import java.util.ArrayList;
import dao.VanPhongPhamDAO;
import model.VanPhongPham;
import java.util.ArrayList;
import java.util.List;

public class ThongKeKho {
    private SachDAO sachDAO = new SachDAO();
    private VanPhongPhamDAO vppDAO = new VanPhongPhamDAO();
    
    // Hàm trả về danh sách Sách cho JTable sách
    public ArrayList<Sach> getListSach() {
        return sachDAO.selectAll();
    }

    // Hàm trả về danh sách Văn Phòng Phẩm cho JTable VPP
    public ArrayList<VanPhongPham> getListVPP() {
        return vppDAO.selectAll();
    }
    // 1. Tính tổng số lượng sách tồn trong kho
    public int tongSoLuongTonSach() {
        // Gọi chuẩn hàm selectAll() từ SachDAO của bạn
        ArrayList<Sach> ds = sachDAO.selectAll(); 
        int tong = 0;
        
        for (Sach s : ds) {
            tong += s.getSoluong(); // Lưu ý: Nếu báo lỗi đỏ ở đây, hãy đổi thành s.getSoLuong() (chữ L viết hoa)
        }
        return tong;
    }

    // 2. Hàm tính tổng số lượng đầu sách khác nhau (Dùng cho ô Tổng Sách)
    public int tongSoDauSach() {
        ArrayList<Sach> ds = sachDAO.selectAll();
        return ds.size(); // Trả về số lượng phần tử (đầu sách) có trong danh sách
    }
    // 3. Hàm đếm số sản phẩm sách sắp hết hàng (Số lượng < 5)
    public int countSachSapHetHang() {
        ArrayList<Sach> ds = sachDAO.selectAll();
        int count = 0;
        
        for (Sach s : ds) {
            if (s.getSoluong() < 5) { // Lưu ý: Đổi thành s.getSoLuong() nếu thuộc tính của bạn viết hoa chữ L
                count++;
            }
        }
        return count;
    }
    // Tính tổng số lượng tất cả các món VPP tồn trong kho
    public int tongSoLuongTonVPP() {
        ArrayList<VanPhongPham> ds = vppDAO.selectAll();
        int tong = 0;
        for (VanPhongPham vpp : ds) {
            tong += vpp.getSoluong(); 
        }
        return tong;
    }

    // Tính tổng số lượng mặt hàng VPP khác nhau (Dùng cho ô Văn Phòng Phẩm)
    public int tongSoDauVPP() {
        ArrayList<VanPhongPham> ds = vppDAO.selectAll();
        return ds.size();
    }

    // Đếm số mặt hàng VPP sắp hết hàng (Số lượng < 5)
    public int countVPPSapHetHang() {
        ArrayList<VanPhongPham> ds = vppDAO.selectAll();
        int count = 0;
        for (VanPhongPham vpp : ds) {
            if (vpp.getSoluong() < 5) {
                count++;
            }
        }
        return count;
    }
    // Hàm tính tổng tồn kho của hệ thống (Dùng cho ô Tổng Tồn Kho lớn)
    public int tongSoLuongTonToanKho() {
        return tongSoLuongTonSach() + tongSoLuongTonVPP();
    }
    // Hàm tổng số lượng mặt hàng sắp hết hàng của cả hệ thống (Dùng cho ô Sắp Hết Hàng lớn)
    public int countToanKhoSapHetHang() {
        return countSachSapHetHang() + countVPPSapHetHang();
    }
    
}