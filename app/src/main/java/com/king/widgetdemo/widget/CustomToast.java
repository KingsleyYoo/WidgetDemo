package com.king.widgetdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.king.widgetdemo.R;

/**
 * <b>Create Date:</b> 2017/3/28<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description: 自定义Toast </b> <br>
 */

public class CustomToast {

    private static TextView mTvMessage;
    private static ImageView mIvImage;
    private static Context mContext = null;
    private Handler mHandler = new Handler();

    public CustomToast(Context context) {
        this.mContext = context;
    }

    public static void showToast(Context context, String message) {

        // 加在Toast布局
        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.custom_toast, null);
        // 初始化布局控件
        mTvMessage = (TextView) toastView.findViewById(R.id.message);
//        mIvImage = (ImageView) toastView.findViewById(R.id.imageView);
        // 设置控件的属性
        mTvMessage.setText(message);
//        mIvImage.setImageResource(R.mipmap.ic_launcher);
        // Toast初始化
        Toast toast = new Toast(context);
        // 获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        // Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toast.setGravity(Gravity.TOP, 0, height / 3);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
    }

}
