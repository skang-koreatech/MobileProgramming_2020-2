package kr.ac.koreatech.swkang.asynctaskprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = findViewById(R.id.progressbar);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // AsyncTask 객체 생성 및 AsyncTask 작업을 시작하기 위한 execute() 함수 호출
                new CounterTask().execute();
            }
        });
    }

    // AsyncTask의 subclass로 CounterTask 클래스 정의
    // 오버라이드 가능한 5개의 함수가 있음
    // doInBackground 함수는 반드시 구현해야 함
    class CounterTask extends AsyncTask<Void, Integer, Integer> {

        // AsyncTask 작업 시작시 화면 갱신을 위한 메소드
        // 처리할 작업이 없으면 구현 하지 않아도 무방
        protected void onPreExecute() {

        }

        // 백그라운드에서 작업 스레드에 의해 처리되는 코드 루틴을 정의하는 메소드
        protected Integer doInBackground(Void... params) {
            while(mProgressStatus < 100) {
                try {
                    // 백그라운드 작업에 시간이 소요되는 과정을 가정하기 위해서 일정 시간 sleep
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // progressbar에 표시할 상태 정보 업데이트
                mProgressStatus++;

                // doInBackground 함수 처리 도중 화면 갱신 작업을 위해
                // publishProgress 함수 호출
                publishProgress(mProgressStatus);
            }
            return mProgressStatus;
        }

        // AsyncTask 작업 중간 상태 업데이트를 위한 메소드
        protected void onProgressUpdate(Integer... value) {
            // 화면 갱신 작업 수행
            // 여기서는 ProgressBar 객체의 setProgress 함수 호출
            mProgress.setProgress(value[0]);
        }

        // AsyncTask 작업 완료 후 실행 메소드
        protected void onPostExecute(Integer result) {
            // AsyncTask 작업 완료 후 필요한 루틴 처리
            mProgress.setProgress(result);
            mProgressStatus = 0;
        }
    }
}