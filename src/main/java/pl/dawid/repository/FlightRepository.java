package pl.dawid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawid.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
