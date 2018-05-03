package com.example.weihui.openweibo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.weihui.openweibo.model.impl.TimeLineRemoteModelImpl;
import com.weihui.openweibo.webapi.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Constants.token = AccessTokenKeeper.readAccessToken(appContext);
        TimeLineRemoteModelImpl.getInstance().getSns();


    }
}
