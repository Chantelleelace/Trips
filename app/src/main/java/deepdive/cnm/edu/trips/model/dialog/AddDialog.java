package deepdive.cnm.edu.trips.model.dialog;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.OnChangeListener;
import java.util.Calendar;

public abstract class AddDialog extends DialogFragment {

  protected Calendar calendar;

  protected void pickDate(final TextInputEditText dateField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.DATE);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(new OnChangeListener() {
      /**
       * This sets the date from my date picker to my TextInputEditText.
       * @param calendar
       */
      @Override
      public void onChange(Calendar calendar) {
        java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
        dateField.setText(format.format(calendar.getTime()));
      }

    });
  }

}
