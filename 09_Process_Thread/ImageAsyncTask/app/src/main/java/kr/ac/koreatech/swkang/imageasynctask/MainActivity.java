package kr.ac.koreatech.swkang.imageasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // download 버튼을 클릭하면 호출되는 메소드
    public void onClick(View view) {
        EditText url = findViewById(R.id.et_url);

        // AsyncTask의 subclass로 정의한 DownloadTask 객체 생성
        DownloadTask task = new DownloadTask();

        // DownloadTask 객체 실행
        // execute()의 매개변수로 EditText에 입력되어 있는 http url을 String 값을 넘겨줌
        task.execute(url.getText().toString());
    }

    // AsyncTask의 subclass로 DownloadTask 클래스 정의
    // 오버라이드 가능한 5개의 함수가 있음
    // doInBackground 함수는 반드시 구현해야 함
    class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... url) {
            try {
                // 해당 URL의 이미지 파일을 Bitmap 객체로 받아오기 위한 함수 호출
                bitmap = downloadUrl(url[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(result);
            Toast.makeText(getApplicationContext(), "Image downloaded successfully", Toast.LENGTH_LONG).show();
        }
    }

    // 인터넷 주소 URL을 String 객체로 받아서
    // Http Connection을 열고 이미지 파일을 받아서 비트맵으로 만드는 함수
    private Bitmap downloadUrl(String strUrl) throws IOException {
        Bitmap bitmap = null;
        InputStream iStream = null;

        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        try {
            iStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(iStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return bitmap;
    }
}