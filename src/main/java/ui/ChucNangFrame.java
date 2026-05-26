/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList; 
import model.Sach;      

/**
 *
 * @author admin
 */
public class ChucNangFrame extends javax.swing.JFrame {

    /**
     * Creates new form ChucNangFrame
     */
    private service.ThongKeKho thongKeKhoService = new service.ThongKeKho();
    private service.QuanLyKho quanLyKhoService = new service.QuanLyKho();
    public ChucNangFrame() {
        initComponents();
        hienThiThongKeKho(); // Đổ dữ liệu chữ
        hienThiTableSach();  // Đổ bảng sách
        hienThiTableVPP();
    }
    private void hienThiThongKeKho() {
        try {
            int tongDauSach = thongKeKhoService.tongSoDauSach();
            int tongDauVPP = thongKeKhoService.tongSoDauVPP();
            int tongTonKhoHeThong = thongKeKhoService.tongSoLuongTonToanKho();   
            int soMaSapHet = thongKeKhoService.countToanKhoSapHetHang();       

            // --- ĐỔ CHUẨN DỮ LIỆU LÊN ĐÚNG LABEL TRÊN FRAME CỦA BẠN ---
            lbTongSach.setText("Tổng Sách: " + tongDauSach + " đầu sách");
            lbVanPhongPham.setText("Văn Phòng Phẩm: " + tongDauVPP + " mặt hàng");
            lbTongTonKho.setText("Tổng Tồn Kho: " + tongTonKhoHeThong + " sản phẩm");
            lbSapHetHang.setText("Sắp Hết Hàng: " + soMaSapHet + " mã");
            
        } catch (Exception e) {
            System.out.println("Lỗi khi load dữ liệu thống kê kho tổng hợp: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void hienThiTableSach() {
        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        try {
            // Duyệt danh sách từ service và ép kiểu đúng với model.Sach bạn đã gửi
            for (model.Sach x : thongKeKhoService.getListSach()) { 
                model.addRow(new Object[]{
                    x.getMasp(),     // Mã SP (chữ p thường theo đúng model)
                    x.getTensp(),    // Tên SP
                    x.getSoluong()   // Số lượng tồn kho
                });
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi load table Sách: " + e.getMessage());
        }
    }
    private void hienThiTableVPP() {
        DefaultTableModel model = (DefaultTableModel) tbVPP.getModel();
        model.setRowCount(0); 

        try {
            // Giả sử class VanPhongPham cũng kế thừa từ SanPham
            for (model.VanPhongPham x : thongKeKhoService.getListVPP()) { 
                model.addRow(new Object[]{
                    x.getMasp(),     // Sử dụng đúng getter kế thừa từ SanPham
                    x.getTensp(),
                    x.getSoluong(),
                    x.getDongia()
                });
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi load table VPP: " + e.getMessage());
        }
    }
    private void hienThiTableQuanLySach() {
        DefaultTableModel model = (DefaultTableModel) tbQuanLySach.getModel();
        model.setRowCount(0);
        try {
            for (model.Sach x : quanLyKhoService.getListSach()) {
                model.addRow(new Object[]{
                    x.getMasp(), x.getTensp(), x.getSoluong(), x.getDongia(),
                    x.getTacGia(), x.getTheloai(), x.getNamXuatban(), x.getNhaXuatBan()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hienThiTableQuanLyVPP() {
        DefaultTableModel modelTable = (DefaultTableModel) tbQuanLyVPP.getModel();
        modelTable.setRowCount(0); 
        try {
            for (model.VanPhongPham x : quanLyKhoService.getListVPP()) {
                modelTable.addRow(new Object[]{
                    x.getMasp(), 
                    x.getTensp(), 
                    x.getSoluong(), 
                    x.getDongia(),
                    x.getThuongHieu(), 
                    x.getXuatXu(), 
                    x.getChatLuong()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearFormSach() {
        tfMaSach.setText("");
        tfTenSach.setText("");
        tfSoLuong.setText("");
        tfDonGia.setText("");
        tfTacGia.setText("");
        tfTheLoai.setText("");
        tfNamXB.setText("");
        tfNhaXB.setText("");
    }

    private void clearFormVPP() {
        tfMaSP.setText("");
        tfTenSanPham.setText("");
        tfSoLuong1.setText("");
        tfDonGia1.setText("");
        tfThuongHieu.setText("");
        tfXuatXu.setText("");
        tfChatLuong.setText("");
    }
    private void hienThiTableThongKeSach() {
        // 1. Lấy Model của bảng tbThongKeSach
        javax.swing.table.DefaultTableModel modelTable = (javax.swing.table.DefaultTableModel) tbThongKeSach.getModel();

        // 2. Xóa sạch dòng cũ trên bảng thống kê trước khi nạp mới
        modelTable.setRowCount(0); 

        // 3. Lấy trực tiếp toàn bộ danh sách Sách từ quanLyKhoService (truyền chuỗi rỗng "" để lấy hết)
        java.util.ArrayList<model.Sach> danhSach = quanLyKhoService.timSach("");

        // 4. Đổ dữ liệu lên bảng Thống Kê
        if (danhSach != null) {
            for (model.Sach x : danhSach) {
                modelTable.addRow(new Object[]{
                    x.getMasp(), 
                    x.getTensp(), 
                    x.getSoluong(), 
                    x.getDongia(),
                    x.getTacGia(), 
                    x.getTheloai(), 
                    x.getNamXuatban(), 
                    x.getNhaXuatBan()
                });
            }
        }
    }
    private void hienThiTableThongKeVPP() {
        // 1. Lấy Model của bảng Thống kê VPP (bạn nhớ kiểm tra đúng tên biến JTable của bạn nhé)
        javax.swing.table.DefaultTableModel modelTable = (javax.swing.table.DefaultTableModel) tbThongKeVPP.getModel();

        // 2. Xóa sạch dòng cũ trước khi nạp dữ liệu mới
        modelTable.setRowCount(0); 

        // 3. Gọi trực tiếp hàm timHangVPP("") từ quanLyKhoService (truyền chuỗi rỗng để lấy toàn bộ)
        java.util.ArrayList<model.VanPhongPham> danhSach = quanLyKhoService.timHangVPP("");

        // 4. Đổ dữ liệu vào bảng Thống kê VPP
        if (danhSach != null) {
            for (model.VanPhongPham x : danhSach) {
                modelTable.addRow(new Object[]{
                    x.getMasp(), 
                    x.getTensp(), 
                    x.getSoluong(), 
                    x.getDongia(),
                    x.getThuongHieu(), 
                    x.getXuatXu(), 
                    x.getChatLuong()
                });
            }
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btTrangChu = new javax.swing.JButton();
        btThongKeKho = new javax.swing.JButton();
        btQuanLyKho = new javax.swing.JButton();
        btThongKeSach = new javax.swing.JButton();
        btThongKeVPP = new javax.swing.JButton();
        mainPN = new javax.swing.JPanel();
        pnTrangChu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        pnThongKeKho = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbVPP = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbTongSach = new javax.swing.JLabel();
        lbVanPhongPham = new javax.swing.JLabel();
        lbTongTonKho = new javax.swing.JLabel();
        lbSapHetHang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        pnQuanLyKho = new javax.swing.JPanel();
        mainGD = new javax.swing.JPanel();
        btQuanLySach = new javax.swing.JButton();
        btQuanLyVPP = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pnQuanLySach = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfDonGia = new javax.swing.JTextField();
        tfNhaXB = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        tfTenSach = new javax.swing.JTextField();
        tfMaSach = new javax.swing.JTextField();
        tfTacGia = new javax.swing.JTextField();
        tfTheLoai = new javax.swing.JTextField();
        tfNamXB = new javax.swing.JTextField();
        tfTimKiem = new javax.swing.JTextField();
        btTimKiem = new javax.swing.JButton();
        btLamMoi = new javax.swing.JButton();
        btXoaBo = new javax.swing.JButton();
        btCapNhat = new javax.swing.JButton();
        btNhapKho = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbQuanLySach = new javax.swing.JTable();
        pnQuanLyVPP = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tfChatLuong = new javax.swing.JTextField();
        tfSoLuong1 = new javax.swing.JTextField();
        tfTenSanPham = new javax.swing.JTextField();
        tfMaSP = new javax.swing.JTextField();
        tfDonGia1 = new javax.swing.JTextField();
        tfThuongHieu = new javax.swing.JTextField();
        tfXuatXu = new javax.swing.JTextField();
        tfTimKiem1 = new javax.swing.JTextField();
        btTimKiem1 = new javax.swing.JButton();
        btLamMoi1 = new javax.swing.JButton();
        btXoaBo1 = new javax.swing.JButton();
        btCapNhat1 = new javax.swing.JButton();
        btNhapKho1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbQuanLyVPP = new javax.swing.JTable();
        pnThongKeSach = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbThongKeSach = new javax.swing.JTable();
        pnThongKeVPP = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbThongKeVPP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Screenshot 2026-05-25 095740.png"))); // NOI18N
        btTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrangChuActionPerformed(evt);
            }
        });
        jPanel1.add(btTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        btThongKeKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Screenshot 2026-05-25 095744.png"))); // NOI18N
        btThongKeKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThongKeKhoActionPerformed(evt);
            }
        });
        jPanel1.add(btThongKeKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 60));

        btQuanLyKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Screenshot 2026-05-25 095748.png"))); // NOI18N
        btQuanLyKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuanLyKhoActionPerformed(evt);
            }
        });
        jPanel1.add(btQuanLyKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 60));

        btThongKeSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Screenshot 2026-05-25 095752.png"))); // NOI18N
        btThongKeSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThongKeSachActionPerformed(evt);
            }
        });
        jPanel1.add(btThongKeSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 80, 60));

        btThongKeVPP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/Screenshot 2026-05-25 095755.png"))); // NOI18N
        btThongKeVPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThongKeVPPActionPerformed(evt);
            }
        });
        jPanel1.add(btThongKeVPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 80, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 480));

        mainPN.setLayout(new java.awt.CardLayout());

        pnTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Trang Chủ");
        pnTrangChu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Nổi Bật");
        pnTrangChu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Có gì Hot?");
        pnTrangChu.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));
        pnTrangChu.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 120, 90));
        pnTrangChu.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 120, 90));
        pnTrangChu.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 120, 90));
        pnTrangChu.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 520, 80));

        mainPN.add(pnTrangChu, "card2");

        pnThongKeKho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Thống Kê Kho");
        pnThongKeKho.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        tbVPP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã SP", "Tên", "Tồn"
            }
        ));
        jScrollPane2.setViewportView(tbVPP);

        pnThongKeKho.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 320, 260));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Tổng Quan Hệ Thống");
        pnThongKeKho.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lbTongSach.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTongSach.setText("Tổng Sách:");
        pnThongKeKho.add(lbTongSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 250, -1));

        lbVanPhongPham.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbVanPhongPham.setText("Văn Phòng Phẩm:");
        pnThongKeKho.add(lbVanPhongPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 240, -1));

        lbTongTonKho.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTongTonKho.setText("Tổng Tồn Kho:");
        pnThongKeKho.add(lbTongTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 240, -1));

        lbSapHetHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSapHetHang.setText("Sắp Hết Hàng:");
        pnThongKeKho.add(lbSapHetHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 250, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Tình Trạng Sách Gần Đây");
        pnThongKeKho.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Tình Trạng Văn Phòng Phẩm Gần Đây");
        pnThongKeKho.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã SP", "Tên", "Tồn"
            }
        ));
        jScrollPane1.setViewportView(tbSach);

        pnThongKeKho.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 320, 260));

        mainPN.add(pnThongKeKho, "card3");

        pnQuanLyKho.setLayout(new java.awt.CardLayout());

        mainGD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btQuanLySach.setText("Quản Lý Sách");
        btQuanLySach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuanLySachActionPerformed(evt);
            }
        });
        mainGD.add(btQuanLySach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, -1));

        btQuanLyVPP.setText("Quản Lý Văn Phòng Phẩm");
        btQuanLyVPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuanLyVPPActionPerformed(evt);
            }
        });
        mainGD.add(btQuanLyVPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel14.setText("Quản Lý Kho");
        mainGD.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        pnQuanLySach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Mã Sách");
        pnQuanLySach.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel16.setText("Tên Sách");
        pnQuanLySach.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel17.setText("Số Lượng");
        pnQuanLySach.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel18.setText("Thể Loại");
        pnQuanLySach.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel19.setText("Đơn Giá");
        pnQuanLySach.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel20.setText("Tác giả");
        pnQuanLySach.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel21.setText("Năm Xuất Bản");
        pnQuanLySach.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jLabel22.setText("Nhà Xuất Bản");
        pnQuanLySach.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));
        pnQuanLySach.add(tfDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 120, -1));
        pnQuanLySach.add(tfNhaXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 110, -1));
        pnQuanLySach.add(tfSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 120, -1));

        tfTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTenSachActionPerformed(evt);
            }
        });
        pnQuanLySach.add(tfTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 120, -1));
        pnQuanLySach.add(tfMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 120, -1));
        pnQuanLySach.add(tfTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, -1));
        pnQuanLySach.add(tfTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 120, -1));
        pnQuanLySach.add(tfNamXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 120, -1));

        tfTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiemActionPerformed(evt);
            }
        });
        pnQuanLySach.add(tfTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, -1));

        btTimKiem.setText("Tìm Kiếm");
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });
        pnQuanLySach.add(btTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        btLamMoi.setText("Làm Mới");
        btLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoiActionPerformed(evt);
            }
        });
        pnQuanLySach.add(btLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        btXoaBo.setText("Xoá Bỏ");
        btXoaBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaBoActionPerformed(evt);
            }
        });
        pnQuanLySach.add(btXoaBo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        btCapNhat.setText("Cập Nhật");
        btCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCapNhatActionPerformed(evt);
            }
        });
        pnQuanLySach.add(btCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        btNhapKho.setText("Nhập Kho");
        btNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhapKhoActionPerformed(evt);
            }
        });
        pnQuanLySach.add(btNhapKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, -1));

        tbQuanLySach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Số Lượng", "Đơn Giá", "Tác Giả", "Thể Loại", "Năm XB", "Nhà XB"
            }
        ));
        jScrollPane5.setViewportView(tbQuanLySach);

        pnQuanLySach.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 640, 190));

        mainGD.add(pnQuanLySach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 640, 360));

        pnQuanLyVPP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setText("Mã Sản Phẩm");
        pnQuanLyVPP.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel24.setText("Tên Sản Phẩm");
        pnQuanLyVPP.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel25.setText("Số Lượng");
        pnQuanLyVPP.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        jLabel26.setText("Thương Hiệu");
        pnQuanLyVPP.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel28.setText("Đơn Giá");
        pnQuanLyVPP.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel29.setText("Xuất Xứ");
        pnQuanLyVPP.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jLabel30.setText("Chất Lượng");
        pnQuanLyVPP.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));
        pnQuanLyVPP.add(tfChatLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 110, -1));
        pnQuanLyVPP.add(tfSoLuong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 120, -1));
        pnQuanLyVPP.add(tfTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, -1));
        pnQuanLyVPP.add(tfMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 120, -1));
        pnQuanLyVPP.add(tfDonGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, -1));
        pnQuanLyVPP.add(tfThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 120, -1));
        pnQuanLyVPP.add(tfXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 120, -1));

        tfTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiem1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(tfTimKiem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, -1));

        btTimKiem1.setText("Tìm Kiếm");
        btTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiem1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(btTimKiem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        btLamMoi1.setText("Làm Mới");
        btLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoi1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(btLamMoi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        btXoaBo1.setText("Xoá Bỏ");
        btXoaBo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaBo1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(btXoaBo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        btCapNhat1.setText("Cập Nhật");
        btCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCapNhat1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(btCapNhat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        btNhapKho1.setText("Nhập Kho");
        btNhapKho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhapKho1ActionPerformed(evt);
            }
        });
        pnQuanLyVPP.add(btNhapKho1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, -1));

        tbQuanLyVPP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thương Hiệu", "Xuất Xứ", "Chất Lượng"
            }
        ));
        jScrollPane6.setViewportView(tbQuanLyVPP);

        pnQuanLyVPP.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 640, 190));

        mainGD.add(pnQuanLyVPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 640, 360));

        pnQuanLyKho.add(mainGD, "card2");

        mainPN.add(pnQuanLyKho, "card6");

        pnThongKeSach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel12.setText("Thống Kê Sách");
        pnThongKeSach.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        tbThongKeSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Số Lượng", "Đơn Giá", "Tác Giả", "Thể Loại", "Năm XB", "Nhà XB"
            }
        ));
        jScrollPane3.setViewportView(tbThongKeSach);

        pnThongKeSach.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 640, 400));

        mainPN.add(pnThongKeSach, "card4");

        pnThongKeVPP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel13.setText("Thống Kê Văn Phòng Phẩm");
        pnThongKeVPP.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        tbThongKeVPP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thương Hiệu", "Xuất Xứ", "Chất Lượng"
            }
        ));
        jScrollPane4.setViewportView(tbThongKeVPP);

        pnThongKeVPP.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 640, 400));

        mainPN.add(pnThongKeVPP, "card5");

        getContentPane().add(mainPN, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 640, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrangChuActionPerformed
        // TODO add your handling code here:
        mainPN.removeAll();
        mainPN.add(pnTrangChu);
        mainPN.repaint();
        mainPN.revalidate();
    }//GEN-LAST:event_btTrangChuActionPerformed

    private void tfTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiemActionPerformed

    private void tfTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiem1ActionPerformed

    private void btThongKeKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThongKeKhoActionPerformed
        // TODO add your handling code here:
        mainPN.removeAll();
        mainPN.add(pnThongKeKho);
        mainPN.repaint();
        mainPN.revalidate();
        
        hienThiThongKeKho(); // Hiện số liệu tổng quan lên Label
        hienThiTableSach();  // Hiện bảng sách nhỏ tại đây
        hienThiTableVPP();   // Hiện bảng VPP nhỏ tại đây
    }//GEN-LAST:event_btThongKeKhoActionPerformed

    private void btQuanLyKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuanLyKhoActionPerformed
        // TODO add your handling code here:
        mainPN.removeAll();
        mainPN.add(pnQuanLyKho);
        mainPN.repaint();
        mainPN.revalidate();
        
        pnQuanLySach.setVisible(true);
        pnQuanLyVPP.setVisible(false);
        hienThiTableQuanLySach();
        hienThiTableQuanLyVPP();
    }//GEN-LAST:event_btQuanLyKhoActionPerformed

    private void btThongKeSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThongKeSachActionPerformed
        // TODO add your handling code here:
        mainPN.removeAll();
        mainPN.add(pnThongKeSach);
        mainPN.repaint();
        mainPN.revalidate();
        
        hienThiTableThongKeSach();
    }//GEN-LAST:event_btThongKeSachActionPerformed

    private void btThongKeVPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThongKeVPPActionPerformed
        // TODO add your handling code here:
        mainPN.removeAll();
        mainPN.add(pnThongKeVPP);
        mainPN.repaint();
        mainPN.revalidate();
        
        hienThiTableThongKeVPP();
    }//GEN-LAST:event_btThongKeVPPActionPerformed

    private void btQuanLySachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuanLySachActionPerformed
        // TODO add your handling code here:
        pnQuanLySach.setVisible(true);
        pnQuanLyVPP.setVisible(false);
        hienThiTableQuanLySach(); // Load lại bảng sách

        mainGD.repaint();
        mainGD.revalidate();
    }//GEN-LAST:event_btQuanLySachActionPerformed

    private void btQuanLyVPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuanLyVPPActionPerformed
        // TODO add your handling code here:
        pnQuanLySach.setVisible(false);
        pnQuanLyVPP.setVisible(true);
        hienThiTableQuanLyVPP(); // Load lại bảng văn phòng phẩm

        mainGD.repaint();
        mainGD.revalidate();
    }//GEN-LAST:event_btQuanLyVPPActionPerformed

    private void btNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhapKhoActionPerformed
        // TODO add your handling code here:
        try {
            // 1. Lấy dữ liệu từ các ô TextField và ép kiểu
            String maSach = tfMaSach.getText().trim();
            String tenSach = tfTenSach.getText().trim();
            int soLuong = Integer.parseInt(tfSoLuong.getText().trim());
            double donGia = Double.parseDouble(tfDonGia.getText().trim());
            String tacGia = tfTacGia.getText().trim();
            String theLoai = tfTheLoai.getText().trim();
            int namXB = Integer.parseInt(tfNamXB.getText().trim());
            String nhaXB = tfNhaXB.getText().trim();

            // Kiểm tra không được để trống thông tin cơ bản
            if (maSach.isEmpty() || tenSach.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã sách và tên sách không được để trống!");
                return;
            }

            // 2. Tạo đối tượng model Sach
            model.Sach s = new model.Sach(maSach, tenSach, soLuong, donGia, tacGia, theLoai, namXB, nhaXB);

            // 3. Gọi service để xử lý nhập kho
            quanLyKhoService.nhapHangSach(s);

            // 4. Cập nhật lại bảng hiển thị và xóa form
            hienThiTableQuanLySach();
            clearFormSach();

            javax.swing.JOptionPane.showMessageDialog(this, "Nhập kho sách thành công!");
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi: Số lượng, Đơn giá và Năm xuất bản phải là số!");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi nhập kho sách: " + e.getMessage());
        }
    }//GEN-LAST:event_btNhapKhoActionPerformed

    private void btCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCapNhatActionPerformed
        // TODO add your handling code here:
        try {
            String maSach = tfMaSach.getText().trim();
            if (maSach.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Sách hoặc chọn dòng cần sửa!");
                return;
            }

            model.Sach s = new model.Sach(
                maSach,
                tfTenSach.getText().trim(),
                Integer.parseInt(tfSoLuong.getText().trim()),
                Double.parseDouble(tfDonGia.getText().trim()),
                tfTacGia.getText().trim(),
                tfTheLoai.getText().trim(),
                Integer.parseInt(tfNamXB.getText().trim()),
                tfNhaXB.getText().trim()
            );

            quanLyKhoService.suaSach(s);
            hienThiTableQuanLySach();
            javax.swing.JOptionPane.showMessageDialog(this, "Cập nhật thông tin sách thành công!");
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi: Định dạng số lượng hoặc đơn giá chưa đúng!");
        }
    }//GEN-LAST:event_btCapNhatActionPerformed

    private void tfTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTenSachActionPerformed

    private void btXoaBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaBoActionPerformed
        // TODO add your handling code here:
        String maSach = tfMaSach.getText().trim();
        if (maSach.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn hoặc nhập mã sách cần xóa!");
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa sách có mã: " + maSach + "?", "Xác nhận xóa", 
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            model.Sach s = new model.Sach();
            s.setMasp(maSach);

            quanLyKhoService.xoaSach(s);
            hienThiTableQuanLySach();
            clearFormSach();
            javax.swing.JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
        }
    }//GEN-LAST:event_btXoaBoActionPerformed

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        // TODO add your handling code here:
        String tuKhoa = tfTimKiem.getText().trim();
        DefaultTableModel modelTable = (DefaultTableModel) tbQuanLySach.getModel();
        modelTable.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        // Gọi hàm timHang đã sửa ở lớp service
        ArrayList<model.Sach> ketQua = quanLyKhoService.timSach(tuKhoa);

        if (ketQua != null) {
            for (model.Sach x : ketQua) {
                modelTable.addRow(new Object[]{
                    x.getMasp(), 
                    x.getTensp(), 
                    x.getSoluong(), 
                    x.getDongia(),
                    x.getTacGia(), // Gọi đúng getter viết hoa chữ G theo file Sach.java của bạn
                    x.getTheloai(), 
                    x.getNamXuatban(), 
                    x.getNhaXuatBan()
                });
            }
        }
    }//GEN-LAST:event_btTimKiemActionPerformed

    private void btLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoiActionPerformed
        // TODO add your handling code here:
        clearFormSach(); // Gọi hàm xóa trắng các ô nhập liệu
        tfTimKiem.setText(""); // Xóa ô tìm kiếm
        hienThiTableQuanLySach(); // Tải lại toàn bộ danh sách gốc lên table
    }//GEN-LAST:event_btLamMoiActionPerformed

    private void btNhapKho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhapKho1ActionPerformed
        // TODO add your handling code here:
        try {
            String maSP = tfMaSP.getText().trim();
            String tenSP = tfTenSanPham.getText().trim();
            int soLuong = Integer.parseInt(tfSoLuong1.getText().trim());
            double donGia = Double.parseDouble(tfDonGia1.getText().trim());
            String thuongHieu = tfThuongHieu.getText().trim();
            String xuatXu = tfXuatXu.getText().trim();
            String chatLuong = tfChatLuong.getText().trim();

            if (maSP.isEmpty() || tenSP.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã sản phẩm và tên sản phẩm không được để trống!");
                return;
            }

            // Khởi tạo đối tượng bằng constructor rỗng và set từng thuộc tính để tránh lệch tham số
            model.VanPhongPham vpp = new model.VanPhongPham();
            vpp.setMasp(maSP);
            vpp.setTensp(tenSP);
            vpp.setSoluong(soLuong);
            vpp.setDongia(donGia);
            vpp.setThuongHieu(thuongHieu);
            vpp.setXuatXu(xuatXu);
            vpp.setChatLuong(chatLuong);

            // Gọi dịch vụ nhập kho
            quanLyKhoService.nhapHangVPP(vpp);

            hienThiTableQuanLyVPP(); // Hàm load lại bảng VPP của bạn
            clearFormVPP();          // Hàm xóa trắng form VPP
            javax.swing.JOptionPane.showMessageDialog(this, "Nhập kho văn phòng phẩm thành công!");
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho Số lượng và Đơn giá!");
        }
    }//GEN-LAST:event_btNhapKho1ActionPerformed

    private void btCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCapNhat1ActionPerformed
        // TODO add your handling code here:
        try {
            String maSP = tfMaSP.getText().trim();
            if (maSP.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng sản phẩm trên bảng để sửa!");
                return;
            }

            model.VanPhongPham vpp = new model.VanPhongPham();
            vpp.setMasp(maSP);
            vpp.setTensp(tfTenSanPham.getText().trim());
            vpp.setSoluong(Integer.parseInt(tfSoLuong1.getText().trim()));
            vpp.setDongia(Double.parseDouble(tfDonGia1.getText().trim()));
            vpp.setThuongHieu(tfThuongHieu.getText().trim());
            vpp.setXuatXu(tfXuatXu.getText().trim());
            vpp.setChatLuong(tfChatLuong.getText().trim());

            quanLyKhoService.suaVPP(vpp);
            hienThiTableQuanLyVPP();
            clearFormVPP();
            javax.swing.JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm VPP thành công!");
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi định dạng dữ liệu số lượng hoặc đơn giá!");
        }
    }//GEN-LAST:event_btCapNhat1ActionPerformed

    private void btXoaBo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaBo1ActionPerformed
        // TODO add your handling code here:
        String maSP = tfMaSP.getText().trim();
        if (maSP.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sản phẩm VPP cần xóa!");
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Bạn chắc chắn muốn xóa sản phẩm VPP mã: " + maSP + " chứ?", "Xác nhận xóa", 
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            model.VanPhongPham vpp = new model.VanPhongPham();
            vpp.setMasp(maSP);

            quanLyKhoService.xoaVPP(vpp);
            hienThiTableQuanLyVPP();
            clearFormVPP();
            javax.swing.JOptionPane.showMessageDialog(this, "Xóa mặt hàng VPP thành công!");
        }
    }//GEN-LAST:event_btXoaBo1ActionPerformed

    private void btLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoi1ActionPerformed
        // TODO add your handling code here:
        clearFormVPP();       // Hàm xóa text ở các ô nhập liệu VPP
        tfTimKiem1.setText(""); // Xóa rỗng ô tìm kiếm VPP
        hienThiTableQuanLyVPP(); // Tải lại toàn bộ danh sách gốc lên bảng
    }//GEN-LAST:event_btLamMoi1ActionPerformed

    private void btTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiem1ActionPerformed
        // TODO add your handling code here:
        String tuKhoa = tfTimKiem1.getText().trim();
        javax.swing.table.DefaultTableModel modelTable = (javax.swing.table.DefaultTableModel) tbQuanLyVPP.getModel();
        modelTable.setRowCount(0); // Xóa dữ liệu cũ trên bảng đi

        // Sử dụng đường dẫn tường minh java.util.ArrayList chống lỗi đỏ 100%
        java.util.ArrayList<model.VanPhongPham> ketQua = quanLyKhoService.timHangVPP(tuKhoa);

        if (ketQua != null) {
            for (model.VanPhongPham x : ketQua) {
                modelTable.addRow(new Object[]{
                    x.getMasp(), 
                    x.getTensp(), 
                    x.getSoluong(), 
                    x.getDongia(),
                    x.getThuongHieu(), 
                    x.getXuatXu(), 
                    x.getChatLuong()
                });
            }
        }
    }//GEN-LAST:event_btTimKiem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChucNangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChucNangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChucNangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChucNangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChucNangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCapNhat;
    private javax.swing.JButton btCapNhat1;
    private javax.swing.JButton btLamMoi;
    private javax.swing.JButton btLamMoi1;
    private javax.swing.JButton btNhapKho;
    private javax.swing.JButton btNhapKho1;
    private javax.swing.JButton btQuanLyKho;
    private javax.swing.JButton btQuanLySach;
    private javax.swing.JButton btQuanLyVPP;
    private javax.swing.JButton btThongKeKho;
    private javax.swing.JButton btThongKeSach;
    private javax.swing.JButton btThongKeVPP;
    private javax.swing.JButton btTimKiem;
    private javax.swing.JButton btTimKiem1;
    private javax.swing.JButton btTrangChu;
    private javax.swing.JButton btXoaBo;
    private javax.swing.JButton btXoaBo1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbSapHetHang;
    private javax.swing.JLabel lbTongSach;
    private javax.swing.JLabel lbTongTonKho;
    private javax.swing.JLabel lbVanPhongPham;
    private javax.swing.JPanel mainGD;
    private javax.swing.JPanel mainPN;
    private javax.swing.JPanel pnQuanLyKho;
    private javax.swing.JPanel pnQuanLySach;
    private javax.swing.JPanel pnQuanLyVPP;
    private javax.swing.JPanel pnThongKeKho;
    private javax.swing.JPanel pnThongKeSach;
    private javax.swing.JPanel pnThongKeVPP;
    private javax.swing.JPanel pnTrangChu;
    private javax.swing.JTable tbQuanLySach;
    private javax.swing.JTable tbQuanLyVPP;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tbThongKeSach;
    private javax.swing.JTable tbThongKeVPP;
    private javax.swing.JTable tbVPP;
    private javax.swing.JTextField tfChatLuong;
    private javax.swing.JTextField tfDonGia;
    private javax.swing.JTextField tfDonGia1;
    private javax.swing.JTextField tfMaSP;
    private javax.swing.JTextField tfMaSach;
    private javax.swing.JTextField tfNamXB;
    private javax.swing.JTextField tfNhaXB;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfSoLuong1;
    private javax.swing.JTextField tfTacGia;
    private javax.swing.JTextField tfTenSach;
    private javax.swing.JTextField tfTenSanPham;
    private javax.swing.JTextField tfTheLoai;
    private javax.swing.JTextField tfThuongHieu;
    private javax.swing.JTextField tfTimKiem;
    private javax.swing.JTextField tfTimKiem1;
    private javax.swing.JTextField tfXuatXu;
    // End of variables declaration//GEN-END:variables
}
