package com.gearvmdesktop.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.model.response.EmployeeResponseModel;
import sun.misc.Unsafe;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

public class GUI extends JFrame implements ActionListener, MouseListener {
    private JLabel lblThoiGian;
    private JLabel txtThoiGian;
    private JLabel lblDangXuat;
    private JLabel lblMaNhanVien;
    private static JLabel txtMaNhanVien;
    private JLabel lblTenNhanVien;
    private static JLabel txtTenNhanVien;

    public GUI(Employee e) throws IOException {
        FlatLightLaf.setup();
        setTitle("GearVM");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        setMaximumSize(DimMax);
        setLocationRelativeTo(null);
        UIManager.put("TabbedPane.selected", new Color(50, 190, 255));
        add(createTabbedPane(e));
        setExtendedState(MAXIMIZED_BOTH);
    }

    public static void main(String args[]) {
        // Set the Nimbus look and feel
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">

        // If Nimbus (introduced in Java SE 6) is not available, stay with the default
        // look and feel. For details see
        // http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI(null).setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * create a JTabbedPane contain tabs
     */
    public JTabbedPane createTabbedPane(Employee e) throws IOException {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return 80;
            }

            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return 200;
            }
        });
        tabbedPane.setBackground(new Color(0, 148, 224));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));


        FrmBanHang frmBanHang = new FrmBanHang();
        FrmHoaDon frmHoaDon = new FrmHoaDon();
        FrmNhanVien frmNhanVien = new FrmNhanVien();
        FrmKhachHang frmKhachHang = new FrmKhachHang();
        FrmSanPham frmHangHoa = new FrmSanPham();
        FrmDonHang frmDonHang = new FrmDonHang();
        FrmKhoHang frmKhoHang = new FrmKhoHang();

        /* create JPanel, which is content of tabs */
        JPanel pnlTrangChu = createPanelTrangChu(e);
        JPanel pnlHoaDon = frmHoaDon.createPanelHoaDon();
        JPanel pnlBanHang = frmBanHang.createPanelBanHang();
        JPanel pnlNhanVien = frmNhanVien.createPanelNhanVien();
        JPanel pnlKhachHang = frmKhachHang.createPanelKhachHang();
        JPanel pnlHangHoa = frmHangHoa.createPanelSanPham();
        JPanel pnlKhoHang = frmKhoHang.createPanelKhoHang();
        JPanel pnlDonHang = frmDonHang.createPanelDonHang();


        ClassLoader classLoader = getClass().getClassLoader();
        URL iconTrangChu = classLoader.getResource("assets/trangchu.png");
        URL iconHoaDon = classLoader.getResource("assets/hoadon.png");
        URL iconBanHang = classLoader.getResource("assets/banhang.png");
        URL iconNhanVien = classLoader.getResource("assets/nhanvien.png");
        URL iconKhachHang = classLoader.getResource("assets/khachhang.png");
        URL iconHangHoa = classLoader.getResource("assets/hanghoa.png");
        URL iconKhoHang = classLoader.getResource("assets/khohang.png");
        URL iconDonHang = classLoader.getResource("assets/donhang.png");



        /* add tab with JPanel */
        tabbedPane.addTab("TRANG CHỦ",  new ImageIcon(iconTrangChu), pnlTrangChu, "TRANG CHỦ");
        tabbedPane.addTab("ĐƠN HÀNG", new ImageIcon(iconHoaDon), pnlDonHang, "ĐƠN HÀNG");
        tabbedPane.addTab("BÁN HÀNG", new ImageIcon(iconBanHang), pnlBanHang, "BÁN HÀNG");
        tabbedPane.addTab("NHÂN VIÊN", new ImageIcon(iconNhanVien), pnlNhanVien, "NHÂN VIÊN");
        tabbedPane.addTab("KHÁCH HÀNG", new ImageIcon(iconKhachHang), pnlKhachHang, "KHÁCH HÀNG");
        tabbedPane.addTab("SẢN PHẨM", new ImageIcon(iconHangHoa), pnlHangHoa, "SẢN PHẨM");
        tabbedPane.addTab("KHO HÀNG", new ImageIcon(iconKhoHang), pnlKhoHang, "KHO HÀNG");
        tabbedPane.addTab("HÓA ĐƠN", new ImageIcon(iconDonHang), pnlHoaDon, "HÓA ĐƠN");

        return tabbedPane;
    }

    public JPanel createPanelTrangChu(Employee e) {

        JPanel pnlContentPane = new JPanel();

        pnlContentPane.setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlContentPane.add(pnlTitle);

        JLabel lblTitle = new JLabel("PHẦN MỀM QUẢN LÝ GEARVM");
        lblTitle.setBounds(50, 0, 1000, 50);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblTitle.setForeground(Color.BLACK);
        pnlContentPane.add(lblTitle);

        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("assets/hinhnen.jpg");
        ImageIcon imageIcon = null;
        if (resourceUrl != null) {
            imageIcon = new ImageIcon(resourceUrl);
        }

        Image image = imageIcon.getImage();
        Image imageResize = image.getScaledInstance(1450, 550, Image.SCALE_SMOOTH);
        JLabel lblHinhNen = new JLabel(new ImageIcon(imageResize));
        lblHinhNen.setBounds(10, 50, 1450, 550);
        pnlContentPane.add(lblHinhNen);

        lblDangXuat = new JLabel("<HTML><U>ĐĂNG XUẤT</U></HTML>");
        lblDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblDangXuat.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lblDangXuat.setBounds(1200, 0, 150, 42);
        pnlContentPane.add(lblDangXuat);

        lblMaNhanVien = new JLabel("MÃ NHÂN VIÊN: ");
        lblMaNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMaNhanVien.setBounds(50, 670, 200, 42);
        txtMaNhanVien = new JLabel();
        txtMaNhanVien.setBounds(250, 670, 200, 42);
        txtMaNhanVien.setHorizontalAlignment(JLabel.LEFT);
        txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnlContentPane.add(lblMaNhanVien);
        pnlContentPane.add(txtMaNhanVien);

        lblTenNhanVien = new JLabel("TÊN NHÂN VIÊN: ");
        lblTenNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTenNhanVien.setBounds(50, 720, 200, 42);
        txtTenNhanVien = new JLabel("QL1001");
        txtTenNhanVien = new JLabel();
        txtTenNhanVien.setBounds(250, 720, 500, 42);
        txtTenNhanVien.setHorizontalAlignment(JLabel.LEFT);
        txtTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnlContentPane.add(lblTenNhanVien);
        pnlContentPane.add(txtTenNhanVien);

        lblThoiGian = new JLabel("THỜI GIAN HIỆN TẠI:");
        lblThoiGian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblThoiGian.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lblThoiGian.setBounds(750, 720, 400, 42);
        txtThoiGian = new JLabel();
        txtThoiGian.setBounds(1010, 720, 400, 42);
        txtThoiGian.setHorizontalAlignment(JLabel.LEFT);
        txtThoiGian.setFont(new Font("Tahoma", Font.ITALIC, 20));

        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtThoiGian.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }
        });
        t.setRepeats(true);
        t.setCoalesce(true);
        t.setInitialDelay(0);
        t.start();
        pnlContentPane.add(lblThoiGian);
        pnlContentPane.add(txtThoiGian);

        pnlContentPane.setBackground(new Color(255, 255, 255));
        lblDangXuat.addMouseListener(this);

        if (e != null) getEmployeeInfo(e);

        return pnlContentPane;
    }

    public void getEmployeeInfo(Employee e) {
        txtMaNhanVien.setText(e.getId().toString());
        txtTenNhanVien.setText(e.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(lblDangXuat)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "?!",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                FrmDangNhap frmDN = new FrmDangNhap();
                frmDN.setVisible(true);
                frmDN.setLocationRelativeTo(null);
                dispose();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static EmployeeResponseModel getEmployeeInfo() {
        EmployeeResponseModel e = new EmployeeResponseModel();
        e.setId(Long.parseLong(txtMaNhanVien.getText()));
        e.setName(txtTenNhanVien.getText());
        return e;
    }

    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }

    public static void readAllDatabaseToTable() throws IOException {
        FrmDonHang.readDatabaseToTable();
        FrmKhachHang.readDatabaseToTable();
        FrmNhanVien.readDatabaseToTable();
        FrmSanPham.readDatabaseToTable();
        FrmKhoHang.readDatabaseToTable();
        FrmBanHang.readDatabase();
    }
}
