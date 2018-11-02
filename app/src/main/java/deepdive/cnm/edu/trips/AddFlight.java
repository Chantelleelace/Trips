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
public class AddFlight extends DialogFragment {

  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String CALENDAR_KEY = "calendar";

  private Calendar calendar;
  private TextInputEditText date;
  private TextInputEditText date1;
  private TextInputEditText time;
  private TextInputEditText time1;

  public AddFlight() {
    // Required empty public constructor
  }

  private void pickTime(final TextInputEditText timeField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.TIME);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getTimeFormat(getActivity());
        timeField.setText(format.format(calendar.getTime()));
      }

    });
  }

  private void pickDate(final TextInputEditText dateField) {
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
    View view = inflater.inflate(R.layout.fragment_add_flight, container, false);
    date = view.findViewById(R.id.date_input);
    date.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickDate(date);
      }
    });
    date1 = view.findViewById(R.id.date_input_2);
    date1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickDate(date1);
      }
    });
    time = view.findViewById(R.id.time_input);
    time.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickTime(time);
      }
    });
    time1 = view.findViewById(R.id.time_input_2);
    time1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        pickTime(time1);
      }
    });


    return view;
    }

  }
