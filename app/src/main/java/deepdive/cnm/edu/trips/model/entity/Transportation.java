package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity (foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
})
public class Transportation {

    @ColumnInfo(name = "transportation_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo (name = "person_id", index = true)
    private long personId;

    @ColumnInfo (name = "hotel_name")
    private long hotelName;

    private long address;

    private long phone;

    @ColumnInfo (name = "check_in")
    private Date checkIn;

    @ColumnInfo (name = "check_out")
    private Date checkOut;

    private String rewards;

    @ColumnInfo (name = "car_type")
    private String carType;

    private int cost;

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

    public long getHotelName() {
        return hotelName;
    }

    public void setHotelName(long hotelName) {
        this.hotelName = hotelName;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
