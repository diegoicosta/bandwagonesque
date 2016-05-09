package moip.poc.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by diegoicosta on 07/05/16.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/", produces = "application/json")
    public @ResponseBody String home() {

        return "{\"greetings\":\"Hello World!\"}";
    }
}
