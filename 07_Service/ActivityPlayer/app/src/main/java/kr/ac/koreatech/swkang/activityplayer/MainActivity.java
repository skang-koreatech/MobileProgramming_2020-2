package kr.ac.koreatech.swkang.activityplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MobileProgramming", "onCreate()");

        setContentView(R.layout.activity_main);

        // MediaPlayer 객체 생성
        mediaPlayer = MediaPlayer.create(this, R.raw.billyjoel_pianoman);
        //****************************

    }

    // 시작 버튼을 누르면 호출되는 onClickStart 메소드 구현
    // 메소드 내부에서는 MediaPlayer 객체를 이용하여 재생 시작
    public void onClickStart(View view) {
        mediaPlayer.start();
    }
    //********************************

    // pause 버튼을 누르면 호출되는 onClickPause 메소드 구현
    // 메소드 내부에서는 MediaPlayer 객체를 이용하여 일시 정지
    public void onClickPause(View view) {
        mediaPlayer.pause();
    }
    //********************************

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

        // MediaPlayer 객체 사용 해제
        //mediaPlayer.release();
        //mediaPlayer = null;
        //****************************
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MobileProgramming", "onDestory()");
    }

}