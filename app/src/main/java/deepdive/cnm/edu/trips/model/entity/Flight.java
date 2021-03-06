package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity //(foreignKeys = {
//        @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
//})
public class Flight {

    @ColumnInfo(name = "flight_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "person_id", index = true)
    private long personId;

    @ColumnInfo(name = "arrival_airport")
    private String arrivalAirport;

    @ColumnInfo(name = "departure_airport")
    private String departureAirport;

    @ColumnInfo(index = true)
    private Date departure = new Date();

    @ColumnInfo(index = true)
    private Date arrival = new Date();

    @ColumnInfo(name = "flight_number")
    private String flightNumber;

    @ColumnInfo(name = "confirmation_number")
    private String confirmationNumber;

    @ColumnInfo(name = "passenger_name")
    private String passengerName;

    @ColumnInfo(name = "flight_rewards")
    private String flightRewards;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getFlightRewards() {
        return flightRewards;
    }

    public void setFlightRewards(String flightRewards) {
        this.flightRewards = flightRewards;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
}