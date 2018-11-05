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

    @ColumnInfo(name = "hotel_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "person_id", index = true)
    private long personId;

    @ColumnInfo(name = "hotel_name")
    private String hotelName;

    @ColumnInfo(name = "check_in", index = true)
    private String checkIn;

    @ColumnInfo(name = "check_out", index = true)
    private String checkOut;

    @ColumnInfo(name = "hotel_confirmation")
    private String hotelConfirmation;

    @ColumnInfo(name = "hotel_rewards")
    private String hotelRewards;

    @ColumnInfo(name = "room_type")
    private String roomType;

    @ColumnInfo(name = "hotel_cost")
    private String cost;

    @ColumnInfo(name = "hotel_address")
    private String hotelAddress;

    @ColumnInfo(name = "hotel_phone")
    private String hotelPhone;

    @ColumnInfo(name = "name_on_reservation")
    private String nameOnResrvation;

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getHotelConfirmation() {
        return hotelConfirmation;
    }

    public void setHotelConfirmation(String hotelConfirmation) {
        this.hotelConfirmation = hotelConfirmation;
    }

    public String getHotelRewards() {
        return hotelRewards;
    }

    public void setHotelRewards(String hotelRewards) {
        this.hotelRewards = hotelRewards;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getNameOnResrvation() {
        return nameOnResrvation;
    }

    public void setNameOnResrvation(String nameOnResrvation) {
        this.nameOnResrvation = nameOnResrvation;
    }
}