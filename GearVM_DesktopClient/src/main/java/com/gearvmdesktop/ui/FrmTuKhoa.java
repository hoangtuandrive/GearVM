package com.gearvmdesktop.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmdesktop.service.PromptService;
import com.gearvmstore.GearVM.model.Prompt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FrmTuKhoa extends JFrame implements ActionListener, MouseListener {
    private static final String tableName = "prompts/";
    private static JTextField txtTim;
    private static DefaultTableModel modelKhachHang;
    private static JTable tableTuKhoa;
    private JComboBox<String> cmbChon;
    private JButton btnTim;
    private JPanel pnExcel;
    private JButton btnImport;
    private JButton btnExport;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private Label lblCauTraLoi;
    private Label lblCauHoi;
    private JPanel pnChucNang;
    private JPanel pnThongTin;
    private JScrollPane pntblTuKhoa;
    private JTextArea txtCauTraLoi;
    private JPanel pnlTimKiem;
    private JTextField txtCauHoi;

    public JPanel createPanelTuKhoa() throws IOException {
        FlatLightLaf.setup();
        pntblTuKhoa = new JScrollPane();
        tableTuKhoa = new JTable();
        pnlTimKiem = new JPanel();
        pnThongTin = new JPanel();
        lblCauHoi = new Label();
        txtCauHoi = new JTextField();
        lblCauTraLoi = new Label();
        txtCauTraLoi = new JTextArea();

        pnChucNang = new JPanel();
        btnThem = new JButton();
        btnSua = new JButton();
        btnXoa = new JButton();

        pnExcel = new JPanel();
        btnImport = new JButton();
        btnExport = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String[] header = {"Từ Khóa Câu Hỏi", "Câu Trả Lời"};

        modelKhachHang = new DefaultTableModel(header, 0);
        tableTuKhoa = new JTable(modelKhachHang) {
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
        tableTuKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tableTuKhoa.setGridColor(getBackground());
        tableTuKhoa.setRowHeight(tableTuKhoa.getRowHeight() + 20);
        tableTuKhoa.setSelectionBackground(new Color(255, 255, 128));
        tableTuKhoa.setSelectionForeground(Color.BLACK);

        JTableHeader tableHeader = tableTuKhoa.getTableHeader();
        tableHeader.setBackground(new Color(0, 148, 224));
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setPreferredSize(new Dimension(WIDTH, 30));
        pntblTuKhoa.add(new JScrollPane(tableTuKhoa));
        pntblTuKhoa.setViewportView(tableTuKhoa);
        tableTuKhoa.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết:"));
        pnThongTin.setToolTipText("Info of selected table object");
        pnThongTin.setName("pnThongTin"); // NOI18N

        lblCauHoi.setText("Từ Khóa Câu Hỏi:");
        lblCauTraLoi.setText("Câu Trả Lời:");

        GroupLayout pnThongTinLayout = new GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblCauHoi, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCauTraLoi, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnThongTinLayout.createSequentialGroup().addComponent(txtCauHoi,
                                        GroupLayout.PREFERRED_SIZE, 169,
                                        GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCauTraLoi, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnThongTinLayout.setVerticalGroup(pnThongTinLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnThongTinLayout.createSequentialGroup().addContainerGap().addGroup(pnThongTinLayout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCauHoi, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCauHoi, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtCauTraLoi, GroupLayout.PREFERRED_SIZE,
                                        300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCauTraLoi, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addContainerGap(28, Short.MAX_VALUE)));

        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng:"));
        pnExcel.setBorder(BorderFactory.createTitledBorder("Xử lý excel:"));

        ClassLoader classLoader = getClass().getClassLoader();
        URL iconThem = classLoader.getResource("assets/them.png");
        URL iconSua = classLoader.getResource("assets/capnhat.png");
        URL iconXoa = classLoader.getResource("assets/xoa.png");
        URL iconXuatFile = classLoader.getResource("assets/xuatexcel.png");
        URL iconNhapFile = classLoader.getResource("assets/docfile.png");
        URL iconLuu = classLoader.getResource("assets/luu.png");
        URL iconHuy = classLoader.getResource("assets/huy.png");
        URL iconTim = classLoader.getResource("assets/timkiem.png");

        btnThem.setText("THÊM");
        btnThem.setIcon(new ImageIcon(iconThem));

        btnSua.setText("SỬA");
        btnSua.setIcon(new ImageIcon(iconSua));

        btnXoa.setText("XÓA");
        btnXoa.setIcon(new ImageIcon(iconXoa));

        btnSave.setText("LƯU");
//        btnSave.setIcon(new ImageIcon(iconLuu));


        btnCancel.setText("HỦY");
//        btnCancel.setIcon(new ImageIcon(iconHuy));

        btnImport.setText("NHẬP FILE");
//        btnImport.setIcon(new ImageIcon(iconNhapFile));

        btnExport.setText("XUẤT FILE");
        btnExport.setIcon(new ImageIcon(iconXuatFile));


        btnImport.setSize(new Dimension(30, 200));

        pnExcel.setBackground(new Color(219, 243, 255));
        pnThongTin.setBackground(new Color(219, 243, 255));
        pnChucNang.setBackground(new Color(219, 243, 255));
        btnThem.setBackground(new Color(0, 148, 224));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);
        btnSua.setBackground(new Color(0, 148, 224));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFocusPainted(false);
        btnXoa.setBackground(new Color(0, 148, 224));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFocusPainted(false);

        // Excel button
        btnSave.setBackground(new Color(0, 148, 224));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnCancel.setBackground(new Color(0, 148, 224));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnImport.setBackground(new Color(0, 148, 224));
        btnImport.setForeground(Color.WHITE);
        btnImport.setFocusPainted(false);
        btnExport.setBackground(new Color(0, 148, 224));
        btnExport.setForeground(Color.WHITE);
        btnExport.setFocusPainted(false);


        GroupLayout pnChucNangLayout = new GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(50)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnThem)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSua)
                        .addGap(48))
                .addGroup(GroupLayout.Alignment.LEADING, pnChucNangLayout.createSequentialGroup()
                        .addGap(70)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnXoa)
                        .addGap(50)));
        pnChucNangLayout.setVerticalGroup(pnChucNangLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnChucNangLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnChucNangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem).addComponent(btnSua))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)
                        .addGroup(pnChucNangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnXoa).addComponent(btnXoa))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15)));

        GroupLayout pnExcelLayout = new GroupLayout(pnExcel);
        pnExcel.setLayout(pnExcelLayout);
        pnExcelLayout.setHorizontalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
                        .addGap(50)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnImport)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnExport)
                        .addGap(60)));

//                .addGroup(GroupLayout.Alignment.LEADING, pnExcelLayout.createSequentialGroup()
//                        .addGap(40)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnSave)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnCancel)
//                        .addGap(80)));
        pnExcelLayout.setVerticalGroup(pnExcelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnExcelLayout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(pnExcelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(btnImport)
                                .addComponent(btnExport))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5)));
//                        .addGroup(pnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                .addComponent(btnSave).addComponent(btnCancel))
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addGap(15)));


        Box b = Box.createHorizontalBox();
        String[] tim = {"Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "SDT", "CMND", "Ngày Sinh", "Địa Chỉ",
                "Email"};
        cmbChon = new JComboBox<String>(tim);
        txtTim = new JTextField(15);
        cmbChon.setSize(20, txtTim.getPreferredSize().height);
        btnTim = new JButton("TÌM KIẾM", new ImageIcon(iconTim));
        btnTim.setBackground(new Color(0, 148, 224));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

//        b.add(cmbChon);
//        b.add(Box.createHorizontalStrut(10));
        b.add(txtTim);
        b.add(Box.createHorizontalStrut(10));
        b.add(btnTim);
        b.add(Box.createHorizontalStrut(30));
        pnlTimKiem.add(b);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        panel.add(getContentPane());
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pntblTuKhoa, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                                .addComponent(pnlTimKiem))
                        .addContainerGap(20, 20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnThongTin, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnExcel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                ))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addComponent(pntblTuKhoa, GroupLayout.PREFERRED_SIZE, 670,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pnlTimKiem, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                .addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
                                        .addComponent(pnThongTin, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 20, 20)
                                        .addComponent(pnExcel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 27, Short.MAX_VALUE)))
                        .addContainerGap()));

        pntblTuKhoa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                "DANH SÁCH CÂU TRẢ LỜI: "));
        lblCauHoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtCauHoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCauTraLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtCauTraLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        cmbChon.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));

        tableTuKhoa.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableTuKhoa.getColumnModel().getColumn(1).setPreferredWidth(700);

        tableTuKhoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTuKhoa.setDefaultEditor(Object.class, null);
        tableTuKhoa.getTableHeader().setReorderingAllowed(false);
        pack();

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnExport.addActionListener(this);
        tableTuKhoa.addMouseListener(this);

        readDatabaseToTable();
        GUI.disableWarning();

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (!validInput()) {
                return;
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if (postRequest()) {
                            JOptionPane.showMessageDialog(this, "Thêm từ khóa thành công!", "Thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            readDatabaseToTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm từ khóa thất bại!", "Thất bại",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException | JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (o.equals(btnSua)) {
            if (!validInput()) {
                return;
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if (putRequest()) {
                            JOptionPane.showMessageDialog(this, "Sửa từ khóa thành công!", "Thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            readDatabaseToTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Sửa từ khóa thất bại!", "Thất bại",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException | JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (o.equals(btnXoa)) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if (deleteRequest()) {
                        JOptionPane.showMessageDialog(this, "Xóa từ khóa thành công!", "Thành công",
                                JOptionPane.INFORMATION_MESSAGE);
                        readDatabaseToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa từ khóa thất bại!", "Thất bại",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (o.equals(btnExport)) {
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setCurrentDirectory(new File(System.getProperty("user.dir")));
            //filter the files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel(.xls)", ".xls");
            fileDialog.setAcceptAllFileFilterUsed(false);
            fileDialog.addChoosableFileFilter(filter);
            int result = fileDialog.showSaveDialog(null);
            //if the user click on save in Jfilechooser
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileDialog.getSelectedFile();
                String filePath = file.getAbsolutePath();
                if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))) {
                    filePath += ".xls";
                }
                if (exportExcel(filePath))
                    JOptionPane.showMessageDialog(null, "Ghi file thành công!!", "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Ghi file thất bại!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            emptyTextField();
            int row = tableTuKhoa.getSelectedRow();
            txtCauHoi.setText(modelKhachHang.getValueAt(row, 0).toString().trim());
            txtCauTraLoi.setText(modelKhachHang.getValueAt(row, 1).toString().trim());
        } catch (NullPointerException ex) {

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

    private void emptyTextField() {
        txtCauHoi.setText(null);
        txtCauTraLoi.setText(null);
    }

    public boolean postRequest() throws IOException, JSONException {
        Prompt p = new Prompt();
        p.setQuestion(txtCauHoi.getText());
        p.setAnswer(txtCauTraLoi.getText());
        return PromptService.postRequest(p);
    }

    public boolean putRequest() throws IOException, JSONException {
        Prompt p = new Prompt();
        p.setQuestion(txtCauHoi.getText());
        p.setAnswer(txtCauTraLoi.getText());
        return PromptService.putRequest(p);
    }

    public boolean deleteRequest() throws IOException, JSONException {
        Prompt p = new Prompt();
        p.setQuestion(txtCauHoi.getText());
        return PromptService.deleteRequest(p);
    }

    public static void emptyTable() {
        DefaultTableModel dm = (DefaultTableModel) tableTuKhoa.getModel();
        dm.setRowCount(0);
    }

    public static void readDatabaseToTable() throws IOException {
        emptyTable();

        ObjectMapper mapper = new ObjectMapper();
        BufferedReader rd = PromptService.getAllRequest(tableName);
        List<Prompt> promptList = Arrays.asList(mapper.readValue(rd, Prompt[].class));
        for (Prompt p : promptList) {
            modelKhachHang.addRow(new Object[]{p.getQuestion(), p.getAnswer()});
        }
    }

    public boolean exportExcel(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("DANH SÁCH KHÁCH HÀNG");

            HSSFRow row;
            HSSFCell cell;

            // Dòng 1 tên
            cell = worksheet.createRow(1).createCell(1);

            HSSFFont newFont = cell.getSheet().getWorkbook().createFont();
            newFont.setBold(true);
            newFont.setFontHeightInPoints((short) 13);
            CellStyle styleTenDanhSach = worksheet.getWorkbook().createCellStyle();
            styleTenDanhSach.setAlignment(HorizontalAlignment.CENTER);
            styleTenDanhSach.setFont(newFont);

            cell.setCellValue("DANH SÁCH TỪ KHÓA");
            cell.setCellStyle(styleTenDanhSach);

            String[] header = {"STT", "Từ Khóa Câu Hỏi", "Câu Trả Lời"};
            worksheet.addMergedRegion(new CellRangeAddress(1, 1, 1, header.length));

            // Dòng 2 người lập
            row = worksheet.createRow(2);

            cell = row.createCell(1);
            cell.setCellValue("Người lập:");
            cell = row.createCell(2);

            cell.setCellValue(GUI_NhanVien.getEmployeeInfo().getName());
            worksheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));

            // Dòng 3 ngày lập
            row = worksheet.createRow(3);
            cell = row.createCell(1);
            cell.setCellValue("Ngày lập:");
            cell = row.createCell(2);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            cell.setCellValue(df.format(new Date()));
            worksheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

            // Dòng 4 tên các cột
            row = worksheet.createRow(4);

            HSSFFont fontHeader = cell.getSheet().getWorkbook().createFont();
            fontHeader.setBold(true);

            CellStyle styleHeader = worksheet.getWorkbook().createCellStyle();
            styleHeader.setFont(fontHeader);
            styleHeader.setBorderBottom(BorderStyle.THIN);
            styleHeader.setBorderTop(BorderStyle.THIN);
            styleHeader.setBorderLeft(BorderStyle.THIN);
            styleHeader.setBorderRight(BorderStyle.THIN);
            styleHeader.setAlignment(HorizontalAlignment.CENTER);

            styleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i + 1);
                cell.setCellValue(header[i]);
                cell.setCellStyle(styleHeader);
            }

            if (tableTuKhoa.getRowCount() == 0) {
                return false;
            }

            HSSFFont fontRow = cell.getSheet().getWorkbook().createFont();
            fontRow.setBold(false);

            CellStyle styleRow = worksheet.getWorkbook().createCellStyle();
            styleRow.setFont(fontRow);
            styleRow.setBorderBottom(BorderStyle.THIN);
            styleRow.setBorderTop(BorderStyle.THIN);
            styleRow.setBorderLeft(BorderStyle.THIN);
            styleRow.setBorderRight(BorderStyle.THIN);

            // Ghi dữ liệu vào bảng
            int STT = 0;
            for (int i = 0; i < tableTuKhoa.getRowCount(); i++) {
                row = worksheet.createRow(5 + i);
                for (int j = 0; j < header.length; j++) {
                    cell = row.createCell(j + 1);
                    if (STT == i) {
                        cell.setCellValue(STT + 1);
                        STT++;
                    } else {
                        try {
                            cell.setCellValue(tableTuKhoa.getValueAt(i, j - 1).toString());
                        } catch (NullPointerException e) {
                            cell.setCellValue("");
                            cell.setCellStyle(styleRow);
                            continue;
                        }
                    }
                    cell.setCellStyle(styleRow);
                }
            }

            for (int i = 1; i < header.length + 1; i++) {
                worksheet.autoSizeColumn(i);
            }

            workbook.write(fileOut);
            workbook.close();
            fileOut.flush();
            fileOut.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validInput() {
        // TODO Auto-generated method stub
        String cauTraLoiText = txtCauTraLoi.getText();

        if (cauTraLoiText.trim().length() > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Câu trả lời không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
