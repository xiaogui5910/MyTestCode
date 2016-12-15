package com.example.lenovo.mytestcode.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.mytestcode.widget.MyVideoView;
import com.example.lenovo.mytestcode.R;

/**
 * Created by lenovo on 2016/11/29.
 */
public class MyFragment extends Fragment {

  private MyVideoView myVideoView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    myVideoView = new MyVideoView(getContext());
    int index = getArguments().getInt("index", 1);
    Uri uri = null;
    switch (index) {
      case 1:
        uri = getUri(R.raw.guide_1);
        break;
      case 2:
        uri = getUri(R.raw.guide_2);
        break;
      case 3:
        uri = getUri(R.raw.guide_3);
        break;
      case 4:
        uri = getUri(R.raw.test1);
        break;
      case 5:
        uri = getUri(R.raw.test2);
        break;
      case 6:
        uri = getUri(R.raw.test3);
        break;
    }
    myVideoView.playVideo(uri);

    return myVideoView;
  }

  public Uri getUri(int resId) {
    Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + resId);
    return uri;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (myVideoView !=null){
      myVideoView.stopPlayback();
    }
  }

}
