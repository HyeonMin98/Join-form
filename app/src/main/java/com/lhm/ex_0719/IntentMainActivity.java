package com.lhm.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    EditText edit_name, edit_age, edit_tel, edit_b_day;
    Dialog dialog;
    Button btn_date, btn_send;
    int num = 3; //뒤로가기 누르고 3초이내시 종료 활성화 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);
        edit_b_day = findViewById(R.id.edit_b_day);
        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(send_click);
        btn_date.setOnClickListener(date_click);


    }

    View.OnClickListener send_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //화면 전환시 파라미터로 전달할 값들
            String name = edit_name.getText().toString();
            if (name.trim().length() == 0) {
                Toast.makeText(IntentMainActivity.this, "이름입력", Toast.LENGTH_SHORT).show();
            }
            String age = edit_age.getText().toString();
            String tel = edit_tel.getText().toString();
            String date = edit_b_day.getText().toString();

            Intent i = new Intent(IntentMainActivity.this, IntentSubActivity.class);
            i.putExtra("m_name", name);
            i.putExtra("m_age", age);
            i.putExtra("m_tel", tel);
            i.putExtra("m_date", date);


            startActivity(i);
        }
    };

    View.OnClickListener date_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //달력에 최초로 표기될 날짜를 구한다.
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);    //월 현재가 7월인데 값으로는 6으로 넘어온다. 1~12월이 0~12월로 들어옴.
            int d = now.get(Calendar.DAY_OF_MONTH);

            dialog = new DatePickerDialog(IntentMainActivity.this,
                    dateSetListener,    //감지자
                    y, m, d);         //년 월 일
            dialog.show();
        }
    };

    //달력의 변경사항을 감지하는 이벤트 감시자.
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            //파라미터 값 중 월일을 의미하는 m은
            //1월 -> 0, 2월 -> 1 ... 12월 -> 11
            //날짜형식 지정 1980-01-01
            String result = String.format("%d-%02d-%02d", y, m + 1, d);
            edit_b_day.setText(result);
        }
    };


    //뒤로가기 버튼 부분
    @Override
    public void onBackPressed() {
        if (num != 3) {
            finish();
        } else {
            Toast.makeText(this, "한번 더 누르면 종료", Toast.LENGTH_SHORT).show();
            //핸들러 호출
            handler.sendEmptyMessage(0);
        }
    }


    //핸들러 준비
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            handler.sendEmptyMessageDelayed(0, 1000);
            if (num > 0) {
                --num;
            } else {
                num = 2;
                handler.removeMessages(0); //remove가 없으면 2 ,1, 0 반복
            }
        }
    };
}