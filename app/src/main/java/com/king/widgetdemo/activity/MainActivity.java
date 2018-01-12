package com.king.widgetdemo.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.king.widgetdemo.R;
import com.king.widgetdemo.receiver.OneShotAlarm;
import com.king.widgetdemo.widget.MyToast;

import java.util.Calendar;

/**
 * FileName: MainActivity
 * Author: YuXin
 * Date: 2018/1/5 14:17
 * Describe: 主页
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String TAG = "MainActivity";
    private static final String INIT_SP_CODE = "code";
    private static final String INIT_KEY_CODE = "factory_code";
    public static final int MODE_READ = Context.MODE_WORLD_READABLE;
    private Button mBtn_broadcast;
    private Button mBtn_dialog;
    private Button mBtn_getcache;
    private TextView mTv_showcache;
    private Context mPackageContext;
    private Button mBtn_showToast;
    private Button mBtn_snackbar;
    private Button mBtn_progress;
    private ProgressBar mProgressBar;
    private boolean isShowProcessBar;
    private Button mBtnAutoSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    private void initView() {
        mBtn_broadcast = (Button) findViewById(R.id.btn_send_broadcast);
        mBtn_dialog = (Button) findViewById(R.id.btn_showDialog);
        mBtn_getcache = (Button) findViewById(R.id.btn_getcache);
        mTv_showcache = (TextView) findViewById(R.id.tv_showcache);
        mBtn_showToast = (Button) findViewById(R.id.btn_showToast);
        mBtn_snackbar = (Button) findViewById(R.id.btn_snackBar);
        mBtn_progress = (Button) findViewById(R.id.btn_showProgress);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progressBar);
        mBtnAutoSize = (Button) findViewById(R.id.btn_auto_size);

        mBtn_broadcast.setOnClickListener(this);
        mBtn_dialog.setOnClickListener(this);
        mBtn_getcache.setOnClickListener(this);
        mBtn_showToast.setOnClickListener(this);
        mBtn_snackbar.setOnClickListener(this);
        mBtn_progress.setOnClickListener(this);
        mBtnAutoSize.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_broadcast:
                // 点击按钮之后,发送一个闹钟广播
                mBtn_broadcast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, OneShotAlarm.class);
                        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.add(Calendar.SECOND, 5);

                        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                        if (am != null) {
                            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                        }
                    }
                });

                break;

            case R.id.btn_showDialog:
                // 显示自定义对话框

                break;

            case R.id.btn_getcache:
                // 获取库存
                try {
                    mPackageContext = this.createPackageContext("com.want.vmc", CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                SharedPreferences sp = mPackageContext.getSharedPreferences(INIT_SP_CODE, MODE_READ);
                if (sp == null) {
                    return;
                } else {
                    String machine_id = sp.getString(INIT_KEY_CODE, "");
                    Log.i(TAG, "machine_id: " + machine_id);

                    if (!TextUtils.isEmpty(machine_id)) {
                        mTv_showcache.setText(machine_id);
                    }
                }

                break;

            case R.id.btn_showToast:
                /**
                 * 弹出自定义Toast提示框
                 */
//                CustomToast.showToast(MainActivity.this, "我是一个自定义Toast");

                /**
                 * 弹出自定义Toast提示框,自定义弹出显示时间
                 */
                MyToast myToast = new MyToast(MainActivity.this);
                WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
                int height = 0;
                if (wm != null) {
                    height = wm.getDefaultDisplay().getHeight();
                    myToast.setGravity(Gravity.TOP, 0, height / 3);
                    myToast.setText("这个是5000毫秒的Toast");
                    myToast.show(5000);
                }

                break;

            case R.id.btn_snackBar:

//                Snackbar.make(v,"这是一片好文章",Snackbar.LENGTH_LONG).show();
                Snackbar.make(v, "这是一片好文章", Snackbar.LENGTH_LONG)
                        .setAction("已收藏!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "已收藏这篇文章", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                break;

            case R.id.btn_showProgress:
                if (!isShowProcessBar) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    isShowProcessBar = true;
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }

                break;

            case R.id.btn_auto_size:
                Intent intent = new Intent(MainActivity.this, AutosizingTextViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
