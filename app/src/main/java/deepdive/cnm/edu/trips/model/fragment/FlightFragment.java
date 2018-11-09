package deepdive.cnm.edu.trips.model.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class FlightFragment extends Fragment implements AddCallBack {

  private ListView view;

  public FlightFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = (ListView) inflater.inflate(R.layout.fragment_flight, container, false);
    new FlightTask().execute();
    return view;
  }

  @Override
  public void update() {
    new FlightTask().execute();
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

  public void refreshList() {
    new FlightTask().execute();
  }

  private class FlightListAdapter extends ArrayAdapter<Flight> {

    public FlightListAdapter(
        @NonNull Context context,
        @NonNull List<Flight> objects) {
      super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
        @NonNull ViewGroup parent) {
      final View view = getLayoutInflater()
          .inflate(R.layout.flight_card_template, parent, false);
      Flight flight = getItem(position);
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
      if (flight.getDepartureDate() != null && flight.getDepartureDate().length()>4) {
        ((TextView) view.findViewById(R.id.departure_date))
            .setText(flight.getDepartureDate().substring(0, 5));
      }
      ((TextView) view.findViewById(R.id.departure_time)).setText(flight.getDepartureTime());
      if (flight.getArrivalDate() != null && flight.getArrivalDate().length()>4) {
        ((TextView) view.findViewById(R.id.arrival_date))
            .setText(flight.getArrivalDate().substring(0, 5));
      }
      ((TextView) view.findViewById(R.id.arrival_time)).setText(flight.getArrivalTime());
      ((TextView) view.findViewById(R.id.flight_confirmation))
          .setText(flight.getConfirmationNumber());
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:m a");
      LocalTime arrivalTime = null;
      if (flight.getArrivalTime() != null && !flight.getArrivalTime().isEmpty()) {
        arrivalTime = LocalTime.parse(flight.getArrivalTime(), dateTimeFormatter);
      }
      LocalTime departureTime = null;
      if (flight.getDepartureTime() != null && !flight.getDepartureTime().isEmpty()) {
        departureTime = LocalTime.parse(flight.getDepartureTime(), dateTimeFormatter);
      }
      Duration length = null;
      if (departureTime != null && arrivalTime != null) {
        length = Duration.between(departureTime, arrivalTime);
      }
      long s = 0;
      if (length != null) {
        s = length.getSeconds();
      }
      String lengthString = String.format("%d:%02d", s / 3600, (s % 3600) / 60);
      ((TextView) view.findViewById(R.id.flight_length)).setText(lengthString);
      //    puts in EXPAND view
      view.findViewById(R.id.first_plane).setVisibility(View.VISIBLE);
      view.findViewById(R.id.airport_code_departure_1).setVisibility(View.VISIBLE);
      view.findViewById(R.id.airport_code_arrival_1).setVisibility(View.VISIBLE);
      view.findViewById(R.id.flight_length).setVisibility(View.VISIBLE);
      view.findViewById(R.id.passenger_1).setVisibility(View.GONE);
      view.findViewById(R.id.passenger_1_rewards).setVisibility(View.GONE);
      view.findViewById(R.id.airport_code_outbound).setVisibility(View.GONE);
      view.findViewById(R.id.airport_code_arrival).setVisibility(View.GONE);
      view.findViewById(R.id.departure_date).setVisibility(View.GONE);
      view.findViewById(R.id.departure_time).setVisibility(View.GONE);
      view.findViewById(R.id.arrival_date).setVisibility(View.GONE);
      view.findViewById(R.id.arrival_time).setVisibility(View.GONE);
      view.findViewById(R.id.flight_confirmation).setVisibility(View.GONE);
      view.findViewById(R.id.flight_number).setVisibility(View.GONE);
      view.findViewById(R.id.plane_outbound_icon).setVisibility(View.GONE);
//    expands card
      view.findViewById(R.id.flight_card_1).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if (view.findViewById(R.id.passenger_1).getVisibility() == View.VISIBLE) {
            view.findViewById(R.id.first_plane).setVisibility(View.VISIBLE);
            view.findViewById(R.id.airport_code_departure_1).setVisibility(View.VISIBLE);
            view.findViewById(R.id.airport_code_arrival_1).setVisibility(View.VISIBLE);
            view.findViewById(R.id.flight_length).setVisibility(View.VISIBLE);
            view.findViewById(R.id.passenger_1).setVisibility(View.GONE);
            view.findViewById(R.id.passenger_1_rewards).setVisibility(View.GONE);
            view.findViewById(R.id.airport_code_outbound).setVisibility(View.GONE);
            view.findViewById(R.id.airport_code_arrival).setVisibility(View.GONE);
            view.findViewById(R.id.departure_date).setVisibility(View.GONE);
            view.findViewById(R.id.departure_time).setVisibility(View.GONE);
            view.findViewById(R.id.arrival_date).setVisibility(View.GONE);
            view.findViewById(R.id.arrival_time).setVisibility(View.GONE);
            view.findViewById(R.id.flight_confirmation).setVisibility(View.GONE);
            view.findViewById(R.id.flight_number).setVisibility(View.GONE);
            view.findViewById(R.id.plane_outbound_icon).setVisibility(View.GONE);
          } else {
            view.findViewById(R.id.first_plane).setVisibility(View.GONE);
            view.findViewById(R.id.airport_code_departure_1).setVisibility(View.GONE);
            view.findViewById(R.id.airport_code_arrival_1).setVisibility(View.GONE);
            view.findViewById(R.id.flight_length).setVisibility(View.GONE);
            view.findViewById(R.id.passenger_1).setVisibility(View.VISIBLE);
            view.findViewById(R.id.passenger_1_rewards).setVisibility(View.VISIBLE);
            view.findViewById(R.id.airport_code_outbound).setVisibility(View.VISIBLE);
            view.findViewById(R.id.airport_code_arrival).setVisibility(View.VISIBLE);
            view.findViewById(R.id.departure_date).setVisibility(View.VISIBLE);
            view.findViewById(R.id.departure_time).setVisibility(View.VISIBLE);
            view.findViewById(R.id.arrival_date).setVisibility(View.VISIBLE);
            view.findViewById(R.id.arrival_time).setVisibility(View.VISIBLE);
            view.findViewById(R.id.flight_confirmation).setVisibility(View.VISIBLE);
            view.findViewById(R.id.flight_number).setVisibility(View.VISIBLE);
            view.findViewById(R.id.plane_outbound_icon).setVisibility(View.VISIBLE);
          }
        }
      });
      return view;
    }
  }
}
