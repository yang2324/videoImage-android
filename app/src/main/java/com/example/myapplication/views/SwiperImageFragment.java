package com.example.myapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BannerAdapter;
import com.example.myapplication.utils.MyGlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SwiperImageFragment extends Fragment {

    protected ViewPager mViewpager;
    private List<View> mViewlist = new ArrayList<>();
    List<Lunbo>  ads = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swiper_image_fragment, container, false);

        mViewpager = view.findViewById(R.id.viewPager);
        Lunbo lunbo = new Lunbo("https://img1.gtimg.com/ninja/2/2020/09/ninja160126192713315.jpg", "2");
        Lunbo lunbo2 = new Lunbo("https://img1.gtimg.com/chinanba/pics/hv1/121/162/2325/151224556.jpg", "2");
        Lunbo lunbo3 = new Lunbo("https://img1.gtimg.com/chinanba/pics/hv1/203/125/2325/151215203.jpg", "2");
        Lunbo lunbo4 = new Lunbo("https://img1.gtimg.com/chinanba/pics/hv1/36/121/2325/151214016.jpg", "2");
        ads.add(lunbo);
        ads.add(lunbo2);
        ads.add(lunbo3);
        ads.add(lunbo4);

        setBannerview(ads);

        return view;
    }

    class Lunbo {
        String url;
        String tag;

        public Lunbo(String url, String tag) {
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

    /**
     * 顶部轮播图
     */
    protected void setBannerview(List<Lunbo> lunbos) {
        //区分图片和视频布局
        for (int i = 0; i < lunbos.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.view_guide_img, null);
            ImageView _imageview = view.findViewById(R.id.iv_center);
            MyGlideImageLoader.getInstance().onNetUrl2(getActivity(), _imageview, lunbos.get(i).getUrl());
            mViewlist.add(view);
        }
        mViewpager.setAdapter(new BannerAdapter(mViewlist));
    }

    public int number(){
        return ads.size();
    }

}