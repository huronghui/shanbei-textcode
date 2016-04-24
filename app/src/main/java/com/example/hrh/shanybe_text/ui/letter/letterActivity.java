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

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.letter.presenter.LetterSelectPresenter;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;
import com.example.hrh.shanybe_text.util.TextJustification;
import com.example.hrh.shanybe_text.weight.AlignTextView;
import com.example.hrh.shanybe_text.weight.TagImageSpan;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/23.
 */
public class letterActivity extends BaseActivity implements LetterView{

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

    LetterSelectPresenter presenter;
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
//                        justedStr = tvContent.getText().toString();
//                        search(justedStr);
                        tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    @Override
    public void initData() {
        presenter = new LetterSelectPresenter(this);
//        tvContent.post(new Runnable() {
//            @Override
//            public void run() {
//                presenter.getInfo(tvContent);
//                tvContent.postInvalidate();
//            }
//        });

        tvContent.setText(getinfo());
    }

    @Override
    public void showLoading(LetterBean list) {

//        String text =
//        tvContent.setText(list.getEnglishContent());
//
    }

    @Override
    public void hideLoading() {
//        tvContent.setText("hhhhhhh");
    }

    @Override
    public void failedLoading() {

    }

    private String getinfo() {
        return "Several cases have been reported in Russia recently of people who can detect colours with their fingers, and even see through solid and walls. One case concerns and eleven-year-old schoolgirl, Vera Petrova, who has normal vision but who can also perceive things with different parts of her skin, and through solid walls. This ability was first noticed by her father. One day she came into his office and happened to put her hands on the door of a locked safe. Suddenly she asked her father why he kept so many old newspapers locked away there, and even described the way they were done up in bundles.\n" +
                "    Vera's curious talent was brought to the notice of a scientific research institute in the town of Ulyanovsk, near where she lives, and in April she was given a series of tests by a special commission of the Ministry of Health of the Russian Federal Republic. During these tests she was able to read a newspaper through an opaque screen and, stranger still, by moving her elbow over a child's game of Lotto she was able to describe the figures and colours printed on it; and, in another instance, wearing stockings and slippers, to make out with her foot the outlines and colours of a picture hidden under a carpet. Other experiments showed that her knees and shoulders had a similar sensitivity. During all these tests Vera was blindfold; and, indeed, except when blindfold she lacked the ability to perceive things with her skin. It was also found that although she could perceive things with her fingers this ability ceased the moment her hands were wet.\n" + "Seeing hands\n" +
                "俄罗斯最近报导了几个事例，有人能用手指看书识字和辨认颜色，甚至能透过厚实的门和墙看到东西。 其中有一例谈到有一个名叫维拉.彼托洛娃的11岁学生。她的视力与常人一样，但她还能用皮肤的不同部位辨认东西，甚至看穿坚实的墙壁。是她父亲首先发现她这一功能的。一天，维拉走进父亲的办公室，偶然把手放在一个锁着的保险柜的门上，她突然问父亲为什么把这么多的旧报纸锁在柜子里，还说了报纸捆扎的情况。\n" +
                "维拉的特异功能引起了她家附近乌里扬诺夫斯克城一个科研单位的注意。4月里，俄罗斯卫生部一个特别委员会对她进行了一系列的测试。在这些测试中，她能隔着不透明的屏幕读报纸。更为奇怪的是，她把肘部在儿童玩的“罗托”纸牌上移动一下，便能说出印在纸牌上的数字和颜色。还有一次，她穿着长筒袜子和拖鞋，能用脚步识别出藏在地毯下面的一幅画的轮廓和颜色。其他实验表明，她的膝盖和双肩有类似的感觉能力，在所有这些实验中，维拉的双眼都是蒙着的。如果不蒙上双眼她的皮肤就不再具有识别物体的能力。这是千真万确的。同时还发现，尽管她能用手指识别东西，但她的手一旦弄湿，这种功能便会立即消失。\n";
    }
}
