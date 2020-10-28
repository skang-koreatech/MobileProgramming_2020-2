package kr.ac.koreatech.swkang.localboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class LocalService extends Service {

    // Binder 클래스를 상속 받는 클래스를 정의
    // getService() 메소드에서 현재 서비스 객체를 반환
    public class LocalBinder extends Binder {
        // 클라이언트가 호출할 수 있는 공개 메소드. 현재 Service 객체 반환
        LocalService getService() {
            return LocalService.this;
        }
    }

    // 위에서 정의한 Binder 클래스의 객체 생성
    // Binder 클래스는 Interface인 IBinder를 구현한 클래스
    private final IBinder mBinder = new LocalBinder();

    // Service 연결이 되었을 때 호출되는 메소드
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MobileProgramming", "LocalService - onBind()");

        // 위에서 생성한 LocalBinder 객체를 반환
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MobileProgramming", "LocalService - onUnbind()");

        return false;
    }

    private final Random mGenerator = new Random();

    // 클라이언트가 호출하게 될 메소드
    public int getRandomNumber() {
        return mGenerator.nextInt(100); // [0, 100) 사이의 난수
    }

    //*****************************
    // 바인딩된 서비스는 모든 연결이 해제되면 Android 시스템이 서비스를 자동으로 소멸시킴
    // 따라서 서비스가 순전히 바인딩된 서비스인 경우 해당 서비스의 생명주기를 관리하지 않아도 됨

    @Override
    public void onCreate() {
        Log.d("MobileProgramming", "LocalService - onCreate()");
    }

    @Override
    public void onDestroy() {
        Log.d("MobileProgramming", "LocalService - onDestroy()");
    }
}
