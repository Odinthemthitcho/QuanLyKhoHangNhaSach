package dao;

import model.Sach;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SachDAO implements Daointerface<Sach> {

    @Override
    public int insert(Sach sach) {
        try {
            // B1: kết nối tới sql
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            //B2: Thực thi lệnh sql
            String sql = "INSERT INTO sach(maSach, TenSach, SoLuong, GiaTien, TheLoai, NamXuatban)" +
                    "VALUES ('" + sach.getMasp() + "' ,'" + sach.getTensp() + "' ,'" + sach.getSoluong() + "' ,'" + sach.getDongia() + "' ,'" + sach.getTheloai() + "', '" + sach.getNamXuatban() + "')";

            //B3: Hiển thị kết quả
            int ketqua = st.executeUpdate(sql);
            System.out.println("Ban da thuc thi"+sql);
            System.out.println("Co " + ketqua + " dong bi thay doi");
            return ketqua;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }



    @Override
    public int update(Sach sach) {
        try {
            // B1: kết nối tới sql
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            //B2: Thực thi lệnh sql
            String sql = "";
            //B3: Hiển thị kết quả
            int ketqua = st.executeUpdate(sql);
            System.out.println("Ban da thuc thi"+sql);
            System.out.println("Co " + ketqua + " dong bi thay doi");
            return ketqua;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Sach sach) {
        try {
            // B1: kết nối tới sql
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            //B2: Thực thi lệnh sql
            String sql = "";
            //B3: Hiển thị kết quả
            int ketqua = st.executeUpdate(sql);
            System.out.println("Ban da thuc thi"+sql);
            System.out.println("Co " + ketqua + " dong bi thay doi");
            return ketqua;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Sach> selectAll() {
        return null;
    }

    @Override
    public Sach selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<Sach> selectByCondition(String condition) {
        return null;
    }
}
