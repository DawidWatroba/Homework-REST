package pl.dawid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawid.model.Tourist;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
