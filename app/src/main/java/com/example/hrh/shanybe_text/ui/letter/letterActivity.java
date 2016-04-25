package com.example.hrh.shanybe_text.ui.letter;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.bean.letter.PositionBean;
import com.example.hrh.shanybe_text.ui.letter.presenter.LetterSelectPresenter;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;
import com.example.hrh.shanybe_text.util.DataBaseManager;
import com.example.hrh.shanybe_text.util.TextJustification;
import com.example.hrh.shanybe_text.weight.AlignTextView;
import com.example.hrh.shanybe_text.weight.TagImageSpan;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.shanbei.greendao.DaoSession;
import com.shanbei.greendao.ScallopLessonInfo;
import com.shanbei.greendao.ScallopLessonInfoDao;
import com.shanbei.greendao.ScallopWord;
import com.shanbei.greendao.ScallopWordDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/23.
 */
public class LetterActivity extends BaseActivity implements LetterView{

    @Bind(R.id.tvHead)
    TextView tvHead;
    @Bind(R.id.tvContent)
    AlignTextView tvContent;
    @Bind(R.id.action_a)
    FloatingActionButton actionA;
    @Bind(R.id.action_b)
    FloatingActionButton actionB;
    @Bind(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;

    public static String LetterStirng = "letterString";
    private String letter;
    LetterSelectPresenter presenter;
    private ScallopLessonInfoDao lessonInfoDao;
    private ScallopWordDao wordInfoDao;
    private ScallopLessonInfo currentLessonInfo;
    private Map<String, List<PositionBean>> map;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_letter;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        tvHead.setText(" unit one");
        tvContent.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        TextJustification.justify(tvContent, tvContent.getMeasuredWidth());
                        search(tvContent.getText().toString());
                        tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LetterActivity.this, " actionA click", Toast.LENGTH_SHORT).show();
                SpannableString s = new SpannableString(tvContent.getText());
                for (String key : map.keySet()) {
                    ScallopWord wordInfo = wordInfoDao.queryBuilder().where(ScallopWordDao.Properties.Content.eq(key)).list().get(0);
                    if (Integer.parseInt(wordInfo.getLevel()) <= 3) {
                        ArrayList<PositionBean> array = (ArrayList<PositionBean>) map
                                .get(key);
                        for (int i = 0; i < array.size(); i++) {
                            s.setSpan(new TagImageSpan(0, 0), array.get(i)
                                            .getStart(), array.get(i).getEnd(),
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }
                tvContent.setText(s);
            }
        });


    }

    @Override
    public void initData() {
        presenter = new LetterSelectPresenter(this);
        Bundle bundle = getIntent().getExtras();
        letter = bundle.getString(LetterStirng);
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
        wordInfoDao = mDaoSession.getScallopWordDao();
//        currentLessonInfo = lessonInfoDao.queryBuilder().where(ScallopLessonInfoDao.Properties.LessonList.eq("Lesson 20")).list().get(0);
        presenter.getInfo(tvContent, letter);
//        tvContent.setText(currentLessonInfo.getContent());

        List<ScallopWord> wordsList = wordInfoDao.queryBuilder().where(ScallopWordDao.Properties.LessonBelong.eq(letter)).list();
        map = new HashMap<String, List<PositionBean>>();
        for (ScallopWord wordInfo:wordsList){
            Log.i("Letter", wordInfo.getContent());
            map.put(wordInfo.getContent(), new ArrayList<PositionBean>());
        }
    }

    @Override
    public void showLoading(LetterBean list) {

        tvContent.setText(list.getEnglishContent());
//
    }

    @Override
    public void hideLoading() {
//        tvContent.setText(currentLessonInfo.getContent());
    }

    @Override
    public void failedLoading() {

    }

    private void search(String string) {
        for (String key:map.keySet()){
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(string);
            while (m.find()) {
                PositionBean pb = new PositionBean(m.start(), m.end());
                map.get(key).add(pb);
            }
        }
    }

}
