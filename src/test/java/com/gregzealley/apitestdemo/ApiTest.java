package com.gregzealley.apitestdemo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {

    private static String TEST_API_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void whenIMakeAValidREquestThenIGetTheExpectedStatus() {
        given()
                .when()
                .get(TEST_API_URL + "/posts")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenIMakeAValidRequestThenIHaveExpectedBody() {
        ExtractableResponse<Response> response =
                given()
                        .when()
                        .get(TEST_API_URL + "/posts")
                        .then()
                        .extract();

        String jsonBody = response.body().asString();
        JsonPath jsonPath = new JsonPath(jsonBody);

        assertThat(jsonPath.getString("id"))
                .contains("1", "2");
    }
}
