package kr.ac.koreatech.swkang.timertask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView m100msCountTv = null;
    TextView m1000msCountTv = null;

    Timer mTimer;
    TimerTask m100msCountTimerTask = null;
    TimerTask m1000msCountTimerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m100msCountTv = findViewById(R.id.ms_100_countdown_text);
        m1000msCountTv = findViewById(R.id.ms_1000_countdown_text);

        // Timer 객체 생성
        mTimer = new Timer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 타이머는 작업 스레드이기 때문에 액티비티가 종료될 때
        // 반드시 중단하여 스레드를 제거시키도록 한다
        mTimer.cancel();
    }

    // Start countdown 버튼 혹은 Stop countdown 버튼을 눌렀을 때 호출되는 메소드
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start:
            {
                // start 버튼이면 startTimerTask() 함수 호출
                startTimerTask();
                break;
            }
            case R.id.stop:
            {
                // stop 버튼이면 stopTimerTask() 함수 호출
                stopTimerTask();
                break;
            }
        }
    }

    private void startTimerTask() {
        // 1. TimerTask 실행 중이라면 중단한다
        stopTimerTask();

        // 2. 새로운 TimerTask를 생성한다
        // 2-1. 100 밀리초마다 카운팅 되는 태스크를 정의
        m100msCountTimerTask = new TimerTask() {
            int mCount = 0;
            @Override
            public void run() {
                mCount++;
                m100msCountTv.post(new Runnable() {
                    @Override
                    public void run() {
                        m100msCountTv.setText("100ms Count: " + mCount);
                    }
                });
            }
        };

        // 2-2. 1000 밀리초마다 카운팅 되는 태스크를 정의
        m1000msCountTimerTask = new TimerTask() {
            int mCount = 0;
            @Override
            public void run() {
                mCount++;
                m1000msCountTv.post(new Runnable() {
                    @Override
                    public void run() {
                        m1000msCountTv.setText("1000ms Count: " + mCount);
                    }
                });
            }
        };

        // 3. TimerTask를 Timer를 통해 실행시킨다
        // 3-1. 즉시 타이머를 구동하고 100 밀리초 단위로 반복하라
        mTimer.schedule(m100msCountTimerTask, 0, 100);
        // 3-2. 1초 후에 타이머를 구동하고 1000 밀리초 단위로 반복하라
        mTimer.schedule(m1000msCountTimerTask, 1000, 1000);
    }

    private void stopTimerTask() {
        // 1. 모든 태스크를 중단한다
        if(m100msCountTimerTask != null || m1000msCountTimerTask != null) {
            m100msCountTimerTask.cancel();
            m100msCountTimerTask = null;

            m1000msCountTimerTask.cancel();
            m1000msCountTimerTask = null;
        }

        // 2. 카운팅 초기화값을 텍스트뷰에 표시
        m100msCountTv.setText("100ms Count: 0");
        m1000msCountTv.setText("1000ms Count: 0");
    }
}