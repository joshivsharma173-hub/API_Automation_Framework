package com.portfolio.tests;

import com.portfolio.base.BaseTest;
import com.portfolio.endpoints.UserEndpoints;
import com.portfolio.payload.User;
import com.portfolio.utils.ExcelDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDrivenUserTest extends BaseTest {

    @DataProvider(name = "excelUserData")
    public Object[][] provideExcelData() {
        return ExcelDataProvider.getUserData();
    }

    @Test(dataProvider = "excelUserData")
    public void createUsersFromExcel(String[] userData) {

        String name   = userData[0];
        String email  = userData[1];
        String gender = userData[2];
        String status = userData[3];

        System.out.println("\n--- [Excel] Testing with user: " + name + " ---");

        User newUser = new User(name, email, gender, status);

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
        System.out.println("✓ [Excel] Created: " + name + " with ID: " + createdId);

        // Cleanup
        Response deleteResponse = UserEndpoints.deleteUser(createdId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 204,
            "Cleanup failed for: " + createdId);
        System.out.println("✓ [Excel] Cleaned up user ID: " + createdId);
    }
}