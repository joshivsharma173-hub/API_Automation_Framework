package com.portfolio.tests;

import com.portfolio.base.BaseTest;
import com.portfolio.endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest extends BaseTest {

    @Test
    public void getAllUsers() {
        Response response = UserEndpoints.getAllUsers();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        Assert.assertNotNull(response.getBody(), "Response body is null");
        System.out.println("First User: " + response.jsonPath().getString("[0].name"));
    }

    @Test
    public void getUserById() {
        // get a valid id first
        Response allUsers = UserEndpoints.getAllUsers();
        int userId = allUsers.jsonPath().getInt("[0].id");

        // validate specific user
        Response response = UserEndpoints.getUserById(userId);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        Assert.assertEquals(response.jsonPath().getInt("id"), userId, "ID mismatch");
        Assert.assertNotNull(response.jsonPath().getString("name"), "Name is null");
    }

    @Test
    public void getUserWithInvalidId() {
        Response response = UserEndpoints.getUserById(9999999);

        Assert.assertEquals(response.getStatusCode(), 404, "Should be 404");
    }
}