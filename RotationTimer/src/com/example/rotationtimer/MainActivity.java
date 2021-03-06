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

    /* Setting up Date Formats */
    hourFormat = new SimpleDateFormat("HH");
    hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    minuteFormat = new SimpleDateFormat("mm");
    minuteFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    secondFormat = new SimpleDateFormat("ss");
    secondFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);

    /* Wiring up UpDownBUttons with their respective views and setting up values */
    hourLayout = (UpDownButton) findViewById(R.id.countDownHours);
    hourLayout.setFormat(hourFormat);
    hourLayout.setMax(23);
    hourLayout.setIncrement(MILLISECONDS_IN_HOUR);
    minuteLayout = (UpDownButton) findViewById(R.id.countDownMinutes);
    minuteLayout.setFormat(minuteFormat);
    minuteLayout.setIncrement(MILLISECONDS_IN_MINUTE);
    secondLayout = (UpDownButton) findViewById(R.id.countDownSeconds);
    secondLayout.setFormat(secondFormat);
    secondLayout.setIncrement(MILLISECONDS_IN_SECOND);

    /* Setting up initial values for state buttons */
    startButton = (Button) findViewById(R.id.start_button);
    startButton.setOnClickListener(this);
    stopButton = (Button) findViewById(R.id.stop_button);
    stopButton.setOnClickListener(this);
    stopButton.setVisibility(View.GONE);
    resetButton = (Button) findViewById(R.id.reset_button);
    resetButton.setOnClickListener(this);
    resetButton.setVisibility(View.GONE);
    update(); // Ensures the view is up to date
  }

  /**
   * Updates the view based on our model countdown timer
   */
  public void update() {
    long timeLeft = mCountDownTimer.getTimeLeft();
    hourLayout.setValue(timeLeft);
    minuteLayout.setValue(timeLeft);
    secondLayout.setValue(timeLeft);
    hideButtons();
    setClickableClock();
    switch (state) { // Ensuring button visibility on state
    case NEW_CLOCK:
      startButton.setVisibility(View.VISIBLE);
      break;
    case TICKING:
      stopButton.setVisibility(View.VISIBLE);
      break;
    case PAUSED:
      startButton.setVisibility(View.VISIBLE);
      resetButton.setVisibility(View.VISIBLE);
    }
  }

  /**
   * Creates a new clock and starts it
   * 
   * @param curTime
   *          The time that you want the clock to start with
   */
  public void newClock(long curTime) {
    mCountDownTimer = new myCountDownTimer(curTime, 1000, this);
    mCountDownTimer.start();
  }

  /**
   * Creates a new clock with the default time and starts it Then plays a sound
   * to show that a new rotation has begun
   */
  public void newClock() {
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);
    mCountDownTimer.start();
    MediaPlayer mp = MediaPlayer.create(this, R.raw.mushroom_sound);
    mp.start();
  }

  /**
   * Gets the current time displayed on UpDownButtons
   * 
   * @return the long value of what each button corresponds to
   */
  private long getTime() {
    long curTime = 0;
    curTime += hourLayout.getValue();
    curTime += minuteLayout.getValue();
    curTime += secondLayout.getValue();
    return curTime;
  }

  /**
   * Command for resetButton Sets the default time to 0, creates a new clock
   * with this default time then resets the state
   */
  private void reset() {
    defaultTime = 0;
    mCountDownTimer = new myCountDownTimer(defaultTime, 1000, this);
    state = NEW_CLOCK;
    update();
  }

  /**
   * helper function for update just hides state buttons
   */
  private void hideButtons() {
    startButton.setVisibility(View.GONE);
    stopButton.setVisibility(View.GONE);
    resetButton.setVisibility(View.GONE);
  }

  /**
   * Command for Stop stops the clock and changes the state
   */
  private void stop() {
    mCountDownTimer.cancel();
    state = PAUSED;
    update();
  }

  /**
   * Helper function for update depending on the state, it will allow buttons to
   * be clickable on the clock
   */
  private void setClickableClock() {
    if (state == NEW_CLOCK) {
      hourLayout.setClickable(true);
      minuteLayout.setClickable(true);
      secondLayout.setClickable(true);
    } else {
      hourLayout.setClickable(false);
      minuteLayout.setClickable(false);
      secondLayout.setClickable(false);
    }
  }

  /**
   * onClick listener for this class
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.start_button:
      if (state == NEW_CLOCK) {
        defaultTime = getTime();
        if (defaultTime != 0) {
          state = TICKING;
          newClock(defaultTime);
        }
      } else {
        state = TICKING;
        newClock(mCountDownTimer.getTimeLeft());
      }
      break;
    case R.id.stop_button:
      stop();
      break;
    case R.id.reset_button:
      reset();
    }
  }
}