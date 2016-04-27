package com.example.hrh.shanybe_text.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hrh.shanybe_text.ui.about.AboutActivity;
import com.example.hrh.shanybe_text.ui.lesson.LessonSelectActivity;
import com.example.hrh.shanybe_text.ui.letter.LetterActivity;
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
    public static void showLessonActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LessonSelectActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 显示Letter显示界面
     * @param context
     */
    public static void showLetterActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LetterActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void showAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
