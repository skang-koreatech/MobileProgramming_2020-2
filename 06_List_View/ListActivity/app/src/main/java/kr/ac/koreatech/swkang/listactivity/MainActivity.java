package kr.ac.koreatech.swkang.listactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        String[] values = {"하스스톤", "몬스터 헌터", "디아블로", "와우", "리니지", "안드로이드", "아이폰"};

        // Android에서 제공하는 String 문자열 하나를 출력하는 layout으로 어댑터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        // ListView에 어댑터 연결
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View view, int position, long id) {
        String item = (String)getListAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_SHORT).show();
    }
}
