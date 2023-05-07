package com.gearvmdesktop.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReportService extends ApiService {
    private static final String url = staticUrl + "/reports/";

    public static BufferedReader getMostSoldProducts() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url + "most-sold-products");
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) return null;
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    }
}
