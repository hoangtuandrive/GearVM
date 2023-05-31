package com.gearvmdesktop.service;

import com.gearvmstore.GearVM.model.Prompt;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PromptService extends ApiService {
    private static final String url = staticUrl + "/prompts/";

    public static boolean postRequest(Prompt p) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        JSONObject json = new JSONObject();
        json.put("question", p.getQuestion());
        json.put("answer", p.getAnswer());

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean putRequest(Prompt p) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpPut request = new HttpPut(url);
        JSONObject json = new JSONObject();
        json.put("question", p.getQuestion());
        json.put("answer", p.getAnswer());

        StringEntity se = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        se.setContentType("application/json;charset=UTF-8");
        request.setEntity(se);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean deleteRequest(Prompt e) throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();

        String stringEncoded = URLEncoder.encode(e.getQuestion(), "UTF-8").replaceAll("\\+", "%20");

        HttpDelete request = new HttpDelete(url + "?question=" + stringEncoded);

        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
