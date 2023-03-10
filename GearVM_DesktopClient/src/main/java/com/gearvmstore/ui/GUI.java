package com.gearvmstore.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.formdev.flatlaf.FlatLightLaf;

public class GUI extends JFrame implements ActionListener, MouseListener {
	private JLabel lblThoiGian;
	private JLabel txtThoiGian;
	private JLabel lblDangXuat;
	private JLabel lblMaNhanVien;
	private JLabel txtMaNhanVien;
	private JLabel lblTenNhanVien;
	private JLabel txtTenNhanVien;

	public GUI() throws IOException {
		FlatLightLaf.setup();
		setTitle("GearVM");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(DimMax);
		setExtendedState(MAXIMIZED_BOTH); 
		setSize(1700, 980);
		setLocationRelativeTo(null);
		UIManager.put("TabbedPane.selected", new Color(50, 190, 255));
		add(createTabbedPane());
	}

	/**
	 * create a JTabbedPane contain tabs
	 */
	public JTabbedPane createTabbedPane() throws IOException {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setUI(new BasicTabbedPaneUI() {
			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
				return 80;
			}

			@Override
			protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
				return 200;
			}
		});
		tabbedPane.setBackground(new Color(0, 148, 224));
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));

		FrmBanHang frmBanHang = new FrmBanHang();
		FrmHoaDon frmHoaDon = new FrmHoaDon();
		FrmNhanVien frmNhanVien = new FrmNhanVien();
		FrmKhachHang frmKhachHang = new FrmKhachHang();
		FrmSanPham frmHangHoa = new FrmSanPham();
		FrmDonHang frmDonHang = new FrmDonHang();

		/* create JPanel, which is content of tabs */
		JPanel pnlTrangChu = createPanelTrangChu();
		JPanel pnlHoaDon = frmHoaDon.createPanelHoaDon();
		JPanel pnlBanHang = frmBanHang.createPanelBanHang();
		JPanel pnlNhanVien = frmNhanVien.createPanelNhanVien();
		JPanel pnlKhachHang = frmKhachHang.createPanelKhachHang();
		JPanel pnlHangHoa = frmHangHoa.createPanelSanPham();
		JPanel pnlDonHang = frmDonHang.createPanelDonHang();

		/* add tab with JPanel */
		tabbedPane.addTab("TRANG CH???", new ImageIcon("image/trangchu.png"), pnlTrangChu, "TRANG CH???");
		tabbedPane.addTab("B??N H??NG", new ImageIcon("image/banhang.png"), pnlBanHang, "B??N H??NG");
		tabbedPane.addTab("NH??N VI??N", new ImageIcon("image/nhanvien.png"), pnlNhanVien, "NH??N VI??N");
		tabbedPane.addTab("KH??CH H??NG", new ImageIcon("image/khachhang.png"), pnlKhachHang, "KH??CH H??NG");
		tabbedPane.addTab("S???N PH???M", new ImageIcon("image/hanghoa.png"), pnlHangHoa, "S???N PH???M");
		tabbedPane.addTab("H??A ????N", new ImageIcon("image/hoadon.png"), pnlHoaDon, "H??A ????N");
		tabbedPane.addTab("????N H??NG", new ImageIcon("image/hoadon.png"), pnlDonHang, "????N H??NG");

		return tabbedPane;
	}

	public JPanel createPanelTrangChu() {
		
		JPanel pnlContentPane = new JPanel();

		pnlContentPane.setLayout(null);

		JPanel pnlTitle = new JPanel();
		pnlContentPane.add(pnlTitle);
		
		JLabel lblTitle = new JLabel("PH???N M???M QU???N L?? GEARVM");
		lblTitle.setBounds(50, 0, 1000, 50);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setForeground(Color.BLACK);
		pnlContentPane.add(lblTitle);

		ImageIcon imageIcon = new ImageIcon("image/hinhnen.jpg");
		Image image = imageIcon.getImage();
		Image imageResize = image.getScaledInstance(1450, 550, Image.SCALE_SMOOTH);
		JLabel lblHinhNen = new JLabel(new ImageIcon(imageResize));
		lblHinhNen.setBounds(10, 50, 1450, 550);
		pnlContentPane.add(lblHinhNen);

		lblDangXuat = new JLabel("<HTML><U>????NG XU???T</U></HTML>");
		lblDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDangXuat.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblDangXuat.setBounds(1200, 0, 150, 42);
		pnlContentPane.add(lblDangXuat);

		lblMaNhanVien = new JLabel("M?? NH??N VI??N: ");
		lblMaNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMaNhanVien.setBounds(50, 670, 200, 42);
		txtMaNhanVien = new JLabel();
		txtMaNhanVien.setBounds(250, 670, 200, 42);
		txtMaNhanVien.setHorizontalAlignment(JLabel.LEFT);
		txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlContentPane.add(lblMaNhanVien);
		pnlContentPane.add(txtMaNhanVien);

		lblTenNhanVien = new JLabel("T??N NH??N VI??N: ");
		lblTenNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTenNhanVien.setBounds(50, 720, 200, 42);
		txtTenNhanVien = new JLabel("QL1001");
		txtTenNhanVien = new JLabel();
		txtTenNhanVien.setBounds(250, 720, 500, 42);
		txtTenNhanVien.setHorizontalAlignment(JLabel.LEFT);
		txtTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlContentPane.add(lblTenNhanVien);
		pnlContentPane.add(txtTenNhanVien);

		lblThoiGian = new JLabel("TH???I GIAN HI???N T???I:");
		lblThoiGian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThoiGian.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblThoiGian.setBounds(750, 720, 400, 42);
		txtThoiGian = new JLabel();
		txtThoiGian.setBounds(1010, 720, 400, 42);
		txtThoiGian.setHorizontalAlignment(JLabel.LEFT);
		txtThoiGian.setFont(new Font("Tahoma", Font.ITALIC, 20));

		Timer t = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtThoiGian.setText(DateFormat.getDateTimeInstance().format(new Date()));
			}
		});
		t.setRepeats(true);
		t.setCoalesce(true);
		t.setInitialDelay(0);
		t.start();
		pnlContentPane.add(lblThoiGian);
		pnlContentPane.add(txtThoiGian);

		pnlContentPane.setBackground(new Color(255, 255, 255));
		lblDangXuat.addMouseListener(this);
		return pnlContentPane;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(lblDangXuat)) {
			int result = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n ????ng xu???t kh??ng?", "?!",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				FrmDangNhap frmDN = new FrmDangNhap();
				frmDN.setVisible(true);
				frmDN.setLocationRelativeTo(null);
				dispose();
			}
		}
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
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
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
				try {
					new GUI().setVisible(true);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
