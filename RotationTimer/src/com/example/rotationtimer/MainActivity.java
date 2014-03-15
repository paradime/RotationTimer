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

  long mMilliseconds = 60000;
  SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  TextView mTextView;
  long s1;

  CountDownTimer mCountDownTimer = new CountDownTimer(mMilliseconds, 1000) {
    @Override
    public void onFinish() {
      mTextView.setText(mSimpleDateFormat.format(0));
    }

    public void onTick(long millisUntilFinished) {
      mTextView.setText(mSimpleDateFormat.format(millisUntilFinished));
      s1 = millisUntilFinished;
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    mTextView = (TextView) findViewById(R.id.countDownTimer);
    ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
    ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);

    mCountDownTimer.start();

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.start_button:
      mCountDownTimer = new CountDownTimer(s1, 1000) {
        @Override
        public void onFinish() {
          mTextView.setText(mSimpleDateFormat.format(0));
        }

        public void onTick(long millisUntilFinished) {
          mTextView.setText(mSimpleDateFormat.format(millisUntilFinished));
          s1 = millisUntilFinished;
        }
      };
      mCountDownTimer.start();
      break;
    case R.id.stop_button:
      mCountDownTimer.cancel();
      break;
    }
  }
}