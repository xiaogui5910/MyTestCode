package com.example.view_texst;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HealthTestForBelief belif;
    private ArrayList<DrugUseTest> al;
    private HealthTestForBelief knowledge;
    private HealthTestForBelief dependence;
    private Button add;
    private LinearLayout content;

    public static final int NONE = 0;
    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果

    public static final String IMAGE_UNSPECIFIED = "image/*";
    private File picture;

    private int j = 1;
    private int i = 1;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();


        belif = (HealthTestForBelief) findViewById(R.id.health_test_for_belief);
        knowledge = (HealthTestForBelief) findViewById(R.id.health_test_for_knowledge);
        dependence = (HealthTestForBelief) findViewById(R.id.health_test_for_dependence);
        belif.setStyle(1);
        knowledge.setStyle(2);
        dependence.setStyle(3);

        belif.setWidthHeight(width,CommonUtils.getDimens(R.dimen.view_test_height));
        knowledge.setWidthHeight(width,CommonUtils.getDimens(R.dimen.view_test_height));
        dependence.setWidthHeight(width,CommonUtils.getDimens(R.dimen.view_test_height));
        belif.setOnClicked(new HealthTestForBelief.OnClicked() {
            @Override
            public void onClicked() {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });


        al = new ArrayList<>();
        for (int i= 0;i<20;i++){
            DrugUseTest drugUseTest = new DrugUseTest(0, 0, "1", i, TestType.DRUG_USE_CONVICTION, "20101122");
            al.add(drugUseTest);
        }
        belif.setData(al);


        add = (Button) findViewById(R.id.bt_add);
        content = (LinearLayout) findViewById(R.id.ll_content);

        add.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                invokeAlbum();
//                invokeCamera();
//                createView();







            }
        });
    }

    private void createView() {
        int childCount = content.getChildCount();
        int child = childCount%3;
        Log.e("childCount == ",childCount+"");
        if (linearLayout == null || linearLayout.getChildCount()%3 == 0){


            linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.getDimens(R.dimen.view_size), 1.0f));
            linearLayout.setHorizontalGravity(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.TOP);


            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            imageView.setMaxWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMaxHeight(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumHeight(CommonUtils.getDimens(R.dimen.view_size));

//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageView.getLayoutParams());

            params.gravity = Gravity.LEFT;

            params.setMargins(100,100,0,0);

            imageView.setLayoutParams(params);


//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size));
//                layoutParams.setMargins(10*i,10,10*i,10);

//                imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);

            Log.e("aa",linearLayout.getChildCount() +"");
            content.addView(linearLayout);


        }else {

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            imageView.setMaxWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMaxHeight(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumHeight(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            Log.e("bb",linearLayout.getChildCount() +"");

//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size));
//                layoutParams.setMargins(10*i,10,10*i,10);

//                imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);

        }

    }

    private void createView1(Uri url) {
        int childCount = content.getChildCount();
        int child = childCount%3;
        Log.e("childCount == ",childCount+"");
        if (linearLayout == null || linearLayout.getChildCount()%3 == 0){


            linearLayout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.getDimens(R.dimen.view_size), 1.0f);

            linearLayout.setLayoutParams(params);
            linearLayout.setHorizontalGravity(LinearLayout.HORIZONTAL);


            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            imageView.setMaxWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMaxHeight(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumHeight(CommonUtils.getDimens(R.dimen.view_size));
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size), 1.0f);
            params1.setMargins(100,0,0,0);
            imageView.setLayoutParams(params1);




//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size));
//                layoutParams.setMargins(10*i,10,10*i,10);

//                imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);

            Log.e("aa",linearLayout.getChildCount() +"");
            content.addView(linearLayout);
            SystemClock.sleep(1000);
            int width = imageView.getWidth();
            Log.e("width = " ,width+"");

        }else {

            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            imageView.setImageURI(url);
            imageView.setMaxWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumWidth(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMaxHeight(CommonUtils.getDimens(R.dimen.view_size));
            imageView.setMinimumHeight(CommonUtils.getDimens(R.dimen.view_size));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size), 1.0f);
            params.setMargins(100,0,0,0);

            imageView.setLayoutParams(params);
            Log.e("bb",linearLayout.getChildCount() +"");

//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtils.getDimens(R.dimen.view_size), CommonUtils.getDimens(R.dimen.view_size));
//                layoutParams.setMargins(10*i,10,10*i,10);

//                imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);

        }

    }


    /**
     * 调用系统相机
     */
    private void invokeCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
        startActivityForResult(intent, PHOTOHRAPH);
    }
    /**
     * 调用系统相册
     */
    private void invokeAlbum() {
        //调用系统相册

        Intent intent2 = new Intent(Intent.ACTION_PICK, null);
        intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(intent2, PHOTOZOOM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.print("url == "+"wwwwwwwwwwwwwwww");
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTOHRAPH) {
            //设置文件保存路径这里放在跟目录下
//			Toast.makeText(FirstLoginActivity.this,"保存图片",Toast.LENGTH_SHORT).show();
            picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null)
            return;

        // 读取相册缩放图片
        if (requestCode == PHOTOZOOM) {
//			Toast.makeText(FirstLoginActivity.this,"读取图片",Toast.LENGTH_SHORT).show();
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTORESOULT) {
            Bundle extras = data.getExtras();
//			Toast.makeText(MainActivity.this,"处理 图片",Toast.LENGTH_SHORT).show();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");

                File file = new File(Environment.getExternalStorageDirectory() + "/ph_temp.jpg");
                if (file.exists()){
                    file.delete();
                }
//
// ByteArrayOutputStream stream = new ByteArrayOutputStream(file);
                try {

                    FileOutputStream out = new FileOutputStream(file);

                    photo.compress(Bitmap.CompressFormat.JPEG, 75, out);// (0 - 100)压缩文件

                    Uri url = Uri.fromFile(file);
                    Toast.makeText(MainActivity.this,"读取图片== " +url,Toast.LENGTH_LONG).show();
                    createView1(url);
//                image.setImageBitmap(photo);
                    out.flush();
                    out.close();
//                    headIcon.setImageURI(url);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", CommonUtils.getDimens(R.dimen.view_size));
        intent.putExtra("outputY",CommonUtils.getDimens(R.dimen.view_size));
        intent.putExtra("return-data", true);
        intent.putExtra("url",uri);

//		Toast.makeText(MainActivity.this,"读取图片11 == " +uri,Toast.LENGTH_LONG).show();
        startActivityForResult(intent, PHOTORESOULT);
    }
}
