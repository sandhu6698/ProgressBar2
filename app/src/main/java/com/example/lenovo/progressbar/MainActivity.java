package com.example.lenovo.progressbar;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
     Button button;
     ProgressDialog progressbar;
     private int status=0;
     private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgessbar(v,ProgressDialog.STYLE_HORIZONTAL);
            }
        });

    }

    private void showProgessbar(View v, int styleHorizontal) {
                progressbar = new ProgressDialog(v.getContext());
                progressbar.setCancelable(true);
                progressbar.setMessage("Progress Bar Demo");
                progressbar.setProgressStyle(styleHorizontal);
                progressbar.setProgress(status);
                progressbar.setMax(100);
                progressbar.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (status<100) {
                            try {
                                Thread.sleep(200);
                                status = status + 4;

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressbar.setProgress(status);
                                }
                            });
                        }
                        if (status>=100){
                            progressbar.dismiss();
                        }
                    }

                }).start();
    }
}
