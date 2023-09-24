package api;

import java.io.IOException;
import java.io.InputStream;

import helpers.JsonParser;
import helpers.ReadConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PostRequests {

    private static String loginUrl = "http://restapi.adequateshop.com/api/authaccount/login";
    private static String baseUrl = "http://restapi.adequateshop.com";
    private static String registerUrl = baseUrl + "/api/authaccount/registration";
    private static String responseCode;
    private static String responseBody;
    private static String accessToken;
    private static String authMessage;

    public static void main(String[] args) {
        String email = "some_test1@gmail.com";
        String password = "apitesting";
        String name = "Testing123";
        try {
            login(email, password);
            register(name, email, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printAccessToken();
    }

    public static void login(String email, String password) throws IOException {
        // Build the post request
        String postBody = "{\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postLogin = new HttpPost(loginUrl);
        postLogin.setEntity(new StringEntity(postBody));
        postLogin.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postLogin);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
        if (responseCode.contains("200") == true) {
            JsonParser json = new JsonParser();
            String authCode = json.getResponseCode(responseBody);
            authMessage = json.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = json.getAccessToken(responseBody);
            }
            System.out.println(accessToken);
        }
    }

    public static void register(String name, String email, String password) throws IOException{
        // Build the post request
        String postBody = "{\"name\":\"" + name + "\", "+" \"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";
        HttpPost postRegister = new HttpPost(registerUrl);
        postRegister.setEntity(new StringEntity(postBody));
        postRegister.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postRegister);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
//        if (responseCode.contains("200") == true) {
//            JsonParser json = new JsonParser();
//            String authCode = json.getResponseCode(responseBody);
//            authMessage = json.getAuthMessage(responseBody);
//            if (authCode.equals("0")) {
//                accessToken = json.getAccessToken(responseBody);
//            }
//        }
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getResponseCode() {
        return responseCode;
    }

    public static String getResponseBody() {
        return responseCode;
    }

    public static String getLoginMessage() {
        return authMessage;
    }

    public static void printAccessToken() {
        System.out.println(accessToken);
    }

}
