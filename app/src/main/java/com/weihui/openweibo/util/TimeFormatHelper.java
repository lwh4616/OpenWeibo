package com.weihui.openweibo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatHelper {

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm");

    public static String format(String created_at) {

        String formatTime = "";
        long time = Date.parse(created_at);
        Date date = new Date(time);
        long duration = System.currentTimeMillis() - time;
        if (duration < 3600 * 1000) {
            int min = Math.round(duration / (1000 * 60.0f));
            if (min < 1) {
                formatTime = ((int) Math.round(duration / 1000.0)) + "秒前";
            } else {
                formatTime = min + "分钟前";
            }

        } else if (duration < 24 * 3600 * 1000) {
            formatTime = "今天 " + timeFormat.format(date);
        } else {
            formatTime = dateFormat.format(date);
        }

        return formatTime;
    }
}
