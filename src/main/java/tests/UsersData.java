package tests;

import api.GetRequests;
import api.PostRequests;
import helpers.JsonParser;
import helpers.ReadConfig;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UsersData {

    private static JSONObject configData;

    @BeforeTest
    public static void credentials() throws FileNotFoundException {
        ReadConfig config = new ReadConfig();
        configData = config.readConfig();
    }

    @Test
    public static void testGetAllUsers() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(configData.getString("username"), configData.getString("password"), configData.getString("baseUrl"));
        GetRequests getRequests = new GetRequests();
        getRequests.getAllUsers(GetRequests.getAccessToken());
        String responseCode = getRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
    }

    @Test
    public static void testGetUserById() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(configData.getString("username"), configData.getString("password"), configData.getString("baseUrl"));
        GetRequests getRequests = new GetRequests();
        getRequests.getUserById(GetRequests.getAccessToken());
        String responseCode = getRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        //System.out.println(PostRequests.getResponseBody());
        //Assert.assertTrue(PostRequests.getResponseBody().contains("id"));
        //Assert.assertTrue(PostRequests.getResponseBody().contains(configData.getString("userId")));
    }
}
