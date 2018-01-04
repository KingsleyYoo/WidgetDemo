package widget;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.king.widget.widgetdemo.R;


/**
 * <b>Create Date:</b> 2017/4/6<br>
 * <b>Author:</b> yuxin<br>
 * <b>Description:</b> <br>
 */

public class MyToast {

    private Context mContext = null;
    private Toast mToast = null;
    private Handler mHandler = null;
    private int duration = 0;
    private int currDuration = 0;
    private final int DEFAULT=2000;
    private Runnable mToastThread = new Runnable() {

        public void run() {
            mToast.show();
            mHandler.postDelayed(mToastThread, DEFAULT);// 每隔2秒显示一次
            if (duration != 0) {
                if (currDuration <= duration) {
                    currDuration += DEFAULT;
                }
                else {
                    cancel();
                }
            }

        }
    };
    private final View mToastView;

    public MyToast(Context context) {
        mContext = context;
        currDuration=DEFAULT;
        mHandler = new Handler(mContext.getMainLooper());
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
        LayoutInflater inflater = LayoutInflater.from(context);
        mToastView = inflater.inflate(R.layout.custom_toast, null);
        mToast.setView(mToastView);
    }

    public void setText(String text) {
//        mToast.setText(text);
        TextView tvMessage = (TextView) mToastView.findViewById(R.id.message);
        tvMessage.setText(text);
    }

    public void show(int duration) {
        this.duration = duration;
        mHandler.post(mToastThread);
    }

    public void setGravity(int gravity, int xOffset, int yOffset){
        mToast.setGravity(gravity, xOffset, yOffset);
    }

    public void setDuration(int duration){
        mToast.setDuration(duration);
    }

    public void setView(View view){
        mToast.setView(view);
    }

    public void cancel( ) {
        mHandler.removeCallbacks(mToastThread);// 先把显示线程删除
        mToast.cancel();// 把最后一个线程的显示效果cancel掉，就一了百了了
        currDuration = DEFAULT;
    }
}
