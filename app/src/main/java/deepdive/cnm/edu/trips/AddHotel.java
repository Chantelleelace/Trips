package deepdive.cnm.edu.trips;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.OnChangeListener;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddHotel extends DialogFragment {

  private TextInputEditText checkInDate;
  private TextInputEditText checkOutDate;
  private Calendar calendar;


  public AddHotel() {
    // Required empty public constructor
  }

  private void pickCheckInDate(final TextInputEditText dateField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.DATE);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
        dateField.setText(format.format(calendar.getTime()));
      }

    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_add_hotel, container, false);
    checkInDate = view.findViewById(R.id.check_in_date);
    checkInDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickCheckInDate(checkInDate);
      }
    });
    checkOutDate = view.findViewById(R.id.check_out_date);
    checkOutDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickCheckInDate(checkOutDate);
      }
    });
    return view;
  }

}
