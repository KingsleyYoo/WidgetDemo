package com.king.widgetdemo.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.king.widgetdemo.R;

public class AutosizingTextViewActivity extends AppCompatActivity {

    private EditText mEtInputText;
    private TextView mTvAutoTextView;
    private TextView mTvTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autosizing_text_view);
        initView();
    }

    private void initView() {
        mEtInputText = (EditText) findViewById(R.id.et_input_text);
        mTvAutoTextView = (TextView) findViewById(R.id.tv_auto_text_view);
        mTvTextSize = (TextView) findViewById(R.id.tv_text_size);

        mEtInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = s.toString().trim();
                // 该TextView中已配置了AutoSize属性
                mTvAutoTextView.setText(inputText);

                // 如果该TextView在xml中没有配置AutoSizeAutoSize，则可以通过代码动态设置；只能适用在API26以上
//                mTvAutoTextView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//                mTvAutoTextView.setAutoSizeTextTypeUniformWithConfiguration(10, 80, 10, TypedValue.COMPLEX_UNIT_SP);
//                mTvAutoTextView.setText(inputText);

                float textSize = mTvAutoTextView.getTextSize();
                mTvTextSize.setText("TextSize: " + String.valueOf(textSize));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
