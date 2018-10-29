package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity (foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
})

public class Flight {

    @ColumnInfo (name = "flight_id")
    @PrimaryKey (autoGenerate = true)
    private long id;

    @ColumnInfo (name = "person_id", index = true)
    private long personId;

    @ColumnInfo (name = "arrival_airport")
    private String arrivalAirport;

    @ColumnInfo (name = "departure_airport")
    private String departureAirport;

    @ColumnInfo (name = "departure_date", index = true)
    private Date departureDate;

    @ColumnInfo (name = "departure_time", index = true)
    private Time departureTime;

    @ColumnInfo (name = "arrival_date")
    private Date arrivalDate;

    @ColumnInfo (name = "arrival_time")
    private Time arrivalTime;

    @ColumnInfo (name = "flight_number")
    private String flightNumber;

    @ColumnInfo (name = "confirmation_name ")
    private String confirmationNumber;

    @ColumnInfo (name = "passenger_name")
    private long passengerName;

    private long rewards;

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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public long getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(long passengerName) {
        this.passengerName = passengerName;
    }

    public long getRewards() {
        return rewards;
    }

    public void setRewards(long rewards) {
        this.rewards = rewards;
    }
}
