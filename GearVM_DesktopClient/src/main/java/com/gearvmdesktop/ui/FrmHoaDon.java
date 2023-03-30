package com.gearvmdesktop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

public class FrmHoaDon extends JFrame {

    private static DefaultTableModel modelHoaDon;
    private static JTable tableHoaDon;
    private static JComboBox<String> cmbTim;
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

    public static void main(String[] args) throws RemoteException {
        new FrmDangNhap().setVisible(true);
    }

    public JPanel createPanelHoaDon() throws RemoteException {
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

        String[] colHeader = {"Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập Hóa Đơn", "Thành Tiền"};
        modelHoaDon = new DefaultTableModel(colHeader, 0);
        JScrollPane tblscroll = new JScrollPane(tableHoaDon);
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

//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
        };
        tableHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableHoaDon.setGridColor(getBackground());
        tableHoaDon.setRowHeight(tableHoaDon.getRowHeight() + 20);
        tableHoaDon.setSelectionBackground(new Color(255, 255, 128));
        tableHoaDon.setSelectionForeground(Color.BLACK);
        tableHoaDon.setPreferredScrollableViewportSize(new Dimension(1200, 590));
        JTableHeader tableHeader = tableHoaDon.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        tblscroll.add(new JScrollPane(tableHoaDon));
        tblscroll.setViewportView(tableHoaDon);
        tableHoaDon.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(p);
        p.add(pNorth, BorderLayout.NORTH);
        pNorth.add(b);
        b.add(bThongtin);

        b.add(btim);

        String[] tim = {"Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập Hóa Đơn", "Thành Tiền"};
        cmbChon = new JComboBox<String>(tim);
        btim.add(cmbChon);
        btim.add(Box.createHorizontalStrut(10));
        cmbTim = new JComboBox<String>();
        cmbTim.setEditable(true);
        AutoCompleteDecorator.decorate(cmbTim);
        cmbTim.setMaximumRowCount(10);
        cmbChon.setSize(200, cmbTim.getPreferredSize().height);
        btim.add(cmbTim);
        btim.add(Box.createHorizontalStrut(10));
        btnTim = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
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
        btnTim1 = new JButton("THỐNG KÊ THEO KHOẢNG NGÀY", new ImageIcon("image/timkiem.png"));
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
        cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim1.setFont(new Font("Tahoma", Font.BOLD, 12));

        p.add(tblscroll, BorderLayout.CENTER);
        p.add(Box.createHorizontalStrut(50), BorderLayout.SOUTH);
        p.add(btnHoaDon = new JButton("LỌC THEO THỨ TỰ HÓA ĐƠN", new ImageIcon("image/hoadon.png")));
        btnHoaDon.setBackground(new Color(0, 148, 224));
        btnHoaDon.setForeground(Color.WHITE);
        btnHoaDon.setFocusPainted(false);
        p.add(Box.createHorizontalStrut(100));
        p.add(lblDoanhThu = new JLabel("TỔNG DOANH THU: "));
        p.add(Box.createHorizontalStrut(20));
        p.add(txtDoanhThu = new JLabel(), BorderLayout.SOUTH);
        p.add(Box.createHorizontalStrut(200));
        p.add(btnSort = new JButton("LỌC THEO THÀNH TIỀN", new ImageIcon("image/thongke.png")));
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
        return p;
    }


}
