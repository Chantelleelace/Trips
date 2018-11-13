package deepdive.cnm.edu.trips.model.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.dialog.AddTransportation;
import deepdive.cnm.edu.trips.model.entity.Transportation;
import java.util.List;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class TransportationFragment extends Fragment implements AddCallBack {

  private ListView view;

  public TransportationFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = (ListView) inflater.inflate(R.layout.transportation_fragment, container, false);
    new TransportationTask().execute();
    return view;
  }

  @Override
  public void update() {
    new TransportationTask().execute();
  }

  private class TransportationTask extends AsyncTask<Void, Void, List<Transportation>> {

    @Override
    protected List<Transportation> doInBackground(Void... voids) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      return tripsDatabase.getTransportationDao().select();
    }

    @Override
    protected void onPostExecute(List<Transportation> transportations) {
      view.setAdapter(new TransportationListAdapter(getActivity(), transportations));

    }
  }

  public void refreshList() { new TransportationTask().execute(); }

  private class TransportationListAdapter extends ArrayAdapter<Transportation> {

    public TransportationListAdapter(
        @NonNull Context context,
        @NonNull List<Transportation> objects) {
      super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
        @NonNull ViewGroup parent) {
      final View view = getLayoutInflater()
          .inflate(R.layout.transportation_card_template, parent, false);
      Transportation transportation = getItem(position);
      ((TextView) view.findViewById(R.id.rental_company_name))
          .setText(transportation.getRentalCompanyName());
      ((TextView) view.findViewById(R.id.rental_company_address))
          .setText(transportation.getRentalCompanyAddress());
      ((TextView) view.findViewById(R.id.rental_company_phone))
          .setText(transportation.getRentalCompanyPhone());
      ((TextView) view.findViewById(R.id.rental_return))
          .setText(transportation.getRentalReturn());
      ((TextView) view.findViewById(R.id.rental_pick_up))
          .setText(transportation.getRentalPickUp());
      ((TextView) view.findViewById(R.id.name_on_rental_reservation))
          .setText(transportation.getNameOnRentalReservation());
      ((TextView) view.findViewById(R.id.rental_confirmation))
          .setText(transportation.getRentalConfirmation());
      ((TextView) view.findViewById(R.id.rental_rewards))
          .setText(transportation.getRentalRewards());
      ((TextView) view.findViewById(R.id.car_type)).setText(transportation.getCarType());
      ((TextView) view.findViewById(R.id.rental_cost)).setText(transportation.getRentalCost());
      Button editButton = view.findViewById(R.id.edit_transportation);
      editButton.setOnClickListener((v) -> {
        // TODO ADD code to display AddTransportation dialog fragment
        AddTransportation newFragment = new AddTransportation();
        newFragment.setTransportationId(transportation.getId());
        newFragment.setAddCallBack(TransportationFragment.this);
        newFragment.show(getActivity().getSupportFragmentManager(), "add transportation dialog");
      });
      //    puts in EXPAND view
      view.findViewById(R.id.calendar_check_in_car).setVisibility(View.VISIBLE);
      view.findViewById(R.id.calendar_check_out_car).setVisibility(View.VISIBLE);
      view.findViewById(R.id.expand_more_transportation).setVisibility(View.VISIBLE);
      view.findViewById(R.id.rental_company_address).setVisibility(View.GONE);
      view.findViewById(R.id.rental_company_phone).setVisibility(View.GONE);
      view.findViewById(R.id.rental_return).setVisibility(View.GONE);
      view.findViewById(R.id.rental_pick_up).setVisibility(View.GONE);
      view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.GONE);
      view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.GONE);
      view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.GONE);
      view.findViewById(R.id.rental_confirmation).setVisibility(View.GONE);
      view.findViewById(R.id.rental_rewards).setVisibility(View.GONE);
      view.findViewById(R.id.car_type).setVisibility(View.GONE);
      view.findViewById(R.id.rental_cost).setVisibility(View.GONE);
      view.findViewById(R.id.expand_less_transportation).setVisibility(View.GONE);
      view.findViewById(R.id.trash_transportation).setVisibility(View.GONE);
      view.findViewById(R.id.edit_transportation).setVisibility(View.GONE);
//    expands card
      view.findViewById(R.id.transportation_card_1)
          .setOnClickListener(v -> {
            if (view.findViewById(R.id.calendar_check_in_car).getVisibility() == View.VISIBLE) {
              view.findViewById(R.id.calendar_check_in_car).setVisibility(View.GONE);
              view.findViewById(R.id.calendar_check_out_car).setVisibility(View.GONE);
              view.findViewById(R.id.expand_more_transportation).setVisibility(View.GONE);
              view.findViewById(R.id.rental_company_address).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_company_phone).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_return).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_pick_up).setVisibility(View.VISIBLE);
              view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.VISIBLE);
              view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.VISIBLE);
              view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_confirmation).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_rewards).setVisibility(View.VISIBLE);
              view.findViewById(R.id.car_type).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_cost).setVisibility(View.VISIBLE);
              view.findViewById(R.id.expand_less_transportation).setVisibility(View.VISIBLE);
              view.findViewById(R.id.trash_transportation).setVisibility(View.VISIBLE);
              view.findViewById(R.id.edit_transportation).setVisibility(View.VISIBLE);
            } else {
              view.findViewById(R.id.calendar_check_in_car).setVisibility(View.VISIBLE);
              view.findViewById(R.id.calendar_check_out_car).setVisibility(View.VISIBLE);
              view.findViewById(R.id.expand_more_transportation).setVisibility(View.VISIBLE);
              view.findViewById(R.id.rental_company_address).setVisibility(View.GONE);
              view.findViewById(R.id.rental_company_phone).setVisibility(View.GONE);
              view.findViewById(R.id.rental_return).setVisibility(View.GONE);
              view.findViewById(R.id.rental_pick_up).setVisibility(View.GONE);
              view.findViewById(R.id.calendar_check_in_car_2).setVisibility(View.GONE);
              view.findViewById(R.id.calendar_check_out_car_2).setVisibility(View.GONE);
              view.findViewById(R.id.name_on_rental_reservation).setVisibility(View.GONE);
              view.findViewById(R.id.rental_confirmation).setVisibility(View.GONE);
              view.findViewById(R.id.rental_rewards).setVisibility(View.GONE);
              view.findViewById(R.id.car_type).setVisibility(View.GONE);
              view.findViewById(R.id.rental_cost).setVisibility(View.GONE);
              view.findViewById(R.id.expand_less_transportation).setVisibility(View.GONE);
              view.findViewById(R.id.trash_transportation).setVisibility(View.GONE);
              view.findViewById(R.id.edit_transportation).setVisibility(View.GONE);
            }
          });
      return view;
    }
  }
}
