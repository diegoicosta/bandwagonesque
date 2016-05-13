package grana.entry.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diegoicosta on 12/05/16.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
