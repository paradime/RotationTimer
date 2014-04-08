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
  long defaultTime = 0;

  public static final long MILLISECONDS_IN_HOUR = 3600000;

  SimpleDateFormat secondFormat;
  SimpleDateFormat minuteFormat;
  SimpleDateFormat hourFormat;
  TextView hourView, minuteView, secondView;
  UpDownButton hourLayout;
  long s1;
  myCountDownTimer mCountDownTimer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMilliseconds = 60000 * 15;

    hourFormat = new SimpleDateFormat("HH");
    hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    minuteFormat = new SimpleDateFormat("mm");
    minuteFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    secondFormat = new SimpleDateFormat("ss");
    secondFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    mCountDownTimer = new myCountDownTimer(mMilliseconds, 1000, this);

    hourLayout = (UpDownButton) findViewById(R.id.countDownHours);
    minuteView = (TextView) findViewById(R.id.countDownMinutes);
    secondView = (TextView) findViewById(R.id.countDownSeconds);
    ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
    ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
    ((TextView) findViewById(R.id.countDownMinutes)).setOnClickListener(this);
    ((TextView) findViewById(R.id.countDownSeconds)).setOnClickListener(this);
    update();
  }

  public void update() {
    long timeLeft = mCountDownTimer.getTimeLeft();
    hourLayout.setValue(Integer.parseInt(hourFormat.format(timeLeft)));
    minuteView.setText(minuteFormat.format(timeLeft));
    secondView.setText(secondFormat.format(timeLeft));
  }

  public void newClock(long curTime) {
    mCountDownTimer = new myCountDownTimer(curTime, 1000, this);
    mCountDownTimer.start();
  }

  public void newClock() {
    mCountDownTimer = new myCountDownTimer(mMilliseconds, 1000, this);
    mCountDownTimer.start();
    MediaPlayer mp = MediaPlayer.create(this, R.raw.mushroom_sound);
    mp.start();
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
    case R.id.countDownMinutes:
      System.out.println("Minutes Pressed");
      break;
    case R.id.countDownSeconds:
      System.out.println("Seconds Pressed");
      break;
    }
  }
}