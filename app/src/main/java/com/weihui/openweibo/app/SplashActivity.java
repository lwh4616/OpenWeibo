package com.weihui.openweibo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.weihui.openweibo.ui.activities.MainTabActivity;
import com.weihui.openweibo.ui.activities.AuthActivity;
import com.weihui.openweibo.webapi.Constants;

/**
 * Created by weihui on 2018/4/8.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.token = AccessTokenKeeper.readAccessToken(this);

        if (null == Constants.token || TextUtils.isEmpty(Constants.token.getRefreshToken())) {
            startActivity(new Intent(this, AuthActivity.class));
        } else {
            startActivity(new Intent(this, MainTabActivity.class));
        }

        finish();

    }

}
