package com.example.real_project;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class choice extends AppCompatActivity {
    // 음식종류 갯수다. result.java에 넣은 음식종류 갯수들을 표시
    int KOR = 9; //9
    int JP =6; //6
    int USA = 4; //4
    int BS = 4; //4
    int YS = 4; //4
    int CHA = 7; //7
    int sum = 0; //0

    // "1" == 음식종류 체크  "0" == 음식종류 체크안함
    String KORFLAG = "0";
    String JPFLAG = "0";
    String USAFLAG = "0";
    String BSFLAG = "0";
    String YSFLAG = "0";
    String CHAFLAG="0";
    // 오디오재생
    MediaPlayer mp;

    TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choiceview);






        Text = (TextView) findViewById(R.id.Text);

        // 버튼 인탠트
        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 선택한 음식종류를 다음 액티비티에 넘겨준다.
                Intent intent = new Intent(choice.this, result.class);
                intent.putExtra("KORFLAG", KORFLAG);
                intent.putExtra("JPFLAG", JPFLAG);
                intent.putExtra("USAFLAG", USAFLAG);
                intent.putExtra("BSFLAG", BSFLAG);
                intent.putExtra("YSFLAG", YSFLAG);
                intent.putExtra("CHAFLAG", CHAFLAG);

                startActivity(intent);
            }
        });

    }



    public void onCheckboxClicked(View view) {         // 체크박스 선택시 함수다
        boolean checked = ((CheckBox) view).isChecked();


        switch (view.getId()) {
            case R.id.checkbox_KOR:    // 예를들어 한식을 체크 or 체크를 풀었다면
                if (checked) { // 체크 했다면
                    sum=sum + KOR; // sum에 한식 음식갯수만큼 넣는다
                    KORFLAG="1"; // 1은 한식을 선택했다는것 정의한다.

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"한식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else { // 체크 풀었을경우
                    sum=sum - KOR; //  sum에서 한식 음식갯수만큼 뺀다
                    KORFLAG="0"; // 0은 선택안했다는것을 정의      ** 밑에는 변수만 다를뿐 코드구조 동일 주석 생략 **

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"한식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;



            case R.id.checkbox_USA:
                if (checked) {
                    sum=sum + USA;
                    USAFLAG="1";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"양식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sum=sum - USA;
                    USAFLAG="0";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"양식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.checkbox_JP:
                if (checked) {
                    sum=sum + JP;
                    JPFLAG="1";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"일식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sum=sum - JP;
                    JPFLAG="0";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"일식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.checkbox_CHA:
                if (checked) {
                    sum=sum + CHA;
                    CHAFLAG="1";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"중식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sum=sum - CHA;
                    CHAFLAG="0";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"중식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.checkbox_BS:
                if (checked) {
                    sum=sum + BS;
                    BSFLAG="1";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"분식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sum=sum - BS;
                    BSFLAG="0";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"분식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.checkbox_YS:
                if (checked) {
                    sum=sum + YS;
                    YSFLAG="1";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"야식 선택하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    sum=sum - YS;
                    YSFLAG="0";

                    Text.setText(sum+" 개의 음식이 기다리고 있습니다!");
                    Toast.makeText(getApplicationContext(),"야식 선택 해제 하셨습니다.",Toast.LENGTH_SHORT).show();
                }
                break;








        }
    }




}
