package com.steven.babyiyo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steven.babyiyo.R;
import com.steven.babyiyo.adapter.SortListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class FragmentFour extends Fragment {

    private SortListAdapter mAdapter;

    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and "
            + "typesetting industry Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_four, container, false);


        return view;

    }

}
