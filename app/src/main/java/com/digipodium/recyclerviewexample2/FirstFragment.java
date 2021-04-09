package com.digipodium.recyclerviewexample2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.Random;

public class FirstFragment extends Fragment {

    private LinkedList<ItemModel> mDataList;
    private ItemAdapter mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mDataList = getDummyData();
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new ItemAdapter(getActivity(), mDataList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            int size = mDataList.size();
            mDataList.addLast(new ItemModel(R.drawable.ic_baseline_book_24,"Book"+size));
            recyclerView.getAdapter().notifyItemInserted(size);
            recyclerView.smoothScrollToPosition(size);
        });
    }

    public LinkedList<ItemModel> getDummyData() {
        int[] icons = new int[]{
                R.drawable.ic_baseline_book_24,
                R.drawable.ic_baseline_airport_shuttle_24,
                R.drawable.ic_baseline_casino_24,
        };
        String[] names = new String[]{
                "Apple", "Banana", "Chewbacca", "Dolphin"
        };

        LinkedList<ItemModel> datalist = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            int icon_idx = new Random().nextInt(icons.length);
            int txt_idx = new Random().nextInt(names.length);
            datalist.addLast(new ItemModel(icons[icon_idx],names[txt_idx]));
        }
        return datalist;
    }
}