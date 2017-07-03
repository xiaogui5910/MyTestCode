package com.example.lenovo.mytestcode.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.RecyclingPagerAdapter;
import com.example.lenovo.mytestcode.adapter.ScalePageTransformer;
import com.example.lenovo.mytestcode.widget.ClipViewPager;

import java.util.ArrayList;
import java.util.List;

public class ClipViewPagerActivity extends AppCompatActivity {

  private ClipViewPager mViewPager;
  private TubatuAdapter mPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clip_view_pager);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
      }
    });

    mViewPager = (ClipViewPager) findViewById(R.id.viewpager);
    mViewPager.setPageTransformer(true, new ScalePageTransformer());

    findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        return mViewPager.dispatchTouchEvent(event);
      }
    });

    mPagerAdapter = new TubatuAdapter(this);
    mViewPager.setAdapter(mPagerAdapter);
    mViewPager.setCurrentItem(25);
    initData();
  }
  private void initData() {
    List<Integer> list = new ArrayList<>();
    list.add(R.drawable.style_xiandai);
    list.add(R.drawable.style_jianyue);
    list.add(R.drawable.style_oushi);
    list.add(R.drawable.style_zhongshi);
    list.add(R.drawable.style_meishi);
    list.add(R.drawable.style_dzh);
    list.add(R.drawable.style_dny);
    list.add(R.drawable.style_rishi);

    list.add(R.drawable.style_xiandai);
    list.add(R.drawable.style_jianyue);
    list.add(R.drawable.style_oushi);
    list.add(R.drawable.style_zhongshi);
    list.add(R.drawable.style_meishi);
    list.add(R.drawable.style_dzh);
    list.add(R.drawable.style_dny);
    list.add(R.drawable.style_rishi);

    //设置OffscreenPageLimit
    mViewPager.setOffscreenPageLimit(Math.min(list.size(), 5));
    mPagerAdapter.addAll(list);

    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
//          Toast.makeText(MainActivity.this,"position="+position, Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  public static class TubatuAdapter extends RecyclingPagerAdapter {

    private final List<Integer> mList;
    private final Context mContext;

    public TubatuAdapter(Context context) {
      mList = new ArrayList<>();
      mContext = context;
    }

    public void addAll(List<Integer> list) {
      mList.addAll(list);
      notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
      ImageView imageView = null;
      if (convertView == null) {
        imageView = new ImageView(mContext);
      } else {
        imageView = (ImageView) convertView;
      }
      imageView.setTag(position);
      imageView.setImageResource(mList.get(position%5));
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(mContext, "position="+position, Toast.LENGTH_SHORT).show();
        }
      });
      return imageView;
    }

    @Override
    public int getCount() {
//            return mList.size();
      return 50;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
