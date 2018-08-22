package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.ItemEntity;
import com.example.lenovo.mytestcode.manager.CardSliderLayoutManager;
import com.example.lenovo.mytestcode.manager.CardSnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lenovo.mytestcode.application.MyTestCodeApp.context;

public class CardSliderActivity extends AppCompatActivity {

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  private CardSliderLayoutManager layoutManger;
  private ArrayList<ItemEntity> dataList;

//  private final SliderAdapter sliderAdapter = new SliderAdapter(pics, 20, new OnCardClickListener());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_slider);
    ButterKnife.bind(this);
    initRecyclerView();
  }

  private void initRecyclerView() {
    dataList = new ArrayList<>();
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 8; i++) {
        Random r = new Random();
        int num = r.nextInt(12) + 1;
        String iconName = "pic_" + num;
        String desc = "item_" + i;
        int iconId = getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        dataList.add(new ItemEntity(desc, iconId));
      }
    }
    recyclerView.setLayoutManager(new CardSliderLayoutManager(this));
    CardSliderAdapter adapter = new CardSliderAdapter(R.layout.item_slider_card, dataList);
    recyclerView.setAdapter(adapter);
    recyclerView.setHasFixedSize(true);

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//          onActiveCardChange();
        }
      }
    });

//    layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();

    new CardSnapHelper().attachToRecyclerView(recyclerView);
  }

  class CardSliderAdapter extends BaseQuickAdapter<ItemEntity,BaseViewHolder> {

    public CardSliderAdapter(int layoutResId, List<ItemEntity> data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemEntity itemEntity) {
      baseViewHolder.setImageResource(R.id.image, itemEntity.getIconId());
    }
  }
}
