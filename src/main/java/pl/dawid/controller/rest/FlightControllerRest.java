package pl.dawid.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawid.model.Flight;
import pl.dawid.repository.FlightRepository;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/flights")
public class FlightControllerRest {

    private FlightRepository flightRepo;
    @Autowired
    public FlightControllerRest(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight> getFlights(@RequestParam(defaultValue = "startDate")String orderBy){
        List<Flight> flights = flightRepo.findAll();
        if("startDate".equals(orderBy)){
            flights.sort(Comparator.comparing(Flight::getStartDate));
        }else if("price".equals(orderBy)){
            flights.sort(Comparator.comparing(Flight::getTicketPrice));
        }
        return flights;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> getFlight(@PathVariable Long id){
        return flightRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFlight(@RequestParam Flight flight){
        flightRepo.save(flight);
    }
    @PutMapping(path = "/{id}")
    public void saveOrUpdate(@RequestBody Flight flight, @PathVariable Long id){
        flightRepo.findById(id).map(x->{
            x.setStartDate(flight.getStartDate().toString());
            x.setStartTime(flight.getStartTime());
            x.setEndDate(flight.getEndDate().toString());
            x.setEndTime(flight.getEndTime());
            x.setNumberOfSeats(flight.getNumberOfSeats());
            x.setPassengers(flight.getPassengers());
            x.setTicketPrice(flight.getTicketPrice());
            return flightRepo.save(x);
        }).orElseGet(()-> {
            flight.setId(id);
            return flightRepo.save(flight);
        });
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTourist(@PathVariable Long id){
        flightRepo.deleteById(id);
    }

}
