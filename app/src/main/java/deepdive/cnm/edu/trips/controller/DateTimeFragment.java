package deepdive.cnm.edu.trips.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * The type Date time fragment.
 */
public class DateTimeFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

  private Mode mode = Mode.DATE;
  private Calendar calendar = null;
  private OnChangeListener listener = null;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Dialog dialog = null;
    Calendar defaultValue = (calendar != null) ? calendar : Calendar.getInstance();
    if (mode == Mode.DATE) {
      // date picking
      dialog = new DatePickerDialog(getActivity(), this,
          defaultValue.get(Calendar.YEAR),
          defaultValue.get(Calendar.MONTH),
          defaultValue.get(Calendar.DAY_OF_MONTH));
    } else {
      // time picking
      dialog = new TimePickerDialog(getActivity(), this,
          defaultValue.get(Calendar.HOUR_OF_DAY),
          defaultValue.get(Calendar.MINUTE),
          DateFormat.is24HourFormat(getActivity()));
    }
    return dialog;
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    Calendar updateValue = Calendar.getInstance();
    updateValue.set(Calendar.YEAR, year);
    updateValue.set(Calendar.MONTH, month);
    updateValue.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    if (listener != null) {
      listener.onChange(updateValue);
    }
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    Calendar updateValue = Calendar.getInstance();
    updateValue.set(Calendar.HOUR_OF_DAY, hourOfDay);
    updateValue.set(Calendar.MINUTE, minute);
    if (listener != null) {
      listener.onChange(updateValue);
    }
  }

  /**
   * Gets mode.
   *
   * @return the mode
   */
  public Mode getMode() {
    return mode;
  }

  /**
   * Sets mode.
   *
   * @param mode the mode
   */
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  /**
   * Gets calendar.
   *
   * @return the calendar
   */
  public Calendar getCalendar() {
    return calendar;
  }

  /**
   * Sets calendar.
   *
   * @param calendar the calendar
   */
  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  /**
   * Gets listener.
   *
   * @return the listener
   */
  public OnChangeListener getListener() {
    return listener;
  }

  /**
   * Sets listener.
   *
   * @param listener the listener
   */
  public void setListener(
      OnChangeListener listener) {
    this.listener = listener;
  }

  /**
   * The enum Mode.
   */
  public enum Mode {
    /**
     * Date mode.
     */
    DATE,
    /**
     * Time mode.
     */
    TIME
  }

  /**
   * The interface On change listener.
   */
  public interface OnChangeListener {

    /**
     * On change.
     *
     * @param calendar the calendar
     */
    void onChange(Calendar calendar);
  }

}
