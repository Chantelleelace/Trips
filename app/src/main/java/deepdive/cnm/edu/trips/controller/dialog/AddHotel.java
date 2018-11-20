package deepdive.cnm.edu.trips.controller.dialog;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Hotel;


/**
 * A simple {@link Fragment} subclass that lets the user add their own hotel information to create
 * new cards.
 */
public class AddHotel extends AddDialog {

  private TextInputEditText checkInDate;
  private TextInputEditText checkOutDate;
  private AddCallBack addCallBack;
  private long hotelId;
  private Hotel hotel;
  private View view;

  /**
   * Sets hotel id.
   *
   * @param hotelId the hotel id
   */
  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }


  /**
   * Instantiates a new Add hotel.
   */
  public AddHotel() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflates the layout for this fragment
    // Inflates calendar for user to pick date
    view = inflater.inflate(R.layout.hotel_add, container, false);
    checkInDate = view.findViewById(R.id.check_in_date_input);
    checkInDate.setOnClickListener(v -> pickDate(checkInDate));
    checkOutDate = view.findViewById(R.id.check_out_date_input);
    checkOutDate.setOnClickListener(v -> pickDate(checkOutDate));
    // saves all input to database
    view.findViewById(R.id.submit_hotel).setOnClickListener(
        v -> {
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_name_input)).getText() != null)) {
            hotel.setHotelName(((TextInputEditText)
                view.findViewById(R.id.hotel_name_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.check_in_date_input)).getText() != null)) {
            hotel.setCheckIn(((TextInputEditText)
                view.findViewById(R.id.check_in_date_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.check_out_date_input)).getText() != null)) {
            hotel.setCheckOut(((TextInputEditText)
                view.findViewById(R.id.check_out_date_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_confirmation_input)).getText() != null)) {
            hotel.setHotelConfirmation(((TextInputEditText)
                view.findViewById(R.id.hotel_confirmation_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_rewards_input)).getText() != null)) {
            hotel.setHotelRewards(((TextInputEditText)
                view.findViewById(R.id.hotel_rewards_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_room_type_input)).getText() != null)) {
            hotel.setRoomType(((TextInputEditText)
                view.findViewById(R.id.hotel_room_type_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_cost_input)).getText() != null)) {
            hotel.setCost(((TextInputEditText)
                view.findViewById(R.id.hotel_cost_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_address_input)).getText() != null)) {
            hotel.setHotelAddress(((TextInputEditText)
                view.findViewById(R.id.hotel_address_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_phone_input)).getText() != null)) {
            hotel.setHotelPhone(((TextInputEditText)
                view.findViewById(R.id.hotel_phone_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.hotel_name_on_reservation_input)).getText() != null)) {
            hotel.setNameOnResrvation(((TextInputEditText)
                view.findViewById(R.id.hotel_name_on_reservation_input)).getText().toString());
          }
          dismiss();
          new HotelTask().execute(hotel);
        });
    if (hotelId != 0) {
      new QueryTask().execute(hotelId);
    } else {
      hotel = new Hotel();
    }
    ((TextInputEditText) view.findViewById(R.id.hotel_phone_input)).addTextChangedListener(
        new PhoneNumberFormattingTextWatcher());
    return view;
  }

  /**
   * This add (Hotel) call back adds a new card to my Hotel fragment from my add hotel fragment.
   *
   * @param addCallBack the add call back
   */
  public void setAddCallBack(AddCallBack addCallBack) {
    this.addCallBack = addCallBack;
  }

  private class HotelTask extends AsyncTask<Hotel, Void, Void> {

    @Override
    protected Void doInBackground(Hotel... hotel) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      if (hotel[0].getId() != 0) {
        tripsDatabase.getHotelDao().update(hotel[0]);
      } else {
        tripsDatabase.getHotelDao().insert(hotel[0]);
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      addCallBack.update();
    }
  }

  private class QueryTask extends AsyncTask<Long, Void, Hotel> {

    @Override
    protected void onPostExecute(Hotel hotel) {
      AddHotel.this.hotel = hotel;
      // sets text from database into fields for user to edit
      ((TextInputEditText) view.findViewById(R.id.hotel_name_input)).setText(hotel.getHotelName());
      ((TextInputEditText) view.findViewById(R.id.check_in_date_input)).setText(hotel.getCheckIn());
      ((TextInputEditText) view.findViewById(R.id.check_out_date_input))
          .setText(hotel.getCheckOut());
      ((TextInputEditText) view.findViewById(R.id.hotel_confirmation_input))
          .setText(hotel.getHotelConfirmation());
      ((TextInputEditText) view.findViewById(R.id.hotel_rewards_input))
          .setText(hotel.getHotelRewards());
      ((TextInputEditText) view.findViewById(R.id.hotel_room_type_input))
          .setText(hotel.getRoomType());
      ((TextInputEditText) view.findViewById(R.id.hotel_cost_input)).setText(hotel.getCost());
      ((TextInputEditText) view.findViewById(R.id.hotel_address_input))
          .setText(hotel.getHotelAddress());
      ((TextInputEditText) view.findViewById(R.id.hotel_phone_input))
          .setText(hotel.getHotelPhone());
      ((TextInputEditText) view.findViewById(R.id.hotel_name_on_reservation_input))
          .setText(hotel.getNameOnResrvation());
    }

    @Override
    protected Hotel doInBackground(Long... hotelIds) {
      return TripsDatabase.getInstance(getContext()).getHotelDao().selectOne(hotelIds[0]);
    }
  }
}
