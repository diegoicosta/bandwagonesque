package grana.entry;

import grana.ApplicationStart;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by diegoicosta on 11/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationStart.class)
@WebAppConfiguration
public class EntryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EntryRepository repository;


    @Before
    public void before() {
        mockMvc(webAppContextSetup(webApplicationContext).build());
    }

    @Test
    public void findByIdWhenNotExists()  {
        given()
            .when().get("/entry/666")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void findOneList() throws Exception {
        Entry entry = createEntry() ;

        given()
            .when().get("/entry/" + entry.getId())
                .then().statusCode(HttpStatus.SC_OK)
                .body("description", equalTo(entry.getDescription()))
                .body("amount", equalTo(entry.getAmount()));

    }

    @Test
    public void listEmptyEntryList() {
        given()
            .when().get("/entry")
                .then().statusCode(HttpStatus.SC_OK)
                .body("", hasItems());
    }

    private Entry createEntry() {
        Entry entry = new Entry();
        entry.setAmount(new Random().nextInt());
        entry.setDescription("Lancamento qualquer");
        return repository.save(entry);
    }

}
