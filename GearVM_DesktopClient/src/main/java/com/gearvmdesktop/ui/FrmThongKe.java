package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.OrderService;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FrmThongKe extends JFrame implements ActionListener {
    private JButton btnBestSale,btnStatic;
    public static void main(String[] args) throws RemoteException {
        // TODO Auto-generated method stub
        new FrmDangNhap().setVisible(true);
    }
    public JPanel createPanelThongKe() throws IOException {
        FlatLightLaf.setup();
        setTitle("FrmThôngKê");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        //Chỉnh vgap để diểu chỉnh chiều dọc
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 350));

        ClassLoader classLoader = getClass().getClassLoader();

        URL iconTim = classLoader.getResource("assets/doanhthu.png");
        URL iconLoad = classLoader.getResource("assets/loctheogia.png");


        btnBestSale = new JButton("Sản Phẩm Bán Chạy",new ImageIcon(iconTim));
        btnBestSale.setBackground(new Color(0, 148, 224));
        btnBestSale.setForeground(Color.WHITE);
        btnBestSale.setPreferredSize(new Dimension(280, 50));
        btnBestSale.setFont(new Font("Tahoma", Font.BOLD, 18));

        btnStatic = new JButton("Thông Kê Doanh Thu",new ImageIcon(iconLoad));
        btnStatic.setBackground(new Color(0, 148, 224));
        btnStatic.setForeground(Color.WHITE);
        btnStatic.setPreferredSize(new Dimension(280, 50));
        btnStatic.setFont(new Font("Tahoma", Font.BOLD, 18));


        panel.add(btnBestSale);
        panel.add(btnStatic);


        add(panel);

        btnStatic.addActionListener(this);
        btnBestSale.addActionListener(this);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnBestSale)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    openFrameProductBestSale();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnStatic)) {

        }
    }
    public void openFrameProductBestSale() throws IOException {
        new FrmSanPhamBanChay();
    }
}
