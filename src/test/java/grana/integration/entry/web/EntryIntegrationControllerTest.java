package grana.integration.entry.web;

import com.jayway.restassured.RestAssured;
import grana.ApplicationStart;
import grana.entry.domain.Entry;
import grana.entry.domain.EntryRepository;
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

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * Created by diegoicosta on 11/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationStart.class)
@WebIntegrationTest(randomPort = true)
public class EntryIntegrationControllerTest {

    @Autowired
    private EmbeddedWebApplicationContext server;

    @Autowired
    private EntryRepository repository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void before() {
        RestAssured.port = port;
    }


    @Test
    public void findByIdWhenNotExists() {
        RestAssured.when()
                .get("/entry/666")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }


    @Test
    public void findOneList() {
        Entry entry = createEntry() ;

        RestAssured.when().get("/entry/" + entry.getId())
                .then().statusCode(HttpStatus.SC_OK)
                .body("description", equalTo(entry.getDescription()))
                .body("amount", equalTo(entry.getAmount()));
    }



    @Test
    public void listEmptyEntryList() {
        RestAssured.when().get("/entry")
                .then().statusCode(HttpStatus.SC_OK)
                .body("", hasItems());
    }

    private Entry createEntry() {
        Entry entry = new Entry();
        entry.setAmount(new Random().nextInt());
        entry.setDescription("Lanacamento qualquer");
        return repository.save(entry);
    }

}
