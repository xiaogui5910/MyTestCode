package com.maxiaoosong.testscanzbar3;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dtr.zbar.scan.CaptureActivity;
import com.maxiaoosong.testscanzbar3.utils.PermissionUtils;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btn2;
    private SoundPool soundPool;
    private int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Log.e(TAG, "onCreate:!!! " );
//        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
//        success = soundPool.load(getApplicationContext(), R.raw.success, 1);
//        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//        @Override
//        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//          Log.e(TAG, "onLoadComplete: ~~~~~~~~~~~~~" );
//        }
//         });
        PermissionUtils.requestPermission(MainActivity.this, PermissionUtils.CODE_CAMERA,mPermissionGrant);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soundPool.play(success,1.0f,1.0f,0,0,1.0f);
//            }
//        });

    }
    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        soundPool.release();
    }

  @Override
  protected void onPause() {
    super.onPause();
//    soundPool.unload(R.raw.success);
    Log.e(TAG, "onPause: ---------------------" );
  }
}
