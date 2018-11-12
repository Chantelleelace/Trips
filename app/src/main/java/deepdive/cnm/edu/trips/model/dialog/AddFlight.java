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
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass to let users add their flight information to new cards.
 */
public class AddFlight extends DialogFragment {

  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String CALENDAR_KEY = "calendar";
  private String test;
  private Calendar calendar;
  private TextInputEditText date;
  private TextInputEditText date1;
  private TextInputEditText time;
  private TextInputEditText time1;
  private AddCallBack addCallBack;

  public AddFlight() {
    // Required empty public constructor
  }

  private void pickTime(final TextInputEditText timeField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.TIME);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      /**
       * This inputs the time from my time picker to my TextInputEditText.
       * @param calendar
       */
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getTimeFormat(getActivity());
        timeField.setText(format.format(calendar.getTime()));
      }

    });
  }

  private void pickDate(final TextInputEditText dateField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.DATE);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      /**
       * This sets the date from my date picker to my TextInputEditText.
       * @param calendar
       */
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
        dateField.setText(format.format(calendar.getTime()));
      }

    });
  }

  /**
   * Gives user a calendar and clock to input date and time fields. Also, this will save users input
   * to our data base and then set it to appropriate fields on the created card.
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
//    Inflates calendar and clock
    final View view = inflater.inflate(R.layout.flight_add, container, false);
    date = view.findViewById(R.id.departure_date_input);
    date.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickDate(date);
      }
    });
    date1 = view.findViewById(R.id.arrival_date_input);
    date1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickDate(date1);
      }
    });
    time = view.findViewById(R.id.departure_time_input);
    time.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickTime(time);
      }
    });
    time1 = view.findViewById(R.id.arrival_time_input);
    time1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickTime(time1);
      }
    });

//    saves all input to database
    view.findViewById(R.id.submit_flight).setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View clickView) {
            Flight flight = new Flight();
            if ((((TextInputEditText)
                view.findViewById(R.id.arrival_airport_input)).getText() != null)) {
              flight.setArrivalAirport(((TextInputEditText)
                  view.findViewById(R.id.arrival_airport_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.departure_airport_input)).getText() != null)) {
              flight.setDepartureAirport(((TextInputEditText)
                  view.findViewById(R.id.departure_airport_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.departure_date_input)).getText() != null)) {
              flight.setDepartureDate(((TextInputEditText)
                  view.findViewById(R.id.departure_date_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.arrival_date_input)).getText() != null)) {
              flight.setArrivalDate(((TextInputEditText)
                  view.findViewById(R.id.arrival_date_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.flight_number_input)).getText() != null)) {
              flight.setFlightNumber(((TextInputEditText)
                  view.findViewById(R.id.flight_number_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.flight_confirmation_input)).getText() != null)) {
              flight.setConfirmationNumber(((TextInputEditText)
                  view.findViewById(R.id.flight_confirmation_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.passenger_name_input)).getText() != null)) {
              flight.setPassengerName(((TextInputEditText)
                  view.findViewById(R.id.passenger_name_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.flight_rewards_input)).getText() != null)) {
              flight.setFlightRewards(((TextInputEditText)
                  view.findViewById(R.id.flight_rewards_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.arrival_time_input)).getText() != null)) {
              flight.setArrivalTime(((TextInputEditText)
                  view.findViewById(R.id.arrival_time_input)).getText().toString());
            }
            if ((((TextInputEditText)
                view.findViewById(R.id.departure_time_input)).getText() != null)) {
              flight.setDepartureTime(((TextInputEditText)
                  view.findViewById(R.id.departure_time_input)).getText().toString());
            }
            dismiss();
            new FlightTask().execute(flight);
          }
        });

    return view;
  }

  /**
   * This add (Flight) call back adds a new card to my Flight fragment from add flight.
   * @param addCallBack
   */
  public void setAddCallBack(AddCallBack addCallBack) {
    this.addCallBack = addCallBack;
  }

  private class FlightTask extends AsyncTask<Flight, Void, Void> {

    @Override
    protected Void doInBackground(Flight... flight) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      tripsDatabase.getFlightDao().insert(flight[0]);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      addCallBack.update();
    }
  }
}
