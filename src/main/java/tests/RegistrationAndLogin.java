package tests;

import api.GetRequests;
import api.PostRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String email;
    private static String password;
    private static String name;

    @BeforeTest
    public static void credentials() {
        email = "some_test3@gmail.com";
        password = "apitesting";
        name = "Testing123";
    }

//    @Test(priority=0)
//    public static void testSuccessfulRegister() throws IOException {
//        PostRequests postRequests = new PostRequests();
//        postRequests.register(name,email, password);
//        String responseCode = postRequests.getResponseCode();
//        Assert.assertTrue(responseCode.contains("200"), responseCode);
//        String authMessage = postRequests.getLoginMessage();
//        Assert.assertTrue(authMessage.contains("success"), authMessage);
//    }

    @Test(priority = 1)
    public static void testSuccessfulLogin() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test(priority = 2)
    public static void testGetAllUsers() throws IOException {
        GetRequests getRequests = new GetRequests();
        getRequests.getAllUsers();
        String responseCode = getRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, "123450");
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login("test@test.com", password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

}
