package com.gearvmdesktop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

public class FrmChiTietHoaDon extends JFrame implements ActionListener {

	private DefaultTableModel modelCTHD;
	private JTable tblCTHD;
	private JLabel lblTenCuaHang;
	private JLabel lblBanHangHoa;
	private JPanel p;
	private JPanel pNorth;
	private JPanel pSouth;
	private Box b;
	private Box bTenCuaHang;
	private Box bTenNV;
	private Box bNgayLap;
	private Box bNgayNhan;
	private Box bNoiNhan;
	private Box bMaHd;
	private Box bTenKH;
	private Box bSouth;
	private Box b1;
	private Box b2;

	private Box bVienTren;
	private Box bVienDuoi;
	private JTextField txtBanHangHoa;
	private JTextField txtTenNV;
	private JLabel lblTenNV;
	private JLabel lblNgayLap;
	private JTextField txtNgayLap;
	private JLabel lblNgayNhan;
	private JTextField txtNgayNhan;
	private JLabel lblNoiNhan;
	private JTextField txtNoiNhan;
	private JLabel lblMaHD;
	private JTextField txtMaHD;
	private JLabel lblTenKH;
	private JTextField txtTenKH;
	private JLabel lblThanhTien;
	private JTextField txtThanhTien;
	private JLabel lblGiamGia;
	private JTextField txtGiamGia;
	private Box bHangHoa;
	private JTextField pVienTren;
	private JTextField pVienDuoi;
	private JButton btnIn;
	private Box b3;


	public FrmChiTietHoaDon(String tenKH, String tenNV, String maHD, Date ngayLapHoaDon) throws RemoteException {
		// TODO Auto-generated constructor stub

		FlatLightLaf.setup();
		setTitle("Hóa Đơn Chi Tiết");
		setSize(800, 750);
		setLocationRelativeTo(null);

		p = new JPanel();
		pNorth = new JPanel();
		pSouth = new JPanel();
		pVienTren = new JTextField(60);
		pVienDuoi = new JTextField(60);

		pVienTren.setText(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		pVienTren.setForeground(Color.BLACK);
		pVienTren.setBorder(null);
		pVienTren.setBackground(null);

		pVienDuoi.setText(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		pVienDuoi.setForeground(Color.BLACK);
		pVienDuoi.setBorder(null);
		pVienDuoi.setBackground(null);

		b = Box.createVerticalBox();
		bTenCuaHang = Box.createHorizontalBox();
		bHangHoa = Box.createHorizontalBox();
		bTenNV = Box.createHorizontalBox();
		bNgayLap = Box.createHorizontalBox();
		bNgayNhan = Box.createHorizontalBox();
		bNoiNhan = Box.createHorizontalBox();
		bMaHd = Box.createHorizontalBox();
		bTenKH = Box.createHorizontalBox();

		bSouth = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();

		bVienTren = Box.createHorizontalBox();
		bVienDuoi = Box.createHorizontalBox();

		lblTenCuaHang = new JLabel();
		lblTenCuaHang.setFont(new Font("Arial", Font.BOLD, 30));
		lblTenCuaHang.setForeground(Color.black);

		txtBanHangHoa = new JTextField(40);
		// txtBanHangHoa.setForeground(Color.BLACK);
		txtBanHangHoa.setBorder(null);
		txtBanHangHoa.setBackground(null);
		txtBanHangHoa.setText("Hóa Đơn Bán Sản Phẩm");
		txtBanHangHoa.setFont(new Font("Arial", Font.BOLD, 20));
		txtBanHangHoa.setForeground(Color.black);

		lblTenNV = new JLabel("Nhân Viên Lập Hóa Đơn:          ");
		txtTenNV = new JTextField(40);
		txtTenNV.setForeground(Color.BLACK);
		txtTenNV.setBorder(null);
		txtTenNV.setBackground(null);
		txtTenNV.setText(tenNV);

		lblNgayLap = new JLabel("Ngày Lập Hóa Đơn");
		txtNgayLap = new JTextField(40);
		txtNgayLap.setForeground(Color.BLACK);
		txtNgayLap.setBorder(null);
		txtNgayLap.setBackground(null);
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		txtNgayLap.setText(date.format(ngayLapHoaDon));

		lblMaHD = new JLabel("Mã Hóa Đơn");
		txtMaHD = new JTextField(40);
		txtMaHD.setForeground(Color.BLACK);
		txtMaHD.setBorder(null);
		txtMaHD.setBackground(null);
		txtMaHD.setText(maHD);

		lblTenKH = new JLabel("Tên Khách Hàng");
		txtTenKH = new JTextField(40);
		txtTenKH.setForeground(Color.BLACK);
		txtTenKH.setBorder(null);
		txtTenKH.setBackground(null);
		txtTenKH.setText(tenKH);

		lblThanhTien = new JLabel("Tổng Thành Tiền:");
		txtThanhTien = new JTextField(40);
		txtThanhTien.setForeground(Color.BLACK);
		txtThanhTien.setBorder(null);
		txtThanhTien.setBackground(null);
		txtThanhTien.setText(null);

		String[] colHeader1 = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng",
				"Thành Tiền"};
		modelCTHD = new DefaultTableModel(colHeader1, 0);
		JScrollPane tblscroll = new JScrollPane(tblCTHD);
		tblCTHD = new JTable(modelCTHD) {
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
		tblCTHD.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblCTHD.setGridColor(getBackground());
		tblCTHD.setRowHeight(tblCTHD.getRowHeight() + 20);
		tblCTHD.setSelectionBackground(new Color(255, 255, 128));
		tblCTHD.setSelectionForeground(Color.BLACK);
		tblCTHD.setPreferredScrollableViewportSize(new Dimension(650, 350));
		JTableHeader tableHeader = tblCTHD.getTableHeader();
		tableHeader.setBackground(new Color(0, 148, 224));
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
		tblscroll.add(new JScrollPane(tblCTHD));
		tblscroll.setViewportView(tblCTHD);
		tblCTHD.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		add(p);
		p.add(pNorth, BorderLayout.NORTH);
		pNorth.add(b);
		b.add(bTenCuaHang);
		bTenCuaHang.add(lblTenCuaHang);

		b.add(bHangHoa);
		bHangHoa.add(txtBanHangHoa);

		b.add(bTenNV);
		bTenNV.add(lblTenNV);
		bTenNV.add(txtTenNV);

		b.add(bNgayLap);
		bNgayLap.add(lblNgayLap);
		bNgayLap.add(txtNgayLap);

		b.add(bMaHd);
		bMaHd.add(lblMaHD);
		bMaHd.add(txtMaHD);

		b.add(bTenKH);
		bTenKH.add(lblTenKH);
		bTenKH.add(txtTenKH);

		b.add(bVienTren);
		bVienTren.add(pVienTren);

		p.add(tblscroll, BorderLayout.CENTER);

		p.add(pSouth, BorderLayout.SOUTH);
		pSouth.add(bSouth);

		bSouth.add(bVienDuoi);
		bVienDuoi.add(pVienDuoi);

		bSouth.add(b2);
		b2.add(lblThanhTien);
		b2.add(txtThanhTien);

		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNgayLap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenKH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtThanhTien.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnIn = new JButton("IN HÓA ĐƠN");
		bSouth.add(b3);
		b3.add(btnIn);
		btnIn.setBackground(new Color(0, 148, 224));
		btnIn.setForeground(Color.WHITE);
		btnIn.setFocusPainted(false);
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));

		// txtBanHangHoa.setPreferredSize(lblMaHD.getPreferredSize());
		lblNgayLap.setPreferredSize(lblTenNV.getPreferredSize());
		lblTenKH.setPreferredSize(lblTenNV.getPreferredSize());
		lblThanhTien.setPreferredSize(lblTenNV.getPreferredSize());
		lblMaHD.setPreferredSize(lblTenNV.getPreferredSize());

		// bTenCuaHang.setBorder(new EmptyBorder(new Insets(10, 0, 10, 10)));
		bTenNV.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		bTenKH.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		bNgayLap.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		bNgayNhan.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		bNoiNhan.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		bMaHd.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b1.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b2.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b3.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

		btnIn.addActionListener(this);
	}
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		new FrmDangNhap().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnIn)) {
			JOptionPane.showMessageDialog(this, "In hoá đơn thành công");
		}
	}


}
