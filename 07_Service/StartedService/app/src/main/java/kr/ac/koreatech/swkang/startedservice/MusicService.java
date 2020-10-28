package kr.ac.koreatech.swkang.startedservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
    private static final String TAG = "MusicService";

    // audio/video 파일 재생을 제어하는데 사용하는 클래스
    MediaPlayer player;

    // Service 동작을 위해 구현해야 하는 메소드

    // public void onCreate()

    // public int onStartCommand(Intent intent, int flags, int startId)

    // public void onDestroy()

    // public IBinder onBind(Intent intent)


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");

        // MediaPlayer 객체 생성
        // create(Context context, int resid)
        player = MediaPlayer.create(this, R.raw.billyjoel_pianoman);
        // 반복재생 여부 설정, setLooping(false)/setLooping(true)
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // intent: startService() 호출 시 넘기는 intent 객체
        // flags: service start 요청에 대한 부가 정보. 0, START_FLAG_REDELIVERY, START_FLAG_RETRY
        // startId: start 요청을 나타내는 unique integer id

        Log.d(TAG, "onStartCommand()");
        Toast.makeText(this, "MusicService 시작", Toast.LENGTH_SHORT).show();

        // MediaPlayer play 시작
        player.start();

        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        Toast.makeText(this, "MusicService 중지", Toast.LENGTH_SHORT).show();

        // MediaPlayer play 중지
        player.stop();

        // MediaPlayer 객체 해제
        player.release();
        player = null;

    }

    // 아래 onBind 메소드가 없으면 어떻게 될까?
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
