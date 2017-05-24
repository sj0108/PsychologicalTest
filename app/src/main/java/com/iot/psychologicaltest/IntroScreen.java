package com.iot.psychologicaltest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class IntroScreen extends Activity {
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.intro);

        Thread timerThred = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(IntroScreen.this, ListViewMain.class);
                    startActivity(intent);
                }
            }
        };
        timerThred.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (false) {
            super.onBackPressed();
        } else {
        }
    }
}