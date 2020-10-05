package kr.ac.koreatech.swkang.activityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int GET_STRING = 1;
    static final int GET_NUMBER = 2;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        text = findViewById(R.id.text);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(in, GET_STRING);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_STRING) {
            if (resultCode == RESULT_OK) {
                text.setText(data.getStringExtra("INPUT_TEXT"));
            } else if (resultCode == RESULT_CANCELED) {
                //
            }
        } else if (requestCode == GET_NUMBER) {
            //
        }
    }
}