package com.gearvmstore.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gearvmstore.model.Product;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class PostRequestExample {
    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/api/products");
        Product p = new Product();
        p.setName("Post");
        p.setPrice(1000);
        p.setType("AAA");
        p.setBrand("Brand Á");
        p.setQuantity(5);
        JSONObject json = new JSONObject();
        json.put("name", p.getName());
        json.put("brand", p.getBrand());
        json.put("type", p.getType());
        json.put("price", p.getPrice());
        json.put("quantity", p.getQuantity());
        StringEntity se = new StringEntity( json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
        System.out.println(response);
    }
}
