package pl.dawid.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawid.model.Tourist;
import pl.dawid.repository.TouristRepository;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("api/tourists")
public class TouristControllerRest {

    private TouristRepository touristRepo;

    @Autowired
    public TouristControllerRest(TouristRepository touristRepository){
        this.touristRepo = touristRepository;
    }

    //find all
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tourist> getTourists(@RequestParam(defaultValue = "firstName")String orderBy){
        List<Tourist> touristList = touristRepo.findAll();
        if("firstName".equals(orderBy)){
            touristList.sort(Comparator.comparing(Tourist::getFirstName));
        }else if("lastName".equals(orderBy)){
            touristList.sort(Comparator.comparing(Tourist::getLastName));
        }
        return touristList;
    }
    //find one
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tourist> getTourist(@PathVariable Long id){
        return touristRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    //save
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveTourist(@RequestBody Tourist tourist){
        touristRepo.save(tourist);
    }

    //update, need to figure out how to do it without this boilerplate set - get...
    @PutMapping(path = "/{id}")
    public void saveOrUpdate(@RequestBody Tourist tourist, @PathVariable Long id){
        touristRepo.findById(id).map(x->{
            if(tourist.getFirstName()!=null){
            x.setFirstName(tourist.getFirstName());}
            if(tourist.getLastName()!=null){
            x.setLastName(tourist.getLastName());}
            if(tourist.getGender()!=null){
            x.setGender(tourist.getGender());}
            if(tourist.getCountry()!=null){
            x.setCountry(tourist.getCountry());}
            if(tourist.getNotes()!=null){
            x.setNotes(tourist.getNotes());}
            if(tourist.getDateOfBirth()!=null){
            x.setDateOfBirth(tourist.getDateOfBirth().toString());}
            if(tourist.getFlights()!=null){
            x.setFlights(tourist.getFlights());}
            return touristRepo.save(x);
        })
                .orElseGet(()->{
                    tourist.setId(id);
                    return touristRepo.save(tourist);
                });
    }
    @DeleteMapping(path = "/{id}")
    public void deleteTourist(@PathVariable Long id){
        touristRepo.deleteById(id);
    }
}
