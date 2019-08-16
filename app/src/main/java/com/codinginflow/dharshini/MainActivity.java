package com.codinginflow.dharshini;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        ViewPager viewPager = findViewById(R.id.viewPager);
        if (viewPager != null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

    }
    @Override
    public void sendDataToActivity(String data) {
      if(data.equals("notify")){
          Intent intent = new Intent(this, MyBroadcastReceiver.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(
                  this.getApplicationContext(), 234324243, intent, 0);
          AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
          alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent);
      }
    }
}