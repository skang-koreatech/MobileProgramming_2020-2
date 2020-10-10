package kr.ac.koreatech.swkang.activitylifecycledestroy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MobileProgramming", "onCreate()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("MobileProgramming", "onConfigurationChanged()");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MobileProgramming", "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MobileProgramming", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MobileProgramming", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MobileProgramming", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MobileProgramming", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MobileProgramming", "onDestory()");
    }
}