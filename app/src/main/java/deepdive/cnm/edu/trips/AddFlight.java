package deepdive.cnm.edu.trips;


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
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.OnChangeListener;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
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

  public AddFlight() {
    // Required empty public constructor
  }

  private void pickTime(final TextInputEditText timeField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.TIME);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
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
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
        dateField.setText(format.format(calendar.getTime()));
      }

    });
  }

  /**
   * Gives user a calendar and clock to input date and time fields.
   * Also, this will save users input to our data base for future use.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
//    Inflates calendar and clock
    View view = inflater.inflate(R.layout.fragment_add_flight, container, false);
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
          public void onClick(View view) {
            Flight flight = new Flight();
            flight.setArrivalAirport(((TextInputEditText)
                view.findViewById(R.id.arrival_airport_input)).getText().toString());
            flight.setDepartureAirport(((TextInputEditText)
                view.findViewById(R.id.departure_airport_input)).getText().toString());
            flight.setDepartureDate(((TextInputEditText)
                view.findViewById(R.id.departure_date_input)).getText().toString());
            flight.setArrivalDate(((TextInputEditText)
                view.findViewById(R.id.arrival_date_input)).getText().toString());
            flight.setFlightNumber(((TextInputEditText)
                view.findViewById(R.id.flight_number_input)).getText().toString());
            flight.setConfirmationNumber(((TextInputEditText)
                view.findViewById(R.id.flight_confirmation_input)).getText().toString());
            flight.setPassengerName(((TextInputEditText)
                view.findViewById(R.id.passenger_name_input)).getText().toString());
            flight.setFlightRewards(((TextInputEditText)
                view.findViewById(R.id.flight_rewards_input)).getText().toString());
            flight.setArrivalTime(((TextInputEditText)
                view.findViewById(R.id.arrival_time_input)).getText().toString());
            flight.setDepartureTime(((TextInputEditText)
                view.findViewById(R.id.departure_time_input)).getText().toString());
          }
        });

    return view;
  }

  private class FlightTask extends AsyncTask<Flight, Void, Void> {

    @Override
    protected Void doInBackground(Flight... flight) {
      return null;
    }
  }

}
