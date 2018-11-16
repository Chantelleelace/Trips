package deepdive.cnm.edu.trips.controller.dialog;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass to let users add their flight information to new cards.
 */
public class AddFlight extends AddDialog {

  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String CALENDAR_KEY = "calendar";
  private String test;
  private TextInputEditText date;
  private TextInputEditText date1;
  private TextInputEditText time;
  private TextInputEditText time1;
  private AddCallBack addCallBack;
  private long flightId;
  private Flight flight;
  private View view;

  /**
   * Sets flight id.
   *
   * @param flightId the flight id
   */
  public void setFlightId(long flightId) {
    this.flightId = flightId;
  }

  /**
   * Instantiates a new Add flight.
   */
  public AddFlight() {
    // Required empty public constructor
  }

  private void pickTime(final TextInputEditText timeField, final boolean isDeparture) {
    Calendar calendar = Calendar.getInstance();
    DateTimeFragment picker = new DateTimeFragment();
    if (isDeparture) {
      calendar.setTime(flight.getDeparture());
    } else {
      calendar.setTime(flight.getArrival());
    }
    picker.setMode(Mode.TIME);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(cal -> {
      // TODO Deal with timezone issues
      if (isDeparture) {
        if (cal.getTime().compareTo(flight.getArrival()) > 0) {
          Toast.makeText(getActivity(),
              "Please choose an arrival time that is after the current departure time.",
              Toast.LENGTH_LONG).show();
          return;
        }
        flight.setDeparture(cal.getTime());
      } else {
        if (cal.getTime().compareTo(flight.getDeparture()) < 0) {
          Toast.makeText(getActivity(),
              "Please choose an arrival time that is after the current departure time.",
              Toast.LENGTH_LONG).show();
          return;
        }
        flight.setArrival(cal.getTime());
      }
      java.text.DateFormat format = DateFormat.getTimeFormat(getActivity());
      timeField.setText(format.format(cal.getTime()));
    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflates the layout for this fragment
    // Inflates calendar and clock
    view = inflater.inflate(R.layout.flight_add, container, false);
    date = view.findViewById(R.id.departure_date_input);
    date.setOnClickListener(v -> pickDate(date));
    date1 = view.findViewById(R.id.arrival_date_input);
    date1.setOnClickListener(v -> pickDate(date1));
    time = view.findViewById(R.id.departure_time_input);
    time.setOnClickListener(v -> pickTime(time, true));
    time1 = view.findViewById(R.id.arrival_time_input);
    time1.setOnClickListener(v -> pickTime(time1, false));
    // saves all input to database
    view.findViewById(R.id.submit_flight).setOnClickListener(
        clickView -> {
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
//          if ((((TextInputEditText)
//              view.findViewById(R.id.departure_date_input)).getText() != null)) {
//            flight.setDepartureDate(((TextInputEditText)
//                view.findViewById(R.id.departure_date_input)).getText().toString());
//          }
//          if ((((TextInputEditText)
//              view.findViewById(R.id.arrival_date_input)).getText() != null)) {
//            flight.setArrivalDate(((TextInputEditText)
//                view.findViewById(R.id.arrival_date_input)).getText().toString());
//          }
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
//          if ((((TextInputEditText)
//              view.findViewById(R.id.arrival_time_input)).getText() != null)) {
//            flight.setArrivalTime(((TextInputEditText)
//                view.findViewById(R.id.arrival_time_input)).getText().toString());
//          }
//          if ((((TextInputEditText)
//              view.findViewById(R.id.departure_time_input)).getText() != null)) {
//            flight.setDepartureTime(((TextInputEditText)
//                view.findViewById(R.id.departure_time_input)).getText().toString());
//          }
          dismiss();
          new FlightTask().execute(flight);
        });
    if (flightId != 0) {
      new QueryTask().execute(flightId);
    } else {
      flight = new Flight();
    }
    return view;
  }

  /**
   * This add (Flight) call back adds a new card to my Flight fragment from add flight.
   *
   * @param addCallBack the add call back
   */
  public void setAddCallBack(AddCallBack addCallBack) {
    this.addCallBack = addCallBack;
  }

  private class FlightTask extends AsyncTask<Flight, Void, Void> {

    @Override
    protected Void doInBackground(Flight... flight) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      if (flight[0].getId() != 0) {
        tripsDatabase.getFlightDao().update(flight[0]);
      } else {
        tripsDatabase.getFlightDao().insert(flight[0]);
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      addCallBack.update();
    }
  }

  private class QueryTask extends AsyncTask<Long, Void, Flight> {

    @Override
    protected void onPostExecute(Flight flight) {
      java.text.DateFormat dateFormat = DateFormat.getDateFormat(getActivity());
      java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getActivity());
      AddFlight.this.flight = flight;
      // sets text from database into fields for user to edit
      ((TextInputEditText) view.findViewById(R.id.arrival_airport_input))
          .setText(flight.getArrivalAirport());
      ((TextInputEditText) view.findViewById(R.id.departure_airport_input))
          .setText(flight.getDepartureAirport());
      ((TextInputEditText) view.findViewById(R.id.departure_date_input))
          .setText(dateFormat.format(flight.getDeparture()));
      ((TextInputEditText) view.findViewById(R.id.arrival_date_input))
          .setText(dateFormat.format(flight.getArrival()));
      ((TextInputEditText) view.findViewById(R.id.flight_number_input))
          .setText(flight.getFlightNumber());
      ((TextInputEditText) view.findViewById(R.id.flight_confirmation_input))
          .setText(flight.getConfirmationNumber());
      ((TextInputEditText) view.findViewById(R.id.passenger_name_input))
          .setText(flight.getPassengerName());
      ((TextInputEditText) view.findViewById(R.id.flight_rewards_input))
          .setText(flight.getFlightRewards());
      ((TextInputEditText) view.findViewById(R.id.arrival_time_input))
          .setText(timeFormat.format(flight.getArrival()));
      ((TextInputEditText) view.findViewById(R.id.departure_time_input))
          .setText(timeFormat.format(flight.getDeparture()));
    }

    @Override
    protected Flight doInBackground(Long... flightIds) {
      return TripsDatabase.getInstance(getContext()).getFlightDao().selectOne(flightIds[0]);
    }
  }
}
