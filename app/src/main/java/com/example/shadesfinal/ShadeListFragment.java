package com.example.shadesfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ShadeListFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    public ShadeListFragment() {
    }
    @SuppressWarnings("unused")
    public static com.example.shadesfinal.ShadeListFragment newInstance(int columnCount) {
        com.example.shadesfinal.ShadeListFragment fragment = new com.example.shadesfinal.ShadeListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shade_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ShadeRecyclerViewAdapter(getShadesFromDB(), context));
        }
        return view;
    }

    private List<com.example.shadesfinal.Shade> getShadesFromDB() {
        ArrayList<com.example.shadesfinal.Shade> shades = new ArrayList<>();
        for(int i = 0; i < com.example.shadesfinal.ShadeDB.shadeNames.length; i++){
            shades.add(new com.example.shadesfinal.Shade(com.example.shadesfinal.ShadeDB.shadeNames[i], com.example.shadesfinal.ShadeDB.shadeDetails[i]));
        }
        return shades;
    }


}