package com.example.hrh.shanybe_text.util;

import android.content.Context;
import android.content.Intent;

import com.example.hrh.shanybe_text.ui.lesson.LessonSelectActivity;
import com.example.hrh.shanybe_text.ui.letter.letterActivity;
import com.example.hrh.shanybe_text.ui.main.MainSelectActivity;

/**
 * Created by hrh on 2016/4/23.
 */
public class UIHelper {

    /**
     * 显示主界面
     * @param context
     */
    public static void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainSelectActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示Lesson选择界面
     * @param context
     */
    public static void showLessonActivity(Context context) {
        Intent intent = new Intent(context, LessonSelectActivity.class);
        context.startActivity(intent);
    }

    /**
     * 显示Letter显示界面
     * @param context
     */
    public static void showLetterActivity(Context context) {
        Intent intent = new Intent(context, letterActivity.class);
        context.startActivity(intent);
    }
}
