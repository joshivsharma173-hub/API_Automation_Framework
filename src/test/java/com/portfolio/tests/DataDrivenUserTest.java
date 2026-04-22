package com.portfolio.tests;

import com.portfolio.base.BaseTest;
import com.portfolio.endpoints.UserEndpoints;
import com.portfolio.payload.User;
import com.portfolio.utils.JsonDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenUserTest extends BaseTest {

    // DataProvider feeds data from JSON file into test method
    @DataProvider(name = "userData")
    public Object[][] provideUserData() {
        return JsonDataProvider.getUserData();
    }

    @Test(dataProvider = "userData")
    public void createMultipleUsers(String[] userData) {

        // Extract values from array
        String name   = userData[0];
        String email  = userData[1];
        String gender = userData[2];
        String status = userData[3];

        System.out.println("\n--- Testing with user: " + name + " ---");

        // Build user object from JSON data
        User newUser = new User(name, email, gender, status);

        // Send POST request
        Response response = UserEndpoints.createUser(newUser);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, 
            "User not created for: " + name);
        Assert.assertEquals(response.jsonPath().getString("name"), name, 
            "Name mismatch for: " + name);
        Assert.assertEquals(response.jsonPath().getString("gender"), gender, 
            "Gender mismatch for: " + name);
        Assert.assertEquals(response.jsonPath().getString("status"), status, 
            "Status mismatch for: " + name);

        int createdId = response.jsonPath().getInt("id");
        System.out.println("✓ Created: " + name + " with ID: " + createdId);

        // Cleanup — delete created user after each test
        Response deleteResponse = UserEndpoints.deleteUser(createdId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 204, 
            "Cleanup failed for user: " + createdId);
        System.out.println("✓ Cleaned up user ID: " + createdId);
    }
}