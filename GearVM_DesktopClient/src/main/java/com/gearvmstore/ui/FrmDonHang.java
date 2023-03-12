package com.gearvmstore.ui;

import com.formdev.flatlaf.FlatLightLaf;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

public class FrmDonHang extends JFrame implements ActionListener {
    private static JTable tableDonHang;
    private static DefaultTableModel modelDonHang;
    private JButton btnTim ,btnGiao,btnHuy,btnChiTiet;
    private JComboBox<String> cmbChon,cmbTim;

    public JPanel createPanelDonHang() {
        FlatLightLaf.setup();
        // TODO Auto-generated constructor stub
        setTitle("FrmBanHang");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();


        Box b1 = Box.createVerticalBox();
        Box b2=Box.createVerticalBox();
        Box b3 = Box.createHorizontalBox();
        Box btim = Box.createHorizontalBox();
        Box bNut = Box.createHorizontalBox();



        String[] colHeader = { "Mã Đơn Hàng", "Tên Khách Hàng", "Số điện thoại","Trạng thái", "Ngày Lập Hóa Đơn",  "Thành Tiền" };
        modelDonHang = new DefaultTableModel(colHeader, 0);
        tableDonHang = new JTable(modelDonHang) {
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
        tableDonHang.setGridColor(getBackground());
        tableDonHang.setRowHeight(tableDonHang.getRowHeight() + 20);
        tableDonHang.setSelectionBackground(new Color(255, 255, 128));
        tableDonHang.setSelectionForeground(Color.BLACK);
        JTableHeader tableHeader = tableDonHang.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        JScrollPane tblscroll = new JScrollPane(tableDonHang);
        tableDonHang.setPreferredScrollableViewportSize(new Dimension(1200, 600));
        b1.add(b2);
        b1.add(tblscroll);
        tblscroll.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH ĐƠN HÀNG: "));

        String[] tim = { "Mã Hóa Đơn", "Tên Khách Hàng","Số Điện Thoại" , "Ngày Lập Hóa Đơn", "Thành Tiền" };
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

        btnTim = new JButton("TÌM KIẾM");
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);

        btnGiao = new JButton("GIAO HÀNG THÀNH CÔNG");
        btnGiao.setBackground(new Color(0, 148, 224));
        btnGiao.setForeground(Color.WHITE);

        btnHuy = new JButton("HỦY ĐƠN HÀNG");
        btnHuy.setBackground(new Color(0, 148, 224));

        btnHuy.setForeground(Color.WHITE);

        btnChiTiet = new JButton("CHI TIẾT");
        btnChiTiet.setBackground(new Color(0, 148, 224));
        btnChiTiet.setForeground(Color.WHITE);

        btnTim.setFocusPainted(false);
        btim.add(btnTim);
        btim.add(Box.createHorizontalStrut(300));

        bNut.add(btnGiao);
        bNut.add(btnHuy);
        bNut.add(btnChiTiet);

        JPanel p1= new JPanel();
        JPanel pNorth= new JPanel();

        add(p);

        p.add(pNorth, BorderLayout.NORTH);
        pNorth.add(b3);
        b3.add(btim);
        b3.add(bNut);

        p.add(p1, BorderLayout.CENTER);
        p1.add(b1);

        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnChiTiet.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGiao.setFont(new Font("Tahoma", Font.BOLD, 12));


        btnChiTiet.addActionListener(this);
        return p;
    }
    public static void main(String[] args) throws RemoteException {
        // TODO Auto-generated method stub
        new FrmDangNhap().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnChiTiet)){
            new FrmChiTietDonHang().setVisible(true);
        }
    }
}
