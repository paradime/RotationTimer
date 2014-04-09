package com.example.rotationtimer;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class UpDownButton extends RelativeLayout implements OnClickListener {
  private ImageButton up_button;
  private ImageButton down_button;
  private TextView up_down_button_value;
  private SimpleDateFormat format;

  private long _value;
  private long _maxValue = 59;
  private long _minValue = 0;
  private long increment;
  private boolean clickable;

  /**
   * sets the clocks value
   * 
   * @param v
   *          a long time value
   */
  public void setValue(long v) {
    _value = v;
    up_down_button_value.setText(format.format(_value));
  }

  /**
   * gets the current value
   * 
   * @return the current value
   */
  public long getValue() {
    return _value;
  }

  /**
   * Creates the button
   * 
   * @param context
   *          the activity where the button should go
   */
  public UpDownButton(Context context) {
    super(context);
    Inflate(context);
    clickable = true;
  }

  /**
   * UNUSED
   * 
   * @param context
   * @param attrs
   */
  public UpDownButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    Inflate(context);
    clickable = true;
  }

  /**
   * Sets the dateTimeFormat for this clock
   * 
   * @param form
   *          a SimpleDateFormat that will be used for this clock
   */
  public void setFormat(SimpleDateFormat form) {
    format = form;
  }

  /**
   * Sets the highest value that this clock can display
   * 
   * @param max
   *          the highest value that the clock should display
   */
  public void setMax(long max) {
    _maxValue = max;
  }

  /**
   * Sets how high the long should increment (Primarily used because time is in
   * base 6 and 3
   * 
   * @param inc
   *          how many X are in a Millisecond
   */
  public void setIncrement(long inc) {
    increment = inc;
    _maxValue = _maxValue * inc;
    _minValue = _minValue * inc;
  }

  /**
   * Changes the clickability of each button
   */
  public void setClickable(boolean isClickable) {
    clickable = isClickable;
  }

  /**
   * Inflates teh buttons on screen
   * 
   * @param context
   *          the context where this button should go
   */
  private void Inflate(Context context) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View inflatedView = inflater.inflate(R.layout.up_down_button, this);
    up_button = (ImageButton) inflatedView.findViewById(R.id.up_down_button_up);
    down_button = (ImageButton) inflatedView
        .findViewById(R.id.up_down_button_down);
    up_down_button_value = (TextView) inflatedView
        .findViewById(R.id.up_down_button_value);
    up_button.setOnClickListener(this);
    down_button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (clickable) {
      if (v == up_button) {
        _value += increment;
        if (_value > _maxValue)
          _value = _minValue;

      } else if (v == down_button) {
        _value -= increment;
        if (_value < _minValue)
          _value = _maxValue;
      }
      setValue(_value);
    }
  }

}