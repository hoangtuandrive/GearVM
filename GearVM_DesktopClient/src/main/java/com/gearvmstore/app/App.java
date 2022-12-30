package com.gearvmstore.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gearvmstore.model.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class App {
    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/api/products");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            JSONObject jsonObject = new JSONObject(line);
//            String value = jsonObject.getString("name");
//            System.out.println(value);
//
//        }

//        JSONArray array = new JSONArray(rd);
//        List<JSONObject> list = new ArrayList();
//        for (int i = 0; i < array.length();list.add(array.getJSONObject(i++)));

        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = Arrays.asList(mapper.readValue(rd, Product[].class));
        list.forEach(System.out::println);
    }
}
