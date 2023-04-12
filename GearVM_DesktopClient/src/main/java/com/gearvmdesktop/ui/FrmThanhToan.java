package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.OrderService;
import com.gearvmdesktop.service.ProductService;
import com.gearvmstore.GearVM.model.PaymentMethod;
import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.dto.order.ProcessDirectOrderPayment;
import com.gearvmstore.GearVM.model.response.EmployeeResponseModel;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.gearvmstore.GearVM.model.response.OrderItemResponseModel;
import com.gearvmstore.GearVM.model.response.ProductResponseModel;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


public class FrmThanhToan extends JFrame implements ActionListener {
    private static final String tableNameProduct = "products/";
    private static final String tableNameOrder = "orders/";
    private static JTable tableSanPham;
    private static DefaultTableModel modelSanPham;
    private final JTextField txtMaDonHang;
    private final JLabel lblTenKhachHang;
    private final JLabel lblMaDonHang;
    private final JTextField txtTenKhachHang;
    private final JLabel lblSdtKhachHang;
    private final JTextField txtSdtKhachHang;
    private final JLabel lblTongTien;
    private final JTextField txtTongTien;
    private final JComboBox<String> cmbPhuongThucThanhToan;
    private final JLabel lblPhuongThucThanhToan;
    private final JButton btnQRCode;
    private final JButton btnProcessPayment;
    private final JButton btnPrintBill;
    private final JLabel lblDiaChi;
    private final JTextField txtDiaChi;
    private final JLabel lblTenKhachHangNhanHang;
    private final JTextField txtTenKhachHangNhanHang;
    private final JLabel lblSdtKhachHangNhanHang;
    private final JTextField txtSdtKhachHangNhanHang;
    private final JComboBox<String> cmbPhuongThucNhanHang;
    private final JLabel lblPhuongThucNhanHang;

    public FrmThanhToan(GetOrderResponse getOrderResponse) throws IOException {
        super("Thanh Toán");

        FlatLightLaf.setup();
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        setMaximumSize(DimMax);
        setExtendedState(MAXIMIZED_BOTH);

        Box b = Box.createHorizontalBox();
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createHorizontalBox();

        String[] colHeader = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Sản Phẩm", "Số Lượng Tồn Kho", "Thành Tiền"};
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

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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
        tableSanPham.setBounds(50, 50, 700, 600);
        tableSanPham.setPreferredScrollableViewportSize(new Dimension(800, 500));

        JScrollPane tblscroll = new JScrollPane(tableSanPham);
        tblscroll.setBorder(BorderFactory.createTitledBorder("GIỎ HÀNG: "));
        b1.add(Box.createVerticalStrut(10));
        b1.add(tblscroll);
        b1.add(Box.createVerticalStrut(20));

        Box b3 = Box.createVerticalBox();
        Box b4 = Box.createHorizontalBox();
        Box b5 = Box.createHorizontalBox();
        Box b6 = Box.createHorizontalBox();
        Box b7 = Box.createHorizontalBox();
        Box b8 = Box.createHorizontalBox();
        Box b9 = Box.createHorizontalBox();
        Box b10 = Box.createHorizontalBox();
        Box b11 = Box.createHorizontalBox();
        Box b12 = Box.createHorizontalBox();
        Box b13 = Box.createHorizontalBox();

        Box b17 = Box.createHorizontalBox();
        Box b18 = Box.createHorizontalBox();
        Box b19 = Box.createHorizontalBox();

        b2.add(Box.createHorizontalStrut(100));
        b2.add(b3);

        b4.add(lblMaDonHang = new JLabel("Mã Đơn Hàng:"));
        b4.add(Box.createHorizontalStrut(10));
        b4.add(txtMaDonHang = new JTextField());
        b3.add(Box.createVerticalStrut(50));
        b3.add(b4);

        b5.add(lblTenKhachHang = new JLabel("Tên Khách Hàng:"));
        b5.add(Box.createHorizontalStrut(10));
        b5.add(txtTenKhachHang = new JTextField());
        b3.add(b5);

        b6.add(lblSdtKhachHang = new JLabel("SDT Khách Hàng:"));
        b6.add(Box.createHorizontalStrut(10));
        b6.add(txtSdtKhachHang = new JTextField());
        b3.add(b6);

        String[] phuongThucNhanHang = {"", "Tại Cửa Hàng", "Giao Hàng Tận Nơi"};
        cmbPhuongThucNhanHang = new JComboBox<String>(phuongThucNhanHang);
        b11.add(lblPhuongThucNhanHang = new JLabel("Phương Thức Nhận Hàng:"));
        b11.add(Box.createHorizontalStrut(10));
        b11.add(cmbPhuongThucNhanHang);
        b3.add(b11);

        b18.add(lblTenKhachHangNhanHang = new JLabel("Tên Khách Hàng Nhận Hàng:"));
        b18.add(Box.createHorizontalStrut(10));
        b18.add(txtTenKhachHangNhanHang = new JTextField());
        b3.add(b18);

        b19.add(lblSdtKhachHangNhanHang = new JLabel("SDT Khách Hàng Nhận Hàng:"));
        b19.add(Box.createHorizontalStrut(10));
        b19.add(txtSdtKhachHangNhanHang = new JTextField());
        b3.add(b19);

        b17.add(lblDiaChi = new JLabel("Địa Chỉ Giao Hàng:"));
        b17.add(Box.createHorizontalStrut(10));
        b17.add(txtDiaChi = new JTextField());
        b3.add(b17);

        b12.add(lblTongTien = new JLabel("Tổng Tiền Thanh Toán:"));
        b12.add(Box.createHorizontalStrut(10));
        b12.add(txtTongTien = new JTextField());
        b3.add(b12);

        String[] phuongThucThanhToan = {"", "Tiền Mặt", "Quét Thẻ", "Chuyển Khoản Ngân Hàng", "Chuyển Khoản Momo"};
        cmbPhuongThucThanhToan = new JComboBox<String>(phuongThucThanhToan);
        b13.add(lblPhuongThucThanhToan = new JLabel("Phương Thức Thanh Toán:"));
        b13.add(Box.createHorizontalStrut(10));
        b13.add(cmbPhuongThucThanhToan);
        b3.add(b13);

        b3.add(Box.createVerticalStrut(15));

        Box b14 = Box.createHorizontalBox();
        Box b15 = Box.createHorizontalBox();
        Box b16 = Box.createHorizontalBox();

        b14.add(btnQRCode = new JButton("HIỂN THỊ MÃ THANH TOÁN QR CODE"));
        b3.add(b14);

        b15.add(btnProcessPayment = new JButton("TIẾN HÀNH THANH TOÁN"));
        b15.add(Box.createHorizontalStrut(15));
        b15.add(btnPrintBill = new JButton("IN HÓA ĐƠN"));
        b3.add(b15);
        b3.add(Box.createVerticalStrut(50));

        b2.add(Box.createHorizontalStrut(50));
        b.add(Box.createHorizontalStrut(20));
        b.add(b1);
        b.add(b2);
        b.setSize(1400, 400);
        add(b);

        lblMaDonHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblPhuongThucThanhToan.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblTenKhachHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblTongTien.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblSdtKhachHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblDiaChi.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblPhuongThucNhanHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblSdtKhachHangNhanHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());
        lblTenKhachHangNhanHang.setPreferredSize(lblSdtKhachHangNhanHang.getPreferredSize());

        b4.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b5.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b6.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b7.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b8.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b9.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b10.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b11.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b12.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b13.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b14.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b15.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b16.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b17.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b18.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        b19.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        readDatabaseToTable(getOrderResponse);
        LoadDataToTextField(getOrderResponse);

        txtMaDonHang.setEditable(false);
        txtSdtKhachHang.setEditable(false);
        txtTenKhachHang.setEditable(false);
        txtTenKhachHangNhanHang.setEditable(false);
        txtSdtKhachHangNhanHang.setEditable(false);
        txtTongTien.setEditable(false);
        txtDiaChi.setEditable(false);
        btnQRCode.setEnabled(false);
        btnProcessPayment.setEnabled(false);
        btnPrintBill.setEnabled(false);

        btnProcessPayment.addActionListener(this);
        btnPrintBill.addActionListener(this);
        btnQRCode.addActionListener(this);
        cmbPhuongThucNhanHang.addActionListener(this);
        cmbPhuongThucThanhToan.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(cmbPhuongThucNhanHang)) {
            if (cmbPhuongThucNhanHang.getSelectedIndex() != 2) {
                txtTenKhachHangNhanHang.setEditable(false);
                txtSdtKhachHangNhanHang.setEditable(false);
                txtDiaChi.setEditable(false);
                txtTenKhachHangNhanHang.setText("");
                txtSdtKhachHangNhanHang.setText("");
            }
            if (cmbPhuongThucNhanHang.getSelectedIndex() != 0 && cmbPhuongThucThanhToan.getSelectedIndex() != 0) {
                btnProcessPayment.setEnabled(true);
            }
            if (cmbPhuongThucNhanHang.getSelectedIndex() == 2) {
                txtTenKhachHangNhanHang.setText(txtTenKhachHang.getText());
                txtSdtKhachHangNhanHang.setText(txtSdtKhachHang.getText());
                txtTenKhachHangNhanHang.setEditable(true);
                txtSdtKhachHangNhanHang.setEditable(true);
                txtDiaChi.setEditable(true);
            }
        }
        if (o.equals(cmbPhuongThucThanhToan)) {
            if (cmbPhuongThucNhanHang.getSelectedIndex() != 0 && cmbPhuongThucThanhToan.getSelectedIndex() != 0) {
                btnProcessPayment.setEnabled(true);
            }
            if (cmbPhuongThucThanhToan.getSelectedIndex() == 3 || cmbPhuongThucThanhToan.getSelectedIndex() == 4) {
                btnQRCode.setEnabled(true);
            }
        }
        if (o.equals(btnProcessPayment)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (patchProcessDirectOrderPayment()) {
                        JOptionPane.showMessageDialog(null, "Thanh toán đơn hàng mã số " + txtMaDonHang.getText() + " thành công", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        GUI.readAllDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thanh toán đơn hàng mã số " + txtMaDonHang.getText() + " thất bại", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void readDatabaseToTable(GetOrderResponse getOrderResponse) throws IOException {
        emptyTable();
        DecimalFormat df = new DecimalFormat("#,##0");

        List<OrderItemResponseModel> orderItems = getOrderResponse.getOrderItems();
        for (OrderItemResponseModel orderItem : orderItems) {
            ProductResponseModel product = orderItem.getProduct();
            double rowPrice = orderItem.getPrice() * orderItem.getQuantity();
            Product productDb = getProductRequest(product.getId().toString());

            modelSanPham.addRow(new Object[]{product.getId(), product.getName(), product.getType(),
                    product.getBrand(), df.format(orderItem.getPrice()), orderItem.getQuantity(), productDb.getQuantity(), df.format(rowPrice)});
        }
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
        dm.setRowCount(0);
    }

    public Product getProductRequest(String productId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ProductService.getRequest(tableNameProduct, productId);
        return mapper.readValue(rd, Product.class);
    }

    public void LoadDataToTextField(GetOrderResponse order) {
        DecimalFormat df = new DecimalFormat("#,##0");

        txtMaDonHang.setText(order.getId().toString());
        txtTenKhachHang.setText(order.getCustomer().getName());
        txtSdtKhachHang.setText(order.getCustomer().getPhoneNumber());
        txtTongTien.setText(df.format(order.getTotalPrice()));
    }

    public boolean patchProcessDirectOrderPayment() throws JSONException, IOException {
        EmployeeResponseModel employeeResponseModel = GUI.getEmployeeInfo();

        ProcessDirectOrderPayment processDirectOrderPayment = new ProcessDirectOrderPayment();
        processDirectOrderPayment.setOrderId(Long.parseLong(txtMaDonHang.getText()));
        processDirectOrderPayment.setEmployeeId(employeeResponseModel.getId());

        if (cmbPhuongThucNhanHang.getSelectedIndex() == 2) {
            processDirectOrderPayment.setShippingName(txtTenKhachHangNhanHang.getText());
            processDirectOrderPayment.setShippingPhone(txtSdtKhachHangNhanHang.getText());
            processDirectOrderPayment.setShippingAddress(txtDiaChi.getText());
        }

        int paymentMethod = cmbPhuongThucThanhToan.getSelectedIndex();
        if (paymentMethod == 1)
            processDirectOrderPayment.setPaymentMethod(PaymentMethod.CASH);
        else if (paymentMethod == 2)
            processDirectOrderPayment.setPaymentMethod(PaymentMethod.SWIPE_CARD);
        else if (paymentMethod == 3)
            processDirectOrderPayment.setPaymentMethod(PaymentMethod.BANK);
        else if (paymentMethod == 4)
            processDirectOrderPayment.setPaymentMethod(PaymentMethod.MOMO);

        return OrderService.patchProcessDirectOrderPayment(processDirectOrderPayment);
    }
}
