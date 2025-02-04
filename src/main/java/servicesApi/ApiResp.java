package servicesApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class ApiResp {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public ApiResp() {
        requestSpecification = given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .log().all();

        responseSpecification = RestAssured.expect();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.time(Matchers.lessThan(5000L));
    }

    public ValidatableResponse getGrade(int id) {
        return given(requestSpecification)
                .basePath("/user/get/" + id)
                .header("Accept", "application/json")
                .when()
                .get()
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse getCourses() {
        return given(requestSpecification)
                .basePath("/courses/get/all")
                .header("Accept", "application/json")
                .when()
                .get()
                .then()
                .spec(responseSpecification);
    }

    public ValidatableResponse getUsers() {
        return given(requestSpecification)
                .basePath("/user/get/all")
                .header("Accept", "application/json")
                .when()
                .get()
                .then()
                .spec(responseSpecification);
    }
}
