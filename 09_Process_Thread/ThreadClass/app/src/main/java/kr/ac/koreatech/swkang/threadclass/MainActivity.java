package kr.ac.koreatech.swkang.threadclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountThread w;
    boolean running = true;
    int mCount = 0;
    TextView tv;

    // 작업 스레드로 사용할 Thread 클래스의 subclass 정의
    class CountThread extends Thread {
        public void run() {
            int i = 0;
            for(i=0; i < 20 && running; i++) {
                // count 값 1 증가
                mCount++;
                //tv.setText("Count: " + mCount);
                try {
                    // thread 1초간 sleep
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Mobile Programming", "Thread: time=" + mCount);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textv);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 작업 스레드 객체 생성
        w = new CountThread();

        // 작업 스레드 시작
        w.start();

        running = true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        running = false;
    }

    public void onClick(View vieW) {
        // TextView의 count 값 갱신
        tv.setText("Count: " + mCount);
    }
}