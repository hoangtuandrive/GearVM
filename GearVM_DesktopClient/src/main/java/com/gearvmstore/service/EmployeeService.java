package com.gearvmstore.service;

import com.gearvmstore.model.Employee;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EmployeeService extends ApiService {
    private static final String url = "http://localhost:8080/api/employees/";

    public static boolean postRequest(Employee e) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        JSONObject json = new JSONObject();
        json.put("name", e.getName());
        json.put("gender", e.getGender());
        json.put("phoneNumber", e.getPhoneNumber());
        json.put("salary", e.getSalary());
        json.put("dateOfBirth", e.getDateOfBirth());
        json.put("address", e.getAddress());
        json.put("email", e.getEmail());
        json.put("role", e.getRole());
        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        System.out.println(response);
        return !response.toString().isEmpty();
    }

    public static boolean putRequest(Employee e) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPut request = new HttpPut(url);
        JSONObject json = new JSONObject();
        json.put("name", e.getName());
        json.put("gender", e.getGender());
        json.put("phoneNumber", e.getPhoneNumber());
        json.put("salary", e.getSalary());
        json.put("dateOfBirth", e.getDateOfBirth());
        json.put("address", e.getAddress());
        json.put("email", e.getEmail());
        json.put("role", e.getRole());
        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        System.out.println(response);
        return !response.toString().isEmpty();
    }
}
