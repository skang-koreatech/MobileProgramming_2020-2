package kr.ac.koreatech.swkang.internalfile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEdit = null;
    TextFileManager mFileMgr = new TextFileManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.edit);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read:
            {
                String data = mFileMgr.load();
                mEdit.setText(data);

                Toast.makeText(this, "불러오기 완료", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.write:
            {
                String data = mEdit.getText().toString();
                mFileMgr.save(data);
                mEdit.setText("");

                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.delete:
            {
                mFileMgr.delete();
                mEdit.setText("");

                Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}