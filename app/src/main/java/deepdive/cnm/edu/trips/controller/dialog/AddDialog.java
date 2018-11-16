package deepdive.cnm.edu.trips.controller.dialog;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import deepdive.cnm.edu.trips.controller.DateTimeFragment;
import deepdive.cnm.edu.trips.controller.DateTimeFragment.Mode;
import java.util.Calendar;

/**
 * The type Add dialog.
 */
public abstract class AddDialog extends DialogFragment {

  /**
   * The Calendar.
   */
  protected Calendar calendar = Calendar.getInstance();

  /**
   * Pick date.
   *
   * @param dateField the date field
   */
  protected void pickDate(final TextInputEditText dateField) {
    DateTimeFragment picker = new DateTimeFragment();
    picker.setMode(Mode.DATE);
    picker.setCalendar(calendar);
    picker.show(getFragmentManager(), picker.getClass().getSimpleName());
    picker.setListener(calendar -> {

      java.text.DateFormat format = DateFormat.getDateFormat(getActivity());
      dateField.setText(format.format(calendar.getTime()));
      AddDialog.this.calendar = calendar;
      // TODO Check for departure after arrival
    });
  }

}
