package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity (foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
})
public class Hotel {

    @ColumnInfo (name = "hotel_id")
    @PrimaryKey (autoGenerate = true)
    private long id;

    @ColumnInfo (name = "person_id", index = true)
    private long personId;

    private String name;

    @ColumnInfo (name = "check_in", index = true)
    private Date checkIn;

    @ColumnInfo (name = "check_out", index = true)
    private Date checkOut;

    private int confirmation;

    private int rewards;

    @ColumnInfo (name = "room_type")
    private String roomType;

    private int cost;

    @ColumnInfo (name = "hotel_address")
    private String hotelAddress;

    @ColumnInfo (name = "hotel_phone")
    private int hotelPhone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(int hotelPhone) {
        this.hotelPhone = hotelPhone;
    }
}
