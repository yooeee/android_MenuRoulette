package com.example.real_project;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class map2 extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    double la;
    double lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map2view);


        Intent intent = getIntent();
        String FOODMENU = intent.getStringExtra("FOODMENU");

         // 위치관련

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

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
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(map2.this,"위치 권한 세팅중...",Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,100,locationListener);

    }
}