package kr.ac.koreatech.swkang.internalfile2;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextFileManager {
    private static final String FILE_NAME = "myFile.txt";
    private Context mContext;

    public TextFileManager(Context _context) {
        mContext = _context;
    }

    // 파일에 문자열 데이터를 쓰는 메소드
    public void save(String data) {
        if (data == null || data.isEmpty() == true) {
            return;
        }
        FileOutputStream fos;
        try {
            fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 데이터를 읽고 문자열 데이터로 반환하는 메소드
    public String load() {
        try {
            FileInputStream fis = mContext.openFileInput(FILE_NAME);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 파일 삭제 메소드
    public void delete() {
        mContext.deleteFile(FILE_NAME);
    }
}
