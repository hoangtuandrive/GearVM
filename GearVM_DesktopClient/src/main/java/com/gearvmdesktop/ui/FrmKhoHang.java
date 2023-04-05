package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.PurchaseService;
import com.gearvmstore.GearVM.model.Purchase;
import com.gearvmstore.GearVM.model.dto.purchase.CreatePurchase;
import com.gearvmstore.GearVM.model.response.EmployeeResponseModel;
import com.gearvmstore.GearVM.model.response.GetPurchaseListResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FrmKhoHang extends JFrame implements ActionListener, MouseListener {
    private static final String tableName = "purchases/";
    private static JComboBox<String> cmbTim;
    private static JTable tableKhoHang;
    private static DefaultTableModel modelKhoHang;
    private JComboBox<String> cmbChon;
    private JButton btnTim;
    private JButton btnXoa;
    private JButton btnNhap;
    private Label lblTenNhanVien;
    private Label lblMaNhanVien;
    private Label lblThoiGianNhap;
    private Label lblMaSanPham;
    private Label lblTenSanPham;
    private Label lblMaNhapHang;
    private JPanel pnChucNang;
    private JPanel pnExcel;
    private JPanel pnThongTin;
    private JScrollPane pntblHangHoa;
    private static JTextField txtTenNhanVien;
    private static JTextField txtMaNhanVien;
    private static JTextField txtThoiGianNhap;
    private static JTextField txtMaSanPham;
    private static JTextField txtTenSanPham;
    private JPanel pnlTimKiem;
    private static JTextField txtMaNhapHang;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnSave;
    private JButton btnCancel;
    private Label lblSoLuong;
    private static JTextField txtSoLuong;
    private Label lblGiaNhap;
    private static JTextField txtGiaNhap;
    private JButton btnChon;
    private Label lblChiTieu;
    private static JTextField txtChiTieu;

    public JPanel createPanelKhoHang() throws IOException {
        FlatLightLaf.setup();
        pntblHangHoa = new JScrollPane();
        tableKhoHang = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblMaNhapHang = new Label();
        txtMaNhapHang = new JTextField();
        lblMaSanPham = new Label();
        txtMaSanPham = new JTextField();
        lblTenSanPham = new Label();
        txtTenSanPham = new JTextField();
        lblMaNhanVien = new Label();
        txtMaNhanVien = new JTextField();
        lblTenNhanVien = new Label();
        txtTenNhanVien = new JTextField();
        lblThoiGianNhap = new Label();
        txtThoiGianNhap = new JTextField();
        lblSoLuong = new Label();
        txtSoLuong = new JTextField();
        lblGiaNhap = new Label();
        txtGiaNhap = new JTextField();
        lblChiTieu = new Label();
        txtChiTieu = new JTextField();

        pnChucNang = new JPanel();
        btnChon = new JButton();
        btnNhap = new JButton();
        btnXoa = new JButton();

        pnExcel = new JPanel();
        btnImport = new JButton();
        btnExport = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String[] header = {"Mã Nhập Hàng", "Mã NV", "Tên Nhân Viên Nhập", "Thời Gian Nhập", "Mã SP", "Tên Sản Phẩm", "Giá Nhập", "Số Lượng", "Chi Tiêu"};
        modelKhoHang = new DefaultTableModel(header, 0);
        tableKhoHang = new JTable(modelKhoHang) {
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
        tableKhoHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableKhoHang.setGridColor(getBackground());
        tableKhoHang.setRowHeight(tableKhoHang.getRowHeight() + 20);
        tableKhoHang.setSelectionBackground(new Color(255, 255, 128));
        tableKhoHang.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableKhoHang.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        tableKhoHang.setColumnSelectionAllowed(false);
        tableKhoHang.setName("tblThongTinNhanVien"); // NOI18N
        pntblHangHoa.setViewportView(tableKhoHang);
        tableKhoHang.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
        pnThongTin.setToolTipText("Info of selected table object");

        lblMaNhapHang.setText("Mã Nhập Hàng:");
        lblMaSanPham.setText("Mã Sản Phẩm:");
        lblTenSanPham.setText("Tên Sản Phẩm:");
        lblMaNhanVien.setText("Mã Nhân Viên:");
        lblTenNhanVien.setText("Tên Nhân Viên:");
        lblThoiGianNhap.setText("Thời Gian Nhập:");
        lblGiaNhap.setText("Giá Nhập Sản Phẩm:");
        lblSoLuong.setText("Số Lượng Nhập:");
        lblChiTieu.setText("Chi Tiêu:");

        GroupLayout pnThongTinLayout = new GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblMaNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaNhapHang, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGiaNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblChiTieu, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblThoiGianNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaNhapHang,
                                        GroupLayout.PREFERRED_SIZE, 169,
                                        GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtThoiGianNhap, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaNhap, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoLuong, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenNhanVien, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaSanPham, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenSanPham, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtChiTieu, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaNhanVien, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnThongTinLayout.setVerticalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaNhapHang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaNhapHang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtThoiGianNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblThoiGianNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiaNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGiaNhap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtChiTieu, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblChiTieu, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE)));

        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng:"));
        pnExcel.setBorder(BorderFactory.createTitledBorder("Xử lý excel:"));

        btnChon.setText("CHỌN SP");
        btnNhap.setText("NHẬP");
        btnXoa.setText("XÓA");
        btnSave.setText("LƯU");
        btnCancel.setText("HỦY");
        btnImport.setText("NHẬP FILE");
        btnExport.setText("XUẤT FILE");


        // Chức năng button
        btnChon.setBackground(new Color(0, 148, 224));
        btnChon.setForeground(Color.WHITE);
        btnChon.setFocusPainted(false);
        btnNhap.setBackground(new Color(0, 148, 224));
        btnNhap.setForeground(Color.WHITE);
        btnNhap.setFocusPainted(false);
        btnXoa.setBackground(new Color(0, 148, 224));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFocusPainted(false);

        // Excel button
        btnSave.setBackground(new Color(0, 148, 224));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnCancel.setBackground(new Color(0, 148, 224));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnImport.setBackground(new Color(0, 148, 224));
        btnImport.setForeground(Color.WHITE);
        btnImport.setFocusPainted(false);
        btnExport.setBackground(new Color(0, 148, 224));
        btnExport.setForeground(Color.WHITE);
        btnExport.setFocusPainted(false);

        GroupLayout pnChucNangLayout = new GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnChon)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnNhap)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(48, 48, 48)));

        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnChon).addComponent(btnNhap).addComponent(btnXoa))
                        .addGap(5)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15)));

        GroupLayout pnExcelLayout = new GroupLayout(pnExcel);
        pnExcel.setLayout(pnExcelLayout);
        pnExcelLayout.setHorizontalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(50)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnImport)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnExport)
                        .addGap(50))
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(40)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnSave)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnCancel)
                        .addGap(70)));
        pnExcelLayout.setVerticalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnExcelLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnExcelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnImport).addComponent(btnExport))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)
                        .addGroup(pnExcelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSave).addComponent(btnCancel))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15)));

        pnThongTin.setBackground(new Color(219, 243, 255));
        pnChucNang.setBackground(new Color(219, 243, 255));
        pnExcel.setBackground(new Color(219, 243, 255));

        Box b = Box.createHorizontalBox();
        String[] tim = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
        cmbChon = new JComboBox<String>(tim);
        cmbTim = new JComboBox<String>();
        cmbTim.setEditable(true);
        AutoCompleteDecorator.decorate(cmbTim);
        cmbTim.setMaximumRowCount(10);
        cmbChon.setSize(20, cmbTim.getPreferredSize().height);
        btnTim = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);

        b.add(cmbChon);
        b.add(Box.createHorizontalStrut(10));
        b.add(cmbTim);
        b.add(Box.createHorizontalStrut(10));
        b.add(btnTim);
        b.add(Box.createHorizontalStrut(30));
        pnlTimKiem.add(b);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        panel.add(getContentPane());
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pntblHangHoa, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                                .addComponent(pnlTimKiem))
                        .addContainerGap(20, 20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnThongTin, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnExcel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                ))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addComponent(pntblHangHoa, GroupLayout.PREFERRED_SIZE, 670,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pnlTimKiem, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                .addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
                                        .addComponent(pnThongTin, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 20, 20)
                                        .addComponent(pnExcel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 27, Short.MAX_VALUE)))
                        .addContainerGap()));

        pack();

        pntblHangHoa.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "LỊCH SỬ KHO HÀNG: "));
        lblMaNhapHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaNhapHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblThoiGianNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtThoiGianNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblChiTieu.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtChiTieu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableKhoHang.setFont(new Font("Tahoma", Font.PLAIN, 14));

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        txtMaNhapHang.setEditable(false);
        txtMaNhanVien.setEditable(false);
        txtTenNhanVien.setEditable(false);
        txtThoiGianNhap.setEditable(false);
        txtMaSanPham.setEditable(false);
        txtTenSanPham.setEditable(false);
        txtChiTieu.setEditable(false);

        tableKhoHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableKhoHang.setDefaultEditor(Object.class, null);
        tableKhoHang.getTableHeader().setReorderingAllowed(false);

        btnChon.addActionListener(this);
        btnNhap.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnExport.addActionListener(this);
        btnImport.addActionListener(this);
        tableKhoHang.addMouseListener(this);

        readDatabaseToTable();
        GUI.disableWarning();

        return panel;
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnChon)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    openFrameSelectProduct();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnNhap)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (postRequest()) {
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        GUI.readAllDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnXoa)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (deleteRequest()) {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaNhapHang.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaNhapHang.getText() + " thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnExport)) {
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setCurrentDirectory(new File(System.getProperty("user.dir")));
            //filter the files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel(.xls)", ".xls");
            fileDialog.setAcceptAllFileFilterUsed(false);
            fileDialog.addChoosableFileFilter(filter);
            int result = fileDialog.showSaveDialog(null);
            //if the user click on save in Jfilechooser
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileDialog.getSelectedFile();
                String filePath = file.getAbsolutePath();
                if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))) {
                    filePath += ".xls";
                }
                if (exportExcel(filePath))
                    JOptionPane.showMessageDialog(null, "Ghi file thành công!!", "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Ghi file thất bại!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableKhoHang.getSelectedRow();
        txtMaNhapHang.setText(modelKhoHang.getValueAt(row, 0).toString().trim());
        txtMaNhanVien.setText(modelKhoHang.getValueAt(row, 1).toString().trim());
        txtTenNhanVien.setText(modelKhoHang.getValueAt(row, 2).toString().trim());
        txtThoiGianNhap.setText(modelKhoHang.getValueAt(row, 3).toString().trim());
        txtMaSanPham.setText(modelKhoHang.getValueAt(row, 4).toString().trim());
        txtTenSanPham.setText(modelKhoHang.getValueAt(row, 5).toString().trim());
        txtGiaNhap.setText(modelKhoHang.getValueAt(row, 6).toString().trim());
        txtSoLuong.setText(modelKhoHang.getValueAt(row, 7).toString().trim());
        String tien[] = modelKhoHang.getValueAt(row, 4).toString().split(",");
        String chiTieu = "";
        for (int i = 0; i < tien.length; i++)
            chiTieu += tien[i];
        txtChiTieu.setText(chiTieu);
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

        BufferedReader rd = PurchaseService.getAllRequest(tableName);
        List<GetPurchaseListResponse> listPurchase = Arrays.asList(mapper.readValue(rd, GetPurchaseListResponse[].class));

        DecimalFormat df = new DecimalFormat("#,##0");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("k:mm dd-MM-yyyy");

        for (GetPurchaseListResponse p : listPurchase) {
            double chiTieu = p.getPrice() * p.getQuantity();
            modelKhoHang.addRow(new Object[]{p.getId(), p.getEmployeeResponseModel().getId(),
                    p.getEmployeeResponseModel().getName(), dateFormat.format(p.getCreatedDate()),
                    p.getProductResponseModel().getId(), p.getProductResponseModel().getName(),
                    df.format(p.getPrice()), p.getQuantity(), df.format(chiTieu)});
        }
    }

    private static void emptyTextField() {
        txtMaNhapHang.setText(null);
        txtMaNhanVien.setText(null);
        txtTenNhanVien.setText(null);
        txtThoiGianNhap.setText(null);
        txtMaSanPham.setText(null);
        txtTenSanPham.setText(null);
        txtGiaNhap.setText(null);
        txtSoLuong.setText(null);
        txtChiTieu.setText(null);
    }

    public boolean postRequest() throws IOException, JSONException {
        CreatePurchase p = new CreatePurchase();
        p.setEmployeeId(Long.parseLong(txtMaNhanVien.getText()));
        p.setProductId(Long.parseLong(txtMaSanPham.getText()));
        p.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        p.setPrice(Double.parseDouble(txtGiaNhap.getText()));
        return PurchaseService.postRequest(p);
    }

    public boolean deleteRequest() throws IOException {
        Purchase p = new Purchase();
        p.setId(Long.parseLong(txtMaNhapHang.getText()));
        return PurchaseService.deleteRequest(p);
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableKhoHang.getModel();
        dm.setRowCount(0);
    }

    public void openFrameSelectProduct() throws IOException {
        new FrmChonSanPham();
    }

    public static void setTextFieldAfterSelectingProduct(String productId, String productName) {
        emptyTextField();
        EmployeeResponseModel employeeResponseModel = GUI.getEmployeeInfo();
        txtMaNhanVien.setText(employeeResponseModel.getId().toString());
        txtTenNhanVien.setText(employeeResponseModel.getName());
        txtMaSanPham.setText(productId);
        txtTenSanPham.setText(productName);
    }

    public boolean exportExcel(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("DANH SÁCH SẢN PHẨM");

            HSSFRow row;
            HSSFCell cell;

            // Dòng 1 tên
            cell = worksheet.createRow(1).createCell(1);

            HSSFFont newFont = cell.getSheet().getWorkbook().createFont();
            newFont.setBold(true);
            newFont.setFontHeightInPoints((short) 13);
            CellStyle styleTenDanhSach = worksheet.getWorkbook().createCellStyle();
            styleTenDanhSach.setAlignment(HorizontalAlignment.CENTER);
            styleTenDanhSach.setFont(newFont);

            cell.setCellValue("DANH SÁCH SẢN PHẨM");
            cell.setCellStyle(styleTenDanhSach);

            String[] header = {"STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
            worksheet.addMergedRegion(new CellRangeAddress(1, 1, 1, header.length));

            // Dòng 2 người lập
            row = worksheet.createRow(2);

            cell = row.createCell(1);
            cell.setCellValue("Người lập:");
            cell = row.createCell(2);

            cell.setCellValue(GUI.getEmployeeInfo().getName());
            worksheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));

            // Dòng 3 ngày lập
            row = worksheet.createRow(3);
            cell = row.createCell(1);
            cell.setCellValue("Ngày lập:");
            cell = row.createCell(2);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            cell.setCellValue(df.format(new Date()));
            worksheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

            // Dòng 4 tên các cột
            row = worksheet.createRow(4);

            HSSFFont fontHeader = cell.getSheet().getWorkbook().createFont();
            fontHeader.setBold(true);

            CellStyle styleHeader = worksheet.getWorkbook().createCellStyle();
            styleHeader.setFont(fontHeader);
            styleHeader.setBorderBottom(BorderStyle.THIN);
            styleHeader.setBorderTop(BorderStyle.THIN);
            styleHeader.setBorderLeft(BorderStyle.THIN);
            styleHeader.setBorderRight(BorderStyle.THIN);
            styleHeader.setAlignment(HorizontalAlignment.CENTER);

            styleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i + 1);
                cell.setCellValue(header[i]);
                cell.setCellStyle(styleHeader);
            }

            if (tableKhoHang.getRowCount() == 0) {
                return false;
            }

            HSSFFont fontRow = cell.getSheet().getWorkbook().createFont();
            fontRow.setBold(false);

            CellStyle styleRow = worksheet.getWorkbook().createCellStyle();
            styleRow.setFont(fontRow);
            styleRow.setBorderBottom(BorderStyle.THIN);
            styleRow.setBorderTop(BorderStyle.THIN);
            styleRow.setBorderLeft(BorderStyle.THIN);
            styleRow.setBorderRight(BorderStyle.THIN);

            // Ghi dữ liệu vào bảng
            int STT = 0;
            for (int i = 0; i < tableKhoHang.getRowCount(); i++) {
                row = worksheet.createRow(5 + i);
                for (int j = 0; j < header.length; j++) {
                    cell = row.createCell(j + 1);
                    if (STT == i) {
                        cell.setCellValue(STT + 1);
                        STT++;
                    } else {
                        if (tableKhoHang.getValueAt(i, j - 1) != null) {
                            if (j == header.length - 2) {
                                String luong[] = tableKhoHang.getValueAt(i, j - 1).toString().split(",");
                                String tienLuong = "";
                                for (int t = 0; t < luong.length; t++)
                                    tienLuong += luong[t];
                                cell.setCellValue(Double.parseDouble(tienLuong));
                            } else
                                cell.setCellValue(tableKhoHang.getValueAt(i, j - 1).toString().trim());
                        }
                    }
                    cell.setCellStyle(styleRow);
                }
            }

            for (int i = 1; i < header.length + 1; i++) {
                worksheet.autoSizeColumn(i);
            }

            workbook.write(fileOut);
            workbook.close();
            fileOut.flush();
            fileOut.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
