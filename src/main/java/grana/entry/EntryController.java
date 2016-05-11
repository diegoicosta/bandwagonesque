package grana.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by diegoicosta on 10/05/16.
 */
@RestController
public class EntryController {

    @Autowired
    private EntryRepository repository;

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public
    @ResponseBody
    Entry create(@RequestBody Entry entry) {
        System.out.println(entry);
        Entry newEntry = repository.save(entry);
        return newEntry;
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Entry findById(@PathVariable("id") long id) {

        Entry entry = repository.findOne(id);
        if (entry == null) {
            throw new EntryNotFound("No entry with id");
        }
        return entry;
    }

    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Entry> findAll() {
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class EntryNotFound extends RuntimeException {
        public EntryNotFound(String message) {
            super(message);
        }
    }

}
