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

  public void setValue(long v) {
    _value = v;
    up_down_button_value.setText(format.format(_value));
  }

  public long getValue() {
    return _value;
  }

  public UpDownButton(Context context) {
    super(context);
    Inflate(context);
    clickable = true;
  }

  public UpDownButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    Inflate(context);
    clickable = true;
  }

  public void setFormat(SimpleDateFormat form) {
    format = form;
  }

  public void setMax(long max) {
    _maxValue = max;
  }

  public void setIncrement(long inc) {
    increment = inc;
    _maxValue = _maxValue * inc;
    _minValue = _minValue * inc;
  }

  public void setClickable(boolean isClickable) {
    clickable = isClickable;
  }

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