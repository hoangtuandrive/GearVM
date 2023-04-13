package com.gearvmdesktop.service;


import com.gearvmstore.GearVM.model.Purchase;
import com.gearvmstore.GearVM.model.dto.purchase.CreatePurchase;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PurchaseService extends ApiService {
    private static final String url = staticUrl + "/purchases/";

    public static boolean postRequest(CreatePurchase p) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        JSONObject json = new JSONObject();
        json.put("productId", p.getProductId());
        json.put("employeeId", p.getEmployeeId());
        json.put("price", p.getPrice());
        json.put("quantity", p.getQuantity());
        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean deleteRequest(Purchase p) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(url + p.getId());
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
