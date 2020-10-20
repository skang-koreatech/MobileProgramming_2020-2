package kr.ac.koreatech.swkang.listviewupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] values = {"하스스톤", "몬스터 헌터", "디아블로", "와우", "리니지", "안드로이드", "아이폰"};
        ArrayList<String> values = new ArrayList<>();

        // Android에서 제공하는 String 문자열 하나를 출력하는 layout으로 어댑터 생성
        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        // layout xml 파일에 정의된 ListView의 객체
        m_ListView = findViewById(R.id.list);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트를 처리할 리스너 설정
        m_ListView.setOnItemClickListener(onClickListItem);
    }

    // 아이템 터치 이벤트 리스너 구현
    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 이벤트 발생 시 해당 아이템 위치를 텍스트로 출력
            Toast.makeText(getApplicationContext(), m_Adapter.getItem(position), Toast.LENGTH_SHORT).show();
        }
    };

    // Add, Remove 버튼 클릭 시 호출
    public void onClick(View view) {
        int count;
        count = m_Adapter.getCount();

        if(view.getId() == R.id.add) {
            // add 버튼 클릭한 경우 리스트의 마지막에 새 아이템 추가
            // adapter에 아이템 추가
            m_Adapter.add("new item " + Integer.toString(count + 1));
        } else if(view.getId() == R.id.remove) {
            // remove 버튼 클릭한 경우 리스트의 마지막 아이템을 삭제
            // 삭제할 아이템이 없으면 메시지 출력 후 종료
            if (count == 0) {
                Toast.makeText(getApplicationContext(), "삭제할 아이템이 없습니다", Toast.LENGTH_SHORT).show();
                return;
            }
            // 리스트의 마지막 아이템을 얻음
            String item = m_Adapter.getItem(count - 1);
            // 해당 아이템을 adapter에서 삭제
            m_Adapter.remove(item);
        }
    }
}