package com.example.lenovo.mytestcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/16.
 */

public class PageFragment extends Fragment {

  public static final String ARG_PAGE = "ARG_PAGE";
  private int mPage;

  public static PageFragment newInstance(int page) {
    Bundle args = new Bundle();
    args.putInt(ARG_PAGE, page);
    PageFragment pageFragment = new PageFragment();
    pageFragment.setArguments(args);
    return pageFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPage = getArguments().getInt(ARG_PAGE);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      list.add(""+(char)i);
    }

    View view = inflater.inflate(R.layout.fragment_page, container, false);
    RecyclerView rvTest = (RecyclerView) view.findViewById(R.id.rv_test);
    rvTest.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvTest.setAdapter(new MyAdapter());
    rvTest.addItemDecoration(new DividerItemDecoration(getActivity(),
            LinearLayoutManager.VERTICAL));
    TextView textView = (TextView) view.findViewById(R.id.tv_text);
    textView.setText("Fragment #" + mPage);
    return view;
  }
  private ArrayList<String> list;
  class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(getActivity()).inflate(R.layout.item, parent, false);
      MyViewHolder holder = new MyViewHolder(view);
      return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.tvItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
      return list.size();
    }
  }
  class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tvItem;

    public MyViewHolder(View itemView) {
      super(itemView);
      tvItem = (TextView) itemView.findViewById(R.id.id_num);
    }
  }

}