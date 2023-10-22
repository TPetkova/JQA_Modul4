package tests;

import api.PostRequests;
import helpers.ReadConfig;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static JSONObject configData;

    @BeforeTest
    public static void credentials() throws IOException {
        ReadConfig config = new ReadConfig();
        configData = config.readConfig();
    }

    @Test(priority=1)
    public static void testSuccessfulRegister() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.register(configData.getString("name"),configData.getString("username"), configData.getString("password"));
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 2)
    public static void testSuccessfulLogin() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(configData.getString("username"), configData.getString("password"), configData.getString("baseUrl"));
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);

    }

//    @Test(priority = 3)
//    public static void testSuccessfulRegisterAndLogin() throws IOException {
//        PostRequests postRequests = new PostRequests();
//        postRequests.register(configData.getString("name"),configData.getString("username"), configData.getString("password"));
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        postRequests.login(configData.getString("username"), configData.getString("password"), configData.getString("baseUrl"));
//        String responseCodeLogin = postRequests.getResponseCode();
//        Assert.assertTrue(responseCodeLogin.contains("200"), responseCodeLogin);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("success"), authMessage);
//
//    }

    @Test(priority = 4)
    public static void testWrongPassword() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(configData.getString("username"), "123450", configData.getString("baseUrl"));
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test(priority = 5)
    public static void testWrongUsername() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login("test@test.com", configData.getString("password"), configData.getString("baseUrl"));
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

}
