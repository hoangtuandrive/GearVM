package com.gearvmdesktop.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

public class FrmThongKe extends JFrame implements ActionListener {
    private JButton btnBestSale, btnFinance;

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


        btnBestSale = new JButton("Thống Kê Sản Phẩm Bán Chạy", new ImageIcon(iconTim));
        btnBestSale.setBackground(new Color(0, 148, 224));
        btnBestSale.setForeground(Color.WHITE);
        btnBestSale.setPreferredSize(new Dimension(280, 50));
        btnBestSale.setFont(new Font("Tahoma", Font.BOLD, 18));

        btnFinance = new JButton("Thống Kê Doanh Thu", new ImageIcon(iconLoad));
        btnFinance.setBackground(new Color(0, 148, 224));
        btnFinance.setForeground(Color.WHITE);
        btnFinance.setPreferredSize(new Dimension(280, 50));
        btnFinance.setFont(new Font("Tahoma", Font.BOLD, 18));


        panel.add(btnBestSale);
        panel.add(btnFinance);


        add(panel);

        btnFinance.addActionListener(this);
        btnBestSale.addActionListener(this);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnBestSale)) {
            try {
                new FrmBaoCaoSanPhamBanChay();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (o.equals(btnFinance)) {
            try {
                new FrmBaoCaoDoanhThu().setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
