package kr.ac.koreatech.swkang.powerbr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 수신한 Broadcast의 intent action이 무엇인지 알아냄
        String action = intent.getAction();

        // action의 종류에 따라서 Toast 메시지를 화면에 표시
        if(action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
        } else if(action == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}