package com.gearvmstore.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.gearvmstore.model.Product;
import sun.misc.Unsafe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;

public class FrmChiTietSanPham extends JFrame implements ActionListener {

    //    private JFrame mainFrame;
//    private JLabel headerLabel;
//    private JLabel statusLabel;
//    private JPanel controlPanel;
    private JButton browseButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel imageLbl;
    private JTextArea idTxt;
    private JTextArea nameTxt;
    private JTextArea detailTxt;
    private JScrollPane scrollPane;
//    private JTextField idTxt;
//    private JTextField nameTxt;
    

    public FrmChiTietSanPham(Product product) {
//        prepareGUI();
        super("Chi tiết sản phẩm");
        setSize(1000, 600);
        this.setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browseButton = new JButton("Chọn ảnh");
        browseButton.setBounds(200, 480, 100, 40);
        saveButton = new JButton("Lưu");
        saveButton.setBounds(400, 480, 100, 40);
        cancelButton = new JButton("Hủy");
        cancelButton.setBounds(520, 480, 100, 40);
        imageLbl = new JLabel();
        imageLbl.setBounds(10, 10, 600, 450);

        idTxt = new JTextArea("Mã sản phẩm: " + product.getId());
        idTxt.setBounds(650, 10, 200, 50);
        idTxt.setOpaque(false);
        idTxt.setBackground(Color.WHITE);
        idTxt.setEditable(false);
        nameTxt = new JTextArea("Tên sản phẩm: " + product.getName());
        nameTxt.setBounds(650, 30, 300, 50);
        nameTxt.setLineWrap(true);
        nameTxt.setOpaque(false);
        nameTxt.setBackground(Color.WHITE);
        nameTxt.setEditable(false);

        detailTxt = new JTextArea();
        scrollPane = new JScrollPane(detailTxt);
        scrollPane.setBounds(650, 100, 300, 360);


        add(browseButton);
        add(saveButton);
        add(cancelButton);
        add(imageLbl);
        add(idTxt);
        add(nameTxt);
        add(scrollPane);

        browseButton.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
//        FrmChiTietSanPham swingControlDemo = new FrmChiTietSanPham();
//        swingControlDemo.showImageIconDemo();
            disableWarning();
            FlatLightLaf.setup();
            Product p = new Product();
            p.setId(1L);
            p.setName("Product 11111111111111111111111111111111111111111111111111111111111111111111111111111111");
            new FrmChiTietSanPham(p);
    }

//    private void prepareGUI() {
//        mainFrame = new JFrame("Vi du ImageIcon - Java Swing");
//        mainFrame.setSize(700, 400);
//        mainFrame.setLayout(new GridLayout(3, 1));
//        mainFrame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent) {
//                System.exit(0);
//            }
//        });
//        headerLabel = new JLabel("", JLabel.CENTER);
//        statusLabel = new JLabel("", JLabel.CENTER);
//        statusLabel.setSize(300, 100);
//        controlPanel = new JPanel();
//        controlPanel.setLayout(new FlowLayout());
//        mainFrame.add(headerLabel);
//        mainFrame.add(controlPanel);
//        mainFrame.add(statusLabel);
//        mainFrame.setVisible(true);
//    }

//    private void showImageIconDemo() throws IOException {
//        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
//        Region region = Region.AP_SOUTHEAST_1;
//        S3Client s3 = S3Client.builder()
//                .region(region)
//                .credentialsProvider(credentialsProvider)
//                .build();
//
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket("gearvm")
//                .key("WeWork_PrivateOffice-1440x810.jpg")
//                .build();
//
//        ResponseInputStream<GetObjectResponse> input = s3.getObject(getObjectRequest);
//        BufferedImage image = ImageIO.read(input);
//
////        listBucketObjects(s3, bucketName);
//        s3.close();
//
//        headerLabel.setText("Control in action: ImageIcon");
//        ImageIcon icon = new ImageIcon(image, "Lock");
//        JLabel commentlabel = new JLabel("This is lock icon", icon, JLabel.CENTER);
//        controlPanel.add(commentlabel);
//        mainFrame.setVisible(true);
//    }

    //TODO: WORK IN PROGRESS https://1bestcsharp.blogspot.com/2015/04/java-how-to-browse-image-file-and-And-Display-It-Using-JFileChooser-In-Java.html
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(browseButton)) {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.dir")));
            //filter the files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);
            //if the user click on save in Jfilechooser
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                imageLbl.setIcon(ResizeImage(path));
            }
            //if the user click on save in Jfilechooser
            else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "Không có ảnh được chọn!", "Thất bại",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}