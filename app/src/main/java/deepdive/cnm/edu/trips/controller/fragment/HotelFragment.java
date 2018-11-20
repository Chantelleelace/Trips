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
import deepdive.cnm.edu.trips.controller.GoogleSearchApi;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.controller.dialog.AddHotel;
import deepdive.cnm.edu.trips.model.entity.Hotel;
import java.util.List;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class HotelFragment extends Fragment implements AddCallBack {

  private ListView view;

  /**
   * Instantiates a new Hotel fragment.
   */
  public HotelFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = (ListView) inflater.inflate(R.layout.hotel_fragment, container, false);
    new HotelTask().execute();
    return view;
  }

  @Override
  public void update() {
    new HotelTask().execute();
  }

  private class DeleteTask extends AsyncTask<Hotel, Void, Void> {

    @Override
    protected void onPostExecute(Void aVoid) {
      new HotelTask().execute();
    }

    @Override
    protected Void doInBackground(Hotel... hotel) {
      TripsDatabase.getInstance(getActivity()).getHotelDao().delete(hotel[0]);
      return null;
    }
  }

  private class HotelTask extends AsyncTask<Void, Void, List<Hotel>> {

    @Override
    protected List<Hotel> doInBackground(Void... voids) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      return tripsDatabase.getHotelDao().select();
    }

    @Override
    protected void onPostExecute(List<Hotel> hotels) {
      view.setAdapter(new HotelListAdapter(getActivity(), hotels));
    }

  }

  /**
   * Refresh list.
   */
  public void refreshList() {
    new HotelTask().execute();
  }

  private class HotelListAdapter extends ArrayAdapter<Hotel> {

    /**
     * Instantiates a new Hotel list adapter.
     *
     * @param context the context
     * @param objects the objects
     */
    public HotelListAdapter(
        @NonNull Context context,
        @NonNull List<Hotel> objects) {
      super(context, 0, objects);
    }

    public class GoogleImageTask extends AsyncTask<String, Void, Void> {

      @Override
      protected Void doInBackground(String... strings) {
        Retrofit restAdapter = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .build();
        GoogleSearchApi restApi = restAdapter.create(GoogleSearchApi.class);
        restApi.customSearch(getString(R.string.googleSearchApiKey), "006852542688344643578:bqsiyiqglvw", "pictures");
        return null;
      }
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
        @NonNull ViewGroup parent) {
      final View view = getLayoutInflater().inflate(R.layout.hotel_card_template, parent, false);
      Hotel hotel = getItem(position);
      ((TextView) view.findViewById(R.id.hotel_name)).setText(hotel.getHotelName());
      ((TextView) view.findViewById(R.id.hotel_address)).setText(hotel.getHotelAddress());
      ((TextView) view.findViewById(R.id.hotel_phone)).setText(hotel.getHotelPhone());
      ((TextView) view.findViewById(R.id.hotel_check_in)).setText(hotel.getCheckIn());
      ((TextView) view.findViewById(R.id.hotel_check_out)).setText(hotel.getCheckOut());
      ((TextView) view.findViewById(R.id.check_in_date)).setText(hotel.getCheckIn());
      ((TextView) view.findViewById(R.id.check_out_date)).setText(hotel.getCheckOut());
      ((TextView) view.findViewById(R.id.name_on_reservation)).setText("Name on Reservation: " + hotel.getNameOnResrvation());
      ((TextView) view.findViewById(R.id.hotel_confirmation)).setText("Hotel Confirmation: #" + hotel.getHotelConfirmation());
      ((TextView) view.findViewById(R.id.hotel_rewards)).setText("Hotel Rewards: #" + hotel.getHotelRewards());
      ((TextView) view.findViewById(R.id.room_type)).setText("Room Type: " + hotel.getRoomType());
      ((TextView) view.findViewById(R.id.hotel_cost)).setText("Cost: $" + hotel.getCost() + "/per night");
      // Lets user delete the card using the delete button
      AppCompatImageButton deleteButton = view.findViewById(R.id.trash_hotel);
      deleteButton.setOnClickListener((v) -> new DeleteTask().execute(hotel));
      // Lets user edit the card using the edit button
      AppCompatImageButton editButton = view.findViewById(R.id.edit_hotel);
      editButton.setOnClickListener((v) -> {
        AddHotel newFragment = new AddHotel();
        newFragment.setHotelId(hotel.getId());
        newFragment.setAddCallBack(HotelFragment.this);
        newFragment.show(getActivity().getSupportFragmentManager(), "add hotel dialog");
      });
      //    puts in EXPAND view
      expandView(view, false);
//    expands card
      view.findViewById(R.id.hotel_card_1).setOnClickListener(v -> {
        if (view.findViewById(R.id.check_in_date).getVisibility() == View.VISIBLE) {
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
    view.findViewById(R.id.check_in_date).setVisibility(visibilityTop);
    view.findViewById(R.id.check_out_date).setVisibility(visibilityTop);
    view.findViewById(R.id.check_in_dash).setVisibility(visibilityTop);
    view.findViewById(R.id.expand_more_hotel).setVisibility(visibilityTop);
    view.findViewById(R.id.hotel_address).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_phone).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_check_in).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_check_out).setVisibility(visibilityBottom);
    view.findViewById(R.id.check_in_date_2).setVisibility(visibilityBottom);
    view.findViewById(R.id.check_out_date_2).setVisibility(visibilityBottom);
    view.findViewById(R.id.name_on_reservation).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_confirmation).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_rewards).setVisibility(visibilityBottom);
    view.findViewById(R.id.room_type).setVisibility(visibilityBottom);
    view.findViewById(R.id.hotel_cost).setVisibility(visibilityBottom);
    view.findViewById(R.id.expand_less_hotel).setVisibility(visibilityBottom);
    view.findViewById(R.id.trash_hotel).setVisibility(visibilityBottom);
    view.findViewById(R.id.edit_hotel).setVisibility(visibilityBottom);
  }
}
