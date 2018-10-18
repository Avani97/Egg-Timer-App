package com.avani.timerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sk;
    int time=0;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reset=(Button) findViewById(R.id.button2);
        reset.setVisibility(View.INVISIBLE);
       sk=(SeekBar)findViewById(R.id.seekBar);
        final TextView tv=(TextView) findViewById(R.id.textView1);
        sk.setMax(600);
        sk.setProgress(0);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int minutes=(int)i/60;
                int seconds=(int)i%60;
                String sec=Integer.toString(seconds);
                if(seconds==0)
                {
                    sec="00";
                }
                else if(seconds<10)
                {
                    String sec2=sec;
                    sec="0"+sec2;
                }
                tv.setText(Integer.toString(minutes)+":"+sec);
                time=i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void onclick(View view)
    {

        view.setVisibility(View.INVISIBLE);
        Button reset=(Button)findViewById(R.id.button2);
        reset.setVisibility(View.VISIBLE);
        sk.setVisibility(View.INVISIBLE);
        final TextView tv1=(TextView) findViewById((R.id.textView1));
        final TextView tv2=(TextView) findViewById((R.id.textView2));
       timer= new CountDownTimer(time*1000,1000)
        {
            public void onTick(long milli)
            {
                long secondsleft=milli/1000;
                int minutes=(int)secondsleft/60;
                int second=(int)secondsleft%60;
                String sec=Integer.toString(second);
                if(second==0)
                {
                   sec="00";
                }
                else if(second<10)
                {
                    String sec2=sec;
                    sec="0"+sec;
                }
                tv1.setText(Integer.toString(minutes)+":"+sec);
            }
            public void onFinish()
            {
                MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.airhorn);
                mp.start();
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.VISIBLE);
                ImageView ig=(ImageView)findViewById(R.id.imageView);
                ig.setImageResource(R.drawable.crack);
            }
        }.start();


    }
    public void reset(View view)
    {

        Button bt=(Button)findViewById(R.id.button);
        final TextView tv1=(TextView) findViewById((R.id.textView1));
        final TextView tv2=(TextView) findViewById((R.id.textView2));
        bt.setVisibility(View.VISIBLE);
        tv1.setText("0:00");
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        ImageView ig=(ImageView)findViewById(R.id.imageView);
        ig.setImageResource(R.drawable.egg);
        sk.setProgress(0);
        sk.setVisibility(View.VISIBLE);
        timer.cancel();

    }

}
