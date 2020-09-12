package kr.ac.koreatech.swkang.buttonevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class MyListenerClass implements View.OnClickListener {
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "+ button pressed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button3);
        MyListenerClass buttonListener = new MyListenerClass();
        button.setOnClickListener(buttonListener);
    }

//    public void buttonPressed(View view) {
//
//        Toast.makeText(getApplicationContext(), "+ button pressed", Toast.LENGTH_LONG).show();
//
//    }
}