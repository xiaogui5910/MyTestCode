package com.example.lenovo.mytestcode.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.widget.MasterLayout;

public class CusImageActivity extends AppCompatActivity {
  static MasterLayout masterLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cus_image);

    masterLayout = (MasterLayout) findViewById(R.id.MasterLayout01);
    initLayout();
  }

  private void initLayout() {
    //Onclick listener of the progress button
    masterLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        masterLayout.animation(); //Need to call this method for animation and progression
        if (masterLayout.flg_frmwrk_mode == 1) {
          //Start state. Call any method that you want to execute
          runOnUiThread(new Runnable() {

            @Override
            public void run() {
              Toast.makeText(CusImageActivity.this,
                      "Starting download", Toast.LENGTH_SHORT)
                      .show();
            }
          });
          new DownLoadSigTask().execute();
        }
        if (masterLayout.flg_frmwrk_mode == 2) {
          //Running state. Call any method that you want to execute
          new DownLoadSigTask().cancel(true);
          masterLayout.reset();
          runOnUiThread(new Runnable() {

            @Override
            public void run() {
              Toast.makeText(CusImageActivity.this,
                      "Download stopped", Toast.LENGTH_SHORT)
                      .show();
            }
          });
        }
        if (masterLayout.flg_frmwrk_mode == 3) {
          //End state. Call any method that you want to execute.
          runOnUiThread(new Runnable() {

            @Override
            public void run() {
              Toast.makeText(CusImageActivity.this,
                      "Download complete", Toast.LENGTH_SHORT)
                      .show();
            }
          });
        }
      }
    });
  }

  static class DownLoadSigTask extends AsyncTask<String, Integer, String> {
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(final String... args) {
      //Creating dummy task and updating progress
      for (int i = 0; i <= 100; i++) {
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        publishProgress(i);
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
      //publishing progress to progress arc
      masterLayout.cusview.setupprogress(progress[0]);
    }


  }
}
