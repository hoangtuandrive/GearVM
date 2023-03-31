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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FrmKhoHang extends JFrame implements ActionListener, MouseListener {
    private static final String tableName = "products/";
    private static final String tableNamePurchase = "purchases/";
    private static JComboBox<String> cmbTim;
    private static JTable tableHangHoa;
    private static DefaultTableModel modelSanPham;
    private JComboBox<String> cmbChon;
    private JButton btnTim;
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnChiTiet;
    private Label lblDonGia;
    private Label lblNhaCungCap;
    private Label lblSoLuong;
    private Label lblTenHangHoa;
    private Label lblLoaiHang;
    private Label lblMaHangHoa;
    private JPanel pnChucNang;
    private JPanel pnExcel;
    private JPanel pnThongTin;
    private JScrollPane pntblHangHoa;
    private JTextField txtDonGia;
    private JTextField txtNhaCungCap;
    private JTextField txtSoLuong;
    private JTextField txtTenHangHoa;
    private JTextField txtLoaiHang;
    private JPanel pnlTimKiem;
    private JTextField txtMaHangHoa;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnSave;
    private JButton btnCancel;

    public JPanel createPanelKhoHang() throws IOException {
        FlatLightLaf.setup();
        pntblHangHoa = new JScrollPane();
        tableHangHoa = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblMaHangHoa = new Label();
        txtMaHangHoa = new JTextField();
        lblTenHangHoa = new Label();
        txtTenHangHoa = new JTextField();
        lblLoaiHang = new Label();
        txtLoaiHang = new JTextField();
        lblNhaCungCap = new Label();
        txtNhaCungCap = new JTextField();
        lblDonGia = new Label();
        txtDonGia = new JTextField();
        lblSoLuong = new Label();
        txtSoLuong = new JTextField();

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String[] header = {"Mã Nhập Hàng", "Mã Đơn Hàng", "Tên Đơn Hàng", "Mã Người Nhập", "Tên Người Nhập", "Thời Gian Nhập", "Giá Nhập"};
        modelSanPham = new DefaultTableModel(header, 0);
        tableHangHoa = new JTable(modelSanPham) {
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
        tableHangHoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableHangHoa.setGridColor(getBackground());
        tableHangHoa.setRowHeight(tableHangHoa.getRowHeight() + 20);
        tableHangHoa.setSelectionBackground(new Color(255, 255, 128));
        tableHangHoa.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableHangHoa.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        tableHangHoa.setColumnSelectionAllowed(false);
        tableHangHoa.setName("tblThongTinNhanVien"); // NOI18N
        pntblHangHoa.setViewportView(tableHangHoa);
        tableHangHoa.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
        pnThongTin.setToolTipText("Info of selected table object");

        lblMaHangHoa.setText("Mã Sản Phẩm:");

        lblTenHangHoa.setText("Tên Sản Phẩm:");

        lblLoaiHang.setText("Loại hàng");

        lblNhaCungCap.setText("Nhà cung cấp");

        lblDonGia.setText("Đơn giá");

        lblSoLuong.setText("Số lượng");

        GroupLayout pnThongTinLayout = new GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblNhaCungCap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLoaiHang, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenHangHoa, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaHangHoa, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDonGia, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaHangHoa,
                                        GroupLayout.PREFERRED_SIZE, 169,
                                        GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSoLuong, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDonGia, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenHangHoa, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLoaiHang, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNhaCungCap, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnThongTinLayout.setVerticalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaHangHoa, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaHangHoa, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenHangHoa, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenHangHoa, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtLoaiHang, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLoaiHang, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtNhaCungCap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNhaCungCap, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtDonGia, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDonGia, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

        GroupLayout pnChucNangLayout = new GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
                        .addGap(48, 48, 48))
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnChiTiet)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(48, 48, 48)));

        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem).addComponent(btnSua))
                        .addGap(5)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnChucNangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnChiTiet).addComponent(btnXoa))
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
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));
        lblMaHangHoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMaHangHoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTenHangHoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTenHangHoa.setFont(new Font("Tahoma", Font.BOLD, 12));
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
        tableHangHoa.setFont(new Font("Tahoma", Font.PLAIN, 14));

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        tableHangHoa.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableHangHoa.getColumnModel().getColumn(1).setPreferredWidth(165);

        txtMaHangHoa.setEditable(false);

        tableHangHoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHangHoa.setDefaultEditor(Object.class, null);
        tableHangHoa.getTableHeader().setReorderingAllowed(false);

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnChiTiet.addActionListener(this);
        btnExport.addActionListener(this);
        btnImport.addActionListener(this);
        tableHangHoa.addMouseListener(this);

        readDatabaseToTable();
        GUI.disableWarning();

        return panel;
    }// </editor-fold>//GEN-END:initComponents

    private boolean validInput() {
        String tenLk = txtTenHangHoa.getText();
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
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
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
                        JOptionPane.showMessageDialog(this, "Sửa sản phẩm mã số " + txtMaHangHoa.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa sản phẩm mã số " + txtMaHangHoa.getText() + " thất bại!", "Thất bại",
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
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaHangHoa.getText() + " thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                        emptyTextField();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm mã số " + txtMaHangHoa.getText() + " thất bại!", "Thất bại",
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
                File file = fileDialog.getSelectedFile();
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
        int row = tableHangHoa.getSelectedRow();
        txtMaHangHoa.setText(modelSanPham.getValueAt(row, 0).toString().trim());
        txtTenHangHoa.setText(modelSanPham.getValueAt(row, 1).toString().trim());
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

    public void readDatabaseToTable() throws IOException {
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
        txtMaHangHoa.setText(null);
        txtTenHangHoa.setText(null);
        txtDonGia.setText(null);
        txtSoLuong.setText(null);
        txtNhaCungCap.setText(null);
        txtLoaiHang.setText(null);

    }

    public Product getRequest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ProductService.getRequest(tableName, txtMaHangHoa.getText());
        return mapper.readValue(rd, Product.class);
    }

    public boolean postRequest() throws IOException {
        Product p = new Product();
        p.setName(txtTenHangHoa.getText());
        p.setType(txtLoaiHang.getText());
        p.setBrand(txtNhaCungCap.getText());
        p.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        p.setPrice(Double.parseDouble(txtDonGia.getText()));
        return ProductService.postRequest(p);
    }

    public boolean putRequest() throws IOException {
        Product p = new Product();
        p.setId(Long.parseLong(txtMaHangHoa.getText()));
        p.setName(txtTenHangHoa.getText());
        p.setType(txtLoaiHang.getText());
        p.setBrand(txtNhaCungCap.getText());
        p.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        p.setPrice(Double.parseDouble(txtDonGia.getText()));
        return ProductService.putRequest(p);
    }

    public boolean deleteRequest() throws IOException {
        Product p = new Product();
        p.setId(Long.parseLong(txtMaHangHoa.getText()));
        return ProductService.deleteRequest(p);
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableHangHoa.getModel();
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

            if (tableHangHoa.getRowCount() == 0) {
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
            for (int i = 0; i < tableHangHoa.getRowCount(); i++) {
                row = worksheet.createRow(5 + i);
                for (int j = 0; j < header.length; j++) {
                    cell = row.createCell(j + 1);
                    if (STT == i) {
                        cell.setCellValue(STT + 1);
                        STT++;
                    } else {
                        if (tableHangHoa.getValueAt(i, j - 1) != null) {
                            if (j == header.length - 2) {
                                String luong[] = tableHangHoa.getValueAt(i, j - 1).toString().split(",");
                                String tienLuong = "";
                                for (int t = 0; t < luong.length; t++)
                                    tienLuong += luong[t];
                                cell.setCellValue(Double.parseDouble(tienLuong));
                            } else
                                cell.setCellValue(tableHangHoa.getValueAt(i, j - 1).toString().trim());
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
