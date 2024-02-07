package rabbit.niloapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rabbit.niloapi.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, String> {
}
