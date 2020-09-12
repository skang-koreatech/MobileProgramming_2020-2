package kr.ac.koreatech.swkang.radiobuttonevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_red:
                if(checked)
                    Toast.makeText(getApplicationContext(), ((RadioButton) view).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_blue:
                if(checked)
                    Toast.makeText(getApplicationContext(), ((RadioButton) view).getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}