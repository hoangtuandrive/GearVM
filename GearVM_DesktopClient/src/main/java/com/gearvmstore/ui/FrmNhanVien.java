package com.gearvmstore.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gearvmstore.model.Employee;
import com.gearvmstore.model.Gender;
import com.gearvmstore.model.Role;
import com.gearvmstore.service.EmployeeService;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

public class FrmNhanVien extends javax.swing.JFrame implements ActionListener, MouseListener {
	private static final String tableName = "employees/";
	private Boolean isQuanLy;
	private JComboBox<String> cmbChon;
	private static JComboBox<String> cmbTim;
	private JButton btnTim;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnThayDoiTinhTrangLamViec;
	private java.awt.Label lblCMND;
	private java.awt.Label lblChucVu;
	private java.awt.Label lblGioiTinh;
	private java.awt.Label lblNgaySinh;
	private java.awt.Label lblSDT;
	private java.awt.Label lblTen;
	private java.awt.Label lblEmail;
	private java.awt.Label lblDiaChi;
	private java.awt.Label lblLuong;
	private java.awt.Label lblmaNhanVien;
	private java.awt.Label lblTrangThai;
	private JPanel pnChucNang;
	private JPanel pnThongTin;
	private JScrollPane pntblNhanVien;
	private static JTable tableNhanVien;
	private JTextField txtCMND;
	private JComboBox<String> cmbChucVu;
	private JComboBox<String> cmbGioiTinh;
	private JTextField txtMaNhanVien;
	private JDateChooser txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtTen;
	private JTextField txtLuong;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtTrangThai;
	private JPanel pnlTimKiem;
	private static DefaultTableModel modelNhanVien;


	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	public JPanel createPanelNhanVien() throws IOException {
		FlatLightLaf.setup();
		pntblNhanVien = new JScrollPane();
		tableNhanVien = new JTable();
		pnlTimKiem = new JPanel();
		pnThongTin = new JPanel();
		lblmaNhanVien = new java.awt.Label();
		txtMaNhanVien = new JTextField();
		lblTen = new java.awt.Label();
		txtTen = new JTextField();
		lblNgaySinh = new java.awt.Label();
		txtNgaySinh = new JDateChooser();
		lblCMND = new java.awt.Label();
		txtCMND = new JTextField();
		lblGioiTinh = new java.awt.Label();
		cmbGioiTinh = new JComboBox<String>();
		lblSDT = new java.awt.Label();
		txtSDT = new JTextField();
		lblChucVu = new java.awt.Label();
		cmbChucVu = new JComboBox<String>();
		lblLuong = new java.awt.Label();
		txtLuong = new JTextField();
		lblEmail = new java.awt.Label();
		txtEmail = new JTextField();
		lblDiaChi = new java.awt.Label();
		txtDiaChi = new JTextField();
		lblTrangThai = new java.awt.Label();
		txtTrangThai = new JTextField();
		pnChucNang = new JPanel();
		btnThem = new JButton();
		btnSua = new JButton();
		btnThayDoiTinhTrangLamViec = new JButton();

		pnThongTin.setBackground(new Color(219, 243, 255));
		pnChucNang.setBackground(new Color(219, 243, 255));
		btnThem.setBackground(new Color(0, 148, 224));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFocusPainted(false);
		btnSua.setBackground(new Color(0, 148, 224));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFocusPainted(false);
		btnThayDoiTinhTrangLamViec.setBackground(new Color(0, 148, 224));
		btnThayDoiTinhTrangLamViec.setForeground(Color.WHITE);
		btnThayDoiTinhTrangLamViec.setFocusPainted(false);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		String[] header = { "Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "CMND", "Giới Tính", "SDT", "Chức Vụ",
				"Email", "Địa Chỉ", "Lương", "Trạng thái" };
		modelNhanVien = new DefaultTableModel(header, 0);
		tableNhanVien = new JTable(modelNhanVien) {
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
		tableNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableNhanVien.setGridColor(getBackground());
		tableNhanVien.setRowHeight(tableNhanVien.getRowHeight() + 20);
		tableNhanVien.setSelectionBackground(new Color(255, 255, 128));
		tableNhanVien.setSelectionForeground(Color.BLACK);
		JTableHeader tableHeader = tableNhanVien.getTableHeader();
		tableHeader.setBackground(new Color(0, 148, 224));
		tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
		pntblNhanVien.add(new JScrollPane(tableNhanVien));

		pntblNhanVien.setViewportView(tableNhanVien);
		tableNhanVien.getColumnModel().getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
		pnThongTin.setToolTipText("Info of selected table object");

		lblmaNhanVien.setText("Mã nhân viên:");
		lblTen.setText("Tên nhân viên:");
		lblNgaySinh.setText("Ngày sinh:");
		lblCMND.setText("CMND:");
		lblGioiTinh.setText("Giới tính:");
		lblSDT.setText("SDT:");
		lblChucVu.setText("Chức vụ:");
		lblEmail.setText("Email:");
		lblDiaChi.setText("Địa chỉ:");
		lblLuong.setText("Lương:");
		lblTrangThai.setText("Trạng Thái:");

		txtNgaySinh.setDateFormatString("dd-MM-yyyy");
		txtNgaySinh.setDate(new Date(1999 - 1900, 1 - 1, 1));

		String[] gioitinh = { "Nam", "Nữ", "Không xác định" };
		cmbGioiTinh = new JComboBox<String>(gioitinh);

		String[] chucvu = { "Nhân Viên", "Quản Lý" };
		cmbChucVu = new JComboBox<String>(chucvu);

		javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
		pnThongTin.setLayout(pnThongTinLayout);
		pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtMaNhanVien,
										javax.swing.GroupLayout.PREFERRED_SIZE, 169,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(cmbChucVu, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtLuong, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		pnThongTinLayout.setVerticalGroup(pnThongTinLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblmaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCMND, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));

		pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng:"));

		btnThem.setText("THÊM");
		btnSua.setText("SỬA");
		btnThayDoiTinhTrangLamViec.setText("CHO NGHỈ VIỆC");

		javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
		pnChucNang.setLayout(pnChucNangLayout);
		pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChucNangLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnThayDoiTinhTrangLamViec)
						.addGap(48, 48, 48)));
		pnChucNangLayout.setVerticalGroup(pnChucNangLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnChucNangLayout.createSequentialGroup().addGap(26, 26, 26)
						.addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnThem).addComponent(btnSua).addComponent(btnThayDoiTinhTrangLamViec))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		Box b = Box.createHorizontalBox();
		String[] tim = { "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "SDT", "Chức Vụ", "Lương", "CMND", "Ngày Sinh",
				"Địa Chỉ", "Email", "Trạng thái" };
		cmbChon = new JComboBox<String>(tim);
		cmbTim = new JComboBox<String>();
		cmbTim.setEditable(true);
		AutoCompleteDecorator.decorate(cmbTim);
		cmbTim.setMaximumRowCount(10);
		cmbChon.setSize(20, cmbTim.getPreferredSize().height);
		btnTim = new JButton("TÌM KIẾM", new ImageIcon("image/timkiem.png"));
		btnTim.setBackground(new Color(0, 148, 224));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFocusPainted(false);

		b.add(cmbChon);
		b.add(Box.createHorizontalStrut(10));
		b.add(cmbTim);
		b.add(Box.createHorizontalStrut(10));
		b.add(btnTim);
		b.add(Box.createHorizontalStrut(30));
		pnlTimKiem.add(b);

		JPanel panel = new JPanel();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		panel.add(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(pntblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
								.addComponent(pnlTimKiem))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(pnChucNang,
										javax.swing.GroupLayout.PREFERRED_SIZE, 300,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
								.addComponent(pntblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 720,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
										pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();

		 txtMaNhanVien.setEditable(false);
		 txtTrangThai.setEditable(false);

		tableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(110);

		pntblNhanVien.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DANH SÁCH NHÂN VIÊN: "));
		lblmaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbChucVu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLuong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTrangThai.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThayDoiTinhTrangLamViec.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));

		// if (!isQuanLy) {
		// btnThem.setEnabled(false);
		// btnSua.setEnabled(false);
		// btnXoa.setEnabled(false);
		// }

		tableNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNhanVien.setDefaultEditor(Object.class, null);
		tableNhanVien.getTableHeader().setReorderingAllowed(false);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnThayDoiTinhTrangLamViec.addActionListener(this);
		btnTim.addActionListener(this);
		tableNhanVien.addMouseListener(this);

		readDatabaseToTable();

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				if (postRequest()) {
					JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
					readDatabaseToTable();
				} else {
					JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Thất bại",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnSua)){
			try{
				if(putRequest()){
					JOptionPane.showMessageDialog(this, "Sửa nhân viên mã số " + txtMaNhanVien.getText() + " thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
					readDatabaseToTable();
				} else {
					JOptionPane.showMessageDialog(this, "Sửa nhân viên mã số " + txtMaNhanVien.getText() + " thất bại!", "Thất bại",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (o.equals(btnThayDoiTinhTrangLamViec)){

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		txtMaNhanVien.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtTen.setText(modelNhanVien.getValueAt(row, 1).toString());
		String dateString = modelNhanVien.getValueAt(row, 2).toString();
		String[] a = dateString.split("-");
		txtNgaySinh
				.setDate(new Date(Integer.parseInt(a[2]) - 1900, Integer.parseInt(a[1]) - 1,
						Integer.parseInt(a[0])));

		txtCMND.setText(modelNhanVien.getValueAt(row, 3).toString());
		cmbGioiTinh.setSelectedItem(FrmNhanVien.modelNhanVien.getValueAt(row, 4).toString().trim());
		txtSDT.setText(modelNhanVien.getValueAt(row, 5).toString());
		cmbChucVu.setSelectedItem(FrmNhanVien.modelNhanVien.getValueAt(row, 6).toString().trim());
		txtEmail.setText(modelNhanVien.getValueAt(row, 7).toString());
		txtDiaChi.setText(modelNhanVien.getValueAt(row, 8).toString());
		String luong[] = modelNhanVien.getValueAt(row, 9).toString().split(",");
		String tienLuong = "";
		for (int i = 0; i < luong.length; i++)
			tienLuong += luong[i];
		txtLuong.setText(tienLuong);
		txtTrangThai.setText(modelNhanVien.getValueAt(row, 10).toString());
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

	private void emptyTextField() {
		txtMaNhanVien.setText(null);
		txtTen.setText(null);
		txtNgaySinh.setDate(null);
		txtCMND.setText(null);
		cmbGioiTinh.setSelectedIndex(0);
		txtSDT.setText(null);
		cmbChucVu.setSelectedIndex(0);
		txtEmail.setText(null);
		txtDiaChi.setText(null);
		txtLuong.setText(null);
	}

	public boolean postRequest() throws IOException {
		Employee e = new Employee();
		e.setName(txtTen.getText());
		e.setGender(getGenderFromCmb());
		e.setNationalId(txtCMND.getText());
		e.setPhoneNumber(txtSDT.getText());
		e.setRole(getRoleFromCmb());
		e.setAddress(txtDiaChi.getText());
		e.setEmail(txtEmail.getText());
		e.setSalary(Double.parseDouble(txtLuong.getText()));
		Calendar birthdayCalendar = txtNgaySinh.getCalendar();
		LocalDate birthdayLocalDate = LocalDate.ofInstant(birthdayCalendar.toInstant(), ZoneId.systemDefault());
		e.setDateOfBirth(birthdayLocalDate);
		return EmployeeService.postRequest(e);
	}

	public boolean putRequest() throws IOException {
		Employee e = new Employee();
		e.setId(Long.parseLong(txtMaNhanVien.getText()));
		e.setName(txtTen.getText());
		e.setGender(getGenderFromCmb());
		e.setNationalId(txtCMND.getText());
		e.setPhoneNumber(txtSDT.getText());
		e.setRole(getRoleFromCmb());
		e.setAddress(txtDiaChi.getText());
		e.setEmail(txtEmail.getText());
		e.setSalary(Double.parseDouble(txtLuong.getText()));
		Calendar birthdayCalendar = txtNgaySinh.getCalendar();
		LocalDate birthdayLocalDate = LocalDate.ofInstant(birthdayCalendar.toInstant(), ZoneId.systemDefault());
		e.setDateOfBirth(birthdayLocalDate);
		return EmployeeService.putRequest(e);
	}

	public boolean patchWorkStatusRequest() throws IOException{
		Employee e = new Employee();
		e.setId(Long.parseLong(txtMaNhanVien.getText()));
		return EmployeeService.patchWorkStatusRequest(e);
	}

	public Gender getGenderFromCmb() {
		int index = cmbGioiTinh.getSelectedIndex();
		if (index == 0) return Gender.MALE;
		else if (index == 1) return Gender.FEMALE;
		else return Gender.UNDEFINED;
	}

	public Role getRoleFromCmb() {
		int index = cmbChucVu.getSelectedIndex();
		if (index == 0 ) return Role.EMPLOYEE;
		else return Role.MANAGER;
	}

	public void readDatabaseToTable() throws IOException {
		emptyTable();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DecimalFormat df = new DecimalFormat("#,##0");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		BufferedReader rd = EmployeeService.getAllRequest(tableName);
		List<Employee> listEmployee = Arrays.asList(mapper.readValue(rd, Employee[].class));
		for(Employee e : listEmployee){
			String gender;
			String role;

			if(e.getGender() == Gender.MALE) gender = "Nam";
			else if(e.getGender() == Gender.FEMALE) gender = "Nữ";
			else gender = "Không xác định";

			if(e.getRole() == Role.EMPLOYEE) role = "Nhân viên";
			else role = "Quản lý";

			modelNhanVien.addRow(new Object[] { e.getId(), e.getName().trim(),
					dateFormat.format(e.getDateOfBirth()), e.getNationalId().trim(), gender,
					e.getPhoneNumber().trim(), role, e.getEmail().trim(), e.getAddress().trim(),
					df.format(e.getSalary()), e.isWorkStatus() ? "Đang làm" : "Đã nghỉ việc" });
		}
	}

	public static void emptyTable() {
		DefaultTableModel dm = (DefaultTableModel) tableNhanVien.getModel();
		dm.setRowCount(0);
	}
}
