package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity (foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
})
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

    @ColumnInfo(name = "departure_date", index = true)
    private String departureDate;

    @ColumnInfo(name = "arrival_date")
    private String arrivalDate;

    @ColumnInfo(name = "flight_number")
    private String flightNumber;

    @ColumnInfo(name = "confirmation_number")
    private String confirmationNumber;

    @ColumnInfo(name = "passenger_name")
    private String passengerName;

    @ColumnInfo(name = "flight_rewards")
    private String flightRewards;

    @ColumnInfo(name = "arrival_time", index = true)
    private String arrivalTime;

    @ColumnInfo(name = "departure_time")
    private String departureTime;

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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}