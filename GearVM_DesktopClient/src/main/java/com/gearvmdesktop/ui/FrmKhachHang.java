package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.CustomerService;
import com.gearvmdesktop.service.EmployeeService;
import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.Gender;
import com.toedter.calendar.JDateChooser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FrmKhachHang extends javax.swing.JFrame implements ActionListener, MouseListener {
    private static final String tableName = "customers/";
    private JTextField txtTim;
    private static DefaultTableModel modelKhachHang;
    private static JTable tableKhachHang;
    private JComboBox<String> cmbChon;
    private JButton btnTim;
    private JPanel pnExcel;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private java.awt.Label lblGioiTinh;
    private java.awt.Label lblSDT;
    private java.awt.Label lblTen;
    private java.awt.Label lblMaKhachHang;
    private java.awt.Label lblEmail;
    private java.awt.Label lblNgaySinh;
    private JPanel pnChucNang;
    private JPanel pnThongTin;
    private JScrollPane pntblKhachHang;
    private JComboBox<String> cmbGioiTinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private JPanel pnlTimKiem;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtEmail;
    private JDateChooser txtNgaySinh;

    public JPanel createPanelKhachHang() throws IOException {
        FlatLightLaf.setup();
        pntblKhachHang = new JScrollPane();
        tableKhachHang = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblMaKhachHang = new java.awt.Label();
        txtMaKhachHang = new javax.swing.JTextField();
        lblTen = new java.awt.Label();
        txtTenKhachHang = new javax.swing.JTextField();
        lblGioiTinh = new java.awt.Label();
        cmbGioiTinh = new JComboBox<String>();
        lblSDT = new java.awt.Label();
        txtSDT = new javax.swing.JTextField();
        lblEmail = new java.awt.Label();
        txtEmail = new javax.swing.JTextField();
        lblNgaySinh = new java.awt.Label();
        txtNgaySinh = new JDateChooser();

        pnChucNang = new JPanel();
        btnThem = new JButton();
        btnSua = new JButton();
        btnXoa = new JButton();

        pnExcel = new JPanel();
        btnImport = new JButton();
        btnExport = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        String[] header = {"Mã Khách hàng", "Tên Khách hàng", "Ngày Sinh", "Giới Tính", "SDT", "Email"};

        modelKhachHang = new DefaultTableModel(header, 0);
        tableKhachHang = new JTable(modelKhachHang) {
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
        tableKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableKhachHang.setGridColor(getBackground());
        tableKhachHang.setRowHeight(tableKhachHang.getRowHeight() + 20);
        tableKhachHang.setSelectionBackground(new Color(255, 255, 128));
        tableKhachHang.setSelectionForeground(Color.BLACK);

        JTableHeader tableHeader = tableKhachHang.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        pntblKhachHang.add(new JScrollPane(tableKhachHang));
        pntblKhachHang.setViewportView(tableKhachHang);
        tableKhachHang.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
        pnThongTin.setToolTipText("Info of selected table object");
        pnThongTin.setName("pnThongTin"); // NOI18N

        String[] gioitinh = {"Nam", "Nữ"};
        cmbGioiTinh = new JComboBox<String>(gioitinh);

        lblMaKhachHang.setText("Mã Khách Hàng:");
        lblTen.setText("Tên Khách Hàng:");
        lblGioiTinh.setText("Giới Tính:");
        lblSDT.setText("SDT:");
        lblEmail.setText("Email:");
        lblNgaySinh.setText("Ngày Sinh");

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");
        txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaKhachHang,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnThongTinLayout.setVerticalGroup(pnThongTinLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addContainerGap(28, Short.MAX_VALUE)));

        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng:"));
        pnExcel.setBorder(BorderFactory.createTitledBorder("Xử lý excel:"));

        ClassLoader classLoader = getClass().getClassLoader();
        URL iconThem = classLoader.getResource("assets/them.png");
        URL iconSua = classLoader.getResource("assets/capnhat.png");
        URL iconXoa = classLoader.getResource("assets/xoa.png");
        URL iconXuatFile = classLoader.getResource("assets/xuatexcel.png");
        URL iconNhapFile = classLoader.getResource("assets/docfile.png");
        URL iconLuu = classLoader.getResource("assets/luu.png");
        URL iconHuy = classLoader.getResource("assets/huy.png");
        URL iconTim = classLoader.getResource("assets/timkiem.png");

        btnThem.setText("THÊM");
        btnThem.setIcon(new ImageIcon(iconThem));

        btnSua.setText("SỬA");
        btnSua.setIcon(new ImageIcon(iconSua));

        btnXoa.setText("XÓA");
        btnXoa.setIcon(new ImageIcon(iconXoa));

        btnSave.setText("LƯU");
//        btnSave.setIcon(new ImageIcon(iconLuu));


        btnCancel.setText("HỦY");
//        btnCancel.setIcon(new ImageIcon(iconHuy));

        btnImport.setText("NHẬP FILE");
//        btnImport.setIcon(new ImageIcon(iconNhapFile));

        btnExport.setText("XUẤT FILE");
        btnExport.setIcon(new ImageIcon(iconXuatFile));


        btnImport.setSize(new Dimension(30, 200));

        pnExcel.setBackground(new Color(219, 243, 255));
        pnThongTin.setBackground(new Color(219, 243, 255));
        pnChucNang.setBackground(new Color(219, 243, 255));
        btnThem.setBackground(new Color(0, 148, 224));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);
        btnSua.setBackground(new Color(0, 148, 224));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFocusPainted(false);
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


        javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(50)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
                        .addGap(48))
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(50)));
        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem).addComponent(btnSua))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnXoa).addComponent(btnXoa))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15)));

        javax.swing.GroupLayout pnExcelLayout = new javax.swing.GroupLayout(pnExcel);
        pnExcel.setLayout(pnExcelLayout);
        pnExcelLayout.setHorizontalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(50)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnExport)
                        .addGap(60)));

//                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
//                        .addGap(40)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnSave)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnCancel)
//                        .addGap(80)));
        pnExcelLayout.setVerticalGroup(pnExcelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnExcelLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                .addComponent(btnImport)
                                .addComponent(btnExport))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)));
//                        .addGroup(pnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                .addComponent(btnSave).addComponent(btnCancel))
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addGap(15)));


        Box b = Box.createHorizontalBox();
        String[] tim = {"Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "SDT", "CMND", "Ngày Sinh", "Địa Chỉ",
                "Email"};
        cmbChon = new JComboBox<String>(tim);
        txtTim = new JTextField();
        cmbChon.setSize(20, txtTim.getPreferredSize().height);
        btnTim = new JButton("TÌM KIẾM", new ImageIcon(iconTim));
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        b.add(cmbChon);
        b.add(Box.createHorizontalStrut(10));
        b.add(txtTim);
        b.add(Box.createHorizontalStrut(10));
        b.add(btnTim);
        b.add(Box.createHorizontalStrut(30));
        pnlTimKiem.add(b);

        JPanel panel = new JPanel();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        panel.add(getContentPane());
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pntblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                                .addComponent(pnlTimKiem))
                        .addContainerGap(20, 20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnExcel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                ))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addComponent(pntblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 670,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                .addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
                                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 20, 20)
                                        .addComponent(pnExcel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 27, Short.MAX_VALUE)))
                        .addContainerGap()));

        pntblKhachHang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "DANH SÁCH KHÁCH HÀNG: "));
        lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));

        tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableKhachHang.getColumnModel().getColumn(2).setPreferredWidth(30);
        tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(30);
        tableKhachHang.getColumnModel().getColumn(4).setPreferredWidth(10);
        tableKhachHang.getColumnModel().getColumn(5).setPreferredWidth(30);
        txtMaKhachHang.setEditable(false);

        tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableKhachHang.setDefaultEditor(Object.class, null);
        tableKhachHang.getTableHeader().setReorderingAllowed(false);
        pack();

        btnXoa.setEnabled(false);
        txtEmail.setEditable(false);

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnExport.addActionListener(this);
        tableKhachHang.addMouseListener(this);

        readDatabaseToTable();
        GUI.disableWarning();

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (!validInput()) {
                return;
            }else {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (postRequest()) {
                        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }
            }
            }
        }
        if (o.equals(btnSua)) {
            if (!validInput()) {
                return;
            }else {
                int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if (putRequest()) {
                            JOptionPane.showMessageDialog(this, "Sửa khách hàng mã số " + txtMaKhachHang.getText() + " thành công!", "Thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            readDatabaseToTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Sửa khách hàng mã số " + txtMaKhachHang.getText() + " thất bại!", "Thất bại",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException | JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (o.equals(btnXoa)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (deleteRequest()) {
                        JOptionPane.showMessageDialog(this, "Sửa khách hàng mã số " + txtMaKhachHang.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa khách hàng mã số " + txtMaKhachHang.getText() + " thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
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
                java.io.File file = fileDialog.getSelectedFile();
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
        try {
            emptyTextField();
            int row = tableKhachHang.getSelectedRow();
            txtMaKhachHang.setText(modelKhachHang.getValueAt(row, 0).toString().trim());
            txtTenKhachHang.setText(modelKhachHang.getValueAt(row, 1).toString().trim());
            String dateString = modelKhachHang.getValueAt(row, 2).toString().trim();
            String[] a = dateString.split("-");
            txtNgaySinh
                    .setDate(new Date(Integer.parseInt(a[2]) - 1900, Integer.parseInt(a[1]) - 1,
                            Integer.parseInt(a[0])));
            cmbGioiTinh.setSelectedItem(modelKhachHang.getValueAt(row, 3).toString().trim());
            txtSDT.setText(modelKhachHang.getValueAt(row, 4).toString().trim());
            txtEmail.setText(modelKhachHang.getValueAt(row, 5).toString().trim());
        } catch (NullPointerException ex) {

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

    private void emptyTextField() {
        txtMaKhachHang.setText(null);
        txtTenKhachHang.setText(null);
        txtNgaySinh.setDate(null);
        cmbGioiTinh.setSelectedIndex(0);
        txtSDT.setText(null);
        txtEmail.setText(null);
    }

    public Gender getGenderFromCmb() {
        int index = cmbGioiTinh.getSelectedIndex();
        if (index == 0) return Gender.MALE;
        else if (index == 1) return Gender.FEMALE;
        else return Gender.UNDEFINED;
    }

    public boolean postRequest() throws IOException, JSONException {
        Customer c = new Customer();
        c.setName(txtTenKhachHang.getText());
        c.setGender(getGenderFromCmb());
        c.setPhoneNumber(txtSDT.getText());
        Calendar birthdayCalendar = txtNgaySinh.getCalendar();
        LocalDate birthdayLocalDate = LocalDate.ofInstant(birthdayCalendar.toInstant(), ZoneId.systemDefault());
        c.setDateOfBirth(birthdayLocalDate);
        return CustomerService.postRequest(c);
    }

    public boolean putRequest() throws IOException, JSONException {
        Customer c = new Customer();
        c.setId(Long.parseLong(txtMaKhachHang.getText()));
        c.setName(txtTenKhachHang.getText());
        c.setGender(getGenderFromCmb());
        c.setPhoneNumber(txtSDT.getText());
        Calendar birthdayCalendar = txtNgaySinh.getCalendar();
        LocalDate birthdayLocalDate = LocalDate.ofInstant(birthdayCalendar.toInstant(), ZoneId.systemDefault());
        c.setDateOfBirth(birthdayLocalDate);
        return CustomerService.putRequest(c);
    }

    public boolean deleteRequest() throws IOException {
        Customer c = new Customer();
        c.setId(Long.parseLong(txtMaKhachHang.getText()));
        return CustomerService.deleteRequest(c);
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableKhachHang.getModel();
        dm.setRowCount(0);
    }

    public static void readDatabaseToTable() throws IOException {
        emptyTable();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        BufferedReader rd = EmployeeService.getAllRequest(tableName);
        List<Customer> customerList = Arrays.asList(mapper.readValue(rd, Customer[].class));
        for (Customer c : customerList) {
            String gender;

            if (c.getGender() == Gender.MALE) gender = "Nam";
            else if (c.getGender() == Gender.FEMALE) gender = "Nữ";
            else gender = "Không xác định";

            modelKhachHang.addRow(new Object[]{c.getId(), c.getName(),
                    dateFormat.format(c.getDateOfBirth()), gender,
                    c.getPhoneNumber(), c.getEmail()});
        }
    }

    public boolean exportExcel(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("DANH SÁCH KHÁCH HÀNG");

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

            cell.setCellValue("DANH SÁCH KHÁCH HÀNG");
            cell.setCellStyle(styleTenDanhSach);

            String[] header = {"STT", "Mã Khách hàng", "Tên Khách hàng", "Ngày Sinh", "Giới Tính", "SDT", "Email"};
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

            if (tableKhachHang.getRowCount() == 0) {
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
            for (int i = 0; i < tableKhachHang.getRowCount(); i++) {
                row = worksheet.createRow(5 + i);
                for (int j = 0; j < header.length; j++) {
                    cell = row.createCell(j + 1);
                    if (STT == i) {
                        cell.setCellValue(STT + 1);
                        STT++;
                    } else {
                        try {
                            if (j == header.length - 6) {
                                cell.setCellValue(Integer.parseInt(tableKhachHang.getValueAt(i, j - 1).toString()));
                            } else
                                cell.setCellValue(tableKhachHang.getValueAt(i, j - 1).toString());
                        } catch (NullPointerException e) {
                            cell.setCellValue("");
                            cell.setCellStyle(styleRow);
                            continue;
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
    private boolean validInput() {
        // TODO Auto-generated method stub

        String tenKH = txtTenKhachHang.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        Date ngaySinh =txtNgaySinh.getDate();
        Date ngayHienTai = new Date();

        if (tenKH.trim().length() > 0) {
            if (!(tenKH.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)0-9]+"))) {
                JOptionPane.showMessageDialog(this, "Tên khách hàng phải là ký tự chữ", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (sdt.trim().length() > 0) {
            if (!(sdt.matches(
                    "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$"))) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

//        if (email.trim().length() > 0) {
//             if (!(email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$"))) {
//                JOptionPane.showMessageDialog(this, "Email không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Email không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
        if(ngaySinh.after(ngayHienTai)){
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ","Lỗi", JOptionPane.ERROR_MESSAGE);
            txtNgaySinh.setDate(new Date());
            return  false;
        }
        return true;
    }
}
