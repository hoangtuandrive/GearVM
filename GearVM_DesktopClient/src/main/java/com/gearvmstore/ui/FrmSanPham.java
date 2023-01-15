/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gearvmstore.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gearvmstore.model.Product;
import com.gearvmstore.service.ApiService;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.formdev.flatlaf.FlatLightLaf;

public class FrmSanPham extends javax.swing.JFrame implements ActionListener, MouseListener {
    private static final String tableName = "products";
    private JComboBox<String> cmbChon;
    private static JComboBox<String> cmbTim;
    private JButton btnTim;

    public JPanel createPanelSanPham() throws IOException {
        FlatLightLaf.setup();
        pntblHangHoa = new javax.swing.JScrollPane();
        tableHangHoa = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblMaHangHoa = new java.awt.Label();
        txtMaHangHoa = new javax.swing.JTextField();
        lblTenHangHoa = new java.awt.Label();
        txtTenHangHoa = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        String[] header = {" Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn"};
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
                                .addComponent(lblTenHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaHangHoa,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenHangHoa, javax.swing.GroupLayout.DEFAULT_SIZE,
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
                                        .addComponent(txtMaHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE,
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

        btnThem.setText("THÊM");
        btnSua.setText("SỬA");
        btnXoa.setText("XÓA");

        btnThem.setBackground(new Color(0, 148, 224));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);
        btnSua.setBackground(new Color(0, 148, 224));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFocusPainted(false);
        btnXoa.setBackground(new Color(0, 148, 224));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFocusPainted(false);

        javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(48, 48, 48)));
        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem).addComponent(btnSua).addComponent(btnXoa))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
                                        .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
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
        cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHangHoa.setFont(new Font("Tahoma", Font.PLAIN, 14));

        tableHangHoa.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableHangHoa.getColumnModel().getColumn(1).setPreferredWidth(165);

        txtMaHangHoa.setEditable(false);

        tableHangHoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHangHoa.setDefaultEditor(Object.class, null);
        tableHangHoa.getTableHeader().setReorderingAllowed(false);

        readDatabaseToTable();

        return panel;
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private java.awt.Label lblDonGia;
    private java.awt.Label lblNhaCungCap;
    private java.awt.Label lblSoLuong;
    private java.awt.Label lblTenHangHoa;
    private java.awt.Label lblLoaiHang;
    private java.awt.Label lblMaHangHoa;
    private JPanel pnChucNang;
    private JPanel pnThongTin;
    private javax.swing.JScrollPane pntblHangHoa;
    private static JTable tableHangHoa;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtNhaCungCap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenHangHoa;
    private javax.swing.JTextField txtLoaiHang;
    private JPanel pnlTimKiem;
    private javax.swing.JTextField txtMaHangHoa;
    private static DefaultTableModel modelSanPham;

    // End of variables declaration//GEN-END:variables

    public static void xoaHetDL() {
        DefaultTableModel dm = (DefaultTableModel) tableHangHoa.getModel();
        dm.setRowCount(0);
    }

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

    }

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

    public void readDatabaseToTable() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = ApiService.getAll(tableName);
        List<Product> listProduct = Arrays.asList(mapper.readValue(rd, Product[].class));
        listProduct.forEach(System.out::println);
        DecimalFormat df = new DecimalFormat("#,##0");
        for (Product p : listProduct) {
            modelSanPham.addRow(new Object[] { p.getId(), p.getName().trim(), p.getType().trim(),
                    p.getBrand().trim(), df.format(p.getPrice()), p.getQuantity() });
        }
    }
}
