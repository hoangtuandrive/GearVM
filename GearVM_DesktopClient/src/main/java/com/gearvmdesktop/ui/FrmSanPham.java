/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.model.Product;
import com.gearvmdesktop.service.ProductService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FrmSanPham extends javax.swing.JFrame implements ActionListener, MouseListener {
    private String newProductId;
    private static final String tableName = "products/";
    private static JComboBox<String> cmbTim;
    private static JTable tableSanPham;
    private static DefaultTableModel modelSanPham;
    private JComboBox<String> cmbChon;
    private JButton btnTim;
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnChiTiet;
    private java.awt.Label lblDonGia;
    private java.awt.Label lblNhaCungCap;
    private java.awt.Label lblSoLuong;
    private java.awt.Label lblTenSanPham;
    private java.awt.Label lblLoaiHang;
    private java.awt.Label lblMaSanPham;
    private JPanel pnChucNang;
    private JPanel pnExcel;
    private JPanel pnThongTin;
    private javax.swing.JScrollPane pntblHangHoa;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtNhaCungCap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtLoaiHang;
    private JPanel pnlTimKiem;
    private javax.swing.JTextField txtMaSanPham;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnSave;
    private JButton btnCancel;

    public JPanel createPanelSanPham() throws IOException {
        FlatLightLaf.setup();
        pntblHangHoa = new javax.swing.JScrollPane();
        tableSanPham = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblMaSanPham = new java.awt.Label();
        txtMaSanPham = new javax.swing.JTextField();
        lblTenSanPham = new java.awt.Label();
        txtTenSanPham = new javax.swing.JTextField();
        lblLoaiHang = new java.awt.Label();
        txtLoaiHang = new javax.swing.JTextField();
        lblNhaCungCap = new java.awt.Label();
        txtNhaCungCap = new javax.swing.JTextField();
        lblDonGia = new java.awt.Label();
        txtDonGia = new javax.swing.JTextField();
        lblSoLuong = new java.awt.Label();
        txtSoLuong = new javax.swing.JTextField();

        pnChucNang = new JPanel();
        btnThem = new JButton();
        btnSua = new JButton();
        btnXoa = new JButton();
        btnChiTiet = new JButton();

        pnExcel = new JPanel();
        btnImport = new JButton();
        btnExport = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        String[] header = {" Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
        modelSanPham = new DefaultTableModel(header, 0);
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

        tableSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableSanPham.setGridColor(getBackground());
        tableSanPham.setRowHeight(tableSanPham.getRowHeight() + 20);
        tableSanPham.setSelectionBackground(new Color(255, 255, 128));
        tableSanPham.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableSanPham.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        tableSanPham.setColumnSelectionAllowed(false);
        tableSanPham.setName("tblThongTinNhanVien"); // NOI18N
        pntblHangHoa.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
        pnThongTin.setToolTipText("Info of selected table object");

        lblMaSanPham.setText("Mã Sản Phẩm:");

        lblTenSanPham.setText("Tên Sản Phẩm:");

        lblLoaiHang.setText("Loại hàng");

        lblNhaCungCap.setText("Nhà cung cấp");

        lblDonGia.setText("Đơn giá");

        lblSoLuong.setText("Số lượng");

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaSanPham,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLoaiHang, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnThongTinLayout.setVerticalGroup(pnThongTinLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE)));

        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng:"));
        pnExcel.setBorder(BorderFactory.createTitledBorder("Xử lý excel:"));

        btnThem.setText("THÊM");
        btnSua.setText("SỬA");
        btnXoa.setText("XÓA");
        btnChiTiet.setText("CHI TIẾT");
        btnSave.setText("LƯU");
        btnCancel.setText("HỦY");
        btnImport.setText("NHẬP FILE");
        btnExport.setText("XUẤT FILE");


        // Chức năng button
        btnThem.setBackground(new Color(0, 148, 224));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);
        btnSua.setBackground(new Color(0, 148, 224));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFocusPainted(false);
        btnXoa.setBackground(new Color(0, 148, 224));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFocusPainted(false);
        btnChiTiet.setBackground(new Color(0, 148, 224));
        btnChiTiet.setForeground(Color.WHITE);
        btnChiTiet.setFocusPainted(false);

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
                        .addGap(48, 48, 48)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
                        .addGap(48, 48, 48))
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnChiTiet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(48, 48, 48)));

        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem).addComponent(btnSua))
                        .addGap(5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnChiTiet).addComponent(btnXoa))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15)));

        javax.swing.GroupLayout pnExcelLayout = new javax.swing.GroupLayout(pnExcel);
        pnExcel.setLayout(pnExcelLayout);
        pnExcelLayout.setHorizontalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(50)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnExport)
                        .addGap(50))
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(40)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnCancel)
                        .addGap(70)));
        pnExcelLayout.setVerticalGroup(pnExcelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnExcelLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnImport).addComponent(btnExport))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)
                        .addGroup(pnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSave).addComponent(btnCancel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        panel.add(getContentPane());
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pntblHangHoa, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
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
                                        .addComponent(pntblHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE, 670,
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

        pack();

        pntblHangHoa.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));
        lblMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLoaiHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtLoaiHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtDonGia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(165);

        txtMaSanPham.setEditable(false);

        tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.getTableHeader().setReorderingAllowed(false);

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnChiTiet.addActionListener(this);
        btnExport.addActionListener(this);
        btnImport.addActionListener(this);
        tableSanPham.addMouseListener(this);

        readDatabaseToTable();
        GUI.disableWarning();

        return panel;
    }// </editor-fold>//GEN-END:initComponents

    private boolean validInput() {
        String tenLk = txtTenSanPham.getText();
        String loaiHang = txtLoaiHang.getText();
        String nhaCC = txtNhaCungCap.getText();
        String gialk = txtDonGia.getText();
        String soLuong = txtSoLuong.getText();
        if (tenLk.trim().length() > 0) {
            if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
                JOptionPane.showMessageDialog(this, "Tên Sản Phẩm không chứa ký tự đặc biệt", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên Sản Phẩm không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (loaiHang.trim().length() > 0) {
            if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
                JOptionPane.showMessageDialog(this, "Tên loại hàng không chứa ký tự đặc biệt", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên loại hàng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (nhaCC.trim().length() > 0) {
            if (!(tenLk.matches("[^\\@\\!\\$\\^\\&\\*\\(\\)]+"))) {
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không chứa ký tự đặc biệt", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên cung cấp không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (gialk.trim().length() > 0) {
            try {
                double x = Double.parseDouble(gialk);
                if (x <= 0) {
                    JOptionPane.showMessageDialog(this, "Giá Sản Phẩm phải lớn hơn 0", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Giá Sản Phẩm phải nhập số", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Giá Sản Phẩm không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (soLuong.trim().length() > 0) {
            try {

                int x = Integer.parseInt(soLuong);
                if (x < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Số lượng phải nhập số", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (postRequest()) {
                        String productName = txtTenSanPham.getText();
                        readDatabaseToTable();
                        int continueResult = JOptionPane.showConfirmDialog(this, "Thêm sản phẩm thành công! Bạn có muốn nhập hàng cho sản phẩm này hay không?", "Thành công", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(continueResult == JOptionPane.YES_OPTION){
                            new FrmThemKhoHang(newProductId, productName);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnSua)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (putRequest()) {
                        JOptionPane.showMessageDialog(this, "Sửa sản phẩm mã số " + txtMaSanPham.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa sản phẩm mã số " + txtMaSanPham.getText() + " thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnXoa)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (deleteRequest()) {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaSanPham.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                        emptyTextField();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaSanPham.getText() + " thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnChiTiet)) {
            try {
                new FrmChiTietSanPham(getRequest());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
                if(!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))) {
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
        int row = tableSanPham.getSelectedRow();
        txtMaSanPham.setText(modelSanPham.getValueAt(row, 0).toString().trim());
        txtTenSanPham.setText(modelSanPham.getValueAt(row, 1).toString().trim());
        txtLoaiHang.setText(modelSanPham.getValueAt(row, 2).toString().trim());
        txtNhaCungCap.setText(modelSanPham.getValueAt(row, 3).toString().trim());
        String tien[] = modelSanPham.getValueAt(row, 4).toString().split(",");
        String donGia = "";
        for (int i = 0; i < tien.length; i++)
            donGia += tien[i];
        txtDonGia.setText(donGia);
        txtSoLuong.setText(modelSanPham.getValueAt(row, 5).toString().trim());
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
        // Get all products
        BufferedReader rd = ProductService.getAllRequest(tableName + "/get-all");
        List<Product> listProduct = Arrays.asList(mapper.readValue(rd, Product[].class));
        DecimalFormat df = new DecimalFormat("#,##0");
        for (Product p : listProduct) {
            modelSanPham.addRow(new Object[]{p.getId(), p.getName(), p.getType(),
                    p.getBrand(), df.format(p.getPrice()), p.getQuantity()});
        }
    }

    private void emptyTextField() {
        txtMaSanPham.setText(null);
        txtTenSanPham.setText(null);
        txtDonGia.setText(null);
        txtSoLuong.setText(null);
        txtNhaCungCap.setText(null);
        txtLoaiHang.setText(null);

    }

    public Product getRequest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ProductService.getRequest(tableName, txtMaSanPham.getText());
        return mapper.readValue(rd, Product.class);
    }

    public boolean postRequest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product p = new Product();
        p.setName(txtTenSanPham.getText());
        p.setType(txtLoaiHang.getText());
        p.setBrand(txtNhaCungCap.getText());
        p.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        p.setPrice(Double.parseDouble(txtDonGia.getText()));
        BufferedReader rd = ProductService.postRequest(p);
        Product product = mapper.readValue(rd, Product.class);
        return product != null;
    }

    public boolean putRequest() throws IOException {
        Product p = new Product();
        p.setId(Long.parseLong(txtMaSanPham.getText()));
        p.setName(txtTenSanPham.getText());
        p.setType(txtLoaiHang.getText());
        p.setBrand(txtNhaCungCap.getText());
        p.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        p.setPrice(Double.parseDouble(txtDonGia.getText()));
        return ProductService.putRequest(p);
    }

    public boolean deleteRequest() throws IOException {
        Product p = new Product();
        p.setId(Long.parseLong(txtMaSanPham.getText()));
        return ProductService.deleteRequest(p);
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
        dm.setRowCount(0);
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

            String[] header = { "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn" };
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

            if (tableSanPham.getRowCount() == 0) {
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
            for (int i = 0; i < tableSanPham.getRowCount(); i++) {
                row = worksheet.createRow(5 + i);
                for (int j = 0; j < header.length; j++) {
                    cell = row.createCell(j + 1);
                    if (STT == i) {
                        cell.setCellValue(STT + 1);
                        STT++;
                    } else {
                        if (tableSanPham.getValueAt(i, j - 1) != null) {
                            if (j == header.length - 2) {
                                String luong[] = tableSanPham.getValueAt(i, j - 1).toString().split(",");
                                String tienLuong = "";
                                for (int t = 0; t < luong.length; t++)
                                    tienLuong += luong[t];
                                cell.setCellValue(Double.parseDouble(tienLuong));
                            } else
                                cell.setCellValue(tableSanPham.getValueAt(i, j - 1).toString().trim());
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
