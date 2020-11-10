package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.utils.ViewSwitcherHelper;
import com.example.myapplication.views.SwiperImageFragment;
import com.example.myapplication.views.SwiperVideoFragment;
import com.example.myapplication.views.SwiperVrFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;


public class BlankFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewSwitcherHelper mViewSwitchHelper;
    TextView textView;

    private List<Fragment> fragments;
    String[] titles = {"VR", "视频", "图片"};

    //获取swiperImageFragment的轮播图数据
    int numbers = new SwiperImageFragment().number();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager_menu);
        textView = view.findViewById(R.id.number);

        //设置数据
        textView.setText(String.valueOf(numbers));

        //页面，数据源
        initFragment();
        initis();

        return view;
    }

    private void initis() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择时触发
                if (tab.getText().equals("视频")) {
                    JZVideoPlayerStandard.goOnPlayOnResume();
                }
                JZVideoPlayerStandard.goOnPlayOnPause();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选择是触发
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //选中之后再次点击即复选时触发
            }
        });

        //ViewPager的适配器
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(), fragments));
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new SwiperVrFragment());
        fragments.add(new SwiperVideoFragment());
        fragments.add(new SwiperImageFragment());
    }

    /**
     * 分类菜单适配器
     */
    private class MyAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}

