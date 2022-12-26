package com.gearvmstore.App;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageIconExam {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public ImageIconExam(){
        prepareGUI();
    }

    public static void main(String[] args) throws IOException {
        ImageIconExam swingControlDemo = new ImageIconExam();
        swingControlDemo.showImageIconDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Vi du ImageIcon - Java Swing");
        mainFrame.setSize(400, 200);
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
        String bucketName = "gearvm";
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
}