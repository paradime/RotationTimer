package com.example.rotationtimer;

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

  private int _value;
  private int _maxValue = 9;
  private int _minValue = 0;

  public void setValue(int v) {
    _value = v;
    up_down_button_value.setText(Integer.toString(_value));
  }

  public int getValue() {
    return _value;
  }

  public UpDownButton(Context context) {
    super(context);
    Inflate(context);
  }

  public UpDownButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    Inflate(context);
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
    if (v == up_button) {
      _value++;
      if (_value > _maxValue)
        _value = _minValue;

    } else if (v == down_button) {
      _value--;
      if (_value < _minValue)
        _value = _maxValue;
    }
    System.out.println(_value + " = newValue");
    setValue(_value);
  }
}