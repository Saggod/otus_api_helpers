package tests;

import helpers.SoapApiHelpers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoapApiTests extends SoapApiHelpers {

    @Test
    public void getUsersSoapTest() {
        Response resp = given()
                .header("Accept", "application/xml")
                .when()
                .get("http://localhost:8080/soap/user/get/all")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(resp.asString());

        XmlPath xmlPath = new XmlPath(resp.asString());
        String userNameActual = xmlPath.getString("Envelope.Body.GetUserResponse.User.Name");
        assertEquals("Test user", userNameActual, "Имя пользователя не соответствует ожидаемому");
        int userScore = xmlPath.getInt("Envelope.Body.GetUserResponse.User.Score");
        assertEquals(78, userScore, "Оценка пользователя не соответствует ожидаемой");



    }
}
