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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.dialog.AddFlight;
import deepdive.cnm.edu.trips.model.dialog.AddHotel;
import deepdive.cnm.edu.trips.model.entity.Hotel;
import java.util.List;


/**
 * A simple {@link Fragment} subclass that holds cards per user submitted data.
 */
public class HotelFragment extends Fragment implements AddCallBack {

  private ListView view;

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

  // TODO Create a clickListener for trashcan to delete cards

  @Override
  public void update() {
    new HotelTask().execute();
    // TODO
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

  public void refreshList() {
    new HotelTask().execute();
  }

  private class HotelListAdapter extends ArrayAdapter<Hotel> {

    public HotelListAdapter(
        @NonNull Context context,
        @NonNull List<Hotel> objects) {
      super(context, 0, objects);
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
      ((TextView) view.findViewById(R.id.name_on_reservation)).setText(hotel.getNameOnResrvation());
      ((TextView) view.findViewById(R.id.hotel_confirmation)).setText(hotel.getHotelConfirmation());
      ((TextView) view.findViewById(R.id.hotel_rewards)).setText(hotel.getHotelRewards());
      ((TextView) view.findViewById(R.id.room_type)).setText(hotel.getRoomType());
      ((TextView) view.findViewById(R.id.hotel_cost)).setText(hotel.getCost());
      Button editButton = view.findViewById(R.id.edit_hotel);
      editButton.setOnClickListener((v) -> {
        // TODO ADD code to display AddFlight dialog fragment
        AddHotel newFragment = new AddHotel();
        newFragment.setHotelId(hotel.getId());
        newFragment.setAddCallBack(HotelFragment.this);
        newFragment.show(getActivity().getSupportFragmentManager(), "add hotel dialog");
      });
      //    puts in EXPAND view
      view.findViewById(R.id.calendar_check_in).setVisibility(View.VISIBLE);
      view.findViewById(R.id.calendar_check_out).setVisibility(View.VISIBLE);
      view.findViewById(R.id.expand_more_hotel).setVisibility(View.VISIBLE);
      view.findViewById(R.id.hotel_address).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_phone).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_check_in).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_check_out).setVisibility(View.GONE);
      view.findViewById(R.id.calendar_check_in_2).setVisibility(View.GONE);
      view.findViewById(R.id.calendar_check_out_2).setVisibility(View.GONE);
      view.findViewById(R.id.name_on_reservation).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_confirmation).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_rewards).setVisibility(View.GONE);
      view.findViewById(R.id.room_type).setVisibility(View.GONE);
      view.findViewById(R.id.hotel_cost).setVisibility(View.GONE);
      view.findViewById(R.id.expand_less_hotel).setVisibility(View.GONE);
      view.findViewById(R.id.trash_hotel).setVisibility(View.GONE);
      view.findViewById(R.id.edit_hotel).setVisibility(View.GONE);
//    expands card
      view.findViewById(R.id.hotel_card_1).setOnClickListener(v -> {
        if (view.findViewById(R.id.calendar_check_in).getVisibility() == View.VISIBLE) {
          view.findViewById(R.id.calendar_check_in).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_out).setVisibility(View.GONE);
          view.findViewById(R.id.expand_more_hotel).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_address).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_phone).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_check_in).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_check_out).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_in_2).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_out_2).setVisibility(View.VISIBLE);
          view.findViewById(R.id.name_on_reservation).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_confirmation).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_rewards).setVisibility(View.VISIBLE);
          view.findViewById(R.id.room_type).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_cost).setVisibility(View.VISIBLE);
          view.findViewById(R.id.expand_less_hotel).setVisibility(View.VISIBLE);
          view.findViewById(R.id.trash_hotel).setVisibility(View.VISIBLE);
          view.findViewById(R.id.edit_hotel).setVisibility(View.VISIBLE);
        } else {
          view.findViewById(R.id.calendar_check_in).setVisibility(View.VISIBLE);
          view.findViewById(R.id.calendar_check_out).setVisibility(View.VISIBLE);
          view.findViewById(R.id.expand_more_hotel).setVisibility(View.VISIBLE);
          view.findViewById(R.id.hotel_address).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_phone).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_check_in).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_check_out).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_in_2).setVisibility(View.GONE);
          view.findViewById(R.id.calendar_check_out_2).setVisibility(View.GONE);
          view.findViewById(R.id.name_on_reservation).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_confirmation).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_rewards).setVisibility(View.GONE);
          view.findViewById(R.id.room_type).setVisibility(View.GONE);
          view.findViewById(R.id.hotel_cost).setVisibility(View.GONE);
          view.findViewById(R.id.expand_less_hotel).setVisibility(View.GONE);
          view.findViewById(R.id.trash_hotel).setVisibility(View.GONE);
          view.findViewById(R.id.edit_hotel).setVisibility(View.GONE);
        }
      });
      return view;
    }
  }
}
