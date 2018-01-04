package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.king.widget.widgetdemo.MainActivity;

/**
 * <b>Create Date:</b> 2017/3/23<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description:</b> <br>
 */

public class BootReceiver extends BroadcastReceiver {

    public static final String TAG = "BootReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "收到开机广播,action:" + action);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
                Log.i(TAG, "收到开机广播,延迟5秒后自启动app");
            }
        }, 5000);  //延迟5秒执行run()方法
    }
}
