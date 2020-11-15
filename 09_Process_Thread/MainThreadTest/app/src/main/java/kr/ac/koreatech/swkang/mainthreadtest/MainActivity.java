package kr.ac.koreatech.swkang.mainthreadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    int mCount = 0;
    TextView mCountTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("MobileProgramming", "onCreate()");
        setContentView(R.layout.activity_main);
        mCountTextView = findViewById(R.id.count);

        // 4초 동안 1초에 1씩 카운트한다
        for(int i = 0; i < 4; i++) {
            //for(int i = 0; i < 20; i++) {
            mCount++;

            Log.i("MobileProgramming", "Current count: " + mCount);

            mCountTextView.setText("Count: " + mCount);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}