package dao;

import model.Sach;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SachDAO implements Daointerface<Sach> {

    @Override
    public int insert(Sach sach) {
        String sqlSanPham = "INSERT INTO SANPHAM(MASP, TENSP, LOAISP, SOLUONG, DONGIA) VALUES (?, ?, ?, ?, ?)";
        String sqlSach = "INSERT INTO SACH(MASP, TACGIA, THELOAI, NAMXUATBAN, NHAXUATBAN) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psSP = conn.prepareStatement(sqlSanPham);
                 PreparedStatement psSach = conn.prepareStatement(sqlSach)) {

                psSP.setString(1, sach.getMasp());
                psSP.setString(2, sach.getTensp());
                psSP.setString(3, "SACH");
                psSP.setInt(4, sach.getSoluong());
                psSP.setDouble(5, sach.getDongia());
                psSP.executeUpdate();

                psSach.setString(1, sach.getMasp());
                psSach.setString(2, sach.getTacGia());
                psSach.setString(3, sach.getTheloai());
                psSach.setInt(4, sach.getNamXuatban());
                psSach.setString(5, sach.getNhaXuatBan());
                int result = psSach.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi insert Sach: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Sach sach) {
        String sqlSanPham = "UPDATE SANPHAM SET TENSP = ?, SOLUONG = ?, DONGIA = ? WHERE MASP = ?";
        String sqlSach = "UPDATE SACH SET TACGIA = ?, THELOAI = ?, NAMXUATBAN = ?, NHAXUATBAN = ? WHERE MASP = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psSP = conn.prepareStatement(sqlSanPham);
                 PreparedStatement psSach = conn.prepareStatement(sqlSach)) {

                psSP.setString(1, sach.getTensp());
                psSP.setInt(2, sach.getSoluong());
                psSP.setDouble(3, sach.getDongia());
                psSP.setString(4, sach.getMasp());
                psSP.executeUpdate();

                psSach.setString(1, sach.getTacGia());
                psSach.setString(2, sach.getTheloai());
                psSach.setInt(3, sach.getNamXuatban());
                psSach.setString(4, sach.getNhaXuatBan());
                psSach.setString(5, sach.getMasp());
                int result = psSach.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi update Sach: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Sach sach) {
        String sqlSach = "DELETE FROM SACH WHERE MASP = ?";
        String sqlSanPham = "DELETE FROM SANPHAM WHERE MASP = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psSach = conn.prepareStatement(sqlSach);
                 PreparedStatement psSP = conn.prepareStatement(sqlSanPham)) {

                psSach.setString(1, sach.getMasp());
                psSach.executeUpdate();

                psSP.setString(1, sach.getMasp());
                int result = psSP.executeUpdate();

                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            System.out.println("Loi delete Sach: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Sach> selectAll() {
        ArrayList<Sach> ds = new ArrayList<>();
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "s.TACGIA, s.THELOAI, s.NAMXUATBAN, s.NHAXUATBAN " +
                "FROM SANPHAM sp INNER JOIN SACH s ON sp.MASP = s.MASP";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sach sach = new Sach(
                        rs.getString("MASP"),
                        rs.getString("TENSP"),
                        rs.getInt("SOLUONG"),
                        rs.getDouble("DONGIA"),
                        rs.getString("TACGIA"),
                        rs.getString("THELOAI"),
                        rs.getInt("NAMXUATBAN"),
                        rs.getString("NHAXUATBAN")
                );
                ds.add(sach);
            }

        } catch (Exception e) {
            System.out.println("Loi selectAll Sach: " + e.getMessage());
            e.printStackTrace();
        }

        return ds;
    }

    @Override
    public Sach selectById(String id) {
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "s.TACGIA, s.THELOAI, s.NAMXUATBAN, s.NHAXUATBAN " +
                "FROM SANPHAM sp INNER JOIN SACH s ON sp.MASP = s.MASP " +
                "WHERE sp.MASP = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Sach(
                            rs.getString("MASP"),
                            rs.getString("TENSP"),
                            rs.getInt("SOLUONG"),
                            rs.getDouble("DONGIA"),
                            rs.getString("TACGIA"),
                            rs.getString("THELOAI"),
                            rs.getInt("NAMXUATBAN"),
                            rs.getString("NHAXUATBAN")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Loi selectById Sach: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Sach> selectByCondition(String condition) {
        ArrayList<Sach> ds = new ArrayList<>();
        String sql = "SELECT sp.MASP, sp.TENSP, sp.SOLUONG, sp.DONGIA, " +
                "s.TACGIA, s.THELOAI, s.NAMXUATBAN, s.NHAXUATBAN " +
                "FROM SANPHAM sp INNER JOIN SACH s ON sp.MASP = s.MASP " +
                "WHERE sp.TENSP LIKE ? OR s.TACGIA LIKE ? OR s.THELOAI LIKE ? OR s.NHAXUATBAN LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String dk = "%" + condition + "%";
            ps.setString(1, dk);
            ps.setString(2, dk);
            ps.setString(3, dk);
            ps.setString(4, dk);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sach sach = new Sach(
                            rs.getString("MASP"),
                            rs.getString("TENSP"),
                            rs.getInt("SOLUONG"),
                            rs.getDouble("DONGIA"),
                            rs.getString("TACGIA"),
                            rs.getString("THELOAI"),
                            rs.getInt("NAMXUATBAN"),
                            rs.getString("NHAXUATBAN")
                    );
                    ds.add(sach);
                }
            }

        } catch (Exception e) {
            System.out.println("Loi selectByCondition Sach: " + e.getMessage());
            e.printStackTrace();
        }

        return ds;
    }
}