package com.example.rotationtimer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.os.CountDownTimer;

public class MainActivity extends Activity implements OnClickListener {

  long mMilliseconds;
  long defaultTime;

  public static final long MILLISECONDS_IN_HOUR = 3600000;
  public static final long MILLISECONDS_IN_MINUTE = 60000;
  public static final long MILLISECONDS_IN_SECOND = 1000;

  private static final int NEW_CLOCK = 0;
  private static final int PAUSED = 1;
  private static final int TICKING = 2;

  SimpleDateFormat secondFormat;
  SimpleDateFormat minuteFormat;
  SimpleDateFormat hourFormat;
  UpDownButton hourLayout, secondLayout, minuteLayout;
  long s1;
  myCountDownTimer mCountDownTimer;
  Button startButton, stopButton, resetButton;

  private int state;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    defaultTime = 0;
    state = NEW_CLOCK;

    hourFormat = new SimpleDateFormat("HH");
    hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    minuteFormat = new SimpleDateFormat("mm");
    minuteFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    secondFormat = new SimpleDateFormat("ss");
    secondFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);

    hourLayout = (UpDownButton) findViewById(R.id.countDownHours);
    hourLayout.setFormat(hourFormat);
    hourLayout.setIncrement(MILLISECONDS_IN_HOUR);
    minuteLayout = (UpDownButton) findViewById(R.id.countDownMinutes);
    minuteLayout.setFormat(minuteFormat);
    minuteLayout.setIncrement(MILLISECONDS_IN_MINUTE);
    secondLayout = (UpDownButton) findViewById(R.id.countDownSeconds);
    secondLayout.setFormat(secondFormat);
    secondLayout.setIncrement(MILLISECONDS_IN_SECOND);

    startButton = (Button) findViewById(R.id.start_button);
    startButton.setOnClickListener(this);
    stopButton = (Button) findViewById(R.id.stop_button);
    stopButton.setOnClickListener(this);
    stopButton.setVisibility(View.GONE);
    resetButton = (Button) findViewById(R.id.reset_button);
    resetButton.setOnClickListener(this);
    resetButton.setVisibility(View.GONE);
    update();
  }

  public void update() {
    long timeLeft = mCountDownTimer.getTimeLeft();
    hourLayout.setValue(timeLeft);
    minuteLayout.setValue(timeLeft);
    secondLayout.setValue(timeLeft);
  }

  public void newClock(long curTime) {
    mCountDownTimer = new myCountDownTimer(curTime, 1000, this);
    mCountDownTimer.start();
  }

  public void newClock() {
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);
    mCountDownTimer.start();
    MediaPlayer mp = MediaPlayer.create(this, R.raw.mushroom_sound);
    mp.start();
  }

  private long getTime() {
    long curTime = 0;
    curTime += hourLayout.getValue();
    curTime += minuteLayout.getValue();
    curTime += secondLayout.getValue();
    return curTime;
  }

  private void reset() {
    defaultTime = 0;
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);
    state = NEW_CLOCK;
    update();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.start_button:
      if (state == NEW_CLOCK) {
        state = TICKING;
        defaultTime = getTime();
        newClock(defaultTime);
      } else {
        newClock(getTime());
      }
      break;
    case R.id.stop_button:
      mCountDownTimer.cancel();
      break;
    case R.id.reset_button:
      reset();
    }
  }
}