package dao;

import model.VanPhongPham;

import java.util.ArrayList;

public class VanPhongPhamDAO implements  Daointerface<VanPhongPham>{
    @Override
    public int insert(VanPhongPham vanPhongPham) {
        return 0;
    }

    @Override
    public int update(VanPhongPham vanPhongPham) {
        return 0;
    }

    @Override
    public int delete(VanPhongPham vanPhongPham) {
        return 0;
    }

    @Override
    public ArrayList<VanPhongPham> selectAll() {
        return null;
    }

    @Override
    public VanPhongPham selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<VanPhongPham> selectByCondition(String condition) {
        return null;
    }
}
