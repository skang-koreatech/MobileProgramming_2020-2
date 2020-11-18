package kr.ac.koreatech.swkang.asynctaskdownload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mState = null;
    FileDownloadTask mFileDownloadTask = null;

    // AsyncTask의 subclass로 FileDownloadTask 클래스 정의
    // 오버라이드 가능한 5개의 함수가 있음
    // doInBackground 함수는 반드시 구현해야 함
    class FileDownloadTask extends AsyncTask<String, Integer, Boolean> {
        // AsyncTask 작업 시작시 화면 갱신을 위한 메소드
        @Override
        protected void onPreExecute() {
            // 화면에 최초 내려받기 시도를 알리는 텍스트 출력
            mState.setText("File Download ...");
        }

        // 백그라운드에서 작업 스레드에 의해 처리되는 루틴을 정의하는 메소
        @Override
        protected Boolean doInBackground(String... downloadInfos) {
            int totalCount = downloadInfos.length;

            // 전달받은 파일 Url 개수만큼 반복하면서 파일을 내려받는다
            for(int i = 0; i < totalCount; i++) {
                // 1. 파일 내려받기 처리 상태를 화면에 표시하기 위해 호출
                // 두 개의 integer 값을 파라미터로 전달
                publishProgress(i+1, totalCount);

                // 아래 isCancelled()로 취소 여부 확인하는
                // 루틴이 있는 경우와 없는 경우 차이를 확인해보자
                //if(isCancelled() == true) {
                //    return false;
                //}

                // 2. 아래를 파일을 내려받는 과정이라고 가정
                try { Thread.sleep(500); }
                catch (InterruptedException e) {e.printStackTrace(); return false;}
            }
            return true;
        }

        // doInBackground() 함수에서 publishProgress() 함수 호출 시 실행되는 메소드
        @Override
        protected void onProgressUpdate(Integer... downloadInfos) {
            // publishProgress 함수에서 2개의 integer 파라미터를 전달했기 때문에
            // downloadInfos는 2개의 integer를 담고 있는 배열로 접근 가능
            int currentCount = downloadInfos[0];
            int totalCount = downloadInfos[1];

            // 화면에 현재의 파일 내려받기 상태를 표시. 예) Downloading: 3/10
            mState.setText("Downloading : " + currentCount + "/" + totalCount);
        }

        // cancel() 함수 호출 시 실행되는 메소드
        @Override
        protected void onCancelled() {
            // 화면에 내려받기가 취소되었음을 표시
            mState.setText("Download canceled");
        }

        // doInBackground() 함수 실행 종료 시 실행되는 메소드
        @Override
        protected void onPostExecute(Boolean result) {
            // 화면에 내려받기 성공/실패 여부를 텍스트로 출력
            if (true == result) {
                mState.setText("Download finished");
            } else {
                mState.setText("Download failed");
            }
            mFileDownloadTask = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mState = findViewById(R.id.state);
    }

    // 시작(start download) 버튼 눌렀을 때 호출
    public void onClickStart(View v) {
        // 만일 파일 내려받기 AsyncTask 객체가 null이면 객체를 생성하고 실행
        if(mFileDownloadTask == null) {
            // 파일 내려받기 AsyncTask 객체를 생성하고 실행
            mFileDownloadTask = new FileDownloadTask();
            mFileDownloadTask.execute("FileUrl_1", "FileUrl_2", "FileUrl_3", "FileUrl_4", "FileUrl_5",
                    "FileUrl_6", "FileUrl_7", "FileUrl_8", "FileUrl_9", "FileUrl_10");
        }
    }

    // 취소(cancel download) 버튼 눌렀을 때 호출
    public void onClickCancel(View v) {
        // 만일 파일 내려받기 AsyncTask가 종료 상태가 아니면 진행을 취소
        if(mFileDownloadTask != null && mFileDownloadTask.getStatus() != AsyncTask.Status.FINISHED) {
            mFileDownloadTask.cancel(false);
            mFileDownloadTask = null;
        }
    }
}