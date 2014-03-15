package com.example.rotationtimer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.os.CountDownTimer;

public class MainActivity extends Activity implements OnClickListener {

  long mMilliseconds = 6000;
  long defaultTime = 0;
  SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  TextView mTextView;
  long s1;
  myCountDownTimer mCountDownTimer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mCountDownTimer = new myCountDownTimer(mMilliseconds, 1000, this);
    mTextView = (TextView) findViewById(R.id.countDownTimer);
    ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
    ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
    ((TextView) findViewById(R.id.countDownTimer)).setOnClickListener(this);
    update();
  }

  public void update() {
    mTextView.setText(mCountDownTimer.getFormat());
  }

  public void newClock(long curTime) {
    mCountDownTimer = new myCountDownTimer(curTime, 1000, this);
    mCountDownTimer.start();
  }

  public void newClock() {
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);
    mCountDownTimer.start();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.start_button:
      mCountDownTimer.startAgain();
      break;
    case R.id.stop_button:
      mCountDownTimer.cancel();
      break;
    }
    case R.id.countDownTimer{
      
    }
  }
}