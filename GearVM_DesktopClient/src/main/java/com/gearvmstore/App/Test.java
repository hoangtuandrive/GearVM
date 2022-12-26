package com.gearvmstore.App;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class Test {
    File tmp = null;
    public static void main(String[] args) throws IOException {

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

        ResponseInputStream<GetObjectResponse> test = s3.getObject(getObjectRequest);
        File tmp = File.createTempFile("s3test", ".jpeg");
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(tmp);

//        listBucketObjects(s3, bucketName);
        s3.close();
    }

    // snippet-start:[s3.java2.list_objects.main]
    public static void listBucketObjects(S3Client s3, String bucketName ) {

        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.print("\n The name of the key is " + myValue.key());
                System.out.print("\n The object is " + calKb(myValue.size()) + " KBs");
                System.out.print("\n The owner is " + myValue.owner());
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    //convert bytes to kbs.
    private static long calKb(Long val) {
        return val/1024;
    }

    public static BufferedImage  byteArrayToImage(byte[] bytes){
        BufferedImage bufferedImage=null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }
}
