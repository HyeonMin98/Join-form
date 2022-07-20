package com.lhm.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    TextView txt_count;
    Button btn_start, btn_stop, btn_reset;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt_count = findViewById(R.id.txt_count);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_reset = findViewById(R.id.btn_reset);

        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
        btn_reset.setOnClickListener(click);

    }   //onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    //백그라운드에서 txt_count값을 1씩 증가시키는 핸들러 호출

                    //핸들러의 handleMessage()메서드를 호출하는 방법
                    handler.sendEmptyMessage(0);

                    btn_start.setEnabled((false));
                    break;

                case R.id.btn_stop:
                    //핸들러 정지
                    handler.removeMessages(0);

                    //start 버튼 활성화
                    btn_start.setEnabled(true);
                    break;

                case R.id.btn_reset:
                    handler.removeMessages(0);
                    count = 0;
                    txt_count.setText(String.valueOf(count));
                    btn_start.setEnabled(true);
                    break;
            }
        }
    };

    //핸들러 준비
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            //백그라운드에서 코드를 실행하는 영역
//            handler.sendEmptyMessage(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            count++;
            
            //String.valueof(count) : count변수를 문자열로 바꿔주는 메서드
            txt_count.setText(String.valueOf(count));
        }

    };

}