package kr.ac.koreatech.swkang.powerbr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 수신한 Broadcast의 intent action이 무엇인지 알아냄
            String action = intent.getAction();

            // action의 종류에 따라서 Toast 메시지를 화면에 표시
            if (action == Intent.ACTION_POWER_CONNECTED) {
                Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
            } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
                Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receiver);
    }
}