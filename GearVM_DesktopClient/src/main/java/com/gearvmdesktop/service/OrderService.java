package com.gearvmdesktop.service;

import com.gearvmstore.GearVM.model.dto.order.ProcessDirectOrderPayment;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderItem;
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
import java.net.URLEncoder;
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

    // 0: created
    // 1: existing cart
    // 2: error
    public static int createDirectOrder(String customerId) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url + "create-directOrder/" + customerId);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) return 0;
        else if (response.getStatusLine().getStatusCode() == 302) return 1;
        else return 2;
    }

    public static BufferedReader getPendingDirectOrderList() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url + "direct-pending");
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) return null;
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    }

    public static BufferedReader getRequestByCustomerNameAndCustomerPhoneNumber(String customerName, String customerPhone) throws IOException {
        HttpClient client = new DefaultHttpClient();

        String customerNameUrlEncoded = URLEncoder.encode(customerName, "UTF-8").replaceAll("\\+", "%20");

        HttpGet request = new HttpGet(url + "get-direct-pending?customerName=" + customerNameUrlEncoded + "&customerPhone=" + customerPhone);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() != 200) return null;
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    }

    public static BufferedReader getAllOnlineOrdersAndPaidDirectOrders() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url + "online-and-paidDirect");
        HttpResponse response = client.execute(request);
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    }

    public static boolean patchUpdateAddOrderItem(UpdateOrderItem updateOrderItem) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "update-add-orderItem");

        JSONObject json = new JSONObject();
        json.put("productId", updateOrderItem.getProductId());
        json.put("customerName", updateOrderItem.getCustomerName());
        json.put("customerPhone", updateOrderItem.getCustomerPhone());
        json.put("amount", updateOrderItem.getAmount());

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean patchUpdateReduceOrderItem(UpdateOrderItem updateOrderItem) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "update-reduce-orderItem");

        JSONObject json = new JSONObject();
        json.put("productId", updateOrderItem.getProductId());
        json.put("customerName", updateOrderItem.getCustomerName());
        json.put("customerPhone", updateOrderItem.getCustomerPhone());
        json.put("amount", updateOrderItem.getAmount());

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean patchProcessDirectOrderPayment(ProcessDirectOrderPayment processDirectOrderPayment) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPatch request = new HttpPatch(url + "process-directOrder-payment");

        JSONObject json = new JSONObject();
        json.put("orderId", processDirectOrderPayment.getOrderId());
        json.put("employeeId", processDirectOrderPayment.getEmployeeId());
        json.put("paymentMethod", processDirectOrderPayment.getPaymentMethod().toString());
        if (processDirectOrderPayment.getShippingAddress() != null) {
            json.put("shippingName", processDirectOrderPayment.getShippingName());
            json.put("shippingPhone", processDirectOrderPayment.getShippingPhone());
            json.put("shippingAddress", processDirectOrderPayment.getShippingAddress());
        }

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
