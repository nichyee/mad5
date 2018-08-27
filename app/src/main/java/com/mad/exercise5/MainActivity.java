package com.mad.exercise5;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Train> mTrainList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TrainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTrainList.add(new Train("Berowra Platform 4", 14, "Late", "Beverly", "15:18"));
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);

        mAdapter = new TrainAdapter(this, mTrainList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareTrainData();

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void prepareTrainData() {
        mTrainList.add(new Train("Albion Platform 1", 14, "On time", "Allawah", "14:11"));
        mTrainList.add(new Train("Arncliffe Platform 2", 14, "Late", "Central", "14:34"));
        mTrainList.add(new Train("Artarmon Platform 3", 14, "On time", "Ashfield", "15:01"));
        mTrainList.add(new Train("Berowra Platform 4", 14, "Late", "Beverly", "15:18"));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all) {
            mTrainList.clear();
            mAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.refresh) {
            new RefreshPage().execute();

        }

        return super.onOptionsItemSelected(item);
    }

    private class RefreshPage extends AsyncTask<Void, Void, Integer[]> {
        Random mRandom = new Random();
        ProgressBar mProgress = findViewById(R.id.main_refresh);
        ArrayList<Integer> mRandomNumbers = new ArrayList<>();
        Integer[] mRandInts = new Integer[mTrainList.size()];

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRecyclerView.setVisibility(View.INVISIBLE);
            mProgress.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Integer[] integers) {
            super.onPostExecute(integers);
            for (int i = 0; i < mTrainList.size(); i++) {
                mTrainList.get(i).setArrivalTime(mRandInts[i]);
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
            mProgress.setVisibility(View.GONE);
        }

        @Override
        protected Integer[] doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < mTrainList.size(); i++) {
                int randNumber = mRandom.nextInt(21) + 1;
                mRandomNumbers.add(randNumber);
            }
            return mRandomNumbers.toArray(mRandInts);
        }
    }

    private class RefreshTrain extends AsyncTask<Void, Void, Integer> {

        Random mRandom = new Random();
        ProgressBar mProgressBar = findViewById(R.id.progress_bar);
        TextView mArrivalTime = findViewById(R.id.arrival_time_tv);

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer randNumber = mRandom.nextInt(21) + 1;
            return randNumber;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mArrivalTime.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            mArrivalTime.setText(integer);
            mAdapter.notifyDataSetChanged();
            mArrivalTime.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
