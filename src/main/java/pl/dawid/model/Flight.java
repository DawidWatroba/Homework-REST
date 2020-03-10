package pl.dawid.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(columnDefinition = "TIME")
    private LocalTime startTime;
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;
    @Column(columnDefinition = "TIME")
    private LocalTime endTime;
    @Column(name = "seats")
    private int numberOfSeats;
    @ManyToMany
    private List<Tourist> passengers;
    @Column(name = "price")
    private double ticketPrice;

    public Flight() {
    }

    public Flight(LocalDate startDate, LocalTime startTime, LocalDate endDate,
                  LocalTime endTime, int numberOfSeats, List<Tourist> passengers, double ticketPrice) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.numberOfSeats = numberOfSeats;
        this.passengers = passengers;
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
