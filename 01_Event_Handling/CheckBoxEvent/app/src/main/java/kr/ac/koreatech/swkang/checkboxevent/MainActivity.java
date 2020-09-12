package kr.ac.koreatech.swkang.checkboxevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_meat:
                if(checked)
                    Toast.makeText(getApplicationContext(), "Meat checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Meat not checked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_cheese:
                if(checked)
                    Toast.makeText(getApplicationContext(), "Cheese checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Cheese not checked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}