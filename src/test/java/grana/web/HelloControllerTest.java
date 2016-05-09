package grana.web;

import com.jayway.restassured.RestAssured;
import grana.ApplicationStart;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by diegoicosta on 09/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationStart.class)
@WebIntegrationTest(randomPort = true)
public class HelloControllerTest {

    @Autowired
    private EmbeddedWebApplicationContext server;

    @Value("${local.server.port}")
    int port;

    @Before
    public void before() {
        RestAssured.port = port;
    }

    @Test
    public void showHelloWorldUsingGET() {
        RestAssured.when().get("/")
                .then().statusCode(HttpStatus.SC_OK)
                .body("greetings", equalTo("Hello World!"));
    }

}
