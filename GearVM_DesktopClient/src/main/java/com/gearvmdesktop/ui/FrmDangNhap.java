package com.gearvmdesktop.ui;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.EmployeeService;
import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.model.dto.user.LoginDTO;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;

public class FrmDangNhap extends JFrame implements ActionListener {
    private static JTextField txtTenDangNhap;
    private JTextField txtMatKhau;
    private JButton btnDangNhap;
    private JButton btnThoat;

    private JLabel lblMK;
    private JLabel lblTDN;

    public FrmDangNhap() {
        FlatLightLaf.setup();

        setTitle("Đăng Nhập");
        setSize(450, 235);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        JPanel p = new JPanel(new BorderLayout());
        this.add(p);

        JPanel pTop = new JPanel();

        JLabel lblTitle = new JLabel("Đăng Nhập");
        lblTitle.setFont(new Font("serif", Font.BOLD, 25));
        lblTitle.setForeground(Color.white);
        pTop.add(lblTitle);

        pTop.setBackground(new Color(0, 148, 224));
        p.add(pTop, BorderLayout.NORTH);

        JPanel pcenter = new JPanel();
        Box b, b1, b2;
        b = Box.createVerticalBox();
        b1 = Box.createHorizontalBox();
        b2 = Box.createHorizontalBox();

        txtTenDangNhap = new JTextField(20);
        b1.add(lblTDN = new JLabel("Email:"));
        b1.add(Box.createHorizontalStrut(45));
        b1.add(txtTenDangNhap);
        b.add(b1);

        b.add(Box.createVerticalStrut(20));
        txtMatKhau = new JPasswordField(20);
        b2.add(lblMK = new JLabel("Mật Khẩu:"));
        b2.add(Box.createHorizontalStrut(45));
        b2.add(txtMatKhau);

        b.add(b2);
        b.add(Box.createVerticalStrut(10));
        pcenter.add(b);

        p.add(pcenter, BorderLayout.CENTER);

        JPanel pBot = new JPanel();
//		btnDangNhap = new JButton("Đăng Nhập", new ImageIcon("image/trangchu.png"));
        btnDangNhap = new JButton("Đăng Nhập");
        btnThoat = new JButton("Thoát");
        pBot.add(Box.createHorizontalStrut(100));
        pBot.add(btnDangNhap);

        pBot.add(btnThoat);
        pcenter.add(pBot);

        btnDangNhap.setBackground(new Color(0, 148, 224));
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnThoat.setBackground(new Color(0, 148, 224));
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setFocusPainted(false);

        txtTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTDN.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMK.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));

        lblTDN.setPreferredSize(lblMK.getPreferredSize());
        txtTenDangNhap.setPreferredSize(txtMatKhau.getPreferredSize());

        txtTenDangNhap.setText("tuan@gmail.com");
        txtMatKhau.setText("abc12345");

        btnDangNhap.addActionListener(this);
    }

    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDangNhap().setVisible(true);
            }
        });
    }

    public Employee login() throws IOException, JSONException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(txtTenDangNhap.getText());
        loginDTO.setPassword(txtMatKhau.getText());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        BufferedReader rd = EmployeeService.login(loginDTO);
        try {
            return mapper.readValue(rd, Employee.class);
        } catch (JsonParseException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnDangNhap)) {
            try {
                Employee employee = login();

                if (employee != null) {
                    GUI gui = new GUI(employee);
                    gui.setVisible(true);
                    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    gui.setLocationRelativeTo(null);
                    dispose();
                } else JOptionPane.showMessageDialog(this, "Đăng Nhập Thất Bại!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (IOException | JSONException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
