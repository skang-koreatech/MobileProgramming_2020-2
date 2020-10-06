package kr.ac.koreatech.swkang.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                break;
            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)12345789"));
                break;
            case R.id.map:
                //intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.30, 127.2?z=10"));
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:37.30, 127.2?z=10"));
                break;
            case R.id.contact:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                break;
            case R.id.email:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:swkang@koreatech.ac.kr"));
                break;
        }
        if (intent != null) {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}