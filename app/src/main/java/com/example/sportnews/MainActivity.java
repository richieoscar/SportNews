package com.example.sportnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SportAdapter adapter;
    ArrayList<Sport> sportData;
    FloatingActionButton fab;
    private int gridCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFab();

        int gridCount = getResources().getInteger(R.integer.grid_column_count);

        setUpRecyclerView();
        initializeData();
        setItemTouchHelperWithRecyclerView();


    }

    private void setFab() {
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v->{
            initializeData();
        });
    }

    private void setItemTouchHelperWithRecyclerView() {
        int swipeDir;
        if(gridCount>1)
            swipeDir = 0;
        else
            swipeDir = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|
                ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                swipeDir) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

               int from = viewHolder.getAdapterPosition();
               int to = target.getAdapterPosition();

               Collections.swap(sportData, from, to);
               adapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                sportData.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setUpRecyclerView(){
        gridCount = getResources().getInteger(R.integer.grid_column_count);
        recyclerView = findViewById(R.id.sportRv);
        sportData = new ArrayList<>();
        adapter = new SportAdapter(sportData, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));
        recyclerView.setAdapter(adapter);




    }

    private void initializeData() {
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sport_info);
        String[] sportSubtitle = getResources().getStringArray(R.array.sports_subtitles);

        sportData.clear();

        TypedArray sportImageResouce = getResources().obtainTypedArray(R.array.sports_images);

        for(int i=0; i< sportsList.length; i++){
            sportData.add(new Sport(sportsList[i], sportsInfo[i], sportSubtitle[i], sportImageResouce.getResourceId(i,0)));

        }
        sportImageResouce.recycle();

        adapter.notifyDataSetChanged();
    }
}