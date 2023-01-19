package com.gearvmstore.ui;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrmChiTietSanPham extends JFrame implements ActionListener {

    //    private JFrame mainFrame;
//    private JLabel headerLabel;
//    private JLabel statusLabel;
//    private JPanel controlPanel;
    JButton button;
    JLabel label;

    public FrmChiTietSanPham() {
//        prepareGUI();
        super("Set Picture Into A JLabel Using JFileChooser In Java");
        button = new JButton("Browse");
        button.setBounds(300, 300, 100, 40);
        label = new JLabel();
        label.setBounds(10, 10, 670, 250);
        add(button);
        add(label);
        button.addActionListener(this);

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 400);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
//        FrmChiTietSanPham swingControlDemo = new FrmChiTietSanPham();
//        swingControlDemo.showImageIconDemo();
        new FrmChiTietSanPham();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Vi du ImageIcon - Java Swing");
        mainFrame.setSize(700, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(300, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showImageIconDemo() throws IOException {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.AP_SOUTHEAST_1;
        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("gearvm")
                .key("WeWork_PrivateOffice-1440x810.jpg")
                .build();

        ResponseInputStream<GetObjectResponse> input = s3.getObject(getObjectRequest);
        BufferedImage image = ImageIO.read(input);

//        listBucketObjects(s3, bucketName);
        s3.close();

        headerLabel.setText("Control in action: ImageIcon");
        ImageIcon icon = new ImageIcon(image, "Lock");
        JLabel commentlabel = new JLabel("This is lock icon", icon, JLabel.CENTER);
        controlPanel.add(commentlabel);
        mainFrame.setVisible(true);
    }

    //TODO: WORK IN PROGRESS https://1bestcsharp.blogspot.com/2015/04/java-how-to-browse-image-file-and-And-Display-It-Using-JFileChooser-In-Java.html
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(button)) {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            //filter the files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);
            //if the user click on save in Jfilechooser
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                label.setIcon(ResizeImage(path));
            }
            //if the user click on save in Jfilechooser
            else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "Không có ảnh được chọn!", "Thất bại",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}