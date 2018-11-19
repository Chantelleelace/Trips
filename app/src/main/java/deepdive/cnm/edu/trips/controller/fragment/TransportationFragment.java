package deepdive.cnm.edu.trips.controller.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.controller.dialog.AddTransportation;
import deepdive.cnm.edu.trips.model.entity.Transportation;
import java.util.List;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class TransportationFragment extends Fragment implements AddCallBack {

  private ListView view;

  /**
   * Instantiates a new Transportation fragment.
   */
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

  private class DeleteTask extends AsyncTask<Transportation, Void, Void> {

    @Override
    protected void onPostExecute(Void aVoid) {
      new TransportationTask().execute();
    }

    @Override
    protected Void doInBackground(Transportation... transportation) {
      TripsDatabase.getInstance(getActivity()).getTransportationDao().delete(transportation[0]);
      return null;
    }
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

  /**
   * Refresh list.
   */
  public void refreshList() { new TransportationTask().execute(); }

  private class TransportationListAdapter extends ArrayAdapter<Transportation> {

    /**
     * Instantiates a new Transportation list adapter.
     *
     * @param context the context
     * @param objects the objects
     */
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
      AppCompatImageButton deleteButton = view.findViewById(R.id.trash_transportation);
      deleteButton.setOnClickListener((v) -> new DeleteTask().execute(transportation));
      AppCompatImageButton editButton = view.findViewById(R.id.edit_transportation);
      editButton.setOnClickListener((v) -> {
        AddTransportation newFragment = new AddTransportation();
        newFragment.setTransportationId(transportation.getId());
        newFragment.setAddCallBack(TransportationFragment.this);
        newFragment.show(getActivity().getSupportFragmentManager(), "add transportation dialog");
      });
      //    puts in EXPAND view
      expandView(view, false);
//    expands card
      view.findViewById(R.id.transportation_card_1)
          .setOnClickListener(v -> {
            if (view.findViewById(R.id.calendar_check_in_car).getVisibility() == View.VISIBLE) {
              expandView(view, true);
            } else {
              expandView(view, false);
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
    view.findViewById(R.id.calendar_check_in_car).setVisibility(visibilityTop);
    view.findViewById(R.id.calendar_check_out_car).setVisibility(visibilityTop);
    view.findViewById(R.id.expand_more_transportation).setVisibility(visibilityTop);
    view.findViewById(R.id.rental_company_address).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_company_phone).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_return).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_pick_up).setVisibility(visibilityBottom);
    view.findViewById(R.id.calendar_check_in_car_2).setVisibility(visibilityBottom);
    view.findViewById(R.id.calendar_check_out_car_2).setVisibility(visibilityBottom);
    view.findViewById(R.id.name_on_rental_reservation).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_confirmation).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_rewards).setVisibility(visibilityBottom);
    view.findViewById(R.id.car_type).setVisibility(visibilityBottom);
    view.findViewById(R.id.rental_cost).setVisibility(visibilityBottom);
    view.findViewById(R.id.expand_less_transportation).setVisibility(visibilityBottom);
    view.findViewById(R.id.trash_transportation).setVisibility(visibilityBottom);
    view.findViewById(R.id.edit_transportation).setVisibility(visibilityBottom);
  }
}
