package com.portfolio.tests;

import com.portfolio.base.BaseTest;
import com.portfolio.endpoints.UserEndpoints;
import com.portfolio.payload.User;
import com.portfolio.utils.ExtentTestListener;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserFlowTest extends BaseTest {

    // Shared variable — stores id across all tests
    private static int userId;

    // Step 1 — CREATE user, extract and store id
    @Test(priority = 1)
    public void createUser() {
        User newUser = new User(
                "Joshiv Kumar",
                "joshiv" + System.currentTimeMillis() + "@test.com",
                "male",
                "active");

        ExtentTestListener.logInfo("Sending POST /users with name: Joshiv Kumar");

        Response response = UserEndpoints.createUser(newUser);

        ExtentTestListener.logInfo("Response Status : " + response.getStatusCode());
        ExtentTestListener.logInfo("Response Body   : " + response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 201, "User not created");
        userId = response.jsonPath().getInt("id");

        ExtentTestListener.logPass("User created successfully with ID: " + userId);

        System.out.println("✓ Created User ID  : " + userId);
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void getCreatedUser() {
        ExtentTestListener.logInfo("Sending GET /users/" + userId);

        Response response = UserEndpoints.getUserById(userId);

        ExtentTestListener.logInfo("Response Status: " + response.getStatusCode());
        ExtentTestListener.logInfo("Response Body  : " + response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "User not found");
        Assert.assertEquals(response.jsonPath().getInt("id"), userId, "ID mismatch");

        ExtentTestListener.logPass("User fetched: " + response.jsonPath().getString("name"));
    }

    @Test(priority = 3, dependsOnMethods = "createUser")
    public void updateCreatedUser() {
        User updatedUser = new User(
                "Joshiv Kumar Updated",
                "joshiv.updated" + System.currentTimeMillis() + "@test.com",
                "male",
                "inactive");

        ExtentTestListener.logInfo("Sending PUT /users/" + userId);

        Response response = UserEndpoints.updateUser(userId, updatedUser);

        ExtentTestListener.logInfo("Response Status: " + response.getStatusCode());
        ExtentTestListener.logInfo("Response Body  : " + response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "User not updated");
        Assert.assertEquals(response.jsonPath().getString("name"), "Joshiv Kumar Updated");

        ExtentTestListener.logPass("User updated: " + response.jsonPath().getString("name"));
    }

    @Test(priority = 4, dependsOnMethods = "createUser")
    public void deleteCreatedUser() {
        ExtentTestListener.logInfo("Sending DELETE /users/" + userId);

        Response response = UserEndpoints.deleteUser(userId);

        ExtentTestListener.logInfo("Response Status: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 204, "User not deleted");

        ExtentTestListener.logPass("User " + userId + " deleted successfully");
    }

    @Test(priority = 5, dependsOnMethods = "deleteCreatedUser")
    public void verifyUserDeleted() {
        ExtentTestListener.logInfo("Verifying GET /users/" + userId + " returns 404");

        Response response = UserEndpoints.getUserById(userId);

        ExtentTestListener.logInfo("Response Status: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 404, "User should not exist");

        ExtentTestListener.logPass("Confirmed: User " + userId + " no longer exists");
    }
}