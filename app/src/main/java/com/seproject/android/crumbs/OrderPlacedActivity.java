package com.seproject.android.crumbs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mac-NB on 01/11/16.
 */

public class OrderPlacedActivity extends AppCompatActivity {
    TextView timer;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_placed_activity);
        timer = (TextView) findViewById(R.id.timerText);

        timer.setText("1:00");
        imageView = (ImageView) findViewById(R.id.image_display);

        final CounterClass timerBar = new CounterClass(30000, 1000);
        timerBar.start();

    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            timer.setText("Completed. Your Order is Ready to be Picked up ! Enjoy your food");
            imageView.setImageResource(R.drawable.cooker1);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            timer.setText(hms);
        }
    }
}
