package helpers;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SoapApiHelpers {
    WireMockServer wireMockServer = new WireMockServer(8080);

    @BeforeEach
    public void mockStartService() {
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/soap/user/get/all"))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/soap+xml")
                        .withBodyFile("allUsers_resp.xml")
                        .withStatus(200)));
    }


    @AfterEach
    public void stopMocks() {
        wireMockServer.stop();
    }
}
