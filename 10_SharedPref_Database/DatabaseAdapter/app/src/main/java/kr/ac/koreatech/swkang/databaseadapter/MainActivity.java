package kr.ac.koreatech.swkang.databaseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class MainActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SQLiteOpenHelper 클래스의 subclass인 DBHelper 클래스 객체 생성
        helper = new DBHelper(this);
        // DBHelper 객체를 이용하여 DB 생성
        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();

        }

        // contacts 테이블에서 모든 레코드를 retrieve
        c = db.rawQuery("SELECT * FROM contacts", null);

        String[] from = {"name", "tel"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        // SimpleCursorAdapter 객체 생성
        // 하나의 리스트 아이템에 2개의 텍스트뷰를 표시하는 레이아웃
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, c, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        // 레이아웃에 정의된 리스트뷰에 대한 참조 객체 얻음
        ListView list = findViewById(R.id.list);
        // 리스트뷰 객체에 어댑터 설정
        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        helper.close();
        c.close();
    }
}