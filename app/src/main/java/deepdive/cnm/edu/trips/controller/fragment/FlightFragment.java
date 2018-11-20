package deepdive.cnm.edu.trips.controller.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.controller.dialog.AddFlight;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class FlightFragment extends Fragment implements AddCallBack {

  private ListView view;

  /**
   * Instantiates a new Flight fragment.
   */
  public FlightFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = (ListView) inflater.inflate(R.layout.flight_fragment, container, false);
    new FlightTask().execute();
    return view;
  }

  @Override
  public void update() {
    new FlightTask().execute();
  }

  private class DeleteTask extends AsyncTask<Flight, Void, Void> {

    @Override
    protected Void doInBackground(Flight... flight) {
      TripsDatabase.getInstance(getActivity()).getFlightDao().delete(flight[0]);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      new FlightTask().execute();
    }
  }

  private class FlightTask extends AsyncTask<Void, Void, List<Flight>> {

    @Override
    protected List<Flight> doInBackground(Void... voids) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      return tripsDatabase.getFlightDao().select();
    }

    @Override
    protected void onPostExecute(List<Flight> flights) {
      view.setAdapter(new FlightListAdapter(getActivity(), flights));

    }
  }

  /**
   * Refresh list.
   */
  public void refreshList() {
    new FlightTask().execute();
  }

  private class FlightListAdapter extends ArrayAdapter<Flight> {

    /**
     * Instantiates a new Flight list adapter.
     *
     * @param context the context
     * @param objects the objects
     */
    public FlightListAdapter(
        @NonNull Context context,
        @NonNull List<Flight> objects) {
      super(context, 0, objects);
    }

    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
        @NonNull ViewGroup parent) {
      java.text.DateFormat dateFormat = new SimpleDateFormat("MM/dd");
      java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getActivity());
      final View view = getLayoutInflater()
          .inflate(R.layout.flight_card_template, parent, false);
      final Flight flight = getItem(position);
      ((TextView) view.findViewById(R.id.flight_number)).setText(flight.getFlightNumber());
      ((TextView) view.findViewById(R.id.passenger_1)).setText(flight.getPassengerName());
      ((TextView) view.findViewById(R.id.passenger_1_rewards)).setText(flight.getFlightRewards());
      ((TextView) view.findViewById(R.id.airport_code_outbound))
          .setText(flight.getDepartureAirport());
      ((TextView) view.findViewById(R.id.airport_code_departure_1))
          .setText(flight.getDepartureAirport());
      ((TextView) view.findViewById(R.id.airport_code_arrival)).setText(flight.getArrivalAirport());
      ((TextView) view.findViewById(R.id.airport_code_arrival_1))
          .setText(flight.getArrivalAirport());
      if (flight.getDeparture() != null) {
        ((TextView) view.findViewById(R.id.departure_date))
            .setText(dateFormat.format(flight.getDeparture()));
      }
      ((TextView) view.findViewById(R.id.departure_time))
          .setText(timeFormat.format(flight.getDeparture()));
      if (flight.getArrival() != null) {
        ((TextView) view.findViewById(R.id.arrival_date))
            .setText(dateFormat.format(flight.getArrival()));
      }
      ((TextView) view.findViewById(R.id.arrival_time))
          .setText(timeFormat.format(flight.getArrival()));
      ((TextView) view.findViewById(R.id.flight_confirmation))
          .setText(flight.getConfirmationNumber());
      Duration length = null;
      if (flight.getDeparture() != null && flight.getArrival() != null) {
        length = Duration.between(LocalDateTime.ofInstant(flight.getDeparture().toInstant(),
            ZoneId.systemDefault()),
            LocalDateTime.ofInstant(flight.getArrival().toInstant(), ZoneId.systemDefault()));
      }
      long s = 0;
      if (length != null) {
        s = length.getSeconds();
      }
      String lengthString = String.format("%d:%02d", s / 3600, (s % 3600) / 60);
      ((TextView) view.findViewById(R.id.flight_length)).setText(lengthString);
      AppCompatImageButton deleteButton = view.findViewById(R.id.trash_flight);
      deleteButton.setOnClickListener((v) -> new DeleteTask().execute(flight));
      AppCompatImageButton editButton = view.findViewById(R.id.edit_flight);
      editButton.setOnClickListener((v) -> {
        AddFlight newFragment = new AddFlight();
        newFragment.setFlightId(flight.getId());
        newFragment.setAddCallBack(FlightFragment.this);
        newFragment.show(getActivity().getSupportFragmentManager(), "add flight dialog");
      });
      //    puts in EXPAND view
      expandView(view, false);
      //    expands card
      view.setOnClickListener(v -> {
        if (view.findViewById(R.id.passenger_1).getVisibility() == View.VISIBLE) {
          expandView(view, false);
        } else {
          expandView(view, true);
        }
      });
      return view;
    }
  }

  private void expandView(View view, boolean expand) {
    int visibilityTop = View.VISIBLE;
    int visibilityBottom = View.GONE;
    if (expand) {
      visibilityTop = View.GONE;
      visibilityBottom = View.VISIBLE;

    }
    view.findViewById(R.id.first_plane).setVisibility(visibilityTop);
    view.findViewById(R.id.airport_code_departure_1).setVisibility(visibilityTop);
    view.findViewById(R.id.airport_code_arrival_1).setVisibility(visibilityTop);
    view.findViewById(R.id.flight_length).setVisibility(visibilityTop);
    view.findViewById(R.id.expand_more_flight).setVisibility(visibilityTop);
    view.findViewById(R.id.passenger_1).setVisibility(visibilityBottom);
    view.findViewById(R.id.passenger_1_rewards).setVisibility(visibilityBottom);
    view.findViewById(R.id.airport_code_outbound).setVisibility(visibilityBottom);
    view.findViewById(R.id.airport_code_arrival).setVisibility(visibilityBottom);
    view.findViewById(R.id.departure_date).setVisibility(visibilityBottom);
    view.findViewById(R.id.departure_time).setVisibility(visibilityBottom);
    view.findViewById(R.id.arrival_date).setVisibility(visibilityBottom);
    view.findViewById(R.id.arrival_time).setVisibility(visibilityBottom);
    view.findViewById(R.id.flight_confirmation).setVisibility(visibilityBottom);
    view.findViewById(R.id.flight_number).setVisibility(visibilityBottom);
    view.findViewById(R.id.plane_outbound_icon).setVisibility(visibilityBottom);
    view.findViewById(R.id.expand_less_flight).setVisibility(visibilityBottom);
    view.findViewById(R.id.trash_flight).setVisibility(visibilityBottom);
    view.findViewById(R.id.edit_flight).setVisibility(visibilityBottom);
  }
}
