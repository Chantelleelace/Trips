package deepdive.cnm.edu.trips.model.dialog;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.OnChangeListener;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Hotel;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddHotel extends DialogFragment {

  private TextInputEditText checkInDate;
  private TextInputEditText checkOutDate;
  private Calendar calendar;
  private AddCallBack addCallBack;


  public AddHotel() {
    // Required empty public constructor
  }

  private void pickCheckInDate(final TextInputEditText dateField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.DATE);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
        dateField.setText(format.format(calendar.getTime()));
      }

    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_add_hotel, container, false);
    checkInDate = view.findViewById(R.id.check_in_date_input);
    checkInDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickCheckInDate(checkInDate);
      }
    });
    checkOutDate = view.findViewById(R.id.check_out_date_input);
    checkOutDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickCheckInDate(checkOutDate);
      }
    });

    view.findViewById(R.id.submit_hotel).setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            Hotel hotel = new Hotel();
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
          }
        });

    return view;
  }

  public void setAddCallBack(AddCallBack addCallBack) {
    this.addCallBack = addCallBack;
  }

  private class HotelTask extends AsyncTask<Hotel, Void, Void> {

    @Override
    protected Void doInBackground(Hotel... hotel) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      tripsDatabase.getHotelDao().insert(hotel[0]);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      addCallBack.update();
    }
  }
}
