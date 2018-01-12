package com.king.widgetdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = s.toString().trim();
                mTvAutoTextView.setText(inputText);

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
