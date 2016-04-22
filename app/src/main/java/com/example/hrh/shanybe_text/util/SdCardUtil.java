package com.example.hrh.shanybe_text.util;

/**
 * Created by hrh on 2016/4/22.
 */
public class SdCardUtil {

    public static boolean ExistSDCard() {

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        else {
            return false;
        }
    }
}
