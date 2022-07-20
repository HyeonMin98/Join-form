package com.lhm.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView txt_name, txt_age, txt_tel,txt_birth, txt_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_tel = findViewById(R.id.txt_tel);
        txt_birth = findViewById(R.id.txt_birth);

        Intent i = getIntent(); //메인에서 넘어온 intent

        //넘겨받은 intent로부터 저장된 값을 추출
        String name = i.getStringExtra("m_name");
        String age = i.getStringExtra("m_age");
        String tel = i.getStringExtra("m_tel");
        String date = i.getStringExtra("m_date");


        txt_name.setText("이름 : " +name);
        txt_age.setText("age : " +age);
        txt_tel.setText("tel : " +tel);
        txt_birth.setText("tel : " +date);


    }
}