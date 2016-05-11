package grana.integration;

import grana.ApplicationStart;
import grana.entry.Entry;
import grana.entry.EntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by diegoicosta on 10/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationStart.class)
@Transactional
public class EntryRepositoryTest {

    @Autowired
    private EntryRepository repository;

    @Test
    public void repositoryIsNotNull() {
        assertThat(repository, notNullValue());
    }

    @Test
    public void findByIdWhenNotExists() {
        Entry entry = repository.findOne(666L);
        assertThat(entry, is(nullValue()));
    }

    @Test
    public void findNoEntriesWhenEmpty() {
        List<Entry> entries = repository.findAll();
        assertThat(entries.size(), is(0));
    }

    @Test
    public void save() {
        Entry entry = new Entry();
        entry.setAmount(1234);
        entry.setDescription("Creditando na conta");
        entry = repository.save(entry);

        assertThat(entry.getId(), not(0));
        assertThat(repository.findOne(entry.getId()), notNullValue());
    }

}
