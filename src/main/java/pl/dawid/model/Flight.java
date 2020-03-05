package pl.dawid.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long id;
    /*
    zmienić String na LocalDateTime, bądź inny do przechowywania daty
    DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE_TIME; [yyyy-mm-ddThh:mm]
    */
    private String start;
    private String landing;
    @Column(name = "seats")
    private int numberOfSeats;
    @ManyToMany
    private List<Tourist> passengers;
    @Column(name = "price")
    private double ticketPrice;

    public Flight() {
    }

    public Flight(String start, String landing, int numberOfSeats, List<Tourist> passengers, double ticketPrice) {
        this.start = start;
        this.landing = landing;
        this.numberOfSeats = numberOfSeats;
        this.passengers = passengers;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Tourist> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Tourist> passengers) {
        this.passengers = passengers;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
