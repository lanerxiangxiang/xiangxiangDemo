package cn.cnpp.searchhistory.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.text.TextPaint;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-20 下午1:31
 * @email: panxiang.song@megatronix.co
 * TypedValue.COMPLEX_UNIT_SP,22
 */
public class NewListUtils {
    public static List<String> getTitleList(List<String> titles, Activity activity) {
        List<String> mList = new ArrayList<>();
        int windowWidth = getWindowWidth(activity);
        if (titles.size() == 1) return titles;
        if (titles != null) {
            int size = titles.size();

            if (size < 3) {
                return cut2(windowWidth, titles, activity);

            } else if (size == 3) {
                return cut3(windowWidth, titles, activity);
            }
        }
        return mList;
    }


    public static List<String> cut3(int windowWidth, List<String> titles, Activity activity) {

        float width = 0;
        for (String s : titles) {
            width += getTextWidth(activity, s, 16);
        }

        if (width > windowWidth - 20) {

            List<String> newList = new ArrayList<>();

            if (titles.get(2).endsWith(".../")) {
                if (titles.get(0).length() > 5)
                    newList.add(0, titles.get(0).substring(0, 1) + ".../");
            } else {
                newList.add(titles.get(0));

            }
            newList.add(1, ".../");
            newList.add(2, titles.get(0).substring(0, 5) + ".../");
            return cut3(windowWidth, newList, activity);
        } else {
            return titles;
        }
    }

    public static List<String> cut2(int windowWidth, List<String> titles, Activity activity) {

        float width = 0;
        for (String s : titles) {
            width += getTextWidth(activity, s, 16);
        }

        if (width > windowWidth - 20) {

            List<String> newList = new ArrayList<>();

            if (titles.get(1).endsWith(".../")) {
                if (titles.get(0).length() > 5)
                    newList.add(0, titles.get(0).substring(0, 1) + ".../");
            } else {
                newList.add(titles.get(0));

            }
            newList.add(1, titles.get(0).substring(0, 5) + ".../");
            return cut2(windowWidth, newList, activity);
        } else {
            return titles;
        }
    }


    public static float getTextWidth(Activity context, String text, int textSize) {
        TextPaint paint = new TextPaint();
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return paint.measureText(text);
    }


    public static int getWindowWidth(Activity activity) {
        if (!isAllScreenDevice(activity)) {
            return getScreenWidth(activity);
        }
        return getScreenRealWidth(activity);


    }

    private static final int PORTRAIT = 0;
    private static final int LANDSCAPE = 1;
    private volatile static Point[] mRealSizes = new Point[2];


    public static int getScreenRealWidth(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getScreenWidth(context);
        }

        int orientation = context.getResources().getConfiguration().orientation;
        orientation = orientation == Configuration.ORIENTATION_PORTRAIT ? PORTRAIT : LANDSCAPE;

        if (mRealSizes[orientation] == null) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return getScreenWidth(context);
            }
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            mRealSizes[orientation] = point;
        }
        return mRealSizes[orientation].x;
    }

    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return 0;
    }

    private volatile static boolean mHasCheckAllScreen;
    private volatile static boolean mIsAllScreenDevice;

    public static boolean isAllScreenDevice(Context context) {
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }
}
