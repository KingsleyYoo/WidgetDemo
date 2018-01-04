package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * <b>Create Date:</b> 2016/12/28<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description:</b> <br>
 */

public class OneShotAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "收到Alarm的广播");
        Toast.makeText(context, "已收到Alarm的广播", Toast.LENGTH_SHORT).show();
    }
}
