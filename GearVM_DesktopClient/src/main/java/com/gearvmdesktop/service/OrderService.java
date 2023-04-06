package com.gearvmdesktop.service;

import com.gearvmstore.GearVM.model.dto.order.UpdateOrderStatusAndEmployee;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OrderService extends ApiService {
    private static final String url = staticUrl + "/orders/";

    public static boolean patchOrderStatus(String orderId, UpdateOrderStatusAndEmployee order) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "update-orderStatus/" + orderId);

        JSONObject json = new JSONObject();
        json.put("orderStatus", order.getOrderStatus().toString());

        JSONObject employeeId = new JSONObject();
        employeeId.put("id", order.getEmployee().getId());
        employeeId.put("name", order.getEmployee().getName());
        json.put("employee", employeeId);


        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean patchOrderStatusAndEmployee(String orderId, UpdateOrderStatusAndEmployee order) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "update-orderStatus-employee/" + orderId);

        JSONObject json = new JSONObject();
        json.put("orderStatus", order.getOrderStatus().toString());

        if (order.getEmployee() != null) {
            JSONObject employeeId = new JSONObject();
            employeeId.put("id", order.getEmployee().getId());
            employeeId.put("name", order.getEmployee().getName());
            json.put("employee", employeeId);
        }

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static void openPaymentDetailOnStripe(String paymentId) {
        try {
            Desktop.getDesktop().browse(new URL("https://dashboard.stripe.com/test/payments/" + paymentId).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean createDirectOrder(String customerId) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url + "create-directOrder/" + customerId);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static BufferedReader getPendingDirectOrderList() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url + "direct-pending");
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) return null;
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    }

    
}
