package com.king.widgetdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * <b>Create Date:</b> 2017/4/6<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description:</b> <br>
 */

public class VToast {

    public static final int LENGTH_MAX = -1;
    private static boolean mCanceled = true;
    private static Handler mHandler;
    private Context mContext;
    private static Toast mToast;

    public VToast(Context context) {
        this(context,new Handler());
    }


    public VToast(Context context, Handler h) {
        mContext = context;
        mHandler = h;
        mToast = Toast.makeText(mContext,"", Toast.LENGTH_SHORT);
//        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        mToast.setGravity(Gravity.TOP, 0, height / 3);
    }

    public void show(int resId,int duration) {
        mToast.setText(resId);
        if(duration != LENGTH_MAX) {
            mToast.setDuration(duration);
            mToast.show();
        } else if(mCanceled) {
            mToast.setDuration(Toast.LENGTH_LONG);
            mCanceled = false;
            showUntilCancel();
        }
    }

    /**
     * @param text 要显示的内容
     * @param duration 显示的时间长
     * 根据LENGTH_MAX进行判断
     * 如果不匹配，进行系统显示
     * 如果匹配，永久显示，直到调用hide()
     */
    public static void show(String text, int duration) {
        mToast.setText(text);
        if(duration != LENGTH_MAX) {
            mToast.setDuration(duration);
            mToast.show();
        } else {
            if(mCanceled) {
                mToast.setDuration(Toast.LENGTH_LONG);
                mCanceled = false;
                showUntilCancel();
            }
        }
    }

    /**
     * 隐藏Toast
     */
    public void hide(){
        mToast.cancel();
        mCanceled = true;
    }

    public boolean isShowing() {
        return !mCanceled;
    }

    private static void showUntilCancel() {
        if(mCanceled)
            return;
        mToast.show();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                showUntilCancel();
            }
        },3000);
    }
}
