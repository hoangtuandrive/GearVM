package com.gearvmstore.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmstore.model.OrderStatus;
import com.gearvmstore.model.Product;
import com.gearvmstore.model.dto.order.UpdateOrderStatusAndEmployee;
import com.gearvmstore.model.response.EmployeeResponseModel;
import com.gearvmstore.model.response.GetOrderResponse;
import com.gearvmstore.model.response.OrderItemResponseModel;
import com.gearvmstore.model.response.ProductResponseModel;
import com.gearvmstore.service.OrderService;
import com.gearvmstore.service.ProductService;

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
import java.time.format.DateTimeFormatter;
import java.util.List;


public class FrmChiTietDonHang extends JFrame implements ActionListener {
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
    private final JLabel lblMaThanhToan;
    private final JTextField txtMaThanhToan;
    private final JLabel lblNgayLapDonHang;
    private final JTextField txtNgayLapDonHang;
    private final JLabel lblNgaySuaDonHang;
    private final JTextField txtNgaySuaDonHang;
    private final JLabel lblMaNhanVien;
    private final JTextField txtMaNhanVien;
    private final JLabel lblTenNhanVien;
    private final JTextField txtTenNhanVien;
    private final JLabel lblTongTien;
    private final JTextField txtTongTien;
    private final JComboBox<String> cmbTrangThai;
    private final JLabel lblTrangThai;
    private final JButton btnXacNhan;
    private final JButton btnTuChoi;
    private final JButton btnThanhCong;
    private final JButton btnThatBai;
    private final JButton btnXemThanhToan;
    private final JButton btnThayDoiTrangThai;

    public FrmChiTietDonHang(GetOrderResponse getOrderResponse) throws IOException {
        super("Chi Tiết Đơn Hàng");

        FlatLightLaf.setup();
        setSize(1400, 800);
        setResizable(true);
        setLocationRelativeTo(null);

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

        b2.add(Box.createHorizontalStrut(100));
        b2.add(b3);

        b4.add(lblMaDonHang = new JLabel("Mã Đơn Hàng:"));
        b4.add(Box.createHorizontalStrut(10));
        b4.add(txtMaDonHang = new JTextField());
        b3.add(b4);

        b5.add(lblTenKhachHang = new JLabel("Tên Khách Hàng:"));
        b5.add(Box.createHorizontalStrut(10));
        b5.add(txtTenKhachHang = new JTextField());
        b3.add(b5);

        b6.add(lblSdtKhachHang = new JLabel("Số Điện Thoại:"));
        b6.add(Box.createHorizontalStrut(10));
        b6.add(txtSdtKhachHang = new JTextField());
        b3.add(b6);

        b7.add(lblMaThanhToan = new JLabel("Mã Thanh Toán:"));
        b7.add(Box.createHorizontalStrut(10));
        b7.add(txtMaThanhToan = new JTextField());
        b3.add(b7);

        b8.add(lblNgayLapDonHang = new JLabel("Ngày Lập Đơn Hàng:"));
        b8.add(Box.createHorizontalStrut(10));
        b8.add(txtNgayLapDonHang = new JTextField());
        b3.add(b8);

        b9.add(lblNgaySuaDonHang = new JLabel("Ngày Sửa Đơn Hàng:"));
        b9.add(Box.createHorizontalStrut(10));
        b9.add(txtNgaySuaDonHang = new JTextField());
        b3.add(b9);

        b10.add(lblMaNhanVien = new JLabel("Mã Nhân Viên Phụ Trách:"));
        b10.add(Box.createHorizontalStrut(10));
        b10.add(txtMaNhanVien = new JTextField());
        b3.add(b10);

        b11.add(lblTenNhanVien = new JLabel("Tên Nhân Viên Phụ Trách:"));
        b11.add(Box.createHorizontalStrut(10));
        b11.add(txtTenNhanVien = new JTextField());
        b3.add(b11);

        b12.add(lblTongTien = new JLabel("Tổng Tiền Thanh Toán:"));
        b12.add(Box.createHorizontalStrut(10));
        b12.add(txtTongTien = new JTextField());
        b3.add(b12);

        String[] trangThai = {"Đang chờ thanh toán", "Đang chờ xác nhận", "Đang giao hàng", "Giao hàng thành công", "Giao hàng thất bại", "Đơn hàng bị hủy"};
        cmbTrangThai = new JComboBox<String>(trangThai);
        b13.add(lblTrangThai = new JLabel("Trạng Thái Đơn Hàng:"));
        b13.add(Box.createHorizontalStrut(10));
        b13.add(cmbTrangThai);
        b3.add(b13);

        b3.add(Box.createVerticalStrut(20));

        Box b14 = Box.createHorizontalBox();
        Box b15 = Box.createHorizontalBox();
        Box b16 = Box.createHorizontalBox();

        b14.add(btnXacNhan = new JButton("XÁC NHẬN GIAO HÀNG"));
        b14.add(Box.createHorizontalStrut(15));
        b14.add(btnTuChoi = new JButton("TỪ CHỐI GIAO HÀNG"));
        b3.add(b14);

        b15.add(btnThanhCong = new JButton("GIAO HÀNG THÀNH CÔNG"));
        b15.add(Box.createHorizontalStrut(15));
        b15.add(btnThatBai = new JButton("GIAO HÀNG THẤT BẠI"));
        b3.add(b15);

        b16.add(btnThayDoiTrangThai = new JButton("THAY ĐỔI TRẠNG THÁI ĐƠN HÀNG"));
        b16.add(Box.createHorizontalStrut(15));
        b16.add(btnXemThanhToan = new JButton("XEM CHI TIẾT THANH TOÁN"));
        b3.add(b16);

        b2.add(Box.createHorizontalStrut(50));
        b.add(Box.createHorizontalStrut(20));
        b.add(b1);
        b.add(b2);
        b.setSize(1400, 400);
        add(b);

        lblMaDonHang.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblTrangThai.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblNgayLapDonHang.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblMaNhanVien.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblNgaySuaDonHang.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblMaThanhToan.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblTenKhachHang.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblTongTien.setPreferredSize(lblTenNhanVien.getPreferredSize());
        lblSdtKhachHang.setPreferredSize(lblTenNhanVien.getPreferredSize());

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

        readDatabaseToTable(getOrderResponse);
        getDataToTextField(getOrderResponse);

        btnThayDoiTrangThai.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnThayDoiTrangThai)){
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if(patchOrderStatusAndEmployee()){
                        JOptionPane.showMessageDialog(this, "Sửa đơn hàng mã số " + txtMaDonHang.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        refreshTextField();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Sửa đơn hàng mã số " + txtMaDonHang.getText() + " thất bại!", "Thất bại",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
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
            ProductResponseModel product = orderItem.getProductId();
            double rowPrice = orderItem.getPrice() * orderItem.getQuantity();
            Product productDb = getRequest(product.getId().toString());

            modelSanPham.addRow(new Object[]{product.getId(), product.getName(), product.getType(),
                    product.getBrand(), df.format(orderItem.getPrice()), orderItem.getQuantity(), productDb.getQuantity(), df.format(rowPrice)});
        }
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
        dm.setRowCount(0);
    }

    public Product getRequest(String productId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ProductService.getRequest(tableNameProduct, productId);
        return mapper.readValue(rd, Product.class);
    }

    public void refreshTextField() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        BufferedReader rd = OrderService.getRequest(tableNameOrder, txtMaDonHang.getText());
        GetOrderResponse order = mapper.readValue(rd, GetOrderResponse.class);

        if(order.getEmployeeId() != null) {
            txtMaNhanVien.setText(order.getEmployeeId().getId().toString());
            txtTenNhanVien.setText(order.getEmployeeId().getName());
        }
    }

    public void getDataToTextField(GetOrderResponse order){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("k:mm dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#,##0");

        if (order.getOrderStatus() == OrderStatus.PAYMENT_PENDING) cmbTrangThai.setSelectedIndex(0);
        else if (order.getOrderStatus() == OrderStatus.PAYMENT_DONE) cmbTrangThai.setSelectedIndex(1);
        else if (order.getOrderStatus() == OrderStatus.SHIPPING) cmbTrangThai.setSelectedIndex(2);
        else if (order.getOrderStatus() == OrderStatus.SHIP_SUCCESS) cmbTrangThai.setSelectedIndex(3);
        else if (order.getOrderStatus() == OrderStatus.SHIP_FAIL) cmbTrangThai.setSelectedIndex(4);
        else if (order.getOrderStatus() == OrderStatus.CANCELLED) cmbTrangThai.setSelectedIndex(5);

        txtMaDonHang.setText(order.getId().toString());
        txtTenKhachHang.setText(order.getCustomerId().getName());
        txtSdtKhachHang.setText(order.getCustomerId().getPhoneNumber());
        txtMaThanhToan.setText(order.getPaymentId());
        txtNgayLapDonHang.setText(dateFormat.format(order.getCreatedDate()));
        txtNgaySuaDonHang.setText(dateFormat.format(order.getUpdatedDate()));
        if(order.getEmployeeId() != null){
            txtMaNhanVien.setText(order.getEmployeeId().getId().toString());
            txtTenNhanVien.setText(order.getEmployeeId().getName());
        }
        txtTongTien.setText(df.format(order.getTotalPrice()));
    }

    // TODO
    public boolean patchOrderStatus(){
        return true;
    }

    public boolean patchOrderStatusAndEmployee() throws IOException {
        String orderId = txtMaDonHang.getText();
        String employeeId = txtMaNhanVien.getText();
        UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee = new UpdateOrderStatusAndEmployee();

        if(cmbTrangThai.getSelectedIndex() == 0) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.PAYMENT_PENDING);
        else if(cmbTrangThai.getSelectedIndex() == 1) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.PAYMENT_DONE);
        else if(cmbTrangThai.getSelectedIndex() == 2) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.SHIPPING);
        else if(cmbTrangThai.getSelectedIndex() == 3) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.SHIP_SUCCESS);
        else if(cmbTrangThai.getSelectedIndex() == 4) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.SHIP_FAIL);
        else if(cmbTrangThai.getSelectedIndex() == 5) updateOrderStatusAndEmployee.setOrderStatus(OrderStatus.CANCELLED);

        if(!employeeId.equals("")){
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            employeeResponseModel.setId(Long.parseLong(employeeId));
            employeeResponseModel.setName(txtTenNhanVien.getText());
            updateOrderStatusAndEmployee.setEmployeeId(employeeResponseModel);
        }
        return OrderService.patchOrderStatusAndEmployee(orderId, updateOrderStatusAndEmployee);
    }
}
