package dao;

import model.VanPhongPham;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VanPhongPhamDAO implements Daointerface<VanPhongPham> {

    @Override
    public int insert(VanPhongPham vanPhongPham) {
        String sqlSanPham = "INSERT INTO SANPHAM(MASP, TENSP, LOAISP, SOLUONG, DONGIA) VALUES (?, ?, ?, ?, ?)";
        String sqlVPP = "INSERT INTO VANPHONGPHAM(MASP, THUONGHIEU, XUATXU, CHATLUONG) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psSP = conn.prepareStatement(sqlSanPham);
                 PreparedStatement psVPP = conn.prepareStatement(sqlVPP)) {

                psSP.setString(1, vanPhongPham.getMasp());
                psSP.setString(2, vanPhongPham.getTensp());
                psSP.setString(3, "VANPHONGPHAM");
                psSP.setInt(4, vanPhongPham.getSoluong());
                psSP.setDouble(5, vanPhongPham.getDongia());
                psSP.executeUpdate();

                psVPP.setString(1, vanPhongPham.getMasp());
                psVPP.setString(2, vanPhongPham.getThuongHieu());
                psVPP.setString(3, vanPhongPham.getXuatXu());
                psVPP.setString(4, vanPhongPham.getChatLuong());
                int result = psVPP.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi insert VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(VanPhongPham vanPhongPham) {
        String sqlSanPham = "UPDATE SANPHAM SET TENSP = ?, SOLUONG = ?, DONGIA = ? WHERE MASP = ?";
        String sqlVPP = "UPDATE VANPHONGPHAM SET THUONGHIEU = ?, XUATXU = ?, CHATLUONG = ? WHERE MASP = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psSP = conn.prepareStatement(sqlSanPham);
                 PreparedStatement psVPP = conn.prepareStatement(sqlVPP)) {

                psSP.setString(1, vanPhongPham.getTensp());
                psSP.setInt(2, vanPhongPham.getSoluong());
                psSP.setDouble(3, vanPhongPham.getDongia());
                psSP.setString(4, vanPhongPham.getMasp());
                psSP.executeUpdate();

                psVPP.setString(1, vanPhongPham.getThuongHieu());
                psVPP.setString(2, vanPhongPham.getXuatXu());
                psVPP.setString(3, vanPhongPham.getChatLuong());
                psVPP.setString(4, vanPhongPham.getMasp());
                int result = psVPP.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi update VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(VanPhongPham vanPhongPham) {
        String sqlVPP = "DELETE FROM VANPHONGPHAM WHERE MASP = ?";
        String sqlSanPham = "DELETE FROM SANPHAM WHERE MASP = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psVPP = conn.prepareStatement(sqlVPP);
                 PreparedStatement psSP = conn.prepareStatement(sqlSanPham)) {

                psVPP.setString(1, vanPhongPham.getMasp());
                psVPP.executeUpdate();

                psSP.setString(1, vanPhongPham.getMasp());
                int result = psSP.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi delete VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<VanPhongPham> selectAll() {
        ArrayList<VanPhongPham> ds = new ArrayList<>();
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "vpp.THUONGHIEU, vpp.XUATXU, vpp.CHATLUONG " +
                "FROM SANPHAM sp INNER JOIN VANPHONGPHAM vpp ON sp.MASP = vpp.MASP";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VanPhongPham vpp = new VanPhongPham(
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getInt("SOLUONG"),
                        rs.getDouble("DONGIA"),
                        rs.getString("THUONGHIEU"),
                        rs.getString("XUATXU"),
                        rs.getString("CHATLUONG")
                );
                ds.add(vpp);
            }

        } catch (Exception e) {
            System.out.println("Loi selectAll VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }

        return ds;
    }

    @Override
    public VanPhongPham selectById(String id) {
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "vpp.THUONGHIEU, vpp.XUATXU, vpp.CHATLUONG " +
                "FROM SANPHAM sp INNER JOIN VANPHONGPHAM vpp ON sp.MASP = vpp.MASP " +
                "WHERE sp.MASP = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new VanPhongPham(
                            rs.getString("MASP"),
                            rs.getString("TENSP"),
                            rs.getInt("SOLUONG"),
                            rs.getDouble("DONGIA"),
                            rs.getString("THUONGHIEU"),
                            rs.getString("XUATXU"),
                            rs.getString("CHATLUONG")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Loi selectById VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<VanPhongPham> selectByCondition(String condition) {
        ArrayList<VanPhongPham> ds = new ArrayList<>();
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "vpp.THUONGHIEU, vpp.XUATXU, vpp.CHATLUONG " +
                "FROM SANPHAM sp INNER JOIN VANPHONGPHAM vpp ON sp.MASP = vpp.MASP " +
                "WHERE sp.TENSP LIKE ? OR vpp.THUONGHIEU LIKE ? OR vpp.XUATXU LIKE ? OR vpp.CHATLUONG LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String dk = "%" + condition + "%";
            ps.setString(1, dk);
            ps.setString(2, dk);
            ps.setString(3, dk);
            ps.setString(4, dk);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VanPhongPham vpp = new VanPhongPham(
                            rs.getString("MASP"),
                            rs.getString("TENSP"),
                            rs.getInt("SOLUONG"),
                            rs.getDouble("DONGIA"),
                            rs.getString("THUONGHIEU"),
                            rs.getString("XUATXU"),
                            rs.getString("CHATLUONG")
                    );
                    ds.add(vpp);
                }
            }

        } catch (Exception e) {
            System.out.println("Loi selectByCondition VanPhongPham: " + e.getMessage());
            e.printStackTrace();
        }

        return ds;
    }
}