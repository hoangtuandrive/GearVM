package com.gearvmstore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.formdev.flatlaf.FlatLightLaf;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JDateChooser;

public class FrmBanHang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private JButton btnTim;
	private static DefaultTableModel modelSanPham;
	private static JTable tableSanPham;
	private JTextField txtMaKhachHang;
	private JCheckBox chkNam;
	private JTextField txtSoLuong;
	private JButton btnCong;
	private DefaultTableModel modelGioHang;
	private JTable tableGioHang;
	private JTextField txtTongTien;
	private JButton btnHuy;
	private JButton btnThanhToan;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JLabel lblMa;
	private JLabel lblTen;
	private JLabel lblSDT;
	private JLabel lblEmail;
	private JLabel lblDiaChi;
	private JLabel lblSoLuong;
	private JTextField txtTenKhachHang;
	private JLabel lblGioiTinh;
	private JComboBox<String> cmbGioiTinh;
	private JLabel lblTongTien;
	private JDateChooser txtNgaySinh;
	private JLabel lblNgaySinh;
	private JLabel lblCMND;
	private JTextField txtCMND;
	private static JComboBox<String> cmbDanhSachSdt;
	private static JComboBox<String> cmbTim;
	private JButton btnTimKHCu;
	private JLabel lblGioHang;
	private JPanel pTitle1;
	private JLabel lblTitle;
	private JLabel lblTitle1;
	private JButton btnTaoGioHang;
	private JButton btnTru;
	private Date ngayLapHoaDon;
	private static JComboBox<String> cmbChon;
	private static JComboBox<String> cmbGioHang;
	public static String maHDMoiDat = "";
	public static String maKHDatGioHang = "";

	public JPanel createPanelBanHang() throws RemoteException {
		FlatLightLaf.setup();
		// TODO Auto-generated constructor stub
		setTitle("FrmBanHang");
		setSize(1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel p = new JPanel();

		Box b = Box.createHorizontalBox();
		Box b1 = Box.createVerticalBox();
		Box b2 = Box.createVerticalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createVerticalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		Box b10 = Box.createHorizontalBox();
		Box b11 = Box.createHorizontalBox();
		Box b12 = Box.createHorizontalBox();
		Box b13 = Box.createHorizontalBox();
		Box b14 = Box.createHorizontalBox();
		Box b15 = Box.createHorizontalBox();
		Box b16 = Box.createHorizontalBox();
		Box b17 = Box.createHorizontalBox();

		String[] tim = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn" };
		cmbChon = new JComboBox<String>(tim);
		b3.add(cmbChon);
		b3.add(Box.createHorizontalStrut(10));
		cmbTim = new JComboBox<String>();
		cmbTim.setEditable(true);
		AutoCompleteDecorator.decorate(cmbTim);
		cmbTim.setMaximumRowCount(10);
		cmbChon.setSize(20, cmbTim.getPreferredSize().height);
		b3.add(cmbTim);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(btnTim = new JButton("TÌM HÀNG", new ImageIcon("image/timhanghoa.png")));
		btnTim.setBackground(new Color(0, 148, 224));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFocusPainted(false);

		b3.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "TÌM SẢN PHẨM	: "));

		String[] colHeader = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng Tồn" };
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
		tableSanPham.setPreferredScrollableViewportSize(new Dimension(650, 580));
		b1.add(b3);
		b1.add(tblscroll);
		tblscroll.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH SẢN PHẨM: "));

		b.add(lblTitle = new JLabel("THÔNG TIN KHÁCH HÀNG"));
		b5.add(lblMa = new JLabel("Mã Khách Hàng:"));
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txtMaKhachHang = new JTextField());
		b6.add(lblTen = new JLabel("Tên Khách Hàng:"));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(txtTenKhachHang = new JTextField());
		b14.add(lblNgaySinh = new JLabel("Ngày Sinh:"));
		b14.add(Box.createHorizontalStrut(10));
		b14.add(txtNgaySinh = new JDateChooser());
		txtNgaySinh.setDateFormatString("yyyy-MM-dd");
		txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));
		b15.add(lblCMND = new JLabel("CMND:"));
		b15.add(Box.createHorizontalStrut(10));
		b15.add(txtCMND = new JTextField());
		String[] gioitinh = { "Nam", "Nữ" };
		cmbGioiTinh = new JComboBox<String>(gioitinh);
		b13.add(lblGioiTinh = new JLabel("Giới Tính:"));
		b13.add(Box.createHorizontalStrut(10));
		b13.add(cmbGioiTinh);
		b7.add(lblSDT = new JLabel("SDT:"));
		b7.add(Box.createHorizontalStrut(10));
		cmbDanhSachSdt = new JComboBox<String>();
		cmbDanhSachSdt.setEditable(true);
		AutoCompleteDecorator.decorate(cmbDanhSachSdt);
		cmbDanhSachSdt.setMaximumRowCount(10);
		b7.add(cmbDanhSachSdt);
		btnTimKHCu = new JButton("TÌM SDT", new ImageIcon("image/timkiem.png"));
		btnTimKHCu.setBackground(new Color(0, 148, 224));
		btnTimKHCu.setForeground(Color.WHITE);
		btnTimKHCu.setFocusPainted(false);
		b7.add(Box.createHorizontalStrut(300));
		b7.add(btnTimKHCu);
		b8.add(lblEmail = new JLabel("Email:"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(txtEmail = new JTextField());
		b9.add(lblDiaChi = new JLabel("Địa Chỉ:"));
		b9.add(Box.createHorizontalStrut(10));
		b9.add(txtDiaChi = new JTextField());

		b17.add(pTitle1 = new JPanel());
		pTitle1.add(lblTitle1 = new JLabel("GIỎ HÀNG"));
		b16.add(lblGioHang = new JLabel("Giỏ Hàng Của:"));
		b16.add(Box.createHorizontalStrut(10));
		cmbGioHang = new JComboBox<String>();
		cmbGioHang.setEditable(true);
		AutoCompleteDecorator.decorate(cmbGioHang);
		cmbGioHang.setMaximumRowCount(10);
		b16.add(cmbGioHang);
		b11.add(lblSoLuong = new JLabel("Số Lượng:"));
		b11.add(Box.createHorizontalStrut(10));
		b11.add(txtSoLuong = new JTextField());
		b11.add(Box.createHorizontalStrut(200));

		b4.add(b);
		b4.add(b7);
		b4.add(b5);
		b4.add(b6);
		b4.add(b14);
		b4.add(b15);
		b4.add(b13);
		b4.add(b8);
		b4.add(b9);
		b4.add(b17);
		b4.add(b16);
		b4.add(b11);
		
		String[] colHeader1 = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Hàng", "Nhà Cung Cấp", "Đơn Giá", "Số Lượng",
				"Thành Tiền" };
		modelGioHang = new DefaultTableModel(colHeader1, 0);
		tableGioHang = new JTable(modelGioHang) {
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
		tableGioHang.setGridColor(getBackground());
		tableGioHang.setRowHeight(tableGioHang.getRowHeight() + 30);
		tableGioHang.setSelectionBackground(new Color(255, 255, 128));
		tableGioHang.setSelectionForeground(Color.BLACK);
		JTableHeader tableHeader1 = tableGioHang.getTableHeader();
		tableHeader1.setBackground(new Color(0, 148, 224));
		tableHeader1.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableHeader1.setForeground(Color.WHITE);
		tableHeader1.setPreferredSize(new Dimension(WIDTH, 30));
		tableSanPham.setRowHeight(tableSanPham.getRowHeight() + 20);

		tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(165);

		JScrollPane tblscroll1 = new JScrollPane(tableGioHang);
		tblscroll1.setBorder(BorderFactory.createTitledBorder("GIỎ HÀNG: "));
		tableGioHang.setPreferredScrollableViewportSize(new Dimension(500, 105));
		b10.add(lblTongTien = new JLabel("Tổng Tiền:"));
		b10.add(txtTongTien = new JTextField());
		b12.add(btnThanhToan = new JButton("THANH TOÁN", new ImageIcon("image/thanhtoan.png")));
		btnThanhToan.setBackground(new Color(0, 148, 224));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFocusPainted(false);
		b12.add(Box.createHorizontalStrut(10));
		b12.add(btnHuy = new JButton("LÀM MỚI", new ImageIcon("image/lammoi.png")));
		btnHuy.setBackground(new Color(0, 148, 224));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFocusPainted(false);
		b2.add(b4);
		b11.add(Box.createHorizontalStrut(10));
		b11.add(btnCong = new JButton("THÊM", new ImageIcon("image/them.png")));
		btnCong.setBackground(new Color(0, 148, 224));
		btnCong.setForeground(Color.WHITE);
		btnCong.setFocusPainted(false);
		b11.add(Box.createHorizontalStrut(5));
		b11.add(btnTru = new JButton("XÓA", new ImageIcon("image/xoa.png")));
		btnTru.setBackground(new Color(0, 148, 224));
		btnTru.setForeground(Color.WHITE);
		btnTru.setFocusPainted(false);
		b9.add(Box.createHorizontalStrut(10));
		b9.add(btnTaoGioHang = new JButton("TẠO GIỎ HÀNG", new ImageIcon("image/them.png")));
		btnTaoGioHang.setBackground(new Color(0, 148, 224));
		btnTaoGioHang.setForeground(Color.WHITE);
		btnTaoGioHang.setFocusPainted(false);
		b2.add(tblscroll1);
		b2.add(b10);
		JPanel p1 = new JPanel();
//		p1.setLayout(new BorderLayout());
		JPanel p2 = new JPanel();
		p2.add(b12);
		b2.add(p2);
		p1.add(b1);
		p1.add(b2);
		p.add(p1, BorderLayout.CENTER);

		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblSDT.setPreferredSize(lblTen.getPreferredSize());
		lblEmail.setPreferredSize(lblTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTen.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
		lblTongTien.setPreferredSize(lblTen.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblTen.getPreferredSize());
		lblCMND.setPreferredSize(lblTen.getPreferredSize());
		lblGioHang.setPreferredSize(lblTen.getPreferredSize());
		cmbDanhSachSdt.setPreferredSize(lblTen.getPreferredSize());

		b.setBorder(new EmptyBorder(new Insets(10, 10, 0, 10)));
		b3.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b5.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b6.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b7.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b8.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b9.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b10.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b11.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b12.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b13.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b14.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b15.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b16.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		b17.setBorder(new EmptyBorder(new Insets(10, 10, 0, 10)));

		btnThanhToan.setBounds(36, 330, 79, 13);
		btnHuy.setBounds(131, 330, 79, 13);
		txtMaKhachHang.setEditable(false);

		cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbDanhSachSdt.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTimKHCu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTaoGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbGioHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSoLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCong.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTru.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));

		tableGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableGioHang.setDefaultEditor(Object.class, null);
		tableGioHang.getTableHeader().setReorderingAllowed(false);
		tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSanPham.setDefaultEditor(Object.class, null);
		tableSanPham.getTableHeader().setReorderingAllowed(false);

		txtTongTien.setBorder(null);
		txtTongTien.setBackground(null);
		txtTongTien.setText(null);

		return p;
	}
}
