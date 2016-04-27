package com.example.hrh.shanybe_text.ui.about;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.util.Util;
import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseActivity;

/**
 * Created by hrh on 2016/4/27.
 */
public class AboutActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener{

    private final String INTRODUCTION = "introduction";
    private final String CURRENT_VERSION = "current_version";
    private final String SHARE = "share";
    private final String STAR = "Star";
    private final String ENCOURAGE = "encourage";
    private final String BLOG = "blog";
    private final String GITHUB = "github";
    private final String EMAIL = "email";
    private final String CHECK = "check_version";

    private Preference mStar;

    private Preference mBolg;
    private Preference mGithub;
    private Preference mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about);


        mStar = findPreference(STAR);
        mBolg = findPreference(BLOG);
        mGithub = findPreference(GITHUB);
        mEmail = findPreference(EMAIL);


        mStar.setOnPreferenceClickListener(this);
        mBolg.setOnPreferenceClickListener(this);
        mGithub.setOnPreferenceClickListener(this);
        mEmail.setOnPreferenceClickListener(this);

//        mVersion.setSummary(this.getString(R.string.version_name) + Util.getVersion(getActivity()));
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
//        if (mVersion == preference) {
//            new AlertDialog.Builder(AboutActivity.this).setTitle("就看天气的完成离不开开源项目的支持，向以下致谢：")
//                    .setMessage("Google Support Design,Gson,Rxjava,RxAndroid,Retrofit," +
//                            "Glide,systembartint")
//                    .setPositiveButton("关闭", null)
//                    .show();
//        }
//        else if (mShare == preference) {
//            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//            sharingIntent.setType("text/plain");
//            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_txt));
//            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_app)));
//        }
//        else if (mStar == preference) {
//
//            new AlertDialog.Builder(AboutActivity.this).setTitle("点赞")
//                    .setMessage("去项目地址给作者个Star，鼓励下作者୧(๑•̀⌄•́๑)૭✧")
//                    .setNegativeButton("复制", new DialogInterface.OnClickListener() {
//                        @Override public void onClick(DialogInterface dialog, int which) {
//                            copyToClipboard(getView(), AboutActivity.this.getResources()
//                                    .getString(
//                                            R.string.app_html));
//                        }
//                    })
//                    .setPositiveButton("打开", new DialogInterface.OnClickListener() {
//                        @Override public void onClick(DialogInterface dialog, int which) {
//                            Uri uri = Uri.parse(getString(R.string.app_html));   //指定网址
//                            Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);           //指定Action
//                            intent.setData(uri);                            //设置Uri
//                            AboutActivity.this.startActivity(intent);        //启动Activity
//                        }
//                    })
//                    .show();
//        }
//        else if (mIntroduction == preference) {
//            Uri uri = Uri.parse(getString(R.string.readme));   //指定网址
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);           //指定Action
//            intent.setData(uri);                            //设置Uri
//            AboutActivity.this.startActivity(intent);        //启动Activity
//        }
//        else if (mEncounrage == preference) {
//            new AlertDialog.Builder(AboutActivity.this).setTitle("请作者喝杯咖啡？(〃ω〃)")
//                    .setMessage("点击按钮后，作者支付宝账号将会复制到剪切板，" + "你就可以使用支付宝转账给作者啦( •̀ .̫ •́ )✧")
//                    .setPositiveButton("好叻", new DialogInterface.OnClickListener() {
//                        @Override public void onClick(DialogInterface dialog, int which) {
//                            copyToClipboard(getView(), AboutActivity.this.getResources()
//                                    .getString(
//                                            R.string.alipay));
//                        }
//                    })
//                    .show();
//        }
//        else if (mBolg == preference) {
//            copyToClipboard(getView(), mBolg.getSummary().toString());
//        }
//        else if (mGithub == preference) {
//
//            copyToClipboard(getView(), mGithub.getSummary().toString());
//        }
//        else if (mEmail == preference) {
//            copyToClipboard(getView(), mEmail.getSummary().toString());
//        }
//        else if (mCheckVersion == preference) {
//            Snackbar.make(getView(), "正在检查(σﾟ∀ﾟ)σ", Snackbar.LENGTH_SHORT).show();
//            CheckVersion.checkVersion(this(), getView());
//        }
        return false;
    }

    //复制黏贴板
    private void copyToClipboard(View view, String info) {
        ClipboardManager manager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("msg", info);
        manager.setPrimaryClip(clipData);
        Snackbar.make(view, "已经复制到剪切板啦( •̀ .̫ •́ )✧", Snackbar.LENGTH_SHORT).show();
    }
}
