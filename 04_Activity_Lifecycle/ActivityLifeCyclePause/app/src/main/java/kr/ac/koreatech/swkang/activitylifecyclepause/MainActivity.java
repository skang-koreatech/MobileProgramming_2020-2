package kr.ac.koreatech.swkang.activitylifecyclepause;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MobileProgramming", "onCreate()");
    }

    public void onClick(View v) {
        startActivity(new Intent(this, SmallActivity.class));
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