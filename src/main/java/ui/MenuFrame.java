package ui;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Dang Nhap SachDAO");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(2,1,10,10));

        JButton btnQLKho = new JButton("1. Quan ly kho");
        JButton btnThongKe = new JButton("2. Thong ke kho");

        add(btnQLKho);
        add(btnThongKe);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

class LoginFrame extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginFrame() {
        setTitle("Dang Nhap SachDAO");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2,5,5));

        add(new JLabel("Ten dang nhap:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Mat khau:"));
        txtPass = new JPasswordField();
        add(txtPass);

        JButton btnLogin = new JButton("Dang nhap");
        add(new JLabel());
        add(btnLogin);

        btnLogin.addActionListener(e -> dangNhap());

        setVisible(true);
    }

    void dangNhap() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if(user.equals("admin") && pass.equals("123")) {
            new MenuFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,"Sai tai khoan hoac mat khau");
        }
    }
}