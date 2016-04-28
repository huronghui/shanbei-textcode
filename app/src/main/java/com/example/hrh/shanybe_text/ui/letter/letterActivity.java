package com.example.hrh.shanybe_text.ui.letter;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseFullScreanActivity;
import com.example.hrh.shanybe_text.domain.letter.LetterBean;
import com.example.hrh.shanybe_text.domain.letter.PositionBean;
import com.example.hrh.shanybe_text.ui.letter.presenter.LetterSelectPresenter;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;
import com.example.hrh.shanybe_text.util.TextJustification;
import com.example.hrh.shanybe_text.view.AlignTextView;
import com.example.hrh.shanybe_text.view.JustifyTextView;
import com.example.hrh.shanybe_text.view.WheelView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.shanbei.greendao.ScallopLessonInfo;
import com.shanbei.greendao.ScallopLessonInfoDao;
import com.shanbei.greendao.ScallopWordDao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/23.
 */
public class LetterActivity extends BaseFullScreanActivity implements LetterView {

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
    @Bind(R.id.tvChineseContent)
    JustifyTextView tvChineseContent;
    private String letter;
    LetterSelectPresenter presenter;
    private ScallopLessonInfoDao lessonInfoDao;
    private ScallopWordDao wordInfoDao;
    private ScallopLessonInfo currentLessonInfo;
    private Map<String, List<PositionBean>> map;
    private String contextInfo;
    private boolean isCheck = true;
    View outerView;
    private static final String[] PLANETS = new String[]{"0级", "1级", "2级", "3级", "4级", "5级", "6级"};

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
                        contextInfo = TextJustification.justify(tvContent, tvContent.getMeasuredWidth());
                        presenter.searchData(tvContent.getText().toString());
                        tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck) {
                    presenter.highCode(contextInfo);
                } else {
                    tvContent.setText(contextInfo);
                }
                isCheck = !isCheck;
            }
        });


        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View outerView = LayoutInflater.from(LetterActivity.this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(3);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d("LetterActivity", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        presenter.setWordSelect(selectedIndex - 1);
                    }
                });
                new AlertDialog.Builder(LetterActivity.this)
                        .setTitle("选择单词高亮等级")
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

    @Override
    public void initData() {
        presenter = new LetterSelectPresenter(this);
        Bundle bundle = getIntent().getExtras();
        letter = bundle.getString(LetterStirng);
        presenter.getInfo(tvContent, letter);
        presenter.getWordInfo(letter);
//        }
    }

    @Override
    public void showLoading(LetterBean list) {
        tvHead.setText(list.getTitle());
        tvContent.setText(list.getEnglishContent());
        tvChineseContent.setText(list.getChineseContent());
//        contextInfo = list.getEnglishContent();
//
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void failedLoading() {

    }

    @Override
    public void showTextSpan(SpannableString spannableString) {
        tvContent.setText(spannableString);
    }

}
