package com.example.rotationtimer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.os.CountDownTimer;

public class myCountDownTimer extends CountDownTimer {

  SimpleDateFormat mSimpleDateFormat;
  long timeLeft, defaultTime;
  MainActivity myActivity;

  /**
   * sets the timeLeft, the current observer, the format for the clock and the
   * countdown time and interval
   * 
   * @param millisInFuture
   *          how long the timer should last
   * @param countDownInterval
   *          how fast the timer should count down
   * @param curView
   *          the current observer
   */
  public myCountDownTimer(long millisInFuture, long countDownInterval,
      MainActivity curView) {
    super(millisInFuture, countDownInterval);
    timeLeft = millisInFuture;
    myActivity = curView;
    mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  /**
   * UNUSED
   * 
   * @param millisInFuture
   * @param countDownInterval
   */
  public myCountDownTimer(long millisInFuture, long countDownInterval) {
    super(millisInFuture, countDownInterval);
  }

  /**
   * @return the current time left on the clock
   */
  public long getTimeLeft() {
    return timeLeft;
  }

  /**
   * Creates a new clock when done
   */
  @Override
  public void onFinish() {
    myActivity.newClock();

  }

  /**
   * Updates the timeLeft on click as it's not available from CountDownTimer
   */
  @Override
  public void onTick(long millisUntilFinished) {
    timeLeft = millisUntilFinished;
    myActivity.update();

  }

}
