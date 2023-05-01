package com.gearvmdesktop.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.CustomerService;
import com.gearvmdesktop.service.OrderService;
import com.gearvmdesktop.service.ProductService;
import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.Gender;
import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderItem;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.gearvmstore.GearVM.model.response.GetPendingDirectOrderListResponse;
import com.gearvmstore.GearVM.model.response.OrderItemResponseModel;
import com.gearvmstore.GearVM.model.response.ProductResponseModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FrmBanHang extends JFrame implements ActionListener, MouseListener {
    private static final String tableNameProduct = "products/";
    private static final String tableNameOrder = "orders/";
    private JButton btnTim;
    private static DefaultTableModel modelSanPham;
    private static JTable tableSanPham;
    private JTextField txtMaKhachHang;
    private static JTextField txtSoLuong;
    private JButton btnCong;
    private DefaultTableModel modelGioHang;
    private static JTable tableGioHang;
    private JTextField txtTongTien;
    private JButton btnLamMoi;
    private JButton btnThanhToan;
    private JLabel lblMa;
    private JLabel lblTen;
    private JLabel lblSDT;
    private JLabel lblSoLuong;
    private JTextField txtTenKhachHang;
    private JLabel lblGioiTinh;
    private JComboBox<String> cmbGioiTinh;
    private JLabel lblTongTien;
    private JDateChooser txtNgaySinh;
    private JLabel lblNgaySinh;
    private static JComboBox<String> cmbDanhSachSdt;
    private JTextField txtTim;
    private JButton btnTimKHCu;
    private JLabel lblGioHang;
    private JPanel pTitle1;
    private JLabel lblTitle;
    private JLabel lblTitle1;
    private JButton btnTaoGioHang;
    private JButton btnTru;
    private static JComboBox<String> cmbChon;
    private static JComboBox<String> cmbGioHang;

    public JPanel createPanelBanHang() throws IOException {
        FlatLightLaf.setup();
        // TODO Auto-generated constructor stub
        setTitle("FrmBanHang");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel p = new JPanel();

        Box b = Box.createHorizontalBox();
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createVerticalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createVerticalBox();
        Box b5 = Box.createHorizontalBox();
        Box b6 = Box.createHorizontalBox();
        Box b7 = Box.createHorizontalBox();
        Box b10 = Box.createHorizontalBox();
        Box b11 = Box.createHorizontalBox();
        Box b12 = Box.createHorizontalBox();
        Box b13 = Box.createHorizontalBox();
        Box b14 = Box.createHorizontalBox();
        Box b16 = Box.createHorizontalBox();
        Box b17 = Box.createHorizontalBox();

        ClassLoader classLoader = getClass().getClassLoader();
        URL iconThem = classLoader.getResource("assets/them.png");

        URL iconGiam = classLoader.getResource("assets/xoa.png");
        URL iconTim = classLoader.getResource("assets/timkiem.png");
        URL iconThanhToan = classLoader.getResource("assets/thanhtoan.png");
        URL iconTimSp = classLoader.getResource("assets/timhanghoa.png");
        URL iconLoad = classLoader.getResource("assets/lammoi.png");

        String[] tim = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
        cmbChon = new JComboBox<String>(tim);
        b3.add(cmbChon);
        b3.add(Box.createHorizontalStrut(10));
        txtTim = new JTextField();
        txtTim.setEditable(true);
        cmbChon.setSize(20, txtTim.getPreferredSize().height);
        b3.add(txtTim);
        b3.add(Box.createHorizontalStrut(10));
        b3.add(btnTim = new JButton("TÌM HÀNG", new ImageIcon(iconTimSp)));
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);

        b3.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "TÌM SẢN PHẨM	: "));

        String[] colHeader = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
        modelSanPham = new DefaultTableModel(colHeader, 0);
        tableSanPham = new JTable(modelSanPham) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Color color1 = new Color(219, 243, 255);
                Color color2 = Color.WHITE;
                if (!c.getBackground().equals(getSelectionBackground())) {
                    Color coleur = (row % 2 == 0 ? color1 : color2);
                    c.setBackground(coleur);
                    coleur = null;
                }
                return c;
            }
        };
        tableSanPham.setGridColor(getBackground());
        tableSanPham.setRowHeight(tableSanPham.getRowHeight() + 20);
        tableSanPham.setSelectionBackground(new Color(255, 255, 128));
        tableSanPham.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableSanPham.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        JScrollPane tblscroll = new JScrollPane(tableSanPham);
        tableSanPham.setPreferredScrollableViewportSize(new Dimension(650, 580));
        b1.add(b3);
        b1.add(tblscroll);
        tblscroll.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));

        b.add(lblTitle = new JLabel("THÔNG TIN KHÁCH HÀNG"));
        b5.add(lblMa = new JLabel("Mã Khách Hàng:"));
        b5.add(Box.createHorizontalStrut(10));
        b5.add(txtMaKhachHang = new JTextField());
        b6.add(lblTen = new JLabel("Tên Khách Hàng:"));
        b6.add(Box.createHorizontalStrut(10));
        b6.add(txtTenKhachHang = new JTextField());
        b14.add(lblNgaySinh = new JLabel("Ngày Sinh:"));
        b14.add(Box.createHorizontalStrut(10));
        b14.add(txtNgaySinh = new JDateChooser());
        txtNgaySinh.setDateFormatString("dd-MM-yyyy");
        txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));
        String[] gioitinh = {"Nam", "Nữ", "Khác"};
        cmbGioiTinh = new JComboBox<String>(gioitinh);
        b13.add(lblGioiTinh = new JLabel("Giới Tính:"));
        b13.add(Box.createHorizontalStrut(10));
        b13.add(cmbGioiTinh);
        b7.add(lblSDT = new JLabel("SDT:"));
        b7.add(Box.createHorizontalStrut(10));
        cmbDanhSachSdt = new JComboBox<String>();
        cmbDanhSachSdt.setEditable(true);
        AutoCompleteDecorator.decorate(cmbDanhSachSdt);
        cmbDanhSachSdt.setMaximumRowCount(10);
        b7.add(cmbDanhSachSdt);
        btnTimKHCu = new JButton("TÌM SDT", new ImageIcon(iconTim));
        btnTimKHCu.setBackground(new Color(0, 148, 224));
        btnTimKHCu.setForeground(Color.WHITE);
        btnTimKHCu.setFocusPainted(false);
        b7.add(Box.createHorizontalStrut(300));
        b7.add(btnTimKHCu);

        b17.add(pTitle1 = new JPanel());
        pTitle1.add(lblTitle1 = new JLabel("GIỎ HÀNG"));
        b16.add(lblGioHang = new JLabel("Giỏ Hàng Của:"));
        b16.add(Box.createHorizontalStrut(10));
        cmbGioHang = new JComboBox<String>();
        cmbGioHang.setEditable(true);
        AutoCompleteDecorator.decorate(cmbGioHang);
        cmbGioHang.setMaximumRowCount(10);
        b16.add(cmbGioHang);
        b11.add(lblSoLuong = new JLabel("Số Lượng:"));
        b11.add(Box.createHorizontalStrut(10));
        b11.add(txtSoLuong = new JTextField());
        b11.add(Box.createHorizontalStrut(200));

        b4.add(b);
        b4.add(b7);
        b4.add(b5);
        b4.add(b6);
        b4.add(b14);
        b4.add(b13);
        b4.add(b17);
        b4.add(b16);
        b4.add(b11);

        String[] colHeader1 = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng",
                "Thành Tiền"};
        modelGioHang = new DefaultTableModel(colHeader1, 0);
        tableGioHang = new JTable(modelGioHang) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Color color1 = new Color(219, 243, 255);
                Color color2 = Color.WHITE;
                if (!c.getBackground().equals(getSelectionBackground())) {
                    Color coleur = (row % 2 == 0 ? color1 : color2);
                    c.setBackground(coleur);
                    coleur = null;
                }
                return c;
            }
        };


        tableGioHang.setGridColor(getBackground());
        tableGioHang.setRowHeight(tableGioHang.getRowHeight() + 30);
        tableGioHang.setSelectionBackground(new Color(255, 255, 128));
        tableGioHang.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader1 = tableGioHang.getTableHeader();
        tableHeader1.setBackground(new Color(0, 148, 224));
        tableHeader1.setFont(new Font("Tahoma", Font.BOLD, 11));
        tableHeader1.setForeground(Color.WHITE);
        tableHeader1.setPreferredSize(new Dimension(WIDTH, 30));
        tableSanPham.setRowHeight(tableSanPham.getRowHeight() + 20);

        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(165);

        JScrollPane tblscroll1 = new JScrollPane(tableGioHang);
        tblscroll1.setBorder(BorderFactory.createTitledBorder("GIỎ HÀNG: "));
        tableGioHang.setPreferredScrollableViewportSize(new Dimension(500, 105));
        b10.add(lblTongTien = new JLabel("Tổng Tiền:"));
        b10.add(txtTongTien = new JTextField());

        b12.add(btnThanhToan = new JButton("THANH TOÁN", new ImageIcon(iconThanhToan)));
        btnThanhToan.setBackground(new Color(0, 148, 224));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setFocusPainted(false);
        b12.add(Box.createHorizontalStrut(10));
        b12.add(btnLamMoi = new JButton("LÀM MỚI", new ImageIcon(iconLoad)));
        btnLamMoi.setBackground(new Color(0, 148, 224));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setFocusPainted(false);
        b2.add(b4);
        b11.add(Box.createHorizontalStrut(10));
        b11.add(btnCong = new JButton("THÊM", new ImageIcon(iconThem)));
        btnCong.setBackground(new Color(0, 148, 224));
        btnCong.setForeground(Color.WHITE);
        btnCong.setFocusPainted(false);
        b11.add(Box.createHorizontalStrut(5));
        b11.add(btnTru = new JButton("GIẢM", new ImageIcon(iconGiam)));
        btnTru.setBackground(new Color(0, 148, 224));
        btnTru.setForeground(Color.WHITE);
        btnTru.setFocusPainted(false);
        b13.add(Box.createHorizontalStrut(10));
        b13.add(btnTaoGioHang = new JButton("TẠO GIỎ HÀNG", new ImageIcon(iconThem)));
        btnTaoGioHang.setBackground(new Color(0, 148, 224));
        btnTaoGioHang.setForeground(Color.WHITE);
        btnTaoGioHang.setFocusPainted(false);
        b2.add(tblscroll1);
        b2.add(b10);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.add(b12);
        b2.add(p2);
        p1.add(b1);
        p1.add(b2);
        p.add(p1, BorderLayout.CENTER);

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblSDT.setPreferredSize(lblTen.getPreferredSize());
        lblSoLuong.setPreferredSize(lblTen.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
        lblTongTien.setPreferredSize(lblTen.getPreferredSize());
        lblNgaySinh.setPreferredSize(lblTen.getPreferredSize());
        lblGioHang.setPreferredSize(lblTen.getPreferredSize());
        cmbDanhSachSdt.setPreferredSize(lblTen.getPreferredSize());

        b.setBorder(new EmptyBorder(new Insets(10, 10, 0, 10)));
        b3.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b5.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b6.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b7.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b10.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b11.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b12.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b13.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b14.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b16.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b17.setBorder(new EmptyBorder(new Insets(10, 10, 0, 10)));

        btnThanhToan.setBounds(36, 330, 79, 13);
        btnLamMoi.setBounds(131, 330, 79, 13);
        txtMaKhachHang.setEditable(false);

        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbDanhSachSdt.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTimKHCu.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTaoGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 16));
        cmbGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCong.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTru.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));

        tableGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGioHang.setDefaultEditor(Object.class, null);
        tableGioHang.getTableHeader().setReorderingAllowed(false);
        tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.getTableHeader().setReorderingAllowed(false);

        txtTongTien.setBorder(null);
        txtTongTien.setBackground(null);
        txtTongTien.setText(null);
        txtSoLuong.setText("0");

        setCustomerTextFieldEditableAndNull(false);

        readDatabaseToTableProduct();
        setAllPhoneNumberToCombobox();
        setPendingDirectOrderToCombobox();

        btnTimKHCu.addActionListener(this);
        btnTaoGioHang.addActionListener(this);
        btnCong.addActionListener(this);
        btnTru.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnThanhToan.addActionListener(this);
        cmbGioHang.addActionListener(this);

        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTimKHCu)) {
            try {
                if (getCustomerByPhoneNumberFromCombobox()) {
                    JOptionPane.showMessageDialog(null, "Đây là khách hàng cũ. Welcome back!", "Welcome back",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Đây là khách hàng mới. Welcome!", "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);
                    setCustomerTextFieldEditableAndNull(true);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (o.equals(btnTaoGioHang)) {
            if (txtTenKhachHang.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin khách hàng", "Thất bại",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        int resultCreateCart = createCart();
                        if (resultCreateCart == 0) {
                            JOptionPane.showMessageDialog(null, "Tạo giỏ hàng thành công. Shopping!", "Thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            clearTextField();
                            GUI.readAllDatabaseToTable();
                            emptyTableCart();
                            cmbGioHang.setSelectedIndex(cmbGioHang.getItemCount() - 1);
                        } else if (resultCreateCart == 1) {
                            JOptionPane.showMessageDialog(null, "Khách hàng đã có giỏ hàng, vui lòng xử lý giỏ hàng này!", "Thất bại",
                                    JOptionPane.INFORMATION_MESSAGE);
                            setSelectedIndexFromPhoneNumber();
                        } else {
                            JOptionPane.showMessageDialog(null, "Tạo giỏ hàng thất bại. Xin vui lòng thử lại!", "Thất bại",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException | JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        }
        if (o.equals(cmbGioHang)) {
            try {
                Object giaTriCbo = cmbGioHang.getSelectedItem();
                if (giaTriCbo == null || giaTriCbo.toString().equals("")) {
                } else {
                    readCurrentCartToTableCart(getRequestByCustomerNameAndCustomerPhoneNumber());
                    txtSoLuong.setText("");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (o.equals(btnCong)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                Object giaTriCmb = cmbGioHang.getSelectedItem();
                int row = tableSanPham.getSelectedRow();
                String soLuong = txtSoLuong.getText();
                System.out.println(modelSanPham.getValueAt(row, 5).toString().trim());
                if (giaTriCmb == null || giaTriCmb.toString().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn giỏ hàng", "Thất bại", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (soLuong.equals("") && soLuong.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng cần thêm!", "Thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm", "Thất bại", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (Integer.parseInt(modelSanPham.getValueAt(row, 5).toString().trim()) < Integer.parseInt(txtSoLuong.getText())) {
                    JOptionPane.showMessageDialog(this, "Không đủ số lượng để thêm sản phẩm", "Thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String productId = modelSanPham.getValueAt(row, 0).toString().trim();
                    try {
                        if (updateAddOrderItem(productId)) {
                            JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào giỏ hàng thành công!", "Thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            readCurrentCartToTableCart(getRequestByCustomerNameAndCustomerPhoneNumber());
                            readDatabaseToTableProduct();
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ hàng thất bại!", "Thất bại",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (JSONException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        if (o.equals(btnTru)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                Object giaTriCmb = cmbGioHang.getSelectedItem();
                int row = tableGioHang.getSelectedRow();
                String soLuong = txtSoLuong.getText();
                if (giaTriCmb == null || giaTriCmb.toString().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn giỏ hàng!", "Thất bại", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (soLuong.equals("") && soLuong.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng cần giảm!", "Thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (row < 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng!", "Thất bại", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (Integer.parseInt(modelGioHang.getValueAt(row, 5).toString().trim()) < Integer.parseInt(txtSoLuong.getText())) {
                    JOptionPane.showMessageDialog(this, "Số sản phẩm cần giảm lớn hơn số sản phẩm trong giỏ hàng!", "Thất bại",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String productId = modelGioHang.getValueAt(row, 0).toString().trim();
                    try {
                        if (updateReduceOrderItem((productId))) {
                            if (tableGioHang.getRowCount() == 1 && Integer.parseInt(modelGioHang.getValueAt(row, 5).toString().trim()) == Integer.parseInt(txtSoLuong.getText())) {
                                JOptionPane.showMessageDialog(null, "Xóa giỏ hàng thành công!", "Thành công",
                                        JOptionPane.INFORMATION_MESSAGE);
                                txtTongTien.setText("0.0 VNĐ");
                                emptyTableCart();
                                setPendingDirectOrderToCombobox();
                            } else {
                                JOptionPane.showMessageDialog(null, "Giảm sản phẩm trong giỏ hàng thành công!", "Thành công",
                                        JOptionPane.INFORMATION_MESSAGE);
                                readCurrentCartToTableCart(getRequestByCustomerNameAndCustomerPhoneNumber());
                            }
                            readDatabaseToTableProduct();
                        } else {
                            JOptionPane.showMessageDialog(this, "Giảm sản phẩm trong giỏ hàng thất bại!", "Thất bại",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (JSONException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        if (o.equals(btnLamMoi)) {
            clearTextField();
        }
        if (o.equals(btnThanhToan)) {
            try {
                new FrmThanhToan(getRequestByCustomerNameAndCustomerPhoneNumber());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //region
    @Override
    public void mouseClicked(MouseEvent e) {

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
    //endregion

    public static void readDatabaseToTableProduct() throws IOException {
        emptyTableProduct();
        ObjectMapper mapper = new ObjectMapper();
        // Get all products
        BufferedReader rd = ProductService.getAllRequest(tableNameProduct + "/get-all");
        List<Product> listProduct = Arrays.asList(mapper.readValue(rd, Product[].class));
        DecimalFormat df = new DecimalFormat("#,##0");
        for (Product p : listProduct) {
            modelSanPham.addRow(new Object[]{p.getId(), p.getName(), p.getType(),
                    p.getBrand(), df.format(p.getPrice()), p.getQuantity()});
        }
    }

    public static void emptyTableProduct() {
        DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
        dm.setRowCount(0);
    }

    public static void emptyTableCart() {
        txtSoLuong.setText("0");
        DefaultTableModel dm = (DefaultTableModel) tableGioHang.getModel();
        dm.setRowCount(0);
    }

    public boolean getCustomerByPhoneNumberFromCombobox() throws IOException {
        try {
            String phoneNumber = cmbDanhSachSdt.getSelectedItem().toString();

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            BufferedReader rd = CustomerService.getCustomerByPhoneNumber(phoneNumber);
            if (rd == null) return false;

            Customer customer = mapper.readValue(rd, Customer.class);
            txtMaKhachHang.setText(customer.getId().toString());
            txtTenKhachHang.setText(customer.getName());
            txtNgaySinh.setDate(Date.from(customer.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            if (customer.getGender() == Gender.MALE) cmbGioiTinh.setSelectedIndex(0);
            else if (customer.getGender() == Gender.FEMALE) cmbGioiTinh.setSelectedIndex(1);
            else if (customer.getGender() == Gender.UNDEFINED) cmbGioiTinh.setSelectedIndex(2);

            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setAllPhoneNumberToCombobox() throws IOException {
        cmbDanhSachSdt.removeAllItems();
        cmbDanhSachSdt.addItem("");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = CustomerService.getAllPhoneNumber();

        String json = rd.readLine();
        List<String> phoneNumbers = mapper.readValue(json, new TypeReference<>() {
        });
        rd.close();

        for (String phoneNumber : phoneNumbers) {
            cmbDanhSachSdt.addItem(phoneNumber);
        }
    }

    public void setCustomerTextFieldEditableAndNull(boolean isEditable) {
        txtTenKhachHang.setEditable(isEditable);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) txtNgaySinh.getDateEditor();
        editor.setEditable(isEditable);
        txtTenKhachHang.setText("");
        txtSoLuong.setText("");
        txtMaKhachHang.setText("");
        txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));
        cmbGioiTinh.setSelectedIndex(0);
    }

    public void clearTextField() {
        txtTenKhachHang.setText("");
        txtSoLuong.setText("");
        txtMaKhachHang.setText("");
        txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));
        cmbGioHang.setSelectedIndex(-1);
        cmbDanhSachSdt.setSelectedIndex(-1);
        cmbGioiTinh.setSelectedIndex(0);
    }

    // 0: created
    // 1: existing cart
    // 2: error
    public int createCart() throws IOException, JSONException {
        Customer customer = new Customer();
        customer.setName(txtTenKhachHang.getText());
        Calendar birthdayCalendar = txtNgaySinh.getCalendar();
        LocalDate birthdayLocalDate = LocalDate.ofInstant(birthdayCalendar.toInstant(), ZoneId.systemDefault());
        customer.setDateOfBirth(birthdayLocalDate);
        customer.setPhoneNumber(cmbDanhSachSdt.getSelectedItem().toString());
        if (cmbGioiTinh.getSelectedIndex() == 0) customer.setGender(Gender.MALE);
        else if (cmbGioiTinh.getSelectedIndex() == 1) customer.setGender(Gender.FEMALE);
        else customer.setGender(Gender.UNDEFINED);

        if (txtMaKhachHang.getText().equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            BufferedReader rd = CustomerService.postRequestWithResponse(customer);
            Customer newCustomer = mapper.readValue(rd, Customer.class);
            if (newCustomer == null) return 2;
            customer.setId(newCustomer.getId());
        } else {
            customer.setId(Long.parseLong(txtMaKhachHang.getText()));
        }
        return OrderService.createDirectOrder(customer.getId().toString());
    }

    private void setSelectedIndexFromPhoneNumber() {
        String sdt = cmbDanhSachSdt.getSelectedItem().toString();
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbDanhSachSdt.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equals(sdt)) {
                cmbGioHang.setSelectedIndex(i);
                break;
            }
        }
    }

    public static void setPendingDirectOrderToCombobox() throws IOException {
        cmbGioHang.removeAllItems();
        cmbGioHang.addItem("");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = OrderService.getPendingDirectOrderList();
        if (rd != null) {
            List<GetPendingDirectOrderListResponse> directOrderList = List.of(mapper.readValue(rd, GetPendingDirectOrderListResponse[].class));
            for (GetPendingDirectOrderListResponse directOrder : directOrderList
            ) {
                String row = directOrder.getCustomerName() + ", " + directOrder.getCustomerPhoneNumber();
                cmbGioHang.addItem(row);
            }
        }
    }

    public static void readDatabase() throws IOException {
        setAllPhoneNumberToCombobox();
        setPendingDirectOrderToCombobox();
        readDatabaseToTableProduct();
    }

    public boolean updateAddOrderItem(String productId) throws JSONException, IOException {
        String cmbGioHangString = cmbGioHang.getSelectedItem().toString();
        if (cmbGioHangString.equals("")) {
            return false;
        }
        String[] parts = cmbGioHangString.split(",");

        UpdateOrderItem updateOrderItem = new UpdateOrderItem();
        updateOrderItem.setProductId(productId);
        updateOrderItem.setCustomerName(parts[0]);
        updateOrderItem.setCustomerPhone(parts[1].trim());
        updateOrderItem.setAmount(Integer.parseInt(txtSoLuong.getText()));

        return OrderService.patchUpdateAddOrderItem(updateOrderItem);
    }

    public boolean updateReduceOrderItem(String productId) throws JSONException, IOException {
        String cmbGioHangString = cmbGioHang.getSelectedItem().toString();
        if (cmbGioHangString.equals("")) {
            return false;
        }
        String[] parts = cmbGioHangString.split(",");

        UpdateOrderItem updateOrderItem = new UpdateOrderItem();
        updateOrderItem.setProductId(productId);
        updateOrderItem.setCustomerName(parts[0]);
        updateOrderItem.setCustomerPhone(parts[1].trim());
        updateOrderItem.setAmount(Integer.parseInt(txtSoLuong.getText()));

        return OrderService.patchUpdateReduceOrderItem(updateOrderItem);
    }

    public GetOrderResponse getRequestByCustomerNameAndCustomerPhoneNumber() throws IOException {
        try {
            String cmbGioHangString = cmbGioHang.getSelectedItem().toString();
            if (cmbGioHangString.equals("")) {
                return null;
            }
            String[] parts = cmbGioHangString.split(",");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            BufferedReader rd = OrderService.getRequestByCustomerNameAndCustomerPhoneNumber(parts[0], parts[1].trim());
            return mapper.readValue(rd, GetOrderResponse.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void readCurrentCartToTableCart(GetOrderResponse getOrderResponse) throws IOException {
        emptyTableCart();
        if (getOrderResponse != null) {
            Double totalPrice = 0.0;
            DecimalFormat df = new DecimalFormat("#,##0");

            List<OrderItemResponseModel> orderItems = getOrderResponse.getOrderItems();
            for (OrderItemResponseModel orderItem : orderItems) {
                ProductResponseModel product = orderItem.getProduct();

                modelGioHang.addRow(new Object[]{product.getId(), product.getName(), product.getType(),
                        product.getBrand(), df.format(product.getPrice()), orderItem.getQuantity(),
                        df.format(orderItem.getPrice() * orderItem.getQuantity())});
                totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            }
            if (totalPrice == 0)
                txtTongTien.setText("0.0 VNĐ");
            else
                txtTongTien.setText(df.format(totalPrice) + " VNĐ");
        }
    }

    public Product getProductRequest(String productId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ProductService.getRequest(tableNameProduct, productId);
        return mapper.readValue(rd, Product.class);
    }
}
