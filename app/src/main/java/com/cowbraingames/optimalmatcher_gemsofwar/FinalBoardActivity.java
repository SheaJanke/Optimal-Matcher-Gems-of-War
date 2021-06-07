package com.cowbraingames.optimalmatcher_gemsofwar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class FinalBoardActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Result> results;
    private int[][] finalBoard;
    private RecyclerView resultsList;
    private Context context;

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        finalBoard = new int[8][8];
        int[] flatFinalBoard = getIntent().getIntArrayExtra("flatFinalBoard");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                assert flatFinalBoard != null;
                assert flatFinalBoard.length == 64;
                finalBoard[i][j] = flatFinalBoard[8*i + j];
            }
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        gridView = findViewById(R.id.board);
        resultsList = findViewById(R.id.results_list);
        findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        fillBoardAndResults();
    }

    private void fillBoardAndResults() {
        boolean[][] selected = new boolean[8][8];
        gridView.setAdapter(new ImageAdapter(context, finalBoard, selected));
        gridView.invalidateViews();
        results = BoardUtils.getSortedResults(finalBoard);
        resultsList.setLayoutManager(new LinearLayoutManager(context));
        ResultsListAdapter resultsListAdapter = new ResultsListAdapter(context, results, finalBoard, gridView);
        resultsList.setAdapter(resultsListAdapter);
    }
}
