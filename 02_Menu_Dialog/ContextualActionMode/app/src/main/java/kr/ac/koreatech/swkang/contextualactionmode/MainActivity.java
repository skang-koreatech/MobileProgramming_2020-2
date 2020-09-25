package kr.ac.koreatech.swkang.contextualactionmode;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.view.ActionMode;
import android.view.ActionMode;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, ActionMode.Callback {

    ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.TextView01);
        text.setOnLongClickListener(this);
    }

    // ActionMode.Callback interface 구현을 위해 필요한 메소ㅡ

    // startActionMode() 메소드가 호출될 때 호출되는 콜백 메소드
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.context_men, menu);
        return true;
    }

    // onCreateActionMode()가 호출된 후에 호출.
    // 액션 메뉴를 refresh하는 목적으로 여러 번 호출될 수 있음
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    // 사용자가 액션 메뉴 항목을 클릭했을 때 호출
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                //
                //
                mode.finish(); // 액션이 선택되었으므로, 컨텍스트 액션 모드를 종료
                return true; // 이벤트를 처리하였으면 true 반환
            default:
                return false;
        }
    }

    // 사용자가 컨텍스트 액션 모드를 빠져나갈 때 호출
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
    }

    // 사용자가 액션 메뉴 항목을 클릭했을 때 호출
    // View.OnLongClickListener를 구현하기 위해 필요한 콜백 메소드
    public boolean onLongClick(View view) {
        if (mActionMode != null) {
            return false;
        }

        // 컨텍스트 액션 모드 시작
        //mActionMode = startSupportActionMode(this);
        mActionMode = startActionMode(this);
        view.setSelected(true);
        return true;
    }
}