package kr.ac.koreatech.swkang.localboundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocalService mService;
    boolean mBound = false;

    // ServiceConnection 인터페이스를 구현한 ServiceConnection 객체 생성
    // onServiceConnected() 콜백 메소드와 onServiceDisconnected() 콜백 메소드를 구현해야 함
    private ServiceConnection mConnection = new ServiceConnection() {

        // Service에 연결(bound)되었을 때 호출되는 callback 메소드
        // Service의 onBind() 메소드에서 반환한 IBinder 객체를 받음 (두번째 인자)
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MobileProgramming", "MainActivity - onServiceConnected()");

            // 두번째 인자로 넘어온 IBinder 객체를 LocalService 클래스에 정의된 LocalBinder 클래스 객체로 캐스팅
            LocalService.LocalBinder binder = (LocalService.LocalBinder)service;

            // LocalService 객체를 참조하기 위해 LocalBinder 객체의 getService() 메소드 호출
            mService = binder.getService();

            mBound = true;
        }

        // Service 연결 해제되었을 때 호출되는 callback 메소드
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MobileProgramming", "MainActivity - onServiceDisconnected()");

            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MobileProgramming", "MainActivity - onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("MobileProgramming", "MainActivity - onStart()");

        // 연결할 Service를 위한 Intent 객체 생성
        Intent intent = new Intent(this, LocalService.class);

        // Service에 연결하기 위해 bindService 호출, 생성한 intent 객체와 구현한 ServiceConnection의 객체를 전달
        // boolean bindService(Intent service, ServiceConnection conn, int flags)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MobileProgramming", "MainActivity - onStop()");

        if(mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MobileProgramming", "MainActivity - onDestroy()");
    }

    // 버튼이 클릭되면 호출
    public void onClick(View view) {
        if(mBound) {
            // Service에 정의된 getRandomNumber 메소드 호출
            int num = mService.getRandomNumber();

            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }
}