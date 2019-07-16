package com.example.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HWJ_AccountActivity extends AppCompatActivity {
    TextView quit;
    RelativeLayout logout, disable, alarm, block_list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwj_account);

        //액션바(뒤로가기 버튼) 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        getSupportActionBar().setTitle("계정설정");

        //로그아웃 버튼 설정
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(HWJ_AccountActivity.this);
                box.setTitle("로그아웃");
                box.setMessage("로그아웃하시겠습니까?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(HWJ_AccountActivity.this, "로그인화면으로 넘어감 ㅎ", Toast.LENGTH_SHORT).show();
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });

        //비활성화 버튼 설정
        disable = findViewById(R.id.disable);
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(HWJ_AccountActivity.this);
                box.setTitle("계정 비활성화");
                box.setMessage("계정을 비활성화하게 되면 일주일간 정보가 남아있다가 소멸됩니다.\n기간 내에 계정을 활성화하면 평소와 같이 PickChat을 사용하실 수 있습니다.\n비활성화 하시겠습니까?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(HWJ_AccountActivity.this, "로그인화면으로 넘어감 ㅎ", Toast.LENGTH_SHORT).show();
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });

        //차단친구목록 버튼 설정
        block_list = findViewById(R.id.block_list);
        block_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HWJ_AccountActivity.this, HWJ_BlockListActivity.class);
                startActivity(intent);
            }
        });

        //알림설정 버튼 설정
        alarm = findViewById(R.id.alarm);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HWJ_AccountActivity.this, HWJ_AlarmActivity.class);
                startActivity(intent);
            }
        });

        //회원탈퇴
        quit = findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HWJ_AccountActivity.this, HWJ_QuitActivity.class);
                startActivity(intent);
            }
        });
    }

    //뒤로가기 버튼 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
