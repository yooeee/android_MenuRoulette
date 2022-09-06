package com.example.real_project;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class result extends AppCompatActivity {
    String[] KOR = {"비빔밥", "삼겹살", "김치찌개", "된장찌개", "돼지국밥", "제육덮밥", "비빔냉면", "물냉면","칼국수"};
    String[] JP = {"돈까스", "초밥", "돈코츠라멘", "회", "우동","샤브샤브"};
    String[] USA = {"피자", "스파게티", "스테이크", "햄버거"};
    String[] BS = {"떡볶이", "튀김", "순대", "김밥"};
    String[] YS = {"족발", "보쌈", "닭발", "치킨"};
    String[] CHA = {"짜장", "짬뽕", "탕수육", "깐풍기", "고추잡채","훠궈","마라탕"};
    private int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    double la;
    double lo;
    TextView Text,bigtext;
    ImageButton BoxBtn;
    Button mapBtn;
    MediaPlayer mp = null;
    List Choice = new ArrayList();

    int Choice_Length = 0;


    public static Context context_main; // context 변수 선언
    public int var; // 다른 Activity에서 접근할 변수




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultview);



        // 각각 버튼 및 텍스트들 설정하기
        Text = (TextView) findViewById(R.id.textView);
        Text.setVisibility(View.GONE);
        Button btn = (Button) findViewById(R.id.mapbtn);
        BoxBtn= (ImageButton) findViewById(R.id.boxbutton);
        bigtext= (TextView) findViewById(R.id.menutext);
        bigtext.setVisibility(View.GONE);
        mapBtn = (Button) findViewById(R.id.mapbtn);
        mapBtn.setVisibility(View.GONE);



        // 사용자가 체크한 음식종류 FLAG 값 가져오기   FLAG값 1인경우  체크를 한거, 0인경우 체크를 안한거
        Intent intent = getIntent();
        String KORFLAG = intent.getStringExtra("KORFLAG");
        String JPFLAG = intent.getStringExtra("JPFLAG");
        String USAFLAG = intent.getStringExtra("USAFLAG");
        String BSFLAG = intent.getStringExtra("BSFLAG");
        String YSFLAG = intent.getStringExtra("YSFLAG");
        String CHAFLAG = intent.getStringExtra("CHAFLAG");

        mp = MediaPlayer.create(result.this,R.raw.bgmbeforeclick);       // 브금 넣기
        mp.start(); //

        if(KORFLAG.equals("1")) {       // 한식을 선택했다면 Choice에 한식음식 넣어주기. 이하 다른 음식종류 동일
            for (int i = Choice_Length; i < Choice_Length+KOR.length; i++) {
                Choice.add(KOR[i-Choice_Length]);

            }

            Choice_Length = Choice_Length + KOR.length;
        }

        if(JPFLAG.equals("1")) { // 일식 선택했다면
            for (int i = Choice_Length; i < Choice_Length+JP.length; i++) {
                Choice.add(JP[i-Choice_Length]);

            }
            Choice_Length = Choice_Length + JP.length;
        }

        if(USAFLAG.equals("1")) { // 양식 선택했다면
            for (int i = Choice_Length; i < Choice_Length+USA.length; i++) {
                Choice.add(USA[i-Choice_Length]);

            }
            Choice_Length = Choice_Length + USA.length;
        }

        if(BSFLAG.equals("1")) { // 분식 선택했다면
            for (int i = Choice_Length; i < Choice_Length+BS.length; i++) {
                Choice.add(BS[i-Choice_Length]);

            }
            Choice_Length = Choice_Length + BS.length;
        }

        if(CHAFLAG.equals("1")) { // 중식선택했다면
            for (int i = Choice_Length; i < Choice_Length+CHA.length; i++) {
                Choice.add(CHA[i-Choice_Length]);

            }
            Choice_Length = Choice_Length + CHA.length;
        }

        if(YSFLAG.equals("1")) { // 야식 선택했다면
            for (int i = Choice_Length; i < Choice_Length+YS.length; i++) {
                Choice.add(YS[i-Choice_Length]);

            }
            Choice_Length = Choice_Length + YS.length;
        }


        if (Choice_Length==0){ // 모두 선택한게 없다면 설정
            Text.setVisibility(View.VISIBLE);
            Text.setText("고른게 없어요.. 뒤로 가서 골라주세요");
            btn.setVisibility(View.GONE);
            BoxBtn.setEnabled(false);

        }

        else {
            Collections.shuffle(Choice); // Choice한 음식종류 내의 음식을 랜덤으로 섞는다




        }

        //
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(result.this,"음식점 찾는중.. 첫 로딩시 20초이내 걸립니다. 기다려도 안나올경우 구글지도 업데이트하세요.",Toast.LENGTH_LONG).show();
                String FOODMENU = ""+Choice.get(0); // 인덱스 0인 음식메뉴를 선정


                ActivityCompat.requestPermissions(result.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                LocationManager locationManager = (LocationManager) result.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        //경도 위도값 지도에 전달하기
                        // 고른 음식메뉴 지도에 전달하기


                        la =  location.getLatitude();
                        lo =  location.getLongitude();

                        Uri uri = Uri.parse(String.format("geo:%f,%f?q=%s",la,lo,FOODMENU));
                        Intent MIntent = new Intent(Intent.ACTION_VIEW, uri);
                        MIntent.setPackage("com.google.android.apps.maps");
                        startActivity(MIntent);

                    }


                };
                //위치권한
                if(ActivityCompat.checkSelfPermission(result.this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(result.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(result.this,"위치 권한 세팅중...",Toast.LENGTH_LONG).show();
                    return;
                }
                // 위치 시간변동할때마다 업데이트
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,100,locationListener);


                //    Uri uri = Uri.parse(String.format("geo:%f,%f?z=10",37.30,127.2));
                // startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }
        });


    }

    public void onBoxClicked(View v){ // 물음표 상자 클릭시 이벤트
        mp.stop(); // 기존 BGM 끄고
        mp = MediaPlayer.create(result.this,R.raw.bgmopenboxwait); // 상자 열리고 결과 뜨기전 BGM을 선정
        mp.start(); // BGM 시작

        BoxBtn.setImageResource(R.drawable.openthebox); // 상자 열리는 이미지로 바뀜

        Handler delayHandler2 = new Handler();                                 // 2초 딜레이 겁니다
        delayHandler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                mp.stop(); // 2초 딜레이후  이전 BGM 끄고 결과 뜰때의 BGM 선정해서 BGM 시작
                mp = MediaPlayer.create(result.this,R.raw.bgmresult);
                mp.start();

                bigtext.setText("" + Choice.get(0)); // 본인은 Choice.get(0) 즉 Choice의 맨 첫번째
                // 인덱스를 뽑은 메뉴로 선정 그 이유는 위에서 Choice내 메뉴들을 한번 랜덤했기 때문.
                bigtext.setTextSize(Dimension.SP,30);        // 텍스트설정들
                bigtext.setVisibility(View.VISIBLE);
                mapBtn.setVisibility(View.VISIBLE);
                BoxBtn.setEnabled(false);


            }
        }, 2000);



    }




}