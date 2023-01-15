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
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class PostRequestExample {
    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/api/products");
        Product input = new Product();
        input.setId(5L);
        input.setName("Post");
        input.setPrice(1000);
        JSONObject json = new JSONObject();
        json.put("name", input.getName());
        StringEntity se = new StringEntity( json.toString());
        se.setContentType("application/json");
        post.setEntity(se);
        HttpResponse response = client.execute(post);
    }
}
