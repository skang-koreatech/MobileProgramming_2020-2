package kr.ac.koreatech.swkang.databaseadapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mycontacts.db";
    private static final int DBVer = 1;

    public DBHelper(Context context) {
        super(context, DBName, null, DBVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, tel TEXT);");

        // contacts 테이블에 10개의 레코드를 입력
        for(int i = 0; i < 10; i++) {
            db.execSQL("INSERT INTO contacts VALUES (null, " + "'김철수 " + i
                    + "', '010-1234-100" + i + "');");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
