package com.weihui.openweibo.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.weihui.openweibo.webapi.Constants;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";

    AuthInfo mAuthInfo;

    SsoHandler mSsoHandler;

    Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);

        WbSdk.install(this, mAuthInfo);

        mSsoHandler = new SsoHandler(this);
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            AuthActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    Log.d(TAG, "success" + mAccessToken.toString());
                    if (mAccessToken.isSessionValid()) {
                        AccessTokenKeeper.writeAccessToken(AuthActivity.this, mAccessToken);
                        Toast.makeText(AuthActivity.this,
                                "Login Success", Toast.LENGTH_SHORT).show();
                        Constants.token = token;
                        startActivity(new Intent(AuthActivity.this, MainTabActivity.class));
                        finish();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Log.d(TAG, "Login cancel");
            Toast.makeText(AuthActivity.this,
                    "Cancel", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getErrorMessage() + ":" + errorMessage.getErrorCode());
        }
    }
}
