package deepdive.cnm.edu.trips.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity//(foreignKeys = {
  //  @ForeignKey(entity = Person.class, parentColumns = "person_id", childColumns = "person_id", onDelete = ForeignKey.CASCADE)
//})
public class Transportation {

  @ColumnInfo(name = "transportation_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "person_id", index = true)
  private long personId;

  @ColumnInfo(name = "company_name")
  private String rentalCompanyName;

  @ColumnInfo(name = "company_address")
  private String rentalCompanyAddress;

  @ColumnInfo(name = "rental_city")
  private String rentalCity;

  @ColumnInfo(name = "rental_state")
  private String rentalState;

  @ColumnInfo(name = "rental_zip")
  private String rentalZip;

  @ColumnInfo(name = "company_phone")
  private String rentalCompanyPhone;

  @ColumnInfo(name = "pick_up")
  private String rentalPickUp;

  @ColumnInfo(name = "return")
  private String rentalReturn;

  @ColumnInfo(name = "rental_rewards")
  private String rentalRewards;

  @ColumnInfo(name = "car_type")
  private String carType;

  @ColumnInfo(name = "rental_cost")
  private String rentalCost;

  @ColumnInfo(name = "name_on_rental_reservation")
  private String nameOnRentalReservation;

  @ColumnInfo(name = "rental_confirmation")
  private String rentalConfirmation;

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

  public String getRentalCompanyName() {
    return rentalCompanyName;
  }

  public void setRentalCompanyName(String rentalCompanyName) {
    this.rentalCompanyName = rentalCompanyName;
  }

  public String getRentalCompanyAddress() {
    return rentalCompanyAddress;
  }

  public void setRentalCompanyAddress(String rentalCompanyAddress) {
    this.rentalCompanyAddress = rentalCompanyAddress;
  }

  public String getRentalCompanyPhone() {
    return rentalCompanyPhone;
  }

  public void setRentalCompanyPhone(String rentalCompanyPhone) {
    this.rentalCompanyPhone = rentalCompanyPhone;
  }

  public String getRentalPickUp() {
    return rentalPickUp;
  }

  public void setRentalPickUp(String rentalPickUp) {
    this.rentalPickUp = rentalPickUp;
  }

  public String getRentalReturn() {
    return rentalReturn;
  }

  public void setRentalReturn(String rentalReturn) {
    this.rentalReturn = rentalReturn;
  }

  public String getRentalRewards() {
    return rentalRewards;
  }

  public void setRentalRewards(String rentalRewards) {
    this.rentalRewards = rentalRewards;
  }

  public String getCarType() {
    return carType;
  }

  public void setCarType(String carType) {
    this.carType = carType;
  }

  public String getRentalCost() {
    return rentalCost;
  }

  public void setRentalCost(String rentalCost) {
    this.rentalCost = rentalCost;
  }

  public String getNameOnRentalReservation() {
    return nameOnRentalReservation;
  }

  public void setNameOnRentalReservation(String nameOnRentalReservation) {
    this.nameOnRentalReservation = nameOnRentalReservation;
  }

  public String getRentalConfirmation() {
    return rentalConfirmation;
  }

  public void setRentalConfirmation(String rentalConfirmation) {
    this.rentalConfirmation = rentalConfirmation;
  }

  public String getRentalCity() {
    return rentalCity;
  }

  public void setRentalCity(String rentalCity) {
    this.rentalCity = rentalCity;
  }

  public String getRentalState() {
    return rentalState;
  }

  public void setRentalState(String rentalState) {
    this.rentalState = rentalState;
  }

  public String getRentalZip() {
    return rentalZip;
  }

  public void setRentalZip(String rentalZip) {
    this.rentalZip = rentalZip;
  }
}