package com.portfolio.base;

import com.portfolio.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeSuite
    public void setUp() {

        // Build request specification — applied to every request
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigReader.get("base.url"))
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + ConfigReader.get("token"))
            .log(LogDetail.ALL)   // logs every request to console
            .build();

        // Build response specification — common assertions for every response
        responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)   // logs every response to console
            .build();

        RestAssured.requestSpecification = requestSpec;
    }
}