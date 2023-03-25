package com.gearvmstore.service;

import com.gearvmstore.model.Customer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomerService extends ApiService {
    private static final String url = staticUrl + "/customers/";

    public static boolean postRequest(Customer c) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        JSONObject json = new JSONObject();
        json.put("name", c.getName());
        json.put("gender", c.getGender());
        json.put("phoneNumber", c.getPhoneNumber());

        String dateFormat = String.format("%02d", c.getDateOfBirth().getDayOfMonth())
                + "-" + String.format("%02d", c.getDateOfBirth().getMonthValue())
                + "-" + c.getDateOfBirth().getYear();
        json.put("dateOfBirth", dateFormat);

        json.put("address", c.getAddress());
        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean putRequest(Customer c) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPut request = new HttpPut(url + c.getId());
        JSONObject json = new JSONObject();
        json.put("name", c.getName());
        json.put("gender", c.getGender());
        json.put("phoneNumber", c.getPhoneNumber());

        String dateFormat = String.format("%02d", c.getDateOfBirth().getDayOfMonth())
                + "-" + String.format("%02d", c.getDateOfBirth().getMonthValue())
                + "-" + c.getDateOfBirth().getYear();
        json.put("dateOfBirth", dateFormat);

        json.put("address", c.getAddress());
        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean deleteRequest(Customer c) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(url + c.getId());
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
