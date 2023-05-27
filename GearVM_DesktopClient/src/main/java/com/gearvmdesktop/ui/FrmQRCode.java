package com.gearvmdesktop.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrmQRCode extends JFrame {
    public FrmQRCode(int i) {
        super();

        String method;
        if (i == 0) method = "Bank";
        else method = "Momo";

        setTitle("Mã QR Code " + method);
        setResizable(false);

        ClassLoader classLoader = getClass().getClassLoader();
        URL iconTrangChu = classLoader.getResource("assets/trangchu.png");
        ImageIcon icon = new ImageIcon(iconTrangChu);
        setIconImage(icon.getImage());

        FlatLightLaf.setup();
        setSize(600, 600);

        Box b = Box.createVerticalBox();

        String url = null;
        if (i == 0) url = "bank";
        else if (i == 1) url = "momo";
        URL resourceUrl = classLoader.getResource("assets/" + url + ".jpg");
        ImageIcon imageIcon = null;
        if (resourceUrl != null) {
            imageIcon = new ImageIcon(resourceUrl);
        }
        Image image = imageIcon.getImage();
        Image imageResize = image.getScaledInstance(600, 550, Image.SCALE_SMOOTH);
        JLabel lblHinh = new JLabel(new ImageIcon(imageResize));

        b.add(lblHinh);

        add(b);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
