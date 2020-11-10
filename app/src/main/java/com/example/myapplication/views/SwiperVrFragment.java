package com.example.myapplication.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.ParallelViewHelper;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SwiperVrFragment extends Fragment {

    ParallelViewHelper parallelViewHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swiper_vr_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.main_image_background);

        parallelViewHelper = new ParallelViewHelper(getActivity(), imageView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        parallelViewHelper.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        parallelViewHelper.stop();
    }
}