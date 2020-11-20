package kr.ac.koreatech.swkang.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class MainActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase db;
    private EditText editName, editTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.name);
        editTel = findViewById(R.id.tel);

        // SQLiteOpenHelper 클래스의 subclass인 DBHelper 클래스 객체 생성
        helper = new DBHelper(this);
        // DBHelper 객체를 이용하여 DB 생성
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }
    }

    // 추가 버튼을 눌렀을 때 호출되는 메소드
    public void insert(View v) {
        // EditText에 입력된 이름과 전화번호를 String 데이터로 추출
        String name = editName.getText().toString();
        String tel = editTel.getText().toString();

        // 이름과 전화번호를 가지고 INSERT 문을 만들어 실행
        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "', '" + tel + "');");
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음", Toast.LENGTH_SHORT).show();

        // EditText 초기화
        editName.setText("");
        editTel.setText("");
    }

    // 탐색 버튼을 눌렀을 때 호출되는 메소드
    public void search(View v) {
        String name = editName.getText().toString();
        Cursor cursor;
        // EditText에 입력된 이름을 가지고 쿼리문을 만들어 실행
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name='" + name + "';", null);

        // 반환된 커서에 ResultSets의 행의 개수가 0개일 경우
        if(cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "해당 이름이 없습니다", Toast.LENGTH_SHORT).show();
            return;
        }
        // 반환된 커서를 가지고 전화번호 얻고 EditText에 표시
        while(cursor.moveToNext()) {
            String tel = cursor.getString(1);
            editTel.setText(tel);
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        helper.close();
    }
}