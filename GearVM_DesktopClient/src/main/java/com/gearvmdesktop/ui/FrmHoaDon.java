package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.OrderService;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FrmHoaDon extends JFrame implements ActionListener, MouseListener {
    private static final String tableName = "orders/";
    private static DefaultTableModel modelHoaDon;
    private static JTable tableHoaDon;
    private static JLabel txtDoanhThu;
    private JTextField txtTim;
    private JLabel lblTim;
    private JButton btnTim;
    private JPanel p;
    private JPanel pNorth;
    private JLabel lblDoanhThu;
    private JComboBox<String> cmbChon;
    private JLabel lblNgay;
    private JDateChooser txtNgayStart;
    private JDateChooser txtNgayEnd;
    private JButton btnTim1;
    private JButton btnSort;
    private boolean sort;
    private JButton btnHoaDon;
    private boolean sortHoaDon;

    public static void main(String[] args) {
        new FrmDangNhap().setVisible(true);
    }

    public JPanel createPanelHoaDon() throws IOException {
        // TODO Auto-generated constructor stub
        FlatLightLaf.setup();
        setTitle("FrmHoaDon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        p = new JPanel();
        pNorth = new JPanel();

        Box b = Box.createVerticalBox();
        Box bThongtin = Box.createHorizontalBox();
        Box btim = Box.createHorizontalBox();

        String[] colHeader = {"Mã Đơn Hàng", "Tên Khách Hàng", "SĐT Khách Hàng", "Trạng Thái", "Ngày Lập Đơn Hàng", "Thành Tiền"};
        modelHoaDon = new DefaultTableModel(colHeader, 0);
        tableHoaDon = new JTable(modelHoaDon) {
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
        tableHoaDon.setGridColor(getBackground());
        tableHoaDon.setRowHeight(tableHoaDon.getRowHeight() + 20);
        tableHoaDon.setSelectionBackground(new Color(255, 255, 128));
        tableHoaDon.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableHoaDon.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        JScrollPane tblscroll = new JScrollPane(tableHoaDon);
        tableHoaDon.setPreferredScrollableViewportSize(new Dimension(1200, 600));
        b.add(tblscroll);
        tblscroll.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH ĐƠN HÀNG: "));

        tableHoaDon.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableHoaDon.setDefaultEditor(Object.class, null);

        add(p);
        p.add(pNorth, BorderLayout.NORTH);
        pNorth.add(b);
        b.add(bThongtin);

        b.add(btim);

        ClassLoader classLoader = getClass().getClassLoader();

        URL iconTim = classLoader.getResource("assets/timkiem.png");
        URL iconLocTheoThuTu = classLoader.getResource("assets/locthutu.png");
        URL iconTheoTien = classLoader.getResource("assets/loctheogia.png");
        URL iconLocTheoKhoangTG = classLoader.getResource("assets/loc.png");


        String[] tim = {"Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập Hóa Đơn", "Thành Tiền"};
        cmbChon = new JComboBox<String>(tim);
        btim.add(cmbChon);
        btim.add(Box.createHorizontalStrut(10));
        txtTim = new JTextField();
        cmbChon.setSize(200, txtTim.getPreferredSize().height);
        btim.add(txtTim);
        btim.add(Box.createHorizontalStrut(10));
        btnTim = new JButton("TÌM KIẾM", new ImageIcon(iconTim));
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);
        btim.add(btnTim);
        btim.add(Box.createHorizontalStrut(100));
        btim.add(lblNgay = new JLabel("Nhập khoảng ngày để thống kê: "));
        btim.add(Box.createHorizontalStrut(10));
        btim.add(txtNgayStart = new JDateChooser());
        btim.add(Box.createHorizontalStrut(10));
        btim.add(txtNgayEnd = new JDateChooser());
        btnTim1 = new JButton("THỐNG KÊ THEO KHOẢNG NGÀY", new ImageIcon(iconLocTheoKhoangTG));
        btnTim1.setBackground(new Color(0, 148, 224));
        btnTim1.setForeground(Color.WHITE);
        btnTim1.setFocusPainted(false);
        btim.add(Box.createHorizontalStrut(10));
        btim.add(btnTim1);
        txtNgayStart.setDateFormatString("  yyyy-MM-dd");
        txtNgayStart.setDate(new Date(2022 - 1900, 1 - 1, 1));
        txtNgayEnd.setDateFormatString("  yyyy-MM-dd");
        txtNgayEnd.setDate(new Date(2022 - 1900, 1 - 1, 1));

//		btnTim.setBorder(new EmptyBorder(new Insets(0, 0, 0, 100)));

        tblscroll.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH HÓA ĐƠN: "));
        txtTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim1.setFont(new Font("Tahoma", Font.BOLD, 12));

        p.add(tblscroll, BorderLayout.CENTER);
        p.add(Box.createHorizontalStrut(50), BorderLayout.SOUTH);
        p.add(btnHoaDon = new JButton("LỌC THEO THỨ TỰ HÓA ĐƠN", new ImageIcon(iconLocTheoThuTu)));
        btnHoaDon.setBackground(new Color(0, 148, 224));
        btnHoaDon.setForeground(Color.WHITE);
        btnHoaDon.setFocusPainted(false);
        p.add(Box.createHorizontalStrut(100));
        p.add(lblDoanhThu = new JLabel("TỔNG DOANH THU: "));
        p.add(Box.createHorizontalStrut(20));
        p.add(txtDoanhThu = new JLabel(), BorderLayout.SOUTH);
        p.add(Box.createHorizontalStrut(200));
        p.add(btnSort = new JButton("LỌC THEO THÀNH TIỀN", new ImageIcon(iconTheoTien)));
        btnSort.setBackground(new Color(0, 148, 224));
        btnSort.setForeground(Color.WHITE);
        btnSort.setFocusPainted(false);
        lblDoanhThu.setBorder(new EmptyBorder(new Insets(20, 0, 0, 0)));
        txtDoanhThu.setBorder(new EmptyBorder(new Insets(20, 0, 0, 0)));
        tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHoaDon.setDefaultEditor(Object.class, null);
        tableHoaDon.getTableHeader().setReorderingAllowed(false);

        lblNgay.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSort.setFont(new Font("Tahoma", Font.BOLD, 14));
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(tableHoaDon.getModel());
        tableHoaDon.setRowSorter(sorter1);

        List<RowSorter.SortKey> sortKeyss = new ArrayList<>(50);
        sortKeyss.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter1.setSortKeys(sortKeyss);
        sortHoaDon = true;

        tableHoaDon.addMouseListener(this);

        readDatabaseToTable();
        return p;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableHoaDon.getSelectedRow();
        if (e.getClickCount() == 2 && tableHoaDon.getSelectedRow() != -1) {
            try {
                GetOrderResponse getOrderResponse = getRequest(tableHoaDon.getValueAt(row, 0).toString().trim());
                new FrmChiTietDonHang(getOrderResponse);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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

    public static void readDatabaseToTable() throws IOException {
        emptyTable();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        BufferedReader rd = OrderService.getDeliveredOrderList();
        List<GetOrderListResponse> getOrderListResponse = Arrays.asList(mapper.readValue(rd, GetOrderListResponse[].class));

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("k:mm dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#,##0");


        for (GetOrderListResponse o : getOrderListResponse) {
            String orderStatusString = "";
            if (o.getOrderStatus() == OrderStatus.PAYMENT_PENDING) orderStatusString = "Đang chờ thanh toán";
            else if (o.getOrderStatus() == OrderStatus.PAYMENT_DONE) orderStatusString = "Đang chờ xác nhận";
            else if (o.getOrderStatus() == OrderStatus.SHIPPING) orderStatusString = "Đang giao hàng";
            else if (o.getOrderStatus() == OrderStatus.SHIP_SUCCESS) orderStatusString = "Giao hàng thành công";
            else if (o.getOrderStatus() == OrderStatus.SHIP_FAIL) orderStatusString = "Giao hàng thất bại";
            else if (o.getOrderStatus() == OrderStatus.REJECTED) orderStatusString = "Đơn hàng bị từ chối";

            modelHoaDon.addRow(new Object[]{
                    o.getId(), o.getCustomer().getName(), o.getCustomer().getPhoneNumber(),
                    orderStatusString, dateFormat.format(o.getCreatedDate()), df.format(o.getTotalPrice())
            });
        }
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableHoaDon.getModel();
        dm.setRowCount(0);
    }

    public GetOrderResponse getRequest(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        BufferedReader rd = OrderService.getRequest(tableName, id);
        return mapper.readValue(rd, GetOrderResponse.class);
    }
}
