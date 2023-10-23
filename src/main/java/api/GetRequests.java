package api;

import helpers.ReadConfig;
import javafx.geometry.Pos;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GetRequests {


    private static String urlUsers;
    private static String accessToken;
    private static String responseCode;
    private static String responseBody;
    private static String urlUserId;



    public GetRequests() throws FileNotFoundException {
        ReadConfig config = new ReadConfig();
        JSONObject configData = config.readConfig();

        urlUsers = configData.getString("baseUrl") + "/api/users?page=1";
        urlUserId = configData.getString("baseUrl") + "/api/users/" + configData.getString("userId");
    }

    public void getAllUsers(String accessToken) throws IOException{
        HttpGet getUsers = new HttpGet(urlUsers);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", "Bearer " + accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUsers);
        responseCode = response.getStatusLine().toString();

        //Parse the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }

        System.out.println(responseCode);
        System.out.println(responseBody);

    }

    public void getUserById(String accessToken) throws IOException{
        HttpGet getUserById = new HttpGet(urlUsers);
        getUserById.setHeader("Content-type", "application/json");
        getUserById.setHeader("Authorization", "Bearer " + accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUserById);
        responseCode = response.getStatusLine().toString();

        //Parse the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }

        System.out.println(responseCode);
        System.out.println(responseBody);

    }

    public static String getResponseCode() {
        return responseCode;
    }

    public static String getAccessToken() {
        return accessToken = PostRequests.getAccessToken();
    }

    public static String getResponseBody() {
        return responseBody;
    }

    ///api/users/{{user_id}}
}
