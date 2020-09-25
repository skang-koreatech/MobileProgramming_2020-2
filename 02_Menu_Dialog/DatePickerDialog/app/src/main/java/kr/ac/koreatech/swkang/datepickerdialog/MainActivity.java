package kr.ac.koreatech.swkang.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        TextView mT;

        public void setTextView(TextView date) {
            mT = date;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar cal = Calendar.getInstance();

            // DatePickerDialog 클래스의 생성자
            // DatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth)
            // Creates a new date picker dialog for the specified date using the parent context's default date picker dialog theme.

            return new DatePickerDialog(getActivity(), this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mT.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        TextView mT;

        public void setTextView(TextView time) {
            mT = time;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar cal = Calendar.getInstance();

            // TimePickerDialog 클래스의 생성자
            // TimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView)
            // Creates a new time picker dialog.

            return new TimePickerDialog(getActivity(), this, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int min) {
            mT.setText("Time: " + hourOfDay + ":" + min);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.button);

        // 버튼을 클릭했을 때 대화상자를 표시하도록 처리
        // OnClickListener를 설정(무명클래스로 이벤트 리스너 객체를 생성하는 방식)하고 onClick 메소드 구현
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dateText = findViewById(R.id.dateText);

                DatePickerFragment dpf = new DatePickerFragment();
                dpf.setTextView(dateText);
                dpf.show(getSupportFragmentManager(), "datePicker");
            }
        });

        Button bt2 = findViewById(R.id.button2);

        // 버튼을 클릭했을 때 대화상자를 표시하도록 처리
        // OnClickListener를 설정(무명클래스로 이벤트 리스너 객체를 생성하는 방식)하고 onClick 메소드 구현
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView timeText = findViewById(R.id.timeText);

                TimePickerFragment tpf = new TimePickerFragment();
                tpf.setTextView(timeText);
                tpf.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }
}