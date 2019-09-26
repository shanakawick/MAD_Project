package com.example.kitchenstories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        RecyclerView mRecyclerView;
        List<FoodData> myFoodList;
        FoodData mFoodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        myFoodList = new ArrayList<>();

        mFoodData = new FoodData("Prawn Curry","Who doesn't love a taste of hot prawn curry on a cold day.Check out the recipe now.","Rs.300",R.drawable.prawn);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("Asian Noodle Salad","Asian inspired noodle salad is a ","",R.drawable.asiannoodle);
        myFoodList.add(mFoodData);

        mFoodData = new FoodData("String Hoppers","what a perfect dish for a perfect breakfast","",R.drawable.stringhoppers);

        myFoodList.add(mFoodData);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);

    }
}
