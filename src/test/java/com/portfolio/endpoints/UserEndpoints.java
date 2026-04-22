package com.portfolio.endpoints;

import com.portfolio.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    private static final String GET_ALL_USERS  = "/users";
    private static final String CREATE_USER    = "/users";
    private static final String GET_USER_BY_ID = "/users/{id}";
    private static final String UPDATE_USER    = "/users/{id}";
    private static final String DELETE_USER    = "/users/{id}";

    // GET all users
    public static Response getAllUsers() {
        return given()
                .when()
                .get(GET_ALL_USERS)
                .then()
                .extract().response();
    }

    // GET user by ID
    public static Response getUserById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(GET_USER_BY_ID)
                .then()
                .extract().response();
    }

    // POST create user
    public static Response createUser(User user) {
        return given()
                .body(user)
                .when()
                .post(CREATE_USER)
                .then()
                .extract().response();
    }

    // PUT update user
    public static Response updateUser(int id, User user) {
        return given()
                .pathParam("id", id)
                .body(user)
                .when()
                .put(UPDATE_USER)
                .then()
                .extract().response();
    }

    // DELETE user
    public static Response deleteUser(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(DELETE_USER)
                .then()
                .extract().response();
    }
}