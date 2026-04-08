package ui;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {

    // Tạo constructor để cài đặt các thông số cơ bản cho màn hình
    public MenuFrame() {
        setTitle("Phần Mềm Quản Lý Kho Hàng Nhà Sách"); // Tên cửa sổ
        setSize(800, 600); // Kích thước màn hình (Rộng x Cao)
        setLocationRelativeTo(null); // Hiển thị ở chính giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tắt chương trình khi bấm dấu X
        
        
    }
}