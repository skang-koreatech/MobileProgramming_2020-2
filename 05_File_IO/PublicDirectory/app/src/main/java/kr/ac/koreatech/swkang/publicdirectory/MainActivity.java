package kr.ac.koreatech.swkang.publicdirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    boolean isPermitted = false;

    private static final String FILE_NAME = "log.txt";
    private File folder;
    private String folderPath;
    private String filePath;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Runtime permission 요청
        requestRuntimePermission();

        // 외부 공용 디렉토리 중 Download 디렉토리에 대한 File 객체 얻음
        folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        // 절대 경로 String 값을 얻음
        folderPath = folder.getAbsolutePath();
        // 로그 파일 절대 경로 생성 (String 값)
        filePath = folderPath + "/" + FILE_NAME;


        edit = findViewById(R.id.edit);

        Button readBtn = findViewById(R.id.read);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream(filePath);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    edit.setText(new String(buffer));
                    fis.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button writeBtn = findViewById(R.id.write);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(filePath);
                    fos.write(edit.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestRuntimePermission() {
        //*******************************************************************
        // Runtime permission check
        //*******************************************************************
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            // WRITE_EXTERNAL_STORAGE 권한이 있는 것
            isPermitted = true;
        }
        //*********************************************************************
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // WRITE_EXTERNAL_STORAGE 권한을 얻음
                    // permission was granted, yay! Do the
                    // read_external_storage-related task you need to do.

                    isPermitted = true;

                } else {
                    // 권한을 얻지 못 하였으므로 location 요청 작업을 수행할 수 없다
                    // 적절히 대처한다

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    isPermitted = false;
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}