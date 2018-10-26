package deepdive.cnm.edu.trips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Flight extends Fragment {


  public Flight() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_flight, container, false);
//    puts in EXPAND view
    view.findViewById(R.id.passenger_1).setVisibility(View.GONE);
    view.findViewById(R.id.airport_code_outbound).setVisibility(View.VISIBLE);
    view.findViewById(R.id.airport_code_arrival).setVisibility(View.VISIBLE);
    view.findViewById(R.id.departure_date).setVisibility(View.VISIBLE);
//    expands card
    view.findViewById(R.id.flight_card_1).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (view.findViewById(R.id.passenger_1).getVisibility() == View.VISIBLE) {
          view.findViewById(R.id.passenger_1).setVisibility(View.GONE);
          view.findViewById(R.id.airport_code_outbound).setVisibility(View.VISIBLE);
          view.findViewById(R.id.airport_code_arrival).setVisibility(View.VISIBLE);
          view.findViewById(R.id.departure_date).setVisibility(View.VISIBLE);
          view.findViewById(R.id.departure_time).setVisibility(View.VISIBLE);
        } else {
          view.findViewById(R.id.passenger_1).setVisibility(View.VISIBLE);
          view.findViewById(R.id.airport_code_outbound).setVisibility(View.VISIBLE);
          view.findViewById(R.id.airport_code_arrival).setVisibility(View.VISIBLE);
          view.findViewById(R.id.departure_date).setVisibility(View.VISIBLE);
          view.findViewById(R.id.departure_time).setVisibility(View.VISIBLE);
        }
      }
    });
    return view;
  }

}
