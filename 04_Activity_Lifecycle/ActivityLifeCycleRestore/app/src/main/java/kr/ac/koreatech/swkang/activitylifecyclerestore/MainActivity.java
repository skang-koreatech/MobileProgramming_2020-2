package kr.ac.koreatech.swkang.activitylifecyclerestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit);
        mEditText.setSaveEnabled(false);

        Log.i("MobileProgramming", "onCreate()");
    }

/*
    // 액티비티 데이터를 백업할 수 있는 메소드
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Mobile Programming", "onSaveInstanceState()");

        // EditText 내용을 onSaveInstanceState의 매개 변수인 Bundle 클래스 객체, outState에 저장

        // 먼저 EditText에 입력된 텍스트를 가지고 와서 String 객체로 변환한다
        String backupString = mEditText.getText().toString();

        // Bundle 객체도 Intent와 비슷하게, key-value 쌍으로 데이터를 저장한다
        outState.putString("Backup_string", backupString);
    }

    // 백업한 데이터를 전달받아 복원할 수 있는 메소드
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("Mobile Programming", "onRestoreInstanceState()");

        // 만약 매개 변수인 Bundle 객체가 null이 아니면,
        // 해당 액티비티에서 백업한 데이터가 존재한다는 것을 의미
        // Bundle 객체에 백업된 데이터를 가지고 와서 EditText 내용을 복원한다
        if(savedInstanceState != null) {
            // Bundle 객체에 데이터를 저장할 때 사용했던 key 값을 가지고 데이터를 얻는다
            String text = savedInstanceState.getString("Backup_string");
            // 해당 데이터를 EditText에 설정한다
            mEditText.setText(text);
        }
    }

 */


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