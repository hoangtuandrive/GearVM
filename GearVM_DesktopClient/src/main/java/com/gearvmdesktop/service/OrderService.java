package com.gearvmdesktop.service;

import com.gearvmdesktop.model.dto.order.UpdateOrderStatusAndEmployee;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OrderService extends ApiService {
    private static final String url = staticUrl + "/orders/";

    public static boolean patchOrderStatus(String orderId, UpdateOrderStatusAndEmployee order) throws IOException{
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

    public static boolean patchOrderStatusAndEmployee(String orderId, UpdateOrderStatusAndEmployee order) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "update-orderStatus-employee/" + orderId);

        JSONObject json = new JSONObject();
        json.put("orderStatus", order.getOrderStatus().toString());

        if(order.getEmployee() != null){
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
}
