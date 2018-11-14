package deepdive.cnm.edu.trips.controller.dialog;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import deepdive.cnm.edu.trips.MainActivity.AddCallBack;
import deepdive.cnm.edu.trips.R;
import deepdive.cnm.edu.trips.model.db.TripsDatabase;
import deepdive.cnm.edu.trips.model.entity.Transportation;


/**
 * A simple {@link Fragment} subclass that lets the user add their own hotel information to create
 * new cards.
 */
public class AddTransportation extends AddDialog {

  private TextInputEditText pickUpDate;
  private TextInputEditText returnDate;
  private AddCallBack addCallBack;
  private long transportationId;
  private Transportation transportation;
  private View view;

  public void setTransportationId(long transportationId) {
    this.transportationId = transportationId;
  }

  public AddTransportation() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflates the layout for this fragment
    // Inflates the calendar
    view = inflater.inflate(R.layout.transportation_add, container, false);
    pickUpDate = view.findViewById(R.id.pick_up_date_input);
    pickUpDate.setOnClickListener(v -> pickDate(pickUpDate));
    returnDate = view.findViewById(R.id.return_date_input);
    returnDate.setOnClickListener(v -> pickDate(returnDate));
    // saves all input to database.
    view.findViewById(R.id.submit_transportation).setOnClickListener(
        v -> {
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_company_input)).getText() != null)) {
            transportation.setRentalCompanyName(((TextInputEditText)
                view.findViewById(R.id.rental_company_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_address_input)).getText() != null)) {
            transportation.setRentalCompanyAddress(((TextInputEditText)
                view.findViewById(R.id.rental_address_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_phone_number_input)).getText() != null)) {
            transportation.setRentalCompanyPhone(((TextInputEditText)
                view.findViewById(R.id.rental_phone_number_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.return_date_input)).getText() != null)) {
            transportation.setRentalReturn(((TextInputEditText)
                view.findViewById(R.id.return_date_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.pick_up_date_input)).getText() != null)) {
            transportation.setRentalPickUp(((TextInputEditText)
                view.findViewById(R.id.pick_up_date_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_car_type_input)).getText() != null)) {
            transportation.setCarType(((TextInputEditText)
                view.findViewById(R.id.rental_car_type_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_cost_input)).getText() != null)) {
            transportation.setRentalCost(((TextInputEditText)
                view.findViewById(R.id.rental_cost_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_name_on_reservation_input)).getText() != null)) {
            transportation.setNameOnRentalReservation(((TextInputEditText)
                view.findViewById(R.id.rental_name_on_reservation_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_confirmation_input)).getText() != null)) {
            transportation.setRentalConfirmation(((TextInputEditText)
                view.findViewById(R.id.rental_confirmation_input)).getText().toString());
          }
          if ((((TextInputEditText)
              view.findViewById(R.id.rental_rewards_input)).getText() != null)) {
            transportation.setRentalRewards(((TextInputEditText)
                view.findViewById(R.id.rental_rewards_input)).getText().toString());
          }
          dismiss();
          new TransportationTask().execute(transportation);
        });
    if (transportationId != 0) {
      new QueryTask().execute(transportationId);
    } else {
      transportation = new Transportation();
    }
    return view;
  }


  /**
   * This add (Transportation) call back add a new card to my Transportation fragment from my add
   * transportation fragment.
   *
   * @param addCallBack the add call back
   */
  public void setAddCallBack(AddCallBack addCallBack) {
    this.addCallBack = addCallBack;
  }

  private class TransportationTask extends AsyncTask<Transportation, Void, Void> {

    @Override
    protected Void doInBackground(Transportation... transportation) {
      TripsDatabase tripsDatabase = TripsDatabase.getInstance(getActivity());
      if (transportation[0].getId() != 0) {
        tripsDatabase.getTransportationDao().update(transportation[0]);
      } else {
        tripsDatabase.getTransportationDao().insert(transportation[0]);
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      addCallBack.update();
    }
  }

  private class QueryTask extends AsyncTask<Long, Void, Transportation> {

    @Override
    protected void onPostExecute(Transportation transportation) {
      AddTransportation.this.transportation = transportation;
      // Sets text from database into fields for user to edit
      ((TextInputEditText) view.findViewById(R.id.rental_company_input))
          .setText(transportation.getRentalCompanyName());
      ((TextInputEditText) view.findViewById(R.id.rental_address_input))
          .setText(transportation.getRentalCompanyAddress());
      ((TextInputEditText) view.findViewById(R.id.rental_phone_number_input))
          .setText(transportation.getRentalCompanyPhone());
      ((TextInputEditText) view.findViewById(R.id.return_date_input))
          .setText(transportation.getRentalReturn());
      ((TextInputEditText) view.findViewById(R.id.pick_up_date_input))
          .setText(transportation.getRentalPickUp());
      ((TextInputEditText) view.findViewById(R.id.rental_car_type_input))
          .setText(transportation.getCarType());
      ((TextInputEditText) view.findViewById(R.id.rental_cost_input))
          .setText(transportation.getRentalCost());
      ((TextInputEditText) view.findViewById(R.id.rental_name_on_reservation_input))
          .setText(transportation.getNameOnRentalReservation());
      ((TextInputEditText) view.findViewById(R.id.rental_confirmation_input))
          .setText(transportation.getRentalConfirmation());
      ((TextInputEditText) view.findViewById(R.id.rental_rewards_input))
          .setText(transportation.getRentalRewards());
    }

    @Override
    protected Transportation doInBackground(Long... transportationIds) {
      return TripsDatabase.getInstance(getContext()).getTransportationDao()
          .selectOne(transportationIds[0]);
    }
  }
}

