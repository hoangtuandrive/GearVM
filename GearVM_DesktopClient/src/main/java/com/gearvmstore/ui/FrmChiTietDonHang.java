package com.gearvmstore.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


public class FrmChiTietDonHang extends JFrame {
    private static JTable tableSanPham;
    private static DefaultTableModel modelSanPham;
//    private JButton btnTim ,btnGiao,btnHuy,btnChiTiet;

    public FrmChiTietDonHang(){
        super("Chi Tiết Đơn Hàng");

        FlatLightLaf.setup();
        setSize(850, 600);
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel p = new JPanel();

        Box b = Box.createHorizontalBox();
        Box b1 = Box.createVerticalBox();
        Box b2=Box.createVerticalBox();

        String[] colHeader = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng","Nhà Cung Cấp", "Đơn Giá",  "Số Lượng" };
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
        JScrollPane tblscroll = new JScrollPane(tableSanPham);
        tableSanPham.setPreferredScrollableViewportSize(new Dimension(800, 500));
        b1.add(b2);
        b1.add(tblscroll);
//        tblscroll.setBorder(
//                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));


        JPanel p1= new JPanel();

        add(p);
        p.add(p1, BorderLayout.CENTER);
        p1.add(b1);

        setVisible(true);

    }
}
