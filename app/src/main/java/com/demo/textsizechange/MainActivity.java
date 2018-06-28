package com.demo.textsizechange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_setting)
    Button btnSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_setting})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_setting:
               goActivity(TextSizeShowActivity.class);
                break;
        }
    }

    @Override
    public void rxBusCall(MessageSocket message) {
        super.rxBusCall(message);
        switch (message.id){
            case 99://重启 不带动画
                //           this.recreate();
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
        }
    }
}
