package com.spurs.thread2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Thread thread;

    boolean isRun=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        isRun=false;
        super.onPause();
    }

    public void clickBtn(View v){
        //5초마다 현재시간 Toast출력
        thread=new Thread(){


            @Override
            public void run() {

                while (isRun){

                    //현재시간 Toast로 보이기..
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //이안에서는 UI변경 가능..
                            Date date=new Date();
                            Toast.makeText(MainActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //5초간 대기..
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//while

            }//run메소드
        };
        thread.start();
    }//clickBtn 메소드
}
