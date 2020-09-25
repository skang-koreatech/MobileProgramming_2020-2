package kr.ac.koreatech.swkang.alertdialogfragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static class ButtonDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("종료 확인 대화 상자")
                    .setMessage("애플리케이션을 종료하시겠습니까?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    public static class ColorDialogFragment extends DialogFragment {
        int choice = -1;
        final CharSequence[] items = {"Red", "Green", "Blue"};

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builderRadio = new AlertDialog.Builder(getActivity());
            builderRadio.setTitle("색상을 선택하시오")
                    .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            //Toast.makeText(getActivity(), items[item], Toast.LENGTH_SHORT).show();
                            choice = item;
                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), items[choice], Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertRadio = builderRadio.create();
            return alertRadio;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);

        // 버튼을 클릭했을 때 대화상자를 표시하도록 처리
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위에서 정의한 ButtonDialogFragment 클래스의 객체를 생성
                DialogFragment myFragment = new ButtonDialogFragment();

                // show 메소드 호출을 통하여 대화상자가 화면에 표시되도록 함
                myFragment.show(getSupportFragmentManager(), "FinishDialog");
            }
        });

        Button b2 = findViewById(R.id.dialogForRadioButton);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment frag = new ColorDialogFragment();
                frag.show(getSupportFragmentManager(), "ColorDialog");
            }
        });
    }
}