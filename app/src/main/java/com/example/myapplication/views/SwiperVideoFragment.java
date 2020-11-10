package com.example.myapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.BannerAdapter;
import com.example.myapplication.utils.MyGlideImageLoader;
import com.example.myapplication.utils.ViewSwitcherHelper;


import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SwiperVideoFragment extends Fragment {

    protected ViewPager mViewpager;
    private ViewSwitcherHelper mViewSwitchHelper;
    private LinearLayout del;
    private List<View> mViewlist = new ArrayList<>();
    List<Lunbos> ads = new ArrayList<>();
    String videourl = "ss.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiper_video_fragment, container, false);

        mViewpager = view.findViewById(R.id.viewPager);
        del = view.findViewById(R.id.del);

        //视频封面图
        Lunbos lunbos = new Lunbos("https://guangdong.mutuan.com//uploads/image/20191219/9513b84d9b2f7a893fcbe7b89a2d147d.jpg", "1");
        ads.add(lunbos);
        setBannerview(ads);

        return view;
    }

    static class Lunbos {
        String url;
        String tag;

        public Lunbos(String url, String tag) {
            this.url = url;
            this.tag = tag;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    protected void setBannerview(List<Lunbos> lunboss) {
        //区分图片和视频布局
        for (int i = 0; i < lunboss.size(); i++) {
            if (lunboss.get(i).getTag().equals("1")) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_guide_video, null);
                JZVideoPlayerStandard videoplayer = view.findViewById(R.id.videoplayer);
                videoplayer.setUp(videourl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
                Glide.with(getActivity()).load(lunboss.get(i).getUrl()).into(videoplayer.thumbImageView);
                mViewlist.add(view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_guide_two, null);
                ImageView _imageview = view.findViewById(R.id.iv_center);
                MyGlideImageLoader.getInstance().onNetUrl2(getActivity(), _imageview, lunboss.get(i).getUrl());
                mViewlist.add(view);
            }
        }
        mViewpager.setAdapter(new BannerAdapter(mViewlist));
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayerStandard.goOnPlayOnPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JZVideoPlayerStandard.releaseAllVideos();
    }
}