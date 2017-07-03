package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumActivity extends AppCompatActivity implements View.OnClickListener {

  private static final String TAG = "RandomNumActivity";
  private EditText etNumList;
  private TextView tvShow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_random_num);

    //12,13,14
    etNumList = (EditText) findViewById(R.id.et_num_list);
    tvShow = (TextView) findViewById(R.id.tv_show);

    findViewById(R.id.btn_calc).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    String listStr = etNumList.getText().toString().trim();

    final String[] listArr = listStr.split("，");
    final List<Integer> list = new ArrayList<>();
    final List<Integer> rNums = new ArrayList<>();

    for (int i = 0; i < listArr.length; i++) {
      list.add(Integer.parseInt(listArr[i]));
    }

    new Thread() {
      @Override
      public void run() {
        while (true) {
          Log.e(TAG, "run: start----------------------------");
          //get 6 random num
          for (int i = 0; i < Integer.MAX_VALUE; i++) {

            Random r = new Random();
            int rNum = r.nextInt(listArr.length);
//            Log.e(TAG, "run: rNum"+rNum );

            Log.e(TAG, "run: rNums.size()=" + rNums.size());
            if (rNums.size() > 6) {
              break;
            }
            if (!rNums.contains(rNum)) {
              rNums.add(rNum);
            }
          }

          //print nums
          Log.e(TAG, "test: rNums=" + rNums);


          //calculate
          int sum = 0;
          for (int i = 0; i < rNums.size(); i++) {
            int index = rNums.get(i);
            sum += list.get(index);
          }
          if (sum == 6666) {
            //print result
            Log.e(TAG, "result: rNums=" + rNums);
            tvShow.setText("result:" + rNums.toString());
            return;
          }
        }
      }
    }.start();

  }

  public int[] getRandomFromArray(int[] array, int count) {
//ArrayList<Integer>arrayList=null;
    int[] a = array;
    int[] result = new int[count];
    boolean r[] = new boolean[array.length];
    Random random = new Random();
    int m = count;//要随机取的元素个数
    if (m > a.length || m < 0)
      return a;
    int n = 0;
    while (true) {
      int temp = random.nextInt(a.length);
      if (!r[temp]) {
        if (n == m)//取到足量随机数后退出循环
          break;
        n++;
        System.out.println("得到的第" + n + "个随机数为：" + a[temp]);
        result[n - 1] = a[temp];
        r[temp] = true;
      }
    }
    return result;
  }
}
