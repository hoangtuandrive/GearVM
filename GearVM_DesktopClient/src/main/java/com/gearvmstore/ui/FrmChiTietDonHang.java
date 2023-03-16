package com.gearvmstore.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmstore.model.Product;
import com.gearvmstore.model.response.GetOrderResponse;
import com.gearvmstore.model.response.OrderItemResponseModel;
import com.gearvmstore.model.response.ProductResponseModel;
import com.gearvmstore.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


public class FrmChiTietDonHang extends JFrame {
    private static JTable tableSanPham;
    private static DefaultTableModel modelSanPham;
//    private JButton btnTim ,btnGiao,btnHuy,btnChiTiet;

    public FrmChiTietDonHang(GetOrderResponse getOrderResponse) throws IOException {
        super("Chi Tiết Đơn Hàng");

        FlatLightLaf.setup();
        setSize(1400, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        String[] colHeader = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng", "Thành Tiền"};
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

        add(tblscroll);
//        tblscroll.setBorder(
//                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));

        readDatabaseToTable(getOrderResponse);

        setVisible(true);
    }

    public void readDatabaseToTable(GetOrderResponse getOrderResponse) throws IOException {
        emptyTable();
        DecimalFormat df = new DecimalFormat("#,##0");

        List<OrderItemResponseModel> orderItems = getOrderResponse.getOrderItems();
        for (OrderItemResponseModel orderItem : orderItems) {
            ProductResponseModel product = orderItem.getProductId();
            double oneItemPrice = orderItem.getPrice() / orderItem.getQuantity();
            modelSanPham.addRow(new Object[]{product.getId(), product.getName(), product.getType(),
                    product.getBrand(), df.format(oneItemPrice), orderItem.getQuantity(), df.format(orderItem.getPrice())});
        }
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
        dm.setRowCount(0);
    }
}
