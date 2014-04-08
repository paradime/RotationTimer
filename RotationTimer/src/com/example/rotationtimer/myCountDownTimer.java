package com.example.rotationtimer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.os.CountDownTimer;

public class myCountDownTimer extends CountDownTimer {

  SimpleDateFormat mSimpleDateFormat;
  long timeLeft, defaultTime;
  MainActivity myActivity;

  public myCountDownTimer(long millisInFuture, long countDownInterval,
      MainActivity curView) {
    super(millisInFuture, countDownInterval);
    timeLeft = millisInFuture;
    myActivity = curView;
    mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  public myCountDownTimer(long millisInFuture, long countDownInterval) {
    super(millisInFuture, countDownInterval);
  }

  public long getTimeLeft() {
    return timeLeft;
  }

  public String getFormat() {
    return mSimpleDateFormat.format(timeLeft);
  }

  public String getSeconds() {
    return String.valueOf((timeLeft / 1000) % 60);
  }

  public String getMinutes() {
    return String.valueOf((timeLeft / 60000) % 60);
  }

  public String getHours() {
    return String.valueOf(timeLeft / 3600000);
  }

  @Override
  public void onFinish() {
    myActivity.newClock();

  }

  public void startAgain() {
    myActivity.newClock(timeLeft);
  }

  @Override
  public void onTick(long millisUntilFinished) {
    timeLeft = millisUntilFinished;
    myActivity.update();

  }

}
