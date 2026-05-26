package service;

import java.util.ArrayList;
import dao.SachDAO;
import dao.VanPhongPhamDAO;
import model.Sach;
import model.VanPhongPham;

public class QuanLyKho {
    private SachDAO sachDAO = new SachDAO();
    private VanPhongPhamDAO vppDAO = new VanPhongPhamDAO();

    // ==========================================
    // CHỨC NĂNG DÀNH CHO SÁCH
    // ==========================================
    
    public ArrayList<Sach> getListSach() {
        return sachDAO.selectAll();
    }

    public void themSach(Sach sach){
        sachDAO.insert(sach);
    }

    public void suaSach(Sach sach){
        sachDAO.update(sach);
    }

    public void xoaSach(Sach sach){
        sachDAO.delete(sach);
    }

    public void nhapHangSach(Sach sachNhap) {
        Sach sachHienTai = sachDAO.selectById(sachNhap.getMasp());
        if (sachHienTai != null) {
            int soLuongMoi = sachHienTai.getSoluong() + sachNhap.getSoluong();
            sachHienTai.setSoluong(soLuongMoi);
            sachDAO.update(sachHienTai);
        } else {
            sachDAO.insert(sachNhap);
        }
    }

    public ArrayList<Sach> timSach(String dieuKien) {
        if (dieuKien == null || dieuKien.trim().isEmpty()) {
            return sachDAO.selectAll();
        }
        return sachDAO.selectByCondition(dieuKien);
    }

    // ==========================================
    // CHỨC NĂNG DÀNH CHO VĂN PHÒNG PHẨM (VPP)
    // ==========================================
    
    public ArrayList<VanPhongPham> getListVPP() {
        return vppDAO.selectAll();
    }

    public void themVPP(VanPhongPham vpp){
        vppDAO.insert(vpp);
    }

    public void suaVPP(VanPhongPham vpp){
        vppDAO.update(vpp);
    }

    public void xoaVPP(VanPhongPham vpp){
        vppDAO.delete(vpp);
    }

    public void nhapHangVPP(VanPhongPham vppNhap) {
        VanPhongPham vppHienTai = vppDAO.selectById(vppNhap.getMasp());
        if (vppHienTai != null) {
            int soLuongMoi = vppHienTai.getSoluong() + vppNhap.getSoluong();
            vppHienTai.setSoluong(soLuongMoi);
            vppDAO.update(vppHienTai);
        } else {
            vppDAO.insert(vppNhap);
        }
    }

    public ArrayList<VanPhongPham> timHangVPP(String dieuKien) {
        if (dieuKien == null || dieuKien.trim().isEmpty()) {
            return vppDAO.selectAll();
        }
        return vppDAO.selectByCondition(dieuKien);
    }
}