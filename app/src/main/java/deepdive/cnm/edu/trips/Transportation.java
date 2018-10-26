package deepdive.cnm.edu.trips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Transportation extends Fragment {


  public Transportation() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_transportation, container, false);
//    puts in EXPAND view
    view.findViewById(R.id.calendar_check_in_car).setVisibility(View.VISIBLE);
    view.findViewById(R.id.calendar_check_out_car).setVisibility(View.VISIBLE);
    view.findViewById(R.id.rental_company_address).setVisibility(View.GONE);
    view.findViewById(R.id.rental_company_phone).setVisibility(View.GONE);
    view.findViewById(R.id.rental_check_in).setVisibility(View.GONE);
    view.findViewById(R.id.rental_check_out).setVisibility(View.GONE);
    view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.GONE);
    view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.GONE);
    view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.GONE);
    view.findViewById(R.id.rental_confirmation).setVisibility(View.GONE);
    view.findViewById(R.id.rental_rewards).setVisibility(View.GONE);
    view.findViewById(R.id.car_type).setVisibility(View.GONE);
    view.findViewById(R.id.rental_cost).setVisibility(View.GONE);
//    expands card
    view.findViewById(R.id.transportation_card_1).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (view.findViewById(R.id.calendar_check_in_car).getVisibility() == View.VISIBLE) {
          view.findViewById(R.id.calendar_check_in_car).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_out_car).setVisibility(View.GONE);
          view.findViewById(R.id.rental_company_address).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_company_phone).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_check_in).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_check_out).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.VISIBLE);
          view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_confirmation).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_rewards).setVisibility(View.VISIBLE);
          view.findViewById(R.id.car_type).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_cost).setVisibility(View.VISIBLE);
        } else {
          view.findViewById(R.id.calendar_check_in_car).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_out_car).setVisibility(View.VISIBLE);
          view.findViewById(R.id.rental_company_address).setVisibility(View.GONE);
          view.findViewById(R.id.rental_company_phone).setVisibility(View.GONE);
          view.findViewById(R.id.rental_check_in).setVisibility(View.GONE);
          view.findViewById(R.id.rental_check_out).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.GONE);
          view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.GONE);
          view.findViewById(R.id.rental_confirmation).setVisibility(View.GONE);
          view.findViewById(R.id.rental_rewards).setVisibility(View.GONE);
          view.findViewById(R.id.car_type).setVisibility(View.GONE);
          view.findViewById(R.id.rental_cost).setVisibility(View.GONE);
        }
      }
    });
    return view;
  }



}
